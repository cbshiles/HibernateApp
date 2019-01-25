package Shakti.HibernateApp.controllers;

import java.net.URI;
import java.security.Principal;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import Shakti.HibernateApp.services.LoginService;

@RestController
public class AuthController {
	
	 private final Logger log =  LoggerFactory.getLogger(this.getClass());
	
    @Value("${app.clientId}")
    private String clientId;
    
    @Autowired
    private RestTemplate oauthTemplate;
    
    @Autowired LoginService loginSrvc;
    
    @Autowired OAuth2ProtectedResourceDetails authDetails;
    
    @PostMapping("/auth")
    public String auth(@RequestBody Map<String, String> pojo) {
    	//userDao.createUser("Chuck", null);
    	
    	//for now assuming login is successful (LoginSAercvce!)
    	
    	UriComponentsBuilder builder = 
    			UriComponentsBuilder.fromHttpUrl("http://localhost:8080/oauth/authorize")
    	        .queryParam("response_type", "code")
    	        .queryParam("client_id", clientId)
    	        .queryParam("redirect_uri", "yahoo.com");
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
    	HttpEntity<?> entity = new HttpEntity<>(headers);

    	String url = builder.toUriString();
    	try {
    	ResponseEntity<String> result 
    	= oauthTemplate.exchange(builder.toUriString(), HttpMethod.GET,
    			entity, String.class);
    	} catch (Exception ex) {
    		log.debug(ex.getMessage());
    	}
        return "Dat "+pojo.get("userId")+" Boot!";
    }
    
    @GetMapping("/quick")
    public String quickMake() {
    	try {
    	loginSrvc.createNewUser("brenan", "pass");
    	return "user made!";
    	} catch(Exception ex) {}
    	return "no user";
    }
    
    @GetMapping("/login")
    public String login() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("<!DOCTYPE>");
    	sb.append("<html>");
    	sb.append("<head>");
    	sb.append("<script src=\"/jquery-3.1.1.js\" > </script> ");
    	sb.append("<script src=\"/app.js\"></script>");
    	sb.append("</head>");
    	sb.append("<body>");
    	String d = " ~ ";
    	sb.append("<input type=\"text\" id=\"userid\"> <input type=\"password\" id=\"passwd\"> <button id=\"button\">Submit</button>");
    	sb.append("</body>");
    	sb.append("</html>");
    	return sb.toString();
    }
    
    @GetMapping("/user/me")
    public Principal user(Principal principal) {
        return principal;
    }

}
