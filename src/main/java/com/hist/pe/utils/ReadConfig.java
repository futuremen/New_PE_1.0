package com.hist.pe.utils;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

public class ReadConfig {
	
	
	private static String propertyFileName= "C:\\config.properties";
    private static ResourceBundle resourceBundle =  ResourceBundle.getBundle(propertyFileName);
   
    public static String getString(String key) {
        if (key == null || key.equals("") || key.equals("null")) {
            return "";
        }
        String result = "";
        try {
            result = resourceBundle.getString(key);
        } catch (MissingResourceException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    
    
    public  void setString(String key,String val) throws IOException {
    	
    	        Properties prop = new Properties();
    	        
    	        
    	        InputStream in = new FileInputStream("C:\\config.properties");
    	        
//    	       
    	        
    	        
    	      
    	        
    	        prop.load(in);
    	        
    	        prop.setProperty(key, val);
    	        
    	        
    	        FileOutputStream fileOutputStream = new FileOutputStream("com/hist/pe/config/config.properties");
    	       
    	        prop.store(fileOutputStream, "last update");
    	      
    	       
    	    
    	        
    	        
    	        
    	        prop.load(in);
    	        
    	        prop.setProperty(key, val);
    	        
    	        
    	        in.close();
    	        fileOutputStream.close();
    	        
    	        
    	        
    	        
    	        
    	       
    	        
    	        
    	      
    	       
    	     
    	    
    }

}
