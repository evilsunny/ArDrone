package com;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Accepting extends Thread {

	  ServerSocket servers ;
	  Socket       fromclient ;
	
	
	public Accepting(ServerSocket servers){
		this.servers=servers;
	}
	
	  @Override
	public  void run() {
		while(true)
		{
			try {
		
			  
		      System.out.print("Waiting for a client...");
		      fromclient= servers.accept();
		      net.fromclient=fromclient;
		      System.out.println("Client connected");
		    } catch (IOException e) {
		      System.out.println("Can't accept");
		      System.exit(-1);
		    }

	}
	  }

}
