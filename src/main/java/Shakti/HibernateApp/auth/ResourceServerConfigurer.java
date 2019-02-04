package Shakti.HibernateApp.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@EnableResourceServer
@Configuration
public class ResourceServerConfigurer extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
        
        .antMatcher("/close/**")//.antMatchers("/**").hasRole("USER").and().formLogin()
        .formLogin().defaultSuccessUrl("/success.html", true)
        .and()
        //.access("#oauth2.hasScope('read')").antMatchers("/**").hasRole("USER").and().formLogin()
                .authorizeRequests()
                .anyRequest().authenticated()
         //       .and()

                ;
    }
    
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
    	//resources.resourceId("clientId");
    }
	
}
