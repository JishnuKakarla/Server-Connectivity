package com.rohith.CloudLabAssignment.CloudBroker;

import java.io.InputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class ExecuteCode {
	
	public void CodeExecute(Session session, String command) 
                          throws JSchException, SftpException {
		
    try {
      
      Channel channel=session.openChannel("exec");
      ((ChannelExec)channel).setCommand(command);
      channel.setInputStream(null);
      ((ChannelExec)channel).setErrStream(System.err);
      
      InputStream in=channel.getInputStream();
      channel.connect();
      byte[] tmp=new byte[1024];
      while(true){
        while(in.available()>0){
          int i=in.read(tmp, 0, 1024);
          if(i<0)break;
          System.out.print(new String(tmp, 0, i));
        }
        if(channel.isClosed()){
        System.out.println("\n");
          System.out.println("Exit Status: "+channel.getExitStatus());
          break;
        }
        
      }
      channel.disconnect();
      in.close();
      System.out.println("DONE");
    } 
    catch (Exception Ex) {
      System.err.println(Ex.toString());
    }
	}
}
