package Shakti.HibernateApp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Shakti.HibernateApp.daos.StuffDao;
import Shakti.HibernateApp.services.UserDetailsSrvc;

@RestController
public class TestController {
	
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Value("${app.clientId}")
    private String clientId;
    
    @Value("${app.clientSecret}")
    private String clientSecret;
    
    @Autowired
    StuffDao stuffDao;
    
    @Autowired UserDetailsSrvc userSrvc;
	
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/close/truncate")
    public String truncate() {
    	stuffDao.truncate();
    	return "User table cleared.";
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
    
    @GetMapping("/quick")
    public String quickMake() {
    	try {
    	userSrvc.createNewUser("brenan", "pass");
    	return "user made!";
    	} catch(Exception ex) {}
    	return "no user";
    }

//  @GetMapping("/user/me")
//  public Principal user(Principal principal) {
//      return principal;
//  }
    
}
