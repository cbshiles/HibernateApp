package Shakti.HibernateApp.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.boot.web.servlet.error.ErrorController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Shakti.HibernateApp.entities.User;

@RestController
public class TestController /* implements ErrorController*/ {

    private static final String PATH = "/error";
	
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
    @RequestMapping("/")
    public String index() {
    	//userDao.createUser("Chuck", null);
        return "Greetings from Spring Boot!";
    }

    /*
    @RequestMapping(value = PATH)
    public String error(){
	return "Error page.";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
    */
    
    @RequestMapping("/truncate")
    public String truncate() {
    	//userDao.truncate();
    	return "User table cleared.";
    }
    
    @RequestMapping("/hello")
    public List<User> hello(){
    	List<User> lzt = new ArrayList<User>();
    	//lzt.add(userDao.createUser("Bob", null));
    	return lzt;
    }
    
/*    @RequestMapping("/user")
    public User getUser(@RequestParam int id) {
    	Optional<User> u = userDao.find(id);
    	if (u.isPresent()) {
    		return u.get();
    	} else {
    		return null;
    	}
    }*/
    
    @Value("${google.clientId}")
    private String clientId;
    
    @RequestMapping("/google-login")
    public String login(@RequestParam(required = false) String state, @RequestParam(required = false) String code, @RequestParam(required = false) String scope,
    		@RequestParam(required = false) String session_state, @RequestParam(required = false) String prompt) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("<!DOCTYPE>");
    	sb.append("<html>");
    	sb.append("<head>");
    	sb.append("<meta name=\"google-signin-client_id\" content=\""+clientId+"\">");
    	sb.append("<script src=\"https://apis.google.com/js/platform.js\" async defer></script>");
    	sb.append("<script>");
    	sb.append("function onSignIn(googleUser) {\n");
    	sb.append("	  var profile = googleUser.getBasicProfile();\n");
                  sb.append("var id_token = googleUser.getAuthResponse().id_token;\n");
		  sb.append("console.log('ID token: ' + id_token); "); 
    		  sb.append("console.log('ID: ' + profile.getId()); "); // Do not send to your backend! Use an ID token instead.
    		  sb.append("console.log('Name: ' + profile.getName()); console.log('Image URL: ' + profile.getImageUrl());");
    		  sb.append("console.log('Email: ' + profile.getEmail());"); // This is null if the 'email' scope is not present. \n }");
		  String chonk = "var xhr = new XMLHttpRequest();\n xhr.open('POST', 'https://demo.meta.golf/tokensignin');\n xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');\n xhr.onload = function() {\n   console.log('Signed in as: ' + xhr.responseText);\n };\n xhr.send('idtoken=' + id_token);";
		      sb.append(chonk+"} </script>");
    	sb.append("</head>");
    	sb.append("<body>");
    	String d = " ~ ";
    	sb.append("<div class=\"g-signin2\" data-onsuccess=\"onSignIn\"></div>\n");
    	sb.append("</body>");
    	sb.append("</html>");
    	return sb.toString();
    }
    
    @RequestMapping("/4j")
    String index2(){
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
        return "log test";
    }
    
    @RequestMapping("/howdy")
    @ResponseBody
    public String home() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return "Welcome, " + username;
    }

}
