// Generated from D:/Android/Projects/ANTLR4_LANGUAGE/antlr4_bash\BashParser.g4 by ANTLR 4.8
package tiiehenry.code.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BashParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface BashParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link BashParser#pipeline}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPipeline(BashParser.PipelineContext ctx);
	/**
	 * Visit a parse tree produced by {@link BashParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmd(BashParser.CmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link BashParser#exec_prefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExec_prefix(BashParser.Exec_prefixContext ctx);
	/**
	 * Visit a parse tree produced by {@link BashParser#assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(BashParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link BashParser#assign_rls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_rls(BashParser.Assign_rlsContext ctx);
	/**
	 * Visit a parse tree produced by {@link BashParser#exec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExec(BashParser.ExecContext ctx);
	/**
	 * Visit a parse tree produced by {@link BashParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(BashParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link BashParser#exec_suffix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExec_suffix(BashParser.Exec_suffixContext ctx);
	/**
	 * Visit a parse tree produced by {@link BashParser#redir}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRedir(BashParser.RedirContext ctx);
	/**
	 * Visit a parse tree produced by {@link BashParser#redir_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRedir_op(BashParser.Redir_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link BashParser#arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg(BashParser.ArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link BashParser#dquote_str}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDquote_str(BashParser.Dquote_strContext ctx);
	/**
	 * Visit a parse tree produced by {@link BashParser#squote_str}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSquote_str(BashParser.Squote_strContext ctx);
	/**
	 * Visit a parse tree produced by {@link BashParser#subst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubst(BashParser.SubstContext ctx);
	/**
	 * Visit a parse tree produced by {@link BashParser#cst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCst(BashParser.CstContext ctx);
	/**
	 * Visit a parse tree produced by {@link BashParser#lpst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLpst(BashParser.LpstContext ctx);
	/**
	 * Visit a parse tree produced by {@link BashParser#rpst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpst(BashParser.RpstContext ctx);
	/**
	 * Visit a parse tree produced by {@link BashParser#arith}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArith(BashParser.ArithContext ctx);
	/**
	 * Visit a parse tree produced by {@link BashParser#param_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam_exp(BashParser.Param_expContext ctx);
	/**
	 * Visit a parse tree produced by {@link BashParser#param_exp_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam_exp_op(BashParser.Param_exp_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link BashParser#grp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGrp(BashParser.GrpContext ctx);
	/**
	 * Visit a parse tree produced by {@link BashParser#paren_grp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParen_grp(BashParser.Paren_grpContext ctx);
	/**
	 * Visit a parse tree produced by {@link BashParser#pure_curly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPure_curly(BashParser.Pure_curlyContext ctx);
	/**
	 * Visit a parse tree produced by {@link BashParser#curly_grp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCurly_grp(BashParser.Curly_grpContext ctx);
}