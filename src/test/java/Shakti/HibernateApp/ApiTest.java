package Shakti.HibernateApp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.common.util.JacksonJsonParser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class ApiTest extends TestCase{
	
	private static final Logger log = LoggerFactory.getLogger(ApiTest.class);
	
	@Value("${google.clientId}")
	private String clientId;

	@Value("${google.clientSecret}")
	private String clientSecret;
	
    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private WebApplicationContext wac;
 
    @Autowired
    private FilterChainProxy springSecurityFilterChain;
 
   /* @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(this.wac)
          .addFilter(springSecurityFilterChain).build();
    }*/
    
    private static final String jsonType = "application/json;charset=UTF-8";
    
    private String obtainAccessToken(String username, String password) throws Exception {
    	  
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", clientId);
        params.add("username", username);
        params.add("password", password);
     
        ResultActions result 
          = mvc.perform(post("/oauth/token")
            .params(params)
            .with(httpBasic(clientId, clientSecret))
            .accept(jsonType))
            .andExpect(status().isOk())
            .andExpect(content().contentType(jsonType));
     
        String resultString = result.andReturn().getResponse().getContentAsString();
     
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }
    
    private final String testEmail = "dummy@prok.org";
    
    @Test
    public void ggg() {
    	assertThat(7).isEqualTo(7);
    }
    /*
    @Test
    public void unauthorizedHello() throws Exception {
    	mvc.perform(get("/hello")
    	.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnauthorized());
    }*/
    
	/*
    mvc.perform(get("/employee")
      .param("email", testEmail))
      .andExpect(status().isUnauthorized());
      */    
	/*
	  @Test 
	    public void authorizedHello() throws Exception{
		  String accessToken = obtainAccessToken("admin", "nimda");
		  
	    	mvc.perform(get("/hello")
	    	.header("Authorization", "Bearer " + accessToken)
	    	.contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk())
	        .andExpect(content()
	        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	        .andExpect(jsonPath("$[0].name", is("Bob")));
	    }
	*/
}