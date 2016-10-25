package com.fsm.example.fakedns;

import java.net.ServerSocket;
import java.net.Socket;

public class DNSEntry {
	
	
	private String 	m_serverRoleName = "server";
	private String 	m_clientRoleName = "client";
	private String 	m_ip = null;
	private String	m_port = null;
	private ServerSocket	m_serverSocket = null;
	private Socket	m_socket = null;
	
	public DNSEntry()
	{
		
	}
	public DNSEntry(String s, String i, String p)
	{
		setServerRoleName(s);
		setIP(i);
		setPort(p);
	}
	
	public void setServerRoleName(String s) { m_serverRoleName = s;  }
	public String getServerRoleName() { return m_serverRoleName; }
	public void setClientRoleName(String c) { m_clientRoleName = c;  }
	public String getClientRoleName() { return m_clientRoleName; }
	public void setIP(String i) { m_ip = i; }
	public String getIP() { return m_ip; }
	public void setPort(String p) { m_port = p; }
	public String getPort() { return m_port; }
	public void setServerSocket(ServerSocket ss) { m_serverSocket = ss; }
	public ServerSocket getServerSocket() { return m_serverSocket; }
	public void setSocket(Socket s) { m_socket = s; }
	public Socket getSocket() { return m_socket; }
	
	
	public String toString()
	{
		String s = getClientRoleName() + "/" + getServerRoleName() + " - " + getIP() + ":" + getPort();
		return s;
	}

}
