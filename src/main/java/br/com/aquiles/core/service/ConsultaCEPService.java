package br.com.aquiles.core.service;

import br.com.aquiles.core.dto.RetornoConsultaCEP;
import br.com.aquiles.core.exception.CEPInvalidoException;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by rlanhellas on 31/03/2017.
 */
@Named(value = "consultaCEPService")
@RequestScoped
public class ConsultaCEPService extends GenericService {

    private final String URL_CONSULTA = "http://viacep.com.br/ws/{cep}/json/";

    public RetornoConsultaCEP consultarCEP(String cep) throws IOException, CEPInvalidoException {
        cep = cep.replace("-", "").trim();
        if (!StringUtils.isNumeric(cep)) {
            throw new CEPInvalidoException("CEP " + cep + " inválido");
        }
        URL url = new URL(URL_CONSULTA.replace("{cep}", cep));
        String content = getContent(url);
        return jsonToObject(content);
    }

    private RetornoConsultaCEP jsonToObject(String jsonContent) {
        Gson gson = new Gson();
        LinkedTreeMap linkedTreeMap = gson.fromJson(jsonContent, LinkedTreeMap.class);
        if (linkedTreeMap.containsKey("erro")) {
            return null;
        }
        RetornoConsultaCEP retornoConsultaCEP = new RetornoConsultaCEP();
        retornoConsultaCEP.setCep(linkedTreeMap.get("cep").toString());
        retornoConsultaCEP.setLogradouro(linkedTreeMap.get("logradouro").toString());
        retornoConsultaCEP.setComplemento(linkedTreeMap.get("complemento").toString());
        retornoConsultaCEP.setBairro(linkedTreeMap.get("bairro").toString());
        retornoConsultaCEP.setLocalidade(linkedTreeMap.get("localidade").toString());
        retornoConsultaCEP.setUf(linkedTreeMap.get("uf").toString());
        retornoConsultaCEP.setUnidade(linkedTreeMap.get("unidade") != null ? linkedTreeMap.get("unidade").toString() : null);
        retornoConsultaCEP.setIbge(linkedTreeMap.get("ibge").toString());
        retornoConsultaCEP.setGia(linkedTreeMap.get("gia").toString());
        return retornoConsultaCEP;
    }

    private String getContent(URL url) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null; ) {
                content.append(line);
            }
        }

        return content.toString();
    }


}
