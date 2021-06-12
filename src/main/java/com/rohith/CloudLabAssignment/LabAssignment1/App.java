
package com.rohith.CloudLabAssignment.LabAssignment1;

import java.io.IOException;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.rohith.CloudLabAssignment.CloudBroker.CodeTransfer;
import com.rohith.CloudLabAssignment.CloudBroker.ExecuteCode;
import com.rohith.CloudLabAssignment.CloudServiceProvider.SSHConnect;

/**
 * Hello world!
 *
 */
public class App 
{
   	public static void main( String[] args ) throws JSchException, SftpException, IOException
    { 
        SSHConnect sshConnect = new SSHConnect();
        
        //Reading Properties file
        sshConnect.Properties();
        
        //Sending the code to Remote Server
        CodeTransfer codeTransfer = new CodeTransfer();
        codeTransfer.fileTransfer(sshConnect);
        
        //Executing the code
        	//Get the session
        	Session session = sshConnect.Connect();
        	
        	//Execute command
        	ExecuteCode executeCode = new ExecuteCode();
        	
        	System.out.println("*******************");
        	System.out.println("Compiling Code ...");
        	System.out.println("*******************");
        	executeCode.CodeExecute(session, "javac code.java");
        
        	//Executing code
        	System.out.println("*******************");
        	System.out.println("Executing Code ...");
        	System.out.println("*******************");
        	executeCode.CodeExecute(session, "java code");
        	
        	session.disconnect();
        	sshConnect = null;
        	executeCode = null;    
    }
}
