package Shakti.HibernateApp;

import java.io.*;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
	
	public static Properties properties = new Properties();
	
	public static void loadParams(String fname) throws IOException{
		properties.load(App.class.getResourceAsStream(fname));
	}
	
    public static void main( String[] args ) throws Exception
    {
    	loadParams("app.properties");
        System.out.println( "Hello, "+properties.getProperty("name")+"!" );
    }
}
