package Shakti.HibernateApp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import Shakti.HibernateApp.Props;

public class AccessDetails {
	
    @Value("${app.clientId}")
    private String clientId;
 
    @Value("${app.clientSecret}")
    private String clientSecret;
    
    @Autowired
    private Props props;
    
    public String getClientId() {
    	return clientId;
    }    
    public String getClientSecret() {
    	return clientSecret;
    }

}
