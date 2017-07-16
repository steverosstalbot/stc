// Generated from DOTGrammar.g4 by ANTLR 4.4

package  com.fsm2Java.grammar.grammarhandler;
import com.fsm2Java.grammar.workingmemory.*;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DOTGrammarParser}.
 */
public interface DOTGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DOTGrammarParser#node_stmt}.
	 * @param ctx the parse tree
	 */
	void enterNode_stmt(@NotNull DOTGrammarParser.Node_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link DOTGrammarParser#node_stmt}.
	 * @param ctx the parse tree
	 */
	void exitNode_stmt(@NotNull DOTGrammarParser.Node_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link DOTGrammarParser#edge_stmt}.
	 * @param ctx the parse tree
	 */
	void enterEdge_stmt(@NotNull DOTGrammarParser.Edge_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link DOTGrammarParser#edge_stmt}.
	 * @param ctx the parse tree
	 */
	void exitEdge_stmt(@NotNull DOTGrammarParser.Edge_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link DOTGrammarParser#graph}.
	 * @param ctx the parse tree
	 */
	void enterGraph(@NotNull DOTGrammarParser.GraphContext ctx);
	/**
	 * Exit a parse tree produced by {@link DOTGrammarParser#graph}.
	 * @param ctx the parse tree
	 */
	void exitGraph(@NotNull DOTGrammarParser.GraphContext ctx);
	/**
	 * Enter a parse tree produced by {@link DOTGrammarParser#a_list}.
	 * @param ctx the parse tree
	 */
	void enterA_list(@NotNull DOTGrammarParser.A_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link DOTGrammarParser#a_list}.
	 * @param ctx the parse tree
	 */
	void exitA_list(@NotNull DOTGrammarParser.A_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link DOTGrammarParser#attr_list}.
	 * @param ctx the parse tree
	 */
	void enterAttr_list(@NotNull DOTGrammarParser.Attr_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link DOTGrammarParser#attr_list}.
	 * @param ctx the parse tree
	 */
	void exitAttr_list(@NotNull DOTGrammarParser.Attr_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link DOTGrammarParser#attr_stmt}.
	 * @param ctx the parse tree
	 */
	void enterAttr_stmt(@NotNull DOTGrammarParser.Attr_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link DOTGrammarParser#attr_stmt}.
	 * @param ctx the parse tree
	 */
	void exitAttr_stmt(@NotNull DOTGrammarParser.Attr_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link DOTGrammarParser#edgeRHS}.
	 * @param ctx the parse tree
	 */
	void enterEdgeRHS(@NotNull DOTGrammarParser.EdgeRHSContext ctx);
	/**
	 * Exit a parse tree produced by {@link DOTGrammarParser#edgeRHS}.
	 * @param ctx the parse tree
	 */
	void exitEdgeRHS(@NotNull DOTGrammarParser.EdgeRHSContext ctx);
	/**
	 * Enter a parse tree produced by {@link DOTGrammarParser#edgeop}.
	 * @param ctx the parse tree
	 */
	void enterEdgeop(@NotNull DOTGrammarParser.EdgeopContext ctx);
	/**
	 * Exit a parse tree produced by {@link DOTGrammarParser#edgeop}.
	 * @param ctx the parse tree
	 */
	void exitEdgeop(@NotNull DOTGrammarParser.EdgeopContext ctx);
	/**
	 * Enter a parse tree produced by {@link DOTGrammarParser#port}.
	 * @param ctx the parse tree
	 */
	void enterPort(@NotNull DOTGrammarParser.PortContext ctx);
	/**
	 * Exit a parse tree produced by {@link DOTGrammarParser#port}.
	 * @param ctx the parse tree
	 */
	void exitPort(@NotNull DOTGrammarParser.PortContext ctx);
	/**
	 * Enter a parse tree produced by {@link DOTGrammarParser#subgraph}.
	 * @param ctx the parse tree
	 */
	void enterSubgraph(@NotNull DOTGrammarParser.SubgraphContext ctx);
	/**
	 * Exit a parse tree produced by {@link DOTGrammarParser#subgraph}.
	 * @param ctx the parse tree
	 */
	void exitSubgraph(@NotNull DOTGrammarParser.SubgraphContext ctx);
	/**
	 * Enter a parse tree produced by {@link DOTGrammarParser#stmt_list}.
	 * @param ctx the parse tree
	 */
	void enterStmt_list(@NotNull DOTGrammarParser.Stmt_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link DOTGrammarParser#stmt_list}.
	 * @param ctx the parse tree
	 */
	void exitStmt_list(@NotNull DOTGrammarParser.Stmt_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link DOTGrammarParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(@NotNull DOTGrammarParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link DOTGrammarParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(@NotNull DOTGrammarParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by {@link DOTGrammarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(@NotNull DOTGrammarParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link DOTGrammarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(@NotNull DOTGrammarParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link DOTGrammarParser#node_id}.
	 * @param ctx the parse tree
	 */
	void enterNode_id(@NotNull DOTGrammarParser.Node_idContext ctx);
	/**
	 * Exit a parse tree produced by {@link DOTGrammarParser#node_id}.
	 * @param ctx the parse tree
	 */
	void exitNode_id(@NotNull DOTGrammarParser.Node_idContext ctx);
}