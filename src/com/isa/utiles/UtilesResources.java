package com.isa.utiles;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UtilesResources {
	
	private static UtilesResources instance;
	private Properties appProperties = null; 
	
	public UtilesResources(){
		try {
			appProperties = new Properties();
			InputStream inputStream = this.getClass().getResourceAsStream("/config.properties");
			appProperties.load( inputStream );
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static UtilesResources getInstance(){
		if (instance == null){
			instance = new UtilesResources();
		}
		return instance;
	}
	
    public static String getProperty(String key) throws IOException{
        return getInstance().getProperties().getProperty(key);
    }
    
    private Properties getProperties(){
        return this.appProperties;
    }	
	
	
}
