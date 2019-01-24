package Shakti.HibernateApp.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import Shakti.HibernateApp.Props;

@Configuration
@EnableOAuth2Client
//@AutoConfigureOrder(4)
public class ClientConfig {
	
    @Value("${app.clientId}")
    private String clientId;
 
    @Value("${app.clientSecret}")
    private String clientSecret;
    
    @Autowired
    private Props props;
	
    @Bean // does need to be a bean?
    public OAuth2ProtectedResourceDetails getResourceDetails() {
        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);
        details.setAccessTokenUri(props.tokenUri());
        details.setUserAuthorizationUri(props.authUri());
        details.setScope(Arrays.asList("openid", "email"));
        details.setPreEstablishedRedirectUri(props.redirectUri());
        details.setUseCurrentUri(false);
        return details;
    }
 
    @Bean
    public OAuth2RestTemplate oAuthTemplate(OAuth2ClientContext clientContext) {
        return new OAuth2RestTemplate(getResourceDetails(), clientContext);
    }

}
