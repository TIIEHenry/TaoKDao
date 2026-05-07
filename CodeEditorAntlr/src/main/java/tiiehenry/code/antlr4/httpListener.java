// Generated from D:/Android/Projects/TaoKDao/grammars-v4-master/http\http.g4 by ANTLR 4.7.2
package tiiehenry.code.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link httpParser}.
 */
public interface httpListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link httpParser#http_message}.
	 * @param ctx the parse tree
	 */
	void enterHttp_message(httpParser.Http_messageContext ctx);
	/**
	 * Exit a parse tree produced by {@link httpParser#http_message}.
	 * @param ctx the parse tree
	 */
	void exitHttp_message(httpParser.Http_messageContext ctx);
	/**
	 * Enter a parse tree produced by {@link httpParser#start_line}.
	 * @param ctx the parse tree
	 */
	void enterStart_line(httpParser.Start_lineContext ctx);
	/**
	 * Exit a parse tree produced by {@link httpParser#start_line}.
	 * @param ctx the parse tree
	 */
	void exitStart_line(httpParser.Start_lineContext ctx);
	/**
	 * Enter a parse tree produced by {@link httpParser#request_line}.
	 * @param ctx the parse tree
	 */
	void enterRequest_line(httpParser.Request_lineContext ctx);
	/**
	 * Exit a parse tree produced by {@link httpParser#request_line}.
	 * @param ctx the parse tree
	 */
	void exitRequest_line(httpParser.Request_lineContext ctx);
	/**
	 * Enter a parse tree produced by {@link httpParser#method}.
	 * @param ctx the parse tree
	 */
	void enterMethod(httpParser.MethodContext ctx);
	/**
	 * Exit a parse tree produced by {@link httpParser#method}.
	 * @param ctx the parse tree
	 */
	void exitMethod(httpParser.MethodContext ctx);
	/**
	 * Enter a parse tree produced by {@link httpParser#request_target}.
	 * @param ctx the parse tree
	 */
	void enterRequest_target(httpParser.Request_targetContext ctx);
	/**
	 * Exit a parse tree produced by {@link httpParser#request_target}.
	 * @param ctx the parse tree
	 */
	void exitRequest_target(httpParser.Request_targetContext ctx);
	/**
	 * Enter a parse tree produced by {@link httpParser#origin_form}.
	 * @param ctx the parse tree
	 */
	void enterOrigin_form(httpParser.Origin_formContext ctx);
	/**
	 * Exit a parse tree produced by {@link httpParser#origin_form}.
	 * @param ctx the parse tree
	 */
	void exitOrigin_form(httpParser.Origin_formContext ctx);
	/**
	 * Enter a parse tree produced by {@link httpParser#absolute_path}.
	 * @param ctx the parse tree
	 */
	void enterAbsolute_path(httpParser.Absolute_pathContext ctx);
	/**
	 * Exit a parse tree produced by {@link httpParser#absolute_path}.
	 * @param ctx the parse tree
	 */
	void exitAbsolute_path(httpParser.Absolute_pathContext ctx);
	/**
	 * Enter a parse tree produced by {@link httpParser#segment}.
	 * @param ctx the parse tree
	 */
	void enterSegment(httpParser.SegmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link httpParser#segment}.
	 * @param ctx the parse tree
	 */
	void exitSegment(httpParser.SegmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link httpParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(httpParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link httpParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(httpParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link httpParser#http_version}.
	 * @param ctx the parse tree
	 */
	void enterHttp_version(httpParser.Http_versionContext ctx);
	/**
	 * Exit a parse tree produced by {@link httpParser#http_version}.
	 * @param ctx the parse tree
	 */
	void exitHttp_version(httpParser.Http_versionContext ctx);
	/**
	 * Enter a parse tree produced by {@link httpParser#http_name}.
	 * @param ctx the parse tree
	 */
	void enterHttp_name(httpParser.Http_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link httpParser#http_name}.
	 * @param ctx the parse tree
	 */
	void exitHttp_name(httpParser.Http_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link httpParser#header_field}.
	 * @param ctx the parse tree
	 */
	void enterHeader_field(httpParser.Header_fieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link httpParser#header_field}.
	 * @param ctx the parse tree
	 */
	void exitHeader_field(httpParser.Header_fieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link httpParser#field_name}.
	 * @param ctx the parse tree
	 */
	void enterField_name(httpParser.Field_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link httpParser#field_name}.
	 * @param ctx the parse tree
	 */
	void exitField_name(httpParser.Field_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link httpParser#token}.
	 * @param ctx the parse tree
	 */
	void enterToken(httpParser.TokenContext ctx);
	/**
	 * Exit a parse tree produced by {@link httpParser#token}.
	 * @param ctx the parse tree
	 */
	void exitToken(httpParser.TokenContext ctx);
	/**
	 * Enter a parse tree produced by {@link httpParser#field_value}.
	 * @param ctx the parse tree
	 */
	void enterField_value(httpParser.Field_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link httpParser#field_value}.
	 * @param ctx the parse tree
	 */
	void exitField_value(httpParser.Field_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link httpParser#field_content}.
	 * @param ctx the parse tree
	 */
	void enterField_content(httpParser.Field_contentContext ctx);
	/**
	 * Exit a parse tree produced by {@link httpParser#field_content}.
	 * @param ctx the parse tree
	 */
	void exitField_content(httpParser.Field_contentContext ctx);
	/**
	 * Enter a parse tree produced by {@link httpParser#field_vchar}.
	 * @param ctx the parse tree
	 */
	void enterField_vchar(httpParser.Field_vcharContext ctx);
	/**
	 * Exit a parse tree produced by {@link httpParser#field_vchar}.
	 * @param ctx the parse tree
	 */
	void exitField_vchar(httpParser.Field_vcharContext ctx);
	/**
	 * Enter a parse tree produced by {@link httpParser#obs_text}.
	 * @param ctx the parse tree
	 */
	void enterObs_text(httpParser.Obs_textContext ctx);
	/**
	 * Exit a parse tree produced by {@link httpParser#obs_text}.
	 * @param ctx the parse tree
	 */
	void exitObs_text(httpParser.Obs_textContext ctx);
	/**
	 * Enter a parse tree produced by {@link httpParser#obs_fold}.
	 * @param ctx the parse tree
	 */
	void enterObs_fold(httpParser.Obs_foldContext ctx);
	/**
	 * Exit a parse tree produced by {@link httpParser#obs_fold}.
	 * @param ctx the parse tree
	 */
	void exitObs_fold(httpParser.Obs_foldContext ctx);
	/**
	 * Enter a parse tree produced by {@link httpParser#pchar}.
	 * @param ctx the parse tree
	 */
	void enterPchar(httpParser.PcharContext ctx);
	/**
	 * Exit a parse tree produced by {@link httpParser#pchar}.
	 * @param ctx the parse tree
	 */
	void exitPchar(httpParser.PcharContext ctx);
	/**
	 * Enter a parse tree produced by {@link httpParser#unreserved}.
	 * @param ctx the parse tree
	 */
	void enterUnreserved(httpParser.UnreservedContext ctx);
	/**
	 * Exit a parse tree produced by {@link httpParser#unreserved}.
	 * @param ctx the parse tree
	 */
	void exitUnreserved(httpParser.UnreservedContext ctx);
	/**
	 * Enter a parse tree produced by {@link httpParser#sub_delims}.
	 * @param ctx the parse tree
	 */
	void enterSub_delims(httpParser.Sub_delimsContext ctx);
	/**
	 * Exit a parse tree produced by {@link httpParser#sub_delims}.
	 * @param ctx the parse tree
	 */
	void exitSub_delims(httpParser.Sub_delimsContext ctx);
	/**
	 * Enter a parse tree produced by {@link httpParser#tchar}.
	 * @param ctx the parse tree
	 */
	void enterTchar(httpParser.TcharContext ctx);
	/**
	 * Exit a parse tree produced by {@link httpParser#tchar}.
	 * @param ctx the parse tree
	 */
	void exitTchar(httpParser.TcharContext ctx);
	/**
	 * Enter a parse tree produced by {@link httpParser#vCHAR}.
	 * @param ctx the parse tree
	 */
	void enterVCHAR(httpParser.VCHARContext ctx);
	/**
	 * Exit a parse tree produced by {@link httpParser#vCHAR}.
	 * @param ctx the parse tree
	 */
	void exitVCHAR(httpParser.VCHARContext ctx);
}