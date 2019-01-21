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
	
	@Value ("${app.redirectPath}")
	private String redirectPath;
	
	@Value ("${app.baseUri}")
	private String baseUri;
	
	@Value("${server.port}")
	private String port;
	
	public String getRedirectUri() {
	    return baseUri+/*":"+port+*/redirectPath;
	}
}
