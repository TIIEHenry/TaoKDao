// Generated from D:/Android/Projects/TaoKDao/grammars-v4-master/inf\inf.g4 by ANTLR 4.7.2
package tiiehenry.code.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link infParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface infVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link infParser#inf}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInf(infParser.InfContext ctx);
	/**
	 * Visit a parse tree produced by {@link infParser#section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection(infParser.SectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link infParser#sectionheader}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSectionheader(infParser.SectionheaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link infParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(infParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link infParser#line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLine(infParser.LineContext ctx);
	/**
	 * Visit a parse tree produced by {@link infParser#stringlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringlist(infParser.StringlistContext ctx);
}