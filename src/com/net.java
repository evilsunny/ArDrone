package com;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
<<<<<<< HEAD
import java.net.ServerSocket;
import java.net.Socket;
=======
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
>>>>>>> b15b1d72dc239386461ef627ce5f800b6f96bd65
import java.net.UnknownHostException;
   
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class net
 */
<<<<<<< HEAD
@WebServlet("/net")
public class net extends HttpServlet {
	    BufferedReader in ;
	    PrintWriter    out;
	    ServerSocket servers ;
	    static Socket fromclient ;
	    static boolean on=true;
	
	
=======

@WebServlet("/net")
public class net extends HttpServlet {
	  /*  BufferedReader in ;
	    PrintWriter    out;
	    static ServerSocket servers ;
	    static Socket fromclient ;*/
//	    static boolean on=true;
	byte[] b = new byte[1000];
	 DatagramPacket pac;
	    
>>>>>>> b15b1d72dc239386461ef627ce5f800b6f96bd65
	@Override
	public void init() throws ServletException 
	{		 
		
<<<<<<< HEAD
//	 	System.out.println("Welcome to Server side");
//
//		    try { 
//		    	 
//		      servers = new ServerSocket(4444);
// 		    } catch (IOException e) {
//		      System.out.println("Couldn't listen to port 4444");
//		      System.exit(-1);}
//		   Thread app = new Program();
//		  	
//	 	try {
		
//		      System.out.print("Waiting for a client...");
//		      fromclient= servers.accept(); 
//		      System.out.println("Client connected");
//		      on=false;
//		    } 
//				catch (IOException e) {
//		      System.out.println("Can't accept");
//		      System.exit(-1);
//		    } 
		try {
			ArDroneDemo.main();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 	super.init(); 
=======
	 	System.out.println("Welcome to Server side");

	/*	    try { 
		    	 
		      servers = new ServerSocket(4444);
		    } catch (IOException e) {
		      System.out.println("Couldn't listen to port 4444");
		      System.exit(-1);}
	//	   Thread app = new Program();
		  	
	 	try {
		
		      System.out.print("Waiting for a client...");
		      fromclient= servers.accept(); 
		      System.out.println("Client connected");
		      on=false;
		    } 
				catch (IOException e) {
		      System.out.println("Can't accept");
		      System.exit(-1);
		    } */
	 	System.out.println("Init begin");
	 	
		 	super.init(); 
		 	

>>>>>>> b15b1d72dc239386461ef627ce5f800b6f96bd65
  		    	   				       
	}     
       
	String s;
<<<<<<< HEAD
	Socket sock;
=======

>>>>>>> b15b1d72dc239386461ef627ce5f800b6f96bd65
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */ 
 	
	  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {			 		   
		    System.out.print(request.getParameter("command")+"  ");
<<<<<<< HEAD
	  	 	s=request.getParameter("command");
	// 	 	DataOutputStream outToClient = new DataOutputStream(fromclient.getOutputStream());
	 	   	
//	    try {    
//			    out = new PrintWriter(fromclient.getOutputStream(),true);
//				//if(s != null) {
//				//	out.write(s+"\n");
//				//	out.flush();
//			 	//	System.out.print("Parameter sent");
	  	 		if( s != null)
		 		ArDroneDemo.setCommand(s);
//				}
//		} catch (IOException e) {
//			// TODO Auto-generated cach block
//			e.printStackTrace();
//			System.out.print("Error");
//		} 	
	}
=======
	     	s=request.getParameter("command");
	// 	DataOutputStream outToClient = new DataOutputStream(fromclient.getOutputStream());
	 //	   	
   /* try {    
		    out = new PrintWriter(fromclient.getOutputStream(),true);
			if(s != null) {
				out.write(s+"\n");
				out.flush();
		    	System.out.print("Parameter sent");
	  	 		if( s != null)
		 		//ArDroneDemo.setCommand(s);
	  	 			System.out.println(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated cach block
			e.printStackTrace();
			System.out.print("Error");
		} 	*/
		    s=s+"\n";
		    b = s.getBytes();
		 	try {
		 		DatagramSocket sock = new DatagramSocket();
				InetAddress addr =
						
				InetAddress.getLocalHost();
				if(s!=""){
				    pac = new DatagramPacket(b, b.length, addr, 4000);

				    sock.send(pac);//отправление пакета}

				    
				    System.out.println("Файл отправлен");
			}
			} catch (UnknownHostException | SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}


>>>>>>> b15b1d72dc239386461ef627ce5f800b6f96bd65
  
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
