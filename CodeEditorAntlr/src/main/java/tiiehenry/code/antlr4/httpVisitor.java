// Generated from D:/Android/Projects/TaoKDao/grammars-v4-master/http\http.g4 by ANTLR 4.7.2
package tiiehenry.code.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link httpParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface httpVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link httpParser#http_message}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHttp_message(httpParser.Http_messageContext ctx);
	/**
	 * Visit a parse tree produced by {@link httpParser#start_line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart_line(httpParser.Start_lineContext ctx);
	/**
	 * Visit a parse tree produced by {@link httpParser#request_line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRequest_line(httpParser.Request_lineContext ctx);
	/**
	 * Visit a parse tree produced by {@link httpParser#method}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod(httpParser.MethodContext ctx);
	/**
	 * Visit a parse tree produced by {@link httpParser#request_target}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRequest_target(httpParser.Request_targetContext ctx);
	/**
	 * Visit a parse tree produced by {@link httpParser#origin_form}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrigin_form(httpParser.Origin_formContext ctx);
	/**
	 * Visit a parse tree produced by {@link httpParser#absolute_path}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAbsolute_path(httpParser.Absolute_pathContext ctx);
	/**
	 * Visit a parse tree produced by {@link httpParser#segment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSegment(httpParser.SegmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link httpParser#query}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuery(httpParser.QueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link httpParser#http_version}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHttp_version(httpParser.Http_versionContext ctx);
	/**
	 * Visit a parse tree produced by {@link httpParser#http_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHttp_name(httpParser.Http_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link httpParser#header_field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeader_field(httpParser.Header_fieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link httpParser#field_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField_name(httpParser.Field_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link httpParser#token}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToken(httpParser.TokenContext ctx);
	/**
	 * Visit a parse tree produced by {@link httpParser#field_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField_value(httpParser.Field_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link httpParser#field_content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField_content(httpParser.Field_contentContext ctx);
	/**
	 * Visit a parse tree produced by {@link httpParser#field_vchar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField_vchar(httpParser.Field_vcharContext ctx);
	/**
	 * Visit a parse tree produced by {@link httpParser#obs_text}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObs_text(httpParser.Obs_textContext ctx);
	/**
	 * Visit a parse tree produced by {@link httpParser#obs_fold}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObs_fold(httpParser.Obs_foldContext ctx);
	/**
	 * Visit a parse tree produced by {@link httpParser#pchar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPchar(httpParser.PcharContext ctx);
	/**
	 * Visit a parse tree produced by {@link httpParser#unreserved}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnreserved(httpParser.UnreservedContext ctx);
	/**
	 * Visit a parse tree produced by {@link httpParser#sub_delims}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSub_delims(httpParser.Sub_delimsContext ctx);
	/**
	 * Visit a parse tree produced by {@link httpParser#tchar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTchar(httpParser.TcharContext ctx);
	/**
	 * Visit a parse tree produced by {@link httpParser#vCHAR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVCHAR(httpParser.VCHARContext ctx);
}