package Shakti.HibernateApp.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@EnableResourceServer
@Configuration
public class ResourceServerConfigurer extends ResourceServerConfigurerAdapter {
	
	@Value("${app.resourceId}")
	private String resourceId;
	
    private static final String SECURED_READ_SCOPE = "#oauth2.hasScope('read')";
    private static final String SECURED_WRITE_SCOPE = "#oauth2.hasScope('write')";
    private static final String SECURED_PATTERN = "/close/**";

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
        .requestMatchers()
        .antMatchers(SECURED_PATTERN).and().authorizeRequests()
        .antMatchers(HttpMethod.POST, SECURED_PATTERN).access(SECURED_WRITE_SCOPE)
        .anyRequest().access(SECURED_READ_SCOPE);
    }
    
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
    	resources.resourceId(resourceId);
    }
	
}
