package Shakti.HibernateApp.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Shakti.HibernateApp.services.LoginService;

@RestController
public class AuthController {
	
    @Value("${app.clientId}")
    private String clientId;
    
    @Autowired LoginService loginSrvc;
    
    @PostMapping("/auth")
    public String auth(@RequestBody Object pojo) {
    	//userDao.createUser("Chuck", null);
        return "Dat Auth Boot!";
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
    	sb.append("<meta name=\"google-signin-client_id\" content=\""+clientId+"\">");
    	//sb.append("<style href=\"/app.css\"></style>");
    	sb.append(" <script type=\"text/javascript\" src=\"https://bob.meta.golf/jquery-3.1.1.js\" > </script> ");
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
