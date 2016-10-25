package com.fsm.example;
/**
 * This is a simple version of the easyFSM example without any action overriding. So it is
 * as simple as it can be.
 **/
import Action.FSMAction;
import States.FSMStateAction;
import FSM.FSM;

import java.io.*;
import java.util.logging.*;

import org.xml.sax.*;

import javax.xml.parsers.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.fsm.example.fakedns.*;

public class Program 
{

    private String m_configFileName = "/Users/stalbot/Documents/workspace/FSM-Example/resources/config.xml";
    private String m_machineName = "127.0.0.1";
    private String m_portNumber = "9091";
    
    public Program()
    {
    	
    }
    public Program(String config)
    {
    	setConfigFileName(config);
    }
    public Program(String config, String machine, String port)
    {
    	setConfigFileName(config);
    	setMachineName(machine);
    	setPortNumber(port);
    }
    
    public void setConfigFileName(String f) { m_configFileName = f; }
    public String getConfigFileName() { return m_configFileName; }
	public void setMachineName(String m) { m_machineName = m; }
	public String getMachineName() { return m_machineName; }
	public void setPortNumber(String p) { m_portNumber = p; }
	public String getPortNumber() { return m_portNumber; }
	
    public  void runFSM() 
    {
        try 
        {
        	System.out.println("Open config file " + getConfigFileName());
        	System.out.println("Class: " + this.getClass().getName());
        	System.out.println("ClassLoader: " + this.getClass().getClassLoader().getClass().getName());
        	System.out.println("resource stream: " + this.getClass().getClassLoader().getResourceAsStream(getConfigFileName()));
        	InputStream inputS = null;
    		try 
    		{

    			FileInputStream m_fileReader = new FileInputStream(getConfigFileName());
    			inputS = m_fileReader;
    			if (m_fileReader != null)
    				System.out.println("Got file " + getConfigFileName());
    			else
    				System.out.println("Not got file!!!" + getConfigFileName());
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        	
        	if (inputS == null)
        	{
        		System.out.println("Input stream is null!!!!");
        		return;
        	}
        	
        	//
        	// I think the @Override enables various actions within the FSM to be overridden at compile time by 
        	// injecting logic into the overridden methods. This is where I would bind the business logic to the 
        	// state machine and use introspection to do it.
        	//
            FSM f = new FSM(inputS, null);
            
            f.setAction("receiveMessage(getSuppliers(uuid))", new FSMAction() {
                @Override
                public boolean action(String curState, String message, String nextState, Object args) {
                    System.out.println("Customized receiveMessage(getSuppliers(uuid))!");
                    return true;
                }
            });
        	
            String command = "";
            String message = "";
            String currentState = f.getCurrentState();
            do
            {
                
                String nextStates[] = null;
            	currentState = f.getCurrentState();
            	//nextStates = f.getValidActions();
            	nextStates=f.getValidCommands();
            	System.out.println("Current State: " + f.getCurrentState() + "\n");
            	if (nextStates.length > 1)
            	{
            		Boolean receiveChoices = true;
            		for (int i=0; (i < nextStates.length); i++)
            		{
            			receiveChoices = receiveChoices && nextStates[i].trim().startsWith("receiveMessage");
            			message = message + "<" + nextStates[i].trim() + ">";
            		}
            		/*
            		if (!receiveChoices)
            		{
            		*/
            			System.out.println("========================================");
            			System.out.println("Valid next commands from here are: ");
            			System.out.print("    ");
            			for (int i=0; (i < nextStates.length); i++)
            			{
            				System.out.print("	<" + nextStates[i].trim() + ">\n");
            			}
            			System.out.println();
            			System.out.println("========================================");
            			System.out.print("Command: ");
            		/*} else {
            			ServerSocket listener = null;
            			try {
            				Integer port = new Integer(getPortNumber());
            				listener = new ServerSocket(port);
            			} catch (Exception e) {
            	        	e.printStackTrace();
            	    	}
            			try 
            			{
            		        Socket socket = listener.accept();
            		        try {
            		        	PrintWriter out =
            		                       new PrintWriter(socket.getOutputStream(), true);
            		                   out.println(message);
            		        } finally {
            		            socket.close();
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
            		}*/
                	try {
                		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                		command = bufferRead.readLine();
                	}
                	catch(IOException e)
                	{
                		e.printStackTrace();
                	}	

            	} else {
            		command = nextStates[0].trim();
            	}
            		
            	System.out.println("Executing " + command);
            	if (command.compareTo("STOP") == 0)
            		return;
            	Thread.sleep(2000);
                if (f.ProcessFSM(command) == null)
                {
                	System.out.println("*** ERROR ***");
                	System.out.println("    " + command + " has no matching state transition");
                }	
            } while (command.compareTo("STOP") != 0);

        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public static void main(String[] args) {
        try {
        	Program p = null;
        	if (args.length >= 1)
        		 p = new Program(args[0]);
        	else 
        		 p = new Program();
            p.runFSM();
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        System.out.println("*** STOPPED ***");
    }
}