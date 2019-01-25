package Shakti.HibernateApp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;

import org.springframework.beans.factory.annotation.Value;

import Shakti.HibernateApp.Props;
import Shakti.HibernateApp.services.UserDetailsSrvc;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
		
	@Autowired
    private UserDetailsSrvc detailsSrvc;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	
	   @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authProvider
	          = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(detailsSrvc);
	        authProvider.setPasswordEncoder(passwordEncoder());
	        return authProvider;
	    }
	    
	   @Autowired
	    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	      auth.authenticationProvider(authenticationProvider());
	    }
	   
	
	   @Override
	   @Bean(name="myAuthenticationManager")
	   public AuthenticationManager authenticationManagerBean() throws Exception {
	       return authenticationManager();
	   }
	   
	
	@Value("${app.loginPath}")
	private String loginPath;
	
	    @Autowired
	    private Props props;
		
		@Autowired
	    private OAuth2RestTemplate restTemplate;
	    
	    @Bean
	    public ConnectionFilter openIdConnectFilter() {
	    	ConnectionFilter cf = new ConnectionFilter(props.redirectUri(), restTemplate);
	    	return cf;
	    }
	 
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.csrf().disable().authorizeRequests()
	        .antMatchers("/close/**").authenticated()
	        .antMatchers("/oauth/authorize").permitAll()
	        .antMatchers("/oauth/**").authenticated()
	        .and()
	        
//	        .formLogin()
//	        .loginPage("/login")
//	        .loginProcessingUrl("/perform_login")
//	        .defaultSuccessUrl("/",true)
//	        .failureUrl("/login?error=true")
//	        .and()
	        
	        .addFilterAfter(new OAuth2ClientContextFilter(), //try just addFilter here (remove class)
	          AbstractPreAuthenticatedProcessingFilter.class)
	        .addFilterAfter(openIdConnectFilter(), OAuth2ClientContextFilter.class)
	        .httpBasic()	
	        .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint(loginPath))
	        .and()
	        .authorizeRequests()
	        .anyRequest().permitAll()
	        ;//.and().csrf().disable(); //remove later
	    }
	    
	    
	    @Override
	 	public void configure(WebSecurity web) throws Exception {
	 		web.ignoring()
	 		// Spring Security should completely ignore URLs starting with /resources/
	 				.antMatchers("/resources/**");
	 	}

}
