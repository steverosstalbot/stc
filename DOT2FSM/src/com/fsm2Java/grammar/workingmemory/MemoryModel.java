package com.fsm2Java.grammar.workingmemory;

import java.util.*;import java.util.ArrayList;
import java.util.HashMap;

public class MemoryModel {
	
	protected ArrayList<Edge>		m_edges = new ArrayList<Edge>();
	protected ArrayList<Node>		m_nodes = new ArrayList<Node>();

	protected HashMap<String,Node> 	m_nodeMap = new HashMap<String,Node>();
	protected HashMap<String,Edge>	m_edgeMap = new HashMap<String,Edge>();
	protected Node					m_startNode = null;
	
	public MemoryModel()
	{
	}
	
	public void setStartingNode(Node n)
	{
		m_startNode = n;
	}
	public Node getStartingNode() { return m_startNode; }
	
	public void addEdge(Edge e)
	{
		//System.out.println("addEdge(" + e.getFrom() + ")");
		m_edges.add(e);
	}
	public void addEdge(String k, Edge v)
	{
		//System.out.println("addEdge(" + k + ")\n" + v);
		m_edgeMap.put(k, v);
	}
	public Edge getEdge(String name)
	{
		Edge e = m_edgeMap.get(name);
		return e;
	}
	public ArrayList<Edge>	getEdges() { return m_edges; }
	public void addNode(Node n)
	{
		//System.out.println("addNode(" + n.getName() + ")");
		if (getStartingNode() == null)
			setStartingNode(n);
		m_nodes.add(n);
	}
	public void addNode(String k, Node v)
	{
		//System.out.println("addNode(" + k + ")\n" + v);
		if (getStartingNode() == null)
			setStartingNode(v);
		m_nodeMap.put(k,v);
	}
	public ArrayList<Node> getNodes() { return m_nodes; }
	public Node getNode(String name)
	{
		Node n = m_nodeMap.get(name);
		return n;
	}
}
