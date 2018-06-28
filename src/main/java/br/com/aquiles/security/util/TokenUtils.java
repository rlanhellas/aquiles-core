package br.com.aquiles.security.util;

import br.com.aquiles.core.exception.ServiceException;
import br.com.aquiles.security.dto.Subject;
import br.com.aquiles.security.exception.TokenException;
import br.com.aquiles.security.service.ApiKeysService;
import com.google.gson.Gson;
import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class TokenUtils {

    private static final String AUTH_HEADER_KEY = "Authorization";
    private static final String AUTH_HEADER_VALUE_PREFIX = "bearer "; // with trailing space to separate util

    @Inject
    private ApiKeysService apiKeysService;

    public String createJWT(HttpServletRequest request, Subject subject) throws TokenException {
        Gson gson = new Gson();
        //The JWT signature algorithm we will be using to sign the util
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //We will sign our JWT with our ApiKey secret
        String apiToken = null;
        try {
            if(apiKeysService.validateToken(subject)){
                apiToken = apiKeysService.getUserKey(subject).getApiKey();
            }
        } catch (ServiceException e) {
            throw new TokenException(e);
        }
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apiToken);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(apiToken)
                .setIssuedAt(now)
                .setSubject(gson.toJson(subject))
                .setIssuer(request.getRequestURI())
                .signWith(signatureAlgorithm, signingKey);

            long expMillis = nowMillis + 30000;//5 min
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public boolean validateJWT(HttpServletRequest request) throws TokenException{
        String jwt ;
        String [] requestStringArray = request.getRequestURL().toString().split("/");
        String apiKey = requestStringArray[requestStringArray.length];
        try{
            if(!request.getRequestURI().contains("/seguranca/token")) {
                jwt = getBearerToken(request);
            }else{
                return true;
            }
        }catch (NullPointerException e){
            throw new SignatureException("Token não encontrado no header da requisição");
        }
        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(apiKey))
                    .parseClaimsJws(jwt).getBody();
        Gson gson = new Gson();
        Subject subject = gson.fromJson(claims.getSubject(), Subject.class);
        return isValid(subject);
    }

    public String createApiKey(String login) throws TokenException {
        String apiToken;
        Subject subject = new Subject();
        subject.setLogin(login);
        try {
            apiToken = apiKeysService.getUserKey(subject).getApiKey();
        }catch (NullPointerException e){
            try {
                apiToken = apiKeysService.createApiKey(subject).getApiKey();
            } catch (ServiceException e1) {
                throw new TokenException(e1);
            }
        } catch (ServiceException e) {
            throw new TokenException(e);
        }
        return apiToken;
    }

    /**
     * Get the bearer util from the HTTP request.
     * The util is in the HTTP request "Authorization" header in the form of: "Bearer [util]"
     */
    public static String getBearerToken( HttpServletRequest request ) {
        String authHeader = request.getHeader( AUTH_HEADER_KEY );
        if ( authHeader != null && authHeader.toLowerCase().startsWith( AUTH_HEADER_VALUE_PREFIX ) ) {
            return authHeader.substring( AUTH_HEADER_VALUE_PREFIX.length() );
        }
        return null;
    }

    private boolean isValid(Subject subject) throws TokenException{
        try {
            return apiKeysService.validateToken(subject);
        } catch (ServiceException e) {
            throw new TokenException(e);
        }
    }
}
