package Shakti.HibernateApp;

import java.io.*;
import java.util.Properties;

/**
 * Hello world!
 *
 */

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import Shakti.HibernateApp.daos.UserDao;

@SpringBootApplication
public class Application {
	
	@Value("${name:Joe}")
	private String name;
	
	//private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
    	//logger.trace("Configuration File Defined To Be :: "+System.getProperty("log4j.configurationFile"));
        SpringApplication.run(Application.class, args);
    	//loadParams("app.properties");
    	
    	
    	
        //System.out.println( "Hello, "+properties.getProperty("name")+"!" );
    }
    
    @Autowired UserDao userDao;

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

        	//printBeans(ctx);
        	
//        	userDao.createUser("Bob", null);
        	System.out.println("exit~");
        };
    }
    
    //List beans that Spring boot is aware of
    private static void printBeans(ApplicationContext ctx) {
        System.out.println("Let's inspect the beans provided by Spring Boot:");
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

	
	public static Properties properties = new Properties();
	
	public static void loadParams(String fname) throws IOException{
		properties.load(Application.class.getResourceAsStream(fname));
	}
	
}
