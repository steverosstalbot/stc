package  com.fsm2Java.grammar.grammarhandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import com.fsm2Java.grammar.workingmemory.*;

public class MyDOT2FSM {
	
	private int				m_dotProcessing = 1;
	private int				m_easyFSMProcessing = 0;
	private String			m_thisRole = "service";
	private String 			m_inboundDoc = "INBOUND";
	private String 			m_outboundDoc = "OUTBOUND";
	private String			m_packagePrefix = "com.thoughtworks.org";
	private ArrayList<Node>	m_traversed = null;
	private static int bootstrap = 0;

	
	public MyDOT2FSM ()
	{
		
	}
	
	//
	// program fsm-input-file [-dot|-easyFSM] [thisrole] [package-name]
	//
    public static void main( String[] args) throws Exception 
    {
    	String dotProcessingFlag = "-dot";
    	String easyFSMProcessingFlag = "-easyFSM";
    	String helpFlag = "-h";
    	MyDOT2FSM fsm = new MyDOT2FSM();
    	fsm.setInboundDoc(fsm.readFileIntoString(args[0]));
    	if (args.length >= 1)
    	{
    		fsm.m_dotProcessing = (args[1].compareTo(dotProcessingFlag));
    		fsm.m_easyFSMProcessing = (args[1].compareTo(easyFSMProcessingFlag));
    		if (args[1].compareTo(helpFlag) == 0)
    		{
    			System.out.println("prog fsm-input-file [-dot|-easyFSM] [thisrole] [package-name]");
    			return;
    		}
    	}
    	if (args.length >= 2)
    		fsm.m_thisRole = args[2];
    	if (args.length >= 4)
    		fsm.setPackagePrefix(args[3]);

    	
    	String output = fsm.translateRaw(fsm.parseDOT());
    }
    
    // Accessor methods
	public String getInboundDoc() { return m_inboundDoc; }
	public String getOutboundDoc() { return m_outboundDoc; }
    public String getPackagePrefix() { return m_packagePrefix; }   
	public void setInboundDoc(String ibd)
	{
		m_inboundDoc = ibd;
	}
	public void setOutboundDoc(String obd)
	{
		m_outboundDoc = obd;
	}
    public void setPackagePrefix(String p)
    {
    	m_packagePrefix = p;
    }
    //
    // Parses a filter
    //
    private ParseTree parseDOT()
    {
    	ParseTree	tree = null;
    	try
    	{
			//System.out.println("Running for dot file <" + getInboundDoc() + ">");
			StringReader stream = new StringReader(getInboundDoc());				// Stream it
			
			ANTLRInputStream input = new ANTLRInputStream(stream);					// ANTLRStream it
			DOTGrammarLexer lexer = new DOTGrammarLexer(input);						// Lex it
			CommonTokenStream tokens = new CommonTokenStream(lexer);				// Tokenise it
			//
			// Parse each filter in turn using the antlr generated parser
			// Tidy this bit up so that we don't swap from Hash to keySet
			//
			DOTGrammarParser parser = new DOTGrammarParser(tokens);
			tree = parser.graph(); 													// begin parsing at rule 'ruleset'			
			return tree;
    	}
    	catch (Exception e)
    	{
    		System.out.println("Exception thrown in parse method");
    		e.printStackTrace();
    	}
    	return tree;
    }
    
 
	// Translates filter to JSON PATH
    public String translateRaw(ParseTree tree)
    {
    	String output = "";
    	MyDOTGrammarVisitor vistor = new MyDOTGrammarVisitor();
        output = vistor.visit(tree);
        fixGraph();
        if (m_dotProcessing == 0)
        	transposeToDOT();
        if (m_easyFSMProcessing == 0)
        	transposeToEasyFSM();
        return output;
    }
    
    public void fixGraph()
    {
    	connectGraph();
    	normaliseGraph();
    }
    
    //
    // Normalising the names so they make more sense.
    //
    // An edge:
    //		"86"->"87" [label="filtersvc!filterContract(usercontext, filters, contractdetails)"];
    //			       [label=""
    // A node:
    //		"87" [label="filtersvc!filterContract(usercontext, filters, contractdetails)_DONE "];
    //
    // a!b r sends message to a called b
    // a?b r receives message from a called b
    // -/- disconnect
    // a?? r accepts session request from a
    // a!! r requests connection to a
    /*
 	!    send (the "normal" send, over an existing connection)
	?    receive (the "normal" receive, over an existing connection)
	!!   connect (make a new connection, client side)
	??   accept (make a new connection, server side)
	-/-  disconnect (close a connection, symmetric action for client/server)
	(!!) wrapClient (wrap a connection, e.g. TLS wrap TCP, client side)
	(??) wrapServer (wrap a connection, e.g. TLS wrap TCP, client side)
	*/
    //
    // where r is this role
    
    public void normaliseGraph()
    {
    	// Specific to DOT notation
    	String arrowHead = "->";
    	String nodeEnd = ": \" ]";
    	String edgeEnd = "\" ]";
    	String nodeStart = "[label=\"";
    	String edgeStart = "[label=\"";
    	String sendOp = "!";
    	String receiveOp = "?";
    	String acceptOp = "??";
    	String connectOp = "!!";
    	String disconnectOp = "-/-";
    	
    	//
    	// We need to manage loops so we use a memory array to mark off which we have changed
    	// so we don't do them twice
    	//
    	m_traversed = new ArrayList<Node>();
    	Node n = WMSingleton.getWorkingMemory().getStartingNode();
    	if (n == null)
    		return;
    	n.setName("START_" + m_thisRole);
    	m_traversed.add(n);
    	
    	ArrayList<Edge>  edges = WMSingleton.getWorkingMemory().getEdges();
    	for (int i=0; (i < edges.size()); i++)
    	{
    		Edge e = edges.get(i);
    		n = e.getNextState();
    		String newName = e.getName();
    		if (newName.contains(acceptOp))
    		{
    			String role = newName.substring(edgeStart.length(), newName.indexOf(acceptOp));
    			newName = "__accept_connection__from_" + role;
    		}
    		else if (newName.contains(connectOp))
    		{
    			String role = newName.substring(edgeStart.length(), newName.indexOf(connectOp));
    			newName = "__request_connection__to_" + role;
    		} 
    		else if (newName.contains(disconnectOp))
    		{
    			String role = newName.substring(edgeStart.length(), newName.indexOf(disconnectOp));
    			newName = "__request_disconnection__from_" + role;
    		}
    		else if (newName.contains(sendOp))
    		{
    			String role = newName.substring(edgeStart.length(), newName.indexOf(sendOp));
    			String action = newName.substring(newName.indexOf(sendOp) + sendOp.length(), newName.length()-edgeEnd.length()+1);
    			newName = "__sendMessage(" + action + ")__to_" + role;
    		}
    		else if (newName.contains(receiveOp))
    		{
    			String role = newName.substring(edgeStart.length(), newName.indexOf(receiveOp));
    			String action = newName.substring(newName.indexOf(receiveOp) + receiveOp.length(), newName.length()-edgeEnd.length()+1);
    			newName = "__receiveMessage(" + action + ")__from_" + role;
    		} 
    		e.setActionName(newName);
   			String nodeName = e.getActionName();
    		nodeName = nodeName + "_DONE";
    		if (!m_traversed.contains(n))
    		{
    			m_traversed.add(n);
    			n.setName(nodeName);
    		}
    	}
    }
    
    // Make sure that the graph is connected correctly by relying on the edge definitions and their 
    // notion of connection
    public void connectGraph()
    {
    	// Specific to DOT notation
    	String arrowHead = "->";
    	
    	Edge e = null;
    	ArrayList<Edge>  edges = WMSingleton.getWorkingMemory().getEdges();
    	for (int i = 0; (i < edges.size()); i++)
    	{
    		e = edges.get(i);
    		Node from = WMSingleton.getWorkingMemory().getNode(e.getFromNormal());
    		Node to = WMSingleton.getWorkingMemory().getNode(e.getToNormal().replaceAll(arrowHead,""));
    		e.addPredessor(from);
    		from.addSuccessor(e);
    		e.addSuccessor(to);
    		to.addPredessor(e);
    	}
    	for (int i = 0; (i < edges.size()); i++)
    	{
    		e = edges.get(i);
    		e.setAttributes();
    	}
    }
    
    //
    //	<STATE id = "STATE_CONNECTION_REQUEST_TO_filtersvc_OBTAINED">
    //		<MESSAGE id = "getSuppliers_FROM_requestor_WITH_uuid" action = "com.thoughtworks.org.receiveMessage" nextState = "STATE_RECEIVED_getSuppliers_FROM_requestor" />
    //		<MESSAGE id = "getContracts_FROM_requestor_WITH_uuid" action = "com.thoughtworks.org.receiveMessage" nextState = "STATE_RECEIVED_getContracts_FROM_requestor" />
    //	</STATE>
    //  
    public void transposeToEasyFSM()
    {
    	ArrayList<Node> nodes = WMSingleton.getWorkingMemory().getNodes();
    	String easyFSMheader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<FSM>\n";
    	String easyFSMtrailer = "\n</FSM>";
    	System.out.println(easyFSMheader);
    	for (int i=0; (i < nodes.size()); i++)
    	{
    		Node n = nodes.get(i);
    		System.out.println("<STATE id = \"" + n.getName() + "\">");
    		for (int j=0; (j < n.getSuccessors().size()); j++)
    		{
    			Edge e = n.getSuccessors().get(j);
    			System.out.println("\t<MESSAGE id = \"" + getMethodFromAction(e.getActionName()) + getRoleFromAction(e.getActionName())+ "\" action = \"" + getPackagePrefix() + "." + getMethodNameFromMethod(getMethodFromAction(e.getActionName()))  + "\" nextState = \"" +
    					e.getNextState().getName() + "\"/>");
    		}
    		System.out.println("</STATE>");
    	}
    	System.out.println(easyFSMtrailer);
    }
    
    //
    //	Node:
    //		"70" [label="START_authorisationSvc"];
    //	Edge:
    //		"70"->"72" [label="__accept_connection__from_requestor"];
    //
    public void transposeToDOT()
    {

       	String DOTheader = "digraph G {\ncompound = true;\n";
       	String DOTtrailer = "}\n";
        Node n = WMSingleton.getWorkingMemory().getStartingNode();
        m_traversed = new ArrayList<Node>();
        System.out.println(DOTheader);
        printDOT(n);
        System.out.println(DOTtrailer);
    }
    
    public void printDOT(Node n)
    {
    	if (n == null)
    		return;
    	if (m_traversed.contains(n))
    		return;
    	System.out.println(n.getId() + " [label=\"" +  n.getName() + "\"];");
    	m_traversed.add(n);
    	for (int i=0; (i < n.getSuccessors().size()); i++)
   		{
    		Edge e = n.getSuccessors().get(i);
    		System.out.println(e.getFromNormal() + e.getToNormal() + " [label=\"" + e.getActionName() + "\"];");
    		Node nextNode = e.getNextState();
    		printDOT(nextNode);
    	}
    }
    
    //
    // Utility methods 
    //  
    
    //
    // __action__[to/from]_role
    //
    public String getMethodFromAction(String s)
    {
    	String sep = "__";
    	String rtn = s;
    	int start = s.indexOf(sep);
    	int end = s.lastIndexOf(sep);
    	if (start < end)
    	{
    		rtn = s.substring(start+sep.length(), end);
    	}
    	return rtn;
    }
    
    public String getMethodNameFromMethod(String s)
    {
    	String marker = "(";
    	String rtn = s;
    	int startMarker = s.indexOf(marker);
    	if (startMarker > 0)
    		rtn = s.substring(0,startMarker);
    	return rtn;
    		
    }
    
    public String getRoleFromAction(String s)
    {
    	String toSep = "__to_";
    	String fromSep = "__from_";
    	String rtn = null;
    	int toStart = s.indexOf(toSep);
    	int fromStart = s.lastIndexOf(fromSep);
    	if (toStart > 0)
    	{
    		rtn = toSep + s.substring(toStart + toSep.length());
    	}
    	else if (fromStart > 0)
    	{
    		rtn = fromSep + s.substring(fromStart + fromSep.length());
    	}
    	return rtn;
    }
    
    
	private String readFileIntoString( String file ) throws IOException 
	{
	    BufferedReader reader = new BufferedReader( new FileReader (file));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");
	    try 
	    {
	        while( ( line = reader.readLine() ) != null ) 
	        {
	            stringBuilder.append( line );
	            stringBuilder.append( ls );
	        }
	        return stringBuilder.toString();
	    } 
	    finally 
	    {
	        reader.close();
	    }
	}
}
