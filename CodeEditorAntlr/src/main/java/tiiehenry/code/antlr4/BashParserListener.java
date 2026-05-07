// Generated from D:/Android/Projects/ANTLR4_LANGUAGE/antlr4_bash\BashParser.g4 by ANTLR 4.8
package tiiehenry.code.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BashParser}.
 */
public interface BashParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link BashParser#pipeline}.
	 * @param ctx the parse tree
	 */
	void enterPipeline(BashParser.PipelineContext ctx);
	/**
	 * Exit a parse tree produced by {@link BashParser#pipeline}.
	 * @param ctx the parse tree
	 */
	void exitPipeline(BashParser.PipelineContext ctx);
	/**
	 * Enter a parse tree produced by {@link BashParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(BashParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link BashParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(BashParser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link BashParser#exec_prefix}.
	 * @param ctx the parse tree
	 */
	void enterExec_prefix(BashParser.Exec_prefixContext ctx);
	/**
	 * Exit a parse tree produced by {@link BashParser#exec_prefix}.
	 * @param ctx the parse tree
	 */
	void exitExec_prefix(BashParser.Exec_prefixContext ctx);
	/**
	 * Enter a parse tree produced by {@link BashParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssign(BashParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link BashParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssign(BashParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link BashParser#assign_rls}.
	 * @param ctx the parse tree
	 */
	void enterAssign_rls(BashParser.Assign_rlsContext ctx);
	/**
	 * Exit a parse tree produced by {@link BashParser#assign_rls}.
	 * @param ctx the parse tree
	 */
	void exitAssign_rls(BashParser.Assign_rlsContext ctx);
	/**
	 * Enter a parse tree produced by {@link BashParser#exec}.
	 * @param ctx the parse tree
	 */
	void enterExec(BashParser.ExecContext ctx);
	/**
	 * Exit a parse tree produced by {@link BashParser#exec}.
	 * @param ctx the parse tree
	 */
	void exitExec(BashParser.ExecContext ctx);
	/**
	 * Enter a parse tree produced by {@link BashParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(BashParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link BashParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(BashParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link BashParser#exec_suffix}.
	 * @param ctx the parse tree
	 */
	void enterExec_suffix(BashParser.Exec_suffixContext ctx);
	/**
	 * Exit a parse tree produced by {@link BashParser#exec_suffix}.
	 * @param ctx the parse tree
	 */
	void exitExec_suffix(BashParser.Exec_suffixContext ctx);
	/**
	 * Enter a parse tree produced by {@link BashParser#redir}.
	 * @param ctx the parse tree
	 */
	void enterRedir(BashParser.RedirContext ctx);
	/**
	 * Exit a parse tree produced by {@link BashParser#redir}.
	 * @param ctx the parse tree
	 */
	void exitRedir(BashParser.RedirContext ctx);
	/**
	 * Enter a parse tree produced by {@link BashParser#redir_op}.
	 * @param ctx the parse tree
	 */
	void enterRedir_op(BashParser.Redir_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link BashParser#redir_op}.
	 * @param ctx the parse tree
	 */
	void exitRedir_op(BashParser.Redir_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link BashParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(BashParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link BashParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(BashParser.ArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link BashParser#dquote_str}.
	 * @param ctx the parse tree
	 */
	void enterDquote_str(BashParser.Dquote_strContext ctx);
	/**
	 * Exit a parse tree produced by {@link BashParser#dquote_str}.
	 * @param ctx the parse tree
	 */
	void exitDquote_str(BashParser.Dquote_strContext ctx);
	/**
	 * Enter a parse tree produced by {@link BashParser#squote_str}.
	 * @param ctx the parse tree
	 */
	void enterSquote_str(BashParser.Squote_strContext ctx);
	/**
	 * Exit a parse tree produced by {@link BashParser#squote_str}.
	 * @param ctx the parse tree
	 */
	void exitSquote_str(BashParser.Squote_strContext ctx);
	/**
	 * Enter a parse tree produced by {@link BashParser#subst}.
	 * @param ctx the parse tree
	 */
	void enterSubst(BashParser.SubstContext ctx);
	/**
	 * Exit a parse tree produced by {@link BashParser#subst}.
	 * @param ctx the parse tree
	 */
	void exitSubst(BashParser.SubstContext ctx);
	/**
	 * Enter a parse tree produced by {@link BashParser#cst}.
	 * @param ctx the parse tree
	 */
	void enterCst(BashParser.CstContext ctx);
	/**
	 * Exit a parse tree produced by {@link BashParser#cst}.
	 * @param ctx the parse tree
	 */
	void exitCst(BashParser.CstContext ctx);
	/**
	 * Enter a parse tree produced by {@link BashParser#lpst}.
	 * @param ctx the parse tree
	 */
	void enterLpst(BashParser.LpstContext ctx);
	/**
	 * Exit a parse tree produced by {@link BashParser#lpst}.
	 * @param ctx the parse tree
	 */
	void exitLpst(BashParser.LpstContext ctx);
	/**
	 * Enter a parse tree produced by {@link BashParser#rpst}.
	 * @param ctx the parse tree
	 */
	void enterRpst(BashParser.RpstContext ctx);
	/**
	 * Exit a parse tree produced by {@link BashParser#rpst}.
	 * @param ctx the parse tree
	 */
	void exitRpst(BashParser.RpstContext ctx);
	/**
	 * Enter a parse tree produced by {@link BashParser#arith}.
	 * @param ctx the parse tree
	 */
	void enterArith(BashParser.ArithContext ctx);
	/**
	 * Exit a parse tree produced by {@link BashParser#arith}.
	 * @param ctx the parse tree
	 */
	void exitArith(BashParser.ArithContext ctx);
	/**
	 * Enter a parse tree produced by {@link BashParser#param_exp}.
	 * @param ctx the parse tree
	 */
	void enterParam_exp(BashParser.Param_expContext ctx);
	/**
	 * Exit a parse tree produced by {@link BashParser#param_exp}.
	 * @param ctx the parse tree
	 */
	void exitParam_exp(BashParser.Param_expContext ctx);
	/**
	 * Enter a parse tree produced by {@link BashParser#param_exp_op}.
	 * @param ctx the parse tree
	 */
	void enterParam_exp_op(BashParser.Param_exp_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link BashParser#param_exp_op}.
	 * @param ctx the parse tree
	 */
	void exitParam_exp_op(BashParser.Param_exp_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link BashParser#grp}.
	 * @param ctx the parse tree
	 */
	void enterGrp(BashParser.GrpContext ctx);
	/**
	 * Exit a parse tree produced by {@link BashParser#grp}.
	 * @param ctx the parse tree
	 */
	void exitGrp(BashParser.GrpContext ctx);
	/**
	 * Enter a parse tree produced by {@link BashParser#paren_grp}.
	 * @param ctx the parse tree
	 */
	void enterParen_grp(BashParser.Paren_grpContext ctx);
	/**
	 * Exit a parse tree produced by {@link BashParser#paren_grp}.
	 * @param ctx the parse tree
	 */
	void exitParen_grp(BashParser.Paren_grpContext ctx);
	/**
	 * Enter a parse tree produced by {@link BashParser#pure_curly}.
	 * @param ctx the parse tree
	 */
	void enterPure_curly(BashParser.Pure_curlyContext ctx);
	/**
	 * Exit a parse tree produced by {@link BashParser#pure_curly}.
	 * @param ctx the parse tree
	 */
	void exitPure_curly(BashParser.Pure_curlyContext ctx);
	/**
	 * Enter a parse tree produced by {@link BashParser#curly_grp}.
	 * @param ctx the parse tree
	 */
	void enterCurly_grp(BashParser.Curly_grpContext ctx);
	/**
	 * Exit a parse tree produced by {@link BashParser#curly_grp}.
	 * @param ctx the parse tree
	 */
	void exitCurly_grp(BashParser.Curly_grpContext ctx);
}