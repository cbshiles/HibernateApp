package Shakti.HibernateApp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import Shakti.HibernateApp.daos.StuffDao;
import Shakti.HibernateApp.entities.User;

@RestController
public class TestController {
	
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Value("${app.clientId}")
    private String clientId;
    
    @Value("${app.clientSecret}")
    private String clientSecret;

    @Value("${app.tokenRedirect}")
    private String tokenRedirect;
	
    @Autowired
    private OAuth2RestTemplate oauthTemplate;
    
    @Autowired
    StuffDao stuffDao;
	
    @RequestMapping("/")
    public String index() {
    	//userDao.createUser("Chuck", null);
        return "Greetings from Spring Boot!";
    }

    
    @RequestMapping("/close/truncate")
    public String truncate() {
    	stuffDao.truncate();
    	return "User table cleared.";
    }
    
    //landing page between authorize & token OAuth endpoints
    @RequestMapping("/landing")
    public String land(@RequestParam(required = false) String code) {
    	
      	UriComponentsBuilder builder = 
    			UriComponentsBuilder.fromHttpUrl("http://localhost:8080/oauth/authorize")
    	        .queryParam("grant_type", "authorization_code")
    	        .queryParam("client_id", clientId)
    	        .queryParam("password", clientSecret)
    	        .queryParam("redirect_uri", tokenRedirect);
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
    	HttpEntity<?> entity = new HttpEntity<>(headers);

    	String url = builder.toUriString();
    	ResponseEntity<String> result;
    	try {
    	result = oauthTemplate.exchange
    			(builder.toUriString(), HttpMethod.POST, entity, String.class);
    	
    	} catch (Exception ex) {
    		log.debug(ex.getMessage());
    	}
    	
    	OAuth2AccessToken result2 = oauthTemplate.getAccessToken();
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append("<!DOCTYPE>");
    	sb.append("<html>");
    	sb.append("<head>");

    	sb.append("</head>");
    	sb.append("<body>");
    	sb.append("<p>"+code+"</p>");
    	sb.append("</body>");
    	sb.append("</html>");
    	return sb.toString();
    }
    
    @RequestMapping("/close/hello")
    public List<User> hello(){
    	List<User> lzt = new ArrayList<User>();
    	//lzt.add(userDao.createUser("Bob", null));
    	return lzt;
    }
    
    @RequestMapping("/4j")
    String index2(){
        log.debug("This is a debug message");
        log.info("This is an info message");
        log.warn("This is a warn message");
        log.error("This is an error message");
        return "log test";
    }
    
    @RequestMapping("/close/howdy")
    @ResponseBody
    public String home() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return "Welcome, " + username;
    }

}
