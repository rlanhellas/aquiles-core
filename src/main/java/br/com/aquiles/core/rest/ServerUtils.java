package br.com.aquiles.core.rest;


import br.com.aquiles.core.annotation.Descricao;
import br.com.aquiles.core.dto.DocServico;
import br.com.aquiles.core.dto.Metodo;
import br.com.aquiles.core.exception.PropertiesLoaderException;
import br.com.aquiles.core.util.PropertiesUtilities;
import br.com.aquiles.core.util.StringUtils;
import org.reflections.Reflections;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by Diorge on 31/08/2016.
 */

@Path("aquiles/service")
@Descricao("Funcoes do Servidor")
public class ServerUtils {

    /**
     * Used to check server info
     *
     * @return {@link String} with version, build date and java version used on compilation
     */
    @GET
    @Path("/version")
    @Descricao("Verifica versão no servidor")
    public String version() throws PropertiesLoaderException {
        return buildTime();
    }

    private String buildTime() throws PropertiesLoaderException {
        return StringUtils.join("Version: " , PropertiesUtilities.readPropertieFromFile("META-INF/aquiles-core.properties","build.version") , " Build in " , PropertiesUtilities.readPropertieFromFile("META-INF/aquiles-core.properties","build.date") , " Using java version: " , PropertiesUtilities.readPropertieFromFile("META-INF/aquiles-core.properties","build.java.version"),"\n Common Version: ",PropertiesUtilities.readPropertieFromFile("META-INF/aquiles-core.properties","build.common"));
    }

    /**
     * Server availability check
     *
     * @return returns Response.ok() = 200
     */
    @GET
    @Path("/teste")
    @Descricao("Ping")
    public Response teste() {
        return Response.ok().build();
    }

    @GET
    @Path("/metodos")
    @Produces("text/html")
    @Descricao("Lista todos os métodos do serviço")
    public String getMetodosDeServico() {

        Reflections reflections = new Reflections("br.com.aquiles");
        Set<Class<? extends Object>> allClasses =
                reflections.getTypesAnnotatedWith(Path.class);
        List<DocServico> listaServico = new LinkedList<DocServico>();
        for (Class classe : allClasses) {
            DocServico docServico = new DocServico();
            for (Annotation a :
                    classe.getAnnotations()) {
                if (a instanceof Descricao) {
                    docServico.setDescricao(((Descricao) a).value());
                }

                if (a instanceof Path) {
                    docServico.setCaminhoPrincipal(((Path) a).value());
                }

            }
            for (Method m : classe.getMethods()) {
                Metodo metodo = new Metodo();
                for (Annotation a : m.getAnnotations()) {
                    if (a instanceof Path) {
                        metodo.setNome(((Path) a).value());
                        for (Parameter parameter :
                                m.getParameters()) {
                            metodo.getParametros().put(parameter.getName(), ((Parameter) parameter).getType().getCanonicalName());
                        }
                    }
                    if (a instanceof Descricao) {
                        metodo.setDescricao(((Descricao) a).value());
                    }
                }
                if (metodo.getNome()!=null) {
                    docServico.getMetodos().add(metodo);
                }
            }
            listaServico.add(docServico);
        }
        StringBuilder htmlResponse = new StringBuilder();
        listaServico.forEach(docServico -> {
            htmlResponse.append("<div>");
            htmlResponse.append("<H1>"+docServico.getCaminhoPrincipal()+"</H1>");
            htmlResponse.append("<p>"+docServico.getDescricao()+"</p>");
            docServico.getMetodos().forEach(metodo -> {
                htmlResponse.append("<ul>"+metodo.getNome()+" : "+metodo.getDescricao());
                metodo.getParametros().forEach((o, o2) -> htmlResponse.append("<li>Parametro: "+o+" Tipo : "+o2+"</li>"));
                htmlResponse.append("</ul>");
            });
            htmlResponse.append("</div>");
        });
        return htmlResponse.toString();
    }


}
