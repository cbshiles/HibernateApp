package Shakti.HibernateApp.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import Shakti.HibernateApp.services.ClientDetailsSrvc;
import Shakti.HibernateApp.services.UserDetailsSrvc;

@Configuration
//@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter{
	
//	@Autowired
//    @Qualifier("authenticationManagerBean")
//    private AuthenticationManager authManager;
//	
//    @Value("${app.clientId}")
//    private String clientId;
// 
//    @Value("${app.clientSecret}")
//    private String clientSecret;
//    
//    @Autowired
//    private UserDetailsSrvc userDetailsService;
//	
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    
//	@Override
//	public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
//	        //clients.withClientDetails(clientDetailsService);
//	            clients.inMemory()
//	                .withClient(clientId)
//	                    .authorizedGrantTypes("password", "refresh_token")
//	                    .authorities("USER")
//	                    .scopes("read", "write")
//	                    .resourceIds("resource")
//	                    .secret(clientSecret);
//	}
//	
//	@Autowired
//  private AuthenticationManager authMan;
//	
//	@Override
//	public void configure(AuthorizationServerEndpointsConfigurer endpoints)
//            throws Exception{
//		endpoints
//		.tokenStore(tokenStore())
//		//.userApprovalHandler(userApprovalHandler())
//		.authenticationManager(authMan)
//		.userDetailsService(userDetailsService);
//	}
//	
//	/**/
//	private static String REALM="CRM_REALM";
//	
//	@Override
//	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//		
//		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").passwordEncoder(passwordEncoder);
//
//	    //oauthServer.realm(REALM);
//	    
//	    //.tokenKeyAccess("isAnonymous() || hasAuthority('ROLE_TRUSTED_CLIENT')")
//	      //         .checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");
//	}
//	
////	@Bean
////	TokenStoreUserApprovalHandler userApprovalHandler() {
////		TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
////		handler.setTokenStore(tokenStore());
////		handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
////		handler.setClientDetailsService(clientDetailsService);
////		return handler;
////	}
// 
//    @Bean
//    @Primary
//    public DefaultTokenServices tokenServices() {
//        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//        defaultTokenServices.setTokenStore(tokenStore());
//        defaultTokenServices.setSupportRefreshToken(false);
//        return defaultTokenServices;
//    }
//   
//
//    @Bean
//    public TokenStore tokenStore() {
//    	return new InMemoryTokenStore();
//    }

}
