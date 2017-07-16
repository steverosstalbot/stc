//
// The general semantics of an Edge or Arc are some form of call or invocation that moves an FSM from
// one state to another state.
//
// For scribble derivative the label that forms part of the Edge has the call.
// The from and to parts are the states that connect the Edge
//

package com.fsm2Java.grammar.workingmemory;

import java.util.ArrayList;

public class Edge {

	protected String				m_id = null;
	protected String				m_messageId = "MESSAGE_ID";
	protected String				m_actionName = "action";
	protected String				m_nextStateName = "NEXT_STATE";
	protected Node					m_nextState = null;
	protected String 				m_name = "Edge";
	protected String 				m_to = null;
	protected String 				m_from = null;
	protected Node					m_predessor = null;
	protected String				m_functionName = null;
	protected ArrayList<Node>		m_successors = new ArrayList<Node>();
	protected String				m_arrowhead = "->";
	
	public Edge()
	{
		
	}
	public Edge(String f, String t, String n)
	{
		setName(n);
		setFrom(f);
		setTo(t);
	}
	
	//
	// Normally in the form "Number"->"Number"
	//
	public void setAttributes()
	{
		if (getId() != null)
		{
			if (getActionName().compareTo("action") != 0)
			{
				String nextState = getActionName() + "_COMPLETED";
				if (getNextState() != null)
				{
					getNextState().setStateName(nextState);
					setNextStateName(nextState);
				}
			}
		}
	}
	public void setId(String id) // OK
	{ 
		m_id = id; 
		WMSingleton.getWorkingMemory().addEdge(m_id,this);
		WMSingleton.getWorkingMemory().addEdge(this);
	}

	public String getId() { return m_id; } // OK
	public String getFromName()
	{
		String rtn = "";
		if (getId() != null)
			rtn = getId().substring(0, getId().indexOf(m_arrowhead));
		return rtn;
	}
	public String getToName()
	{
		String rtn = "";
		if (getId() != null)
			rtn = getId().substring(getId().indexOf(m_arrowhead)+m_arrowhead.length());
		return rtn;
	}
	public void setMessageId(String m)
	{
		m_messageId = m;
	}
	public String getMessageId() 
	{ 
		return m_messageId; 
	}
	public void setActionName(String a)
	{
		m_actionName = a;
	}
	public String getActionName() { return m_actionName; }
	public void setNextStateName(String n)
	{
		m_nextStateName = n;
	}
	public String getNextStateName() { return m_nextStateName; }
	public void setNextState(Node n)
	{
		m_nextState = n;
	}
	public Node getNextState() { return m_nextState; }
	
	public void setName(String n)
	{
		m_name = n;
	}
	public String getName() 
	{
		String idPart = getToName();
		idPart = idPart.replaceAll("\"\"", "_");
		idPart = idPart.replaceAll("\"","");
		String namePart = "_" + idPart + "(";
		String rtn = m_name.replace("(", namePart);
		//System.out.println("getName()");
		//System.out.println("    <" + idPart + ">");
		//System.out.println("    <" + namePart + ">");
		//System.out.println("    <" + rtn + ">");
		
		return m_name; 
	}
	public void setTo(String t)
	{
		m_to = t;
	}
	public String getToNormal() { return m_to; }
	public void setFrom(String f)
	{
		m_from = f;
	}
	public String getFromNormal() { return m_from; }
	
	public void addPredessor(Node n)
	{
		if (n == null)
			return;
		m_predessor = n;
	}
	public Node getPredessor()
	{
		return m_predessor;
	}
	public void addSuccessor(Node n)
	{
		if (n == null)
			return;
		if (m_successors.contains(n))
			return;
		m_successors.add(n);
		setNextState(n);
	}
	public ArrayList<Node> getSuccessors()
	{
		return m_successors;
	}

	public String toString()
	{
		String s = "EDGE:\n";
		s = s + "	getId(" + getId() + ")\n" +
				"	getFromName(" + getFromName() + ")\n" +
				"	getToName(" + getToName() + ")\n" +
				"	getMessageId(" + getMessageId() + ")\n" +
				"	getActionName(" + getActionName() + ")\n" +
				"	getNextStateName(" + getNextStateName() + ")\n" +
				"	getName(" + getName() + ")\n" +
				"	getFromNormal(" + getFromNormal() + ")\n" +
				"	getToNormal(" + getToNormal() + ")";
		return s;
	}
}

