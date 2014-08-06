package br.com.util;

import java.sql.SQLException;

public class SVAException extends SQLException{

	private String message = null;
	 
    public SVAException() {
        super();
    }
 
    public SVAException(String message) {
    	super(message);
    	message = retornaMsgTratada(message);
        this.message = message;
    }
 
    public SVAException(Throwable cause) {
        super(cause);
    }
 
    @Override
    public String toString() {
        return message;
    }
 
    @Override
    public String getMessage() {
        return message;
    }
	
    
    public static String retornaMsgTratada(String message){
    	if (message.contains("ORA-20100")){
    		int pos_ini = 0;
    		while (true){
    			if (message.substring(pos_ini, pos_ini + 9).equalsIgnoreCase("ORA-20100")){
    				pos_ini = pos_ini + 9;
    				message = message.substring(pos_ini, message.length());
    				break;
    			}
    			pos_ini++;
    		}
    		
    		int pos_fin = pos_ini + 1;
    		while (true){
    			if (message.substring(pos_fin, pos_fin + 3).equalsIgnoreCase("ORA")){
    				break;
    			}
    			pos_fin++;
    		}
    		
    		return message.substring(1, pos_fin);
    	}else if (message.contains("ORA-20001")){
    		int pos_ini = 0;
    		while (message.contains("ORA-20001")){
    			if (message.substring(pos_ini, pos_ini + 9).equalsIgnoreCase("ORA-20001")){
    				pos_ini = pos_ini + 9;
    				message = message.substring(pos_ini, message.length());
    				pos_ini = 0;
    				continue;
    			}
    			pos_ini++;
    		}
    		
    		
    		int pos_fin = 0;
    		
    		if (!message.contains("ORA")){
    			pos_fin = message.length();
    		}
    		
    		while (message.contains("ORA")){
    			if (message.substring(pos_fin, pos_fin + 3).equalsIgnoreCase("ORA")){
    				break;
    			}
    			pos_fin++;
    		}
    		
    		return message.substring(1, pos_fin);
    	}
    	
    	return message;
    }
	
}
