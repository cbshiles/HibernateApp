package Shakti.HibernateApp.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Shakti.HibernateApp.Application;
import Shakti.HibernateApp.User;
import Shakti.HibernateApp.daos.UserDao;

@RestController
public class TestController {
	
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired UserDao userDao;
	
    @RequestMapping("/")
    public String index() {
    	userDao.createUser("Chuck", null);
        return "Greetings from Spring Boot!";
    }
    
    @RequestMapping("/truncate")
    public String truncate() {
    	userDao.truncate();
    	return "User table cleared.";
    }
    
    @RequestMapping("/hello")
    public List<User> hello(){
    	List<User> lzt = new ArrayList<User>();
    	lzt.add(userDao.createUser("Bob", null));
    	return lzt;
    }
    
    @RequestMapping("/user")
    public User getUser(@RequestParam int id) {
    	Optional<User> u = userDao.find(id);
    	if (u.isPresent()) {
    		return u.get();
    	} else {
    		return null;
    	}
    }
    
    @RequestMapping("/4j")
    String index2(){
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
        return "log test";
    }

}
