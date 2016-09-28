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
import java.util.logging.Level;
import org.xml.sax.*;
import javax.xml.parsers.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Program 
{

    private String _configFileName = "/Users/stalbot/Documents/workspace/FSM-Example/resources/config.xml";

    public Program()
    {
    	
    }
    public Program(String config)
    {
    	_configFileName = config;
    }
    public  void testFSM() 
    {
        try 
        {
        	System.out.println("Open config file " + _configFileName);
        	System.out.println("Class: " + this.getClass().getName());
        	System.out.println("ClassLoader: " + this.getClass().getClassLoader().getClass().getName());
        	System.out.println("resource stream: " + this.getClass().getClassLoader().getResourceAsStream(_configFileName));
        	InputStream inputS = null;
    		try 
    		{

    			FileInputStream m_fileReader = new FileInputStream(_configFileName);
    			inputS = m_fileReader;
    			if (m_fileReader != null)
    				System.out.println("Got file " + _configFileName);
    			else
    				System.out.println("Not got file!!!" + _configFileName);
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
        	
            String command = "";
            String currentState = f.getCurrentState();
            do
            {
                
                String nextStates[] = null;
            	currentState = f.getCurrentState();
            	//nextStates = f.getValidActions();
            	nextStates=f.getValidCommands();
            	System.out.println("========================================");
            	System.out.println("Current State: " + f.getCurrentState() + "\n");
            	System.out.println("Valid next commands from here are:");
            	System.out.print("    ");
            	for (int i=0; (i < nextStates.length); i++)
            		System.out.print("	<" + nextStates[i].trim() + ">\n");
            	System.out.println();
            	System.out.println("========================================");
            	
            	System.out.print("Command: ");
            	try
            	{
            	    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            	    command = bufferRead.readLine();
                	if (f.ProcessFSM(command) == null)
                	{
                		System.out.println("*** ERROR ***");
                		System.out.println("    " + command + " has no matching state transition");
                	}

            	}
            	catch(IOException e)
            	{
            		e.printStackTrace();
            	}	

            } while (currentState.compareTo("STOP") != 0);

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
            p.testFSM();
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        System.out.println("*** STOPPED ***");
    }
}