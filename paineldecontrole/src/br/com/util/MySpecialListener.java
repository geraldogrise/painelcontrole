package br.com.util;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;  
import javax.servlet.ServletContextListener;  
import javax.sql.DataSource;

import org.apache.log4j.Logger;
  
public class MySpecialListener implements ServletContextListener {  

	private static Logger logger = Logger.getLogger(Conexao.class.getName());

    public void contextInitialized(ServletContextEvent ev) {  
    	logger.debug("MySpecialListener - contextInitialized");
    	 try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (Exception e) {
			logger.debug("MySpecialListener - contextInitialized error");
			e.printStackTrace();
		}
    	 logger.debug("MySpecialListener - contextInitialized");
    }  
      
    public void contextDestroyed(ServletContextEvent ev) {  
        try {  
        	
        	
        	logger.debug("MySpecialListener - contextDestroyed");
        	
        	
        	
            Context ic = new InitialContext();  
            Context CONTEXT = (Context) ic.lookup("java:/comp/env");  
            DataSource ds = (DataSource)CONTEXT.lookup("jdbc/myoracle");  
            ds.getConnection().close();
            ds = null;
            
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while(drivers.hasMoreElements()) {
            	Driver driver = drivers.nextElement();
            	 logger.debug(String.format("deregistering jdbc driver: %s", driver));
                DriverManager.deregisterDriver(driver );
                logger.debug("MySpecialListener - Deregister drive fim ");
                
            }
            
            
            logger.debug("MySpecialListener - contextDestroyed final");
        } catch (Exception e) {  
        	logger.debug("MySpecialListener - Error");
            e.printStackTrace();  
        }   
    }      
} 
