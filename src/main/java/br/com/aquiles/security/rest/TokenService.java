package br.com.aquiles.security.rest;

import br.com.aquiles.security.dto.Subject;
import br.com.aquiles.security.exception.TokenException;
import br.com.aquiles.security.service.ApiKeysService;
import br.com.aquiles.security.service.LoginService;
import br.com.aquiles.security.util.TokenUtils;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("seguranca")
public class TokenService {
    @Inject
    ApiKeysService apiKeysService;
    @Inject
    TokenUtils tokenUtils;
    @Inject
    @Named(value = "loginService")
    LoginService loginService;

    @GET
    @Path("token/{key}")
    public String token(@Context HttpServletRequest request,@PathParam("key") String apiKey){
        Subject subject = new Subject();
        String token="";
        try {
            token = tokenUtils.createJWT(request, subject);
        }catch (TokenException e){
            return e.getMessage();
        }
        return token;
    }
    @GET
    @Path("generateApiKey/{login}")
    public Response generateApiKey(@PathParam("login") String login){
        String key = "";
        try{
            key = tokenUtils.createApiKey(login);
        }catch (TokenException e){
            Response.serverError().entity(e).build();
        }
        return Response.ok().entity(key).build();
    }
}
