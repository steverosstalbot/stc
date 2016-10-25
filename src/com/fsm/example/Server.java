package com.fsm.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;


public class Server {

	public String	m_machineName = "127.0.0.1";
	public String	m_portNumber = "9091";
	
	public Server(String m, String p)
	{
		setMachineName(m);
		setPortNumber(p);
	}
	public Server()
	{
		
	}
	public void setMachineName(String m) { m_machineName = m; }
	public String getMachineName() { return m_machineName; }
	public void setPortNumber(String p) { m_portNumber = p; }
	public String getPortNumber() { return m_portNumber; }
	
	public void go()
	{
		ServerSocket listener = null;
		try {
			Integer port = new Integer(getPortNumber());
			listener = new ServerSocket(port);
		} catch (Exception e) {
        	e.printStackTrace();
    	}
		try 
		{
			while (true) 
			{
	        	Socket socket = listener.accept();
	            try {
	                   PrintWriter out =
	                       new PrintWriter(socket.getOutputStream(), true);
	                   out.println("Hello World");
	                   Thread.sleep(2000);
	            } finally {
	                    socket.close();
	            }
	        }
		} catch (Exception e) {
        	e.printStackTrace();
	    } finally {
	    	try {
	            listener.close();
	    	} catch (Exception e) {
	        	e.printStackTrace();
	    	}
	    }
	}
	
    public static void main(String[] args) {
        try {
        	Server s = null;
        	if (args.length >= 2)
        		 s = new Server(args[0], args[1]);
        	else 
        		 s = new Server();
        	System.out.println("Server running on " + s.getMachineName() + ":" + s.getPortNumber());
            s.go();
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        System.out.println("*** STOPPED ***");
    }
}
