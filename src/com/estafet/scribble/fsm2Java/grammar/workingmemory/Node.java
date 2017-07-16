//
// A Node in the sense of a scribble based FSM represents one or more functions that maybe called
// Thus they serve to document choice
//
package com.fsm2Java.grammar.workingmemory;

import java.util.ArrayList;

public class Node {
	
	protected String				m_id = null;
	protected String				m_stateName = "START";
	protected String 				m_name = "Node";
	protected String				m_description = "Description";
	protected ArrayList<Edge>		m_predessors = new ArrayList<Edge>();
	protected ArrayList<Edge>		m_successors = new ArrayList<Edge>();
	
	public Node()
	{
	}
	public Node(String n)
	{
		setName(n);
	}
	public Node(String n, String d)
	{
		setName(n);
		setDescription(d);
	}
	public void setId(String id) // OK
	{ 
		m_id = id; 
		WMSingleton.getWorkingMemory().addNode(m_id,this);
		WMSingleton.getWorkingMemory().addNode(this);
	}
	public String getId() { return m_id; } // OK
	
	public void setStateName(String s)
	{
		m_stateName = s;
	}
	public String getStateName() { return m_stateName; }
	
	public void setName(String n) // OK
	{
		m_name = n;
	}
	public String getName() 
	{ 
		String idPart = getId();
		idPart = idPart.replaceAll("\"","");
		String newName = m_name + "_" + idPart;
		return newName; 
	} // OK
	public void setDescription(String s)
	{
		m_description = s;
	}
	public String getDescription() { return m_description; }
	public void addSuccessor(Edge e)
	{
		//System.out.println("Node(" + getId() + ").addSuccessor(" + e.getId() + ")");

		if (m_successors.contains(e))
			return;
		m_successors.add(e);
	}
	public ArrayList<Edge> getSuccessors()
	{
		return m_successors;
	}
	public void addPredessor(Edge e)
	{
		//System.out.println("Node(" + getId() + ").addPredessor(" + e.getId() + ")");

		if (m_predessors.contains(e))
			return;
		m_predessors.add(e);
	}
	public ArrayList<Edge> getPredessors()
	{
		return m_predessors;
	}

	public String toString()
	{
		String s = "NODE:\n" + 
				"	getId(" + getId() + ")\n" + 
				"	getName(" + getName() + ")\n" +
				"	getStateName(" + getStateName() + ")\n" +
				"	getDescription(" + getDescription() + ")" +
				"	Num Predessors(" + getPredessors().size() +  ")\n" +
				"	Num Successors(" + getSuccessors().size() + ")";

		return s;
	}
}
