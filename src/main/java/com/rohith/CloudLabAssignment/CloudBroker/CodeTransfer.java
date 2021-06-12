package com.rohith.CloudLabAssignment.CloudBroker;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.rohith.CloudLabAssignment.CloudServiceProvider.SSHConnect;

public class CodeTransfer {
	public void fileTransfer(SSHConnect sshconnect) 
					throws JSchException, SftpException {
		
		ChannelSftp channelSftp = sshconnect.SFTPConnect();
	    channelSftp.connect();
	 
	    String localFile = "code.java";
	    String remoteDir = "";
	    
	    System.out.println("Uploading file ...");
	    
	    
	    long startTime = System.currentTimeMillis();
	    channelSftp.put(localFile, remoteDir + "code.java");
	    long endTime = System.currentTimeMillis();
	    
	    System.out.println("\n");
		String time = "Uploaded in "+(endTime-startTime)+"ms";
	    System.out.println("Upload Successful ..."+time);
	    channelSftp.exit();
	}
}
