package Shakti.HibernateApp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
@Order(0)
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter{
	
    @Value("${app.clientId}")
    private String clientId;
 
    @Value("${app.clientSecret}")
    private String clientSecret;
    
    @Value("${app.redirectPath}")
    private String redirectPath;
    
	@Value("${app.resourceId}")
	private String resourceId;
    
    @Autowired 
    PasswordEncoder passwordEncoder;
	
	@Override
	public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
	            clients.inMemory()
	                .withClient(clientId)
	                    .authorizedGrantTypes("authorization_code", "refresh_token")
	                    .authorities("USER")
	                    .redirectUris(redirectPath)
	                    .scopes("read", "write")
	                    .resourceIds(resourceId)
	                    .secret(passwordEncoder.encode(clientSecret));
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints)
            throws Exception{
		endpoints.tokenStore(tokenStore());
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {	
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }  

    @Bean
    public TokenStore tokenStore() {
    	return new InMemoryTokenStore();
    }

}
