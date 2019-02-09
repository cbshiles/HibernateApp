package Shakti.HibernateApp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import Shakti.HibernateApp.services.UserDetailsSrvc;

@EnableWebSecurity
@Order(Ordered.LOWEST_PRECEDENCE)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
		
	@Autowired
    private UserDetailsSrvc userDetailsSrvc;
	
	@Autowired 
	private PasswordEncoder pwEncoder;
   
	   @Autowired
	    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		   auth.userDetailsService(userDetailsSrvc).passwordEncoder(pwEncoder);
	    }
	
	   @Override
	   @Bean(name="myAuthenticationManager")
	   public AuthenticationManager authenticationManagerBean() throws Exception {
	       return authenticationManager();
	   }
	    
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	        .authorizeRequests()
	        .antMatchers("/oauth/authorize").authenticated()
	        .and()	        
	        .formLogin()
	        ;
	    }
	    
	    
	    @Override
	 	public void configure(WebSecurity web) throws Exception {
	 		//web.ignoring().antMatchers(HttpMethod.OPTIONS, "/oauth/token");
	 				//.antMatchers("/resources/**");
	 	}

}
