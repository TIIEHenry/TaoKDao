// Generated from D:/Android/Projects/TaoKDao/grammars-v4-master/inf\inf.g4 by ANTLR 4.7.2
package tiiehenry.code.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link infParser}.
 */
public interface infListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link infParser#inf}.
	 * @param ctx the parse tree
	 */
	void enterInf(infParser.InfContext ctx);
	/**
	 * Exit a parse tree produced by {@link infParser#inf}.
	 * @param ctx the parse tree
	 */
	void exitInf(infParser.InfContext ctx);
	/**
	 * Enter a parse tree produced by {@link infParser#section}.
	 * @param ctx the parse tree
	 */
	void enterSection(infParser.SectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link infParser#section}.
	 * @param ctx the parse tree
	 */
	void exitSection(infParser.SectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link infParser#sectionheader}.
	 * @param ctx the parse tree
	 */
	void enterSectionheader(infParser.SectionheaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link infParser#sectionheader}.
	 * @param ctx the parse tree
	 */
	void exitSectionheader(infParser.SectionheaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link infParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(infParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link infParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(infParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link infParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(infParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link infParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(infParser.LineContext ctx);
	/**
	 * Enter a parse tree produced by {@link infParser#stringlist}.
	 * @param ctx the parse tree
	 */
	void enterStringlist(infParser.StringlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link infParser#stringlist}.
	 * @param ctx the parse tree
	 */
	void exitStringlist(infParser.StringlistContext ctx);
}