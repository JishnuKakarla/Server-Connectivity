package com.rohith.CloudLabAssignment.CloudServiceProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SSHConnect {
	String host;
    String user;
    String password;
    
    public void Properties() throws IOException {
    	try {
    		
			Properties prop = new Properties();
			
			prop.load(new FileInputStream("application.properties"));
			
			// get the property value and print it out
			this.host = prop.getProperty("HOST");
			this.user = prop.getProperty("USER");
			this.password = prop.getProperty("PASS");
 
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
    	
    }
    
    public Session Connect() {
    	try{
	    	
	    	java.util.Properties config = new java.util.Properties(); 
	    	config.put("StrictHostKeyChecking", "no");
	    	JSch jsch = new JSch();
	    	Session session=jsch.getSession(user, host, 22);
	    	session.setPassword(password);
	    	session.setConfig(config);
	    	session.connect();
	    	System.out.println("**************************************");
	    	System.out.println("Connected to SSH Server ... IP: "+host);
	    	System.out.println("**************************************");
	    	return session;
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
		return null;
    }
    
    public ChannelSftp SFTPConnect() throws JSchException {
    	try{
	    	
	    	java.util.Properties config = new java.util.Properties(); 
	    	config.put("StrictHostKeyChecking", "no");
	    	JSch jsch = new JSch();
	    	Session session=jsch.getSession(user, host, 22);
	    	session.setPassword(password);
	    	session.setConfig(config);
	    	session.connect();
	    	System.out.println("**************************************");
	    	System.out.println("Connected to SFTP Server ... IP : "+host);
	    	System.out.println("**************************************");
	    	return (ChannelSftp)session.openChannel("sftp");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
		return null;
    }
}
