package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Command extends Thread {

	 PrintWriter out = null;
	 BufferedReader in = null;
	 Socket fromserver = null;
	
	 String fuser;
	 String fserver;
	 BufferedReader inu;
	
	public Command(){

		try {
			fromserver = new Socket(InetAddress.getLocalHost(),4444);
			in = new BufferedReader(new InputStreamReader(fromserver.getInputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 					   
	BufferedReader inu = new BufferedReader(new InputStreamReader(System.in));
	  			System.out.println("Hi! ");		    
	}
	
	@Override
	public  void run() {
		 String input;
		System.out.println("work1");
		while(true) {
		//	System.out.println("loop");
			try {
				input = in.readLine();
				System.out.println(input+" drone");
				ArDroneDemo.setCommand(input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}		

	}

}
