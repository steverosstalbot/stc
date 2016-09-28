package  com.fsm2Java.grammar.grammarhandler;

import org.antlr.v4.runtime.misc.NotNull;

import com.fsm2Java.grammar.workingmemory.*;


public class MyDOTGrammarVisitor extends DOTGrammarBaseVisitor<String>
{
	
	public String visitGraph(@NotNull DOTGrammarParser.GraphContext ctx)
	{
		for (int i=0; (i < ctx.getChildCount()); i++)
		{
			visit(ctx.getChild(i));
		}
		return "Graph";
	}
	
	public String visitEdge_stmt(@NotNull DOTGrammarParser.Edge_stmtContext ctx)
	{
		String from = ctx.getChild(0).getText();
		String to = ctx.getChild(1).getText();
		String name = ctx.getChild(2).getText();
		Edge edge = new Edge(from,to,name);
		edge.setId(to + from);
		edge.setActionName(name);

		return "Edge";
	}

	public String visitNode_stmt(@NotNull DOTGrammarParser.Node_stmtContext ctx) 
	{
		String id = ctx.getChild(0).getText();
		String label = ctx.getChild(1).getText();
		Node node = new Node(id, label);
		node.setId(id);
		node.setStateName(id);
		
		return "Node";
	}
}
