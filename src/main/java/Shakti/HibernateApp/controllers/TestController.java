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
import org.springframework.web.bind.annotation.GetMapping;
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
    
    @GetMapping("/close/hello")
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
    public List<String> home() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<String> lzt = new ArrayList<String>();
        lzt.add("Welcome, " + username);
        return lzt;
    }

}
