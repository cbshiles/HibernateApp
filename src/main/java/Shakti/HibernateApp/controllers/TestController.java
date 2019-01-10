package Shakti.HibernateApp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Shakti.HibernateApp.daos.UserDao;

@RestController
public class TestController {
	
	@Autowired UserDao userDao;
	
    @RequestMapping("/")
    public String index() {
    	userDao.createUser("Chuck", null);
        return "Greetings from Spring Boot!";
    }
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @RequestMapping("/4j")
    String index2(){
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
        return "index";
    }

}
