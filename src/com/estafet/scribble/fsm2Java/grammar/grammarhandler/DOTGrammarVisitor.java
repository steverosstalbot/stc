// Generated from DOTGrammar.g4 by ANTLR 4.4

package  com.fsm2Java.grammar.grammarhandler;
import com.fsm2Java.grammar.workingmemory.*;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DOTGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DOTGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link DOTGrammarParser#node_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNode_stmt(@NotNull DOTGrammarParser.Node_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link DOTGrammarParser#edge_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEdge_stmt(@NotNull DOTGrammarParser.Edge_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link DOTGrammarParser#graph}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGraph(@NotNull DOTGrammarParser.GraphContext ctx);
	/**
	 * Visit a parse tree produced by {@link DOTGrammarParser#a_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitA_list(@NotNull DOTGrammarParser.A_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link DOTGrammarParser#attr_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttr_list(@NotNull DOTGrammarParser.Attr_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link DOTGrammarParser#attr_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttr_stmt(@NotNull DOTGrammarParser.Attr_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link DOTGrammarParser#edgeRHS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEdgeRHS(@NotNull DOTGrammarParser.EdgeRHSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DOTGrammarParser#edgeop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEdgeop(@NotNull DOTGrammarParser.EdgeopContext ctx);
	/**
	 * Visit a parse tree produced by {@link DOTGrammarParser#port}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPort(@NotNull DOTGrammarParser.PortContext ctx);
	/**
	 * Visit a parse tree produced by {@link DOTGrammarParser#subgraph}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubgraph(@NotNull DOTGrammarParser.SubgraphContext ctx);
	/**
	 * Visit a parse tree produced by {@link DOTGrammarParser#stmt_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt_list(@NotNull DOTGrammarParser.Stmt_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link DOTGrammarParser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(@NotNull DOTGrammarParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by {@link DOTGrammarParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(@NotNull DOTGrammarParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link DOTGrammarParser#node_id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNode_id(@NotNull DOTGrammarParser.Node_idContext ctx);
}