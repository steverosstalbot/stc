package com.fsm.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;


public class Client {

	public String	m_machineName = "127.0.0.1";
	public String	m_portNumber = "9091";
	
	public Client(String m, String p)
	{
		setMachineName(m);
		setPortNumber(p);
	}
	public Client()
	{
		
	}
	public void setMachineName(String m) { m_machineName = m; }
	public String getMachineName() { return m_machineName; }
	public void setPortNumber(String p) { m_portNumber = p; }
	public String getPortNumber() { return m_portNumber; }
	
	public void go()
	{
		//System.out.println("Valid command are: ");
		//
		// Get the valid commands for this role
		//
		String command = "";
    	//System.out.print("Command: ");
    	try {
    		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
    		//command = bufferRead.readLine();
            String serverAddress = JOptionPane.showInputDialog(
                    "Enter IP Address of a machine that is\n" +
                    "running the service on port 9091:");
                Socket s = new Socket(serverAddress, 9091);
                BufferedReader input =
                    new BufferedReader(new InputStreamReader(s.getInputStream()));
                String answer = input.readLine();
                JOptionPane.showMessageDialog(null, answer);
                System.exit(0);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
	}
	
    public static void main(String[] args) {
        try {
        	Client c = null;
        	if (args.length >= 2)
        		 c = new Client(args[0], args[1]);
        	else 
        		 c = new Client();
            c.go();
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        System.out.println("*** STOPPED ***");
    }
}
