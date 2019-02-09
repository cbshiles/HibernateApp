package Shakti.HibernateApp.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import Shakti.HibernateApp.Props;

@Configuration
@EnableOAuth2Client
public class ClientConfig {
	
    @Value("${app.clientId}")
    private String clientId;
 
    @Value("${app.clientSecret}")
    private String clientSecret;
    
    @Autowired
    private Props props;
    
	@Value("${app.resourceId}")
	private String resourceId;
	
    @Bean
    //@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
    public OAuth2ProtectedResourceDetails getResourceDetails() {
        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        details.setId(resourceId);
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);
        details.setScope(Arrays.asList("read", "write"));
        
        details.setAccessTokenUri(props.tokenUri());
        details.setUserAuthorizationUri(props.authUri());
        details.setPreEstablishedRedirectUri(props.redirectUri());
        details.setUseCurrentUri(false);
        return details;
    }
 
    @Bean
    public OAuth2RestTemplate oAuthTemplate(OAuth2ClientContext clientContext) {
        return new OAuth2RestTemplate(getResourceDetails(), clientContext);
    }

}
