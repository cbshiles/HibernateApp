package Shakti.HibernateApp.auth;

import java.io.IOException;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Shakti.HibernateApp.Props;

public class ConnectionFilter extends AbstractAuthenticationProcessingFilter{

	   private static final Logger log = LoggerFactory.getLogger(ConnectionFilter.class);
	    
@Autowired
private Props props;
	    
	    @Value("${app.clientId}")
	    private String clientId;


	    @Override
	    @Autowired
	    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
	        super.setAuthenticationManager(authenticationManager);
	    }
	    
	    
	    private OAuth2RestTemplate restTemplate;
		 
	    public ConnectionFilter(String defaultFilterProcessesUrl, OAuth2RestTemplate rt) {
	        super(defaultFilterProcessesUrl);
	        restTemplate = rt;
	    }
	    
	    @Override
	    public Authentication attemptAuthentication(
	      HttpServletRequest request, HttpServletResponse response) 
	      throws AuthenticationException, IOException, ServletException {
	        OAuth2AccessToken accessToken;
	        try {
	            accessToken = restTemplate.getAccessToken();
	        } catch (OAuth2Exception e) {
	            throw new BadCredentialsException("Could not obtain access token", e);
	        }
	        try {
	            String idToken = accessToken.getAdditionalInformation().get("id_token").toString();
	            String kid = JwtHelper.headers(idToken).get("kid");
	            Jwt tokenDecoded = JwtHelper.decodeAndVerify(idToken, verifier(kid));
	            Map<String, String> authInfo = mapAuthInfo(tokenDecoded);
	            verifyClaims(authInfo);
	            UserDeets user = new UserDeets(authInfo, accessToken);
	            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
	        } catch (InvalidTokenException e) {
	            throw new BadCredentialsException("Could not obtain user details from token", e);
	        }
	    }
	    
		private Map<String, String> mapAuthInfo(Jwt jwt) throws JsonParseException, JsonMappingException, IOException{
		    TypeReference<HashMap<String,String>> typeRef = new TypeReference<HashMap<String,String>>() {};
		    log.info(jwt.getClaims());
		    return new ObjectMapper().readValue(jwt.getClaims(), typeRef);
	    }
	     
	    private RsaVerifier verifier(String kid) throws InvalidTokenException {
			try {
				JwkProvider provider = new UrlJwkProvider(new URL(props.jwkUri()));
				Jwk jwk = provider.get(kid);
				return new RsaVerifier((RSAPublicKey) jwk.getPublicKey());
			} catch (Exception ex) {
				throw new InvalidTokenException("Could not verify token.", ex);
			}
	    }
	    
	    
	    
	public void verifyClaims(Map<String, String> claims) {
		//exp must be in seconds
		Date expireDate = new Date(Integer.parseInt(claims.get("exp")) * 1000L);
		for (String key : claims.keySet()) {
			log.info("claim " + key + ": " + claims.get(key));
		}
		if (expireDate.before(new Date()) || !claims.get("iss").equals(props.issuer()) || !claims.get("aud").equals(clientId)) {
			throw new RuntimeException("Invalid claims");
		}
	}

}
