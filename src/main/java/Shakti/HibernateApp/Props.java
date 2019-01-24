package Shakti.HibernateApp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/*
 * To be used to access 'compound' properties.
 * This class is not need to directly access properties,
 * but can be useful when multiple properties must be combined.
 */
@Service
public class Props {
	
	@Value ("${app.baseUri}")
	private String baseUri;
	
	@Value ("${app.redirectPath}")
	private String redirectPath;
	
	@Value ("${app.tokenPath}")
	private String tokenPath;
	
	@Value ("${app.authPath}")
	private String authPath;
	
	@Value ("${app.jwkPath}")
	private String jwkPath;
	
	
	public String redirectUri() {
	    return baseUri+redirectPath;
	}
	
	public String tokenUri() {
	    return baseUri+tokenPath;
	}
	
	public String authUri() {
	    return baseUri+authPath;
	}
	
	public String jwkUri() {
	    return baseUri+jwkPath;
	}
	
	public String issuer() {
		return baseUri;
	}

}
