// Generated from D:/Android/Projects/TaoKDao/grammars-v4-master/http\http.g4 by ANTLR 4.7.2
package tiiehenry.code.antlr4;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class httpParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		SP=10, ALPHA=11, DIGIT=12, Pct_encoded=13, HEXDIG=14, LColumn=15, RColumn=16, 
		SemiColon=17, Equals=18, Period=19, CRLF=20, Minus=21, Dot=22, Underscore=23, 
		Tilde=24, QuestionMark=25, Slash=26, ExclamationMark=27, Colon=28, At=29, 
		DollarSign=30, Hashtag=31, Ampersand=32, Percent=33, SQuote=34, Star=35, 
		Plus=36, Caret=37, BackQuote=38, VBar=39, OWS=40, HTAB=41, VCHAR=42, OBS_TEXT=43;
	public static final int
		RULE_http_message = 0, RULE_start_line = 1, RULE_request_line = 2, RULE_method = 3, 
		RULE_request_target = 4, RULE_origin_form = 5, RULE_absolute_path = 6, 
		RULE_segment = 7, RULE_query = 8, RULE_http_version = 9, RULE_http_name = 10, 
		RULE_header_field = 11, RULE_field_name = 12, RULE_token = 13, RULE_field_value = 14, 
		RULE_field_content = 15, RULE_field_vchar = 16, RULE_obs_text = 17, RULE_obs_fold = 18, 
		RULE_pchar = 19, RULE_unreserved = 20, RULE_sub_delims = 21, RULE_tchar = 22, 
		RULE_vCHAR = 23;
	private static String[] makeRuleNames() {
		return new String[] {
			"http_message", "start_line", "request_line", "method", "request_target", 
			"origin_form", "absolute_path", "segment", "query", "http_version", "http_name", 
			"header_field", "field_name", "token", "field_value", "field_content", 
			"field_vchar", "obs_text", "obs_fold", "pchar", "unreserved", "sub_delims", 
			"tchar", "vCHAR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'GET'", "'HEAD'", "'POST'", "'PUT'", "'DELETE'", "'CONNECT'", 
			"'OPTIONS'", "'TRACE'", "'HTTP/'", "' '", null, null, null, null, "'('", 
			"')'", "';'", "'='", "','", "'\n'", "'-'", "'.'", "'_'", "'~'", "'?'", 
			"'/'", "'!'", "':'", "'@'", "'$'", "'#'", "'&'", "'%'", "'''", "'*'", 
			"'+'", "'^'", "'`'", "'|'", null, "'\t'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "SP", "ALPHA", 
			"DIGIT", "Pct_encoded", "HEXDIG", "LColumn", "RColumn", "SemiColon", 
			"Equals", "Period", "CRLF", "Minus", "Dot", "Underscore", "Tilde", "QuestionMark", 
			"Slash", "ExclamationMark", "Colon", "At", "DollarSign", "Hashtag", "Ampersand", 
			"Percent", "SQuote", "Star", "Plus", "Caret", "BackQuote", "VBar", "OWS", 
			"HTAB", "VCHAR", "OBS_TEXT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "http.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public httpParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class Http_messageContext extends ParserRuleContext {
		public Start_lineContext start_line() {
			return getRuleContext(Start_lineContext.class,0);
		}
		public List<TerminalNode> CRLF() { return getTokens(httpParser.CRLF); }
		public TerminalNode CRLF(int i) {
			return getToken(httpParser.CRLF, i);
		}
		public List<Header_fieldContext> header_field() {
			return getRuleContexts(Header_fieldContext.class);
		}
		public Header_fieldContext header_field(int i) {
			return getRuleContext(Header_fieldContext.class,i);
		}
		public Http_messageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_http_message; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).enterHttp_message(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).exitHttp_message(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof httpVisitor ) return ((httpVisitor<? extends T>)visitor).visitHttp_message(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Http_messageContext http_message() throws RecognitionException {
		Http_messageContext _localctx = new Http_messageContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_http_message);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			start_line();
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALPHA) | (1L << DIGIT) | (1L << Minus) | (1L << Dot) | (1L << Underscore) | (1L << Tilde) | (1L << ExclamationMark) | (1L << DollarSign) | (1L << Hashtag) | (1L << Ampersand) | (1L << Percent) | (1L << SQuote) | (1L << Star) | (1L << Plus) | (1L << Caret) | (1L << BackQuote) | (1L << VBar))) != 0)) {
				{
				{
				setState(49);
				header_field();
				setState(50);
				match(CRLF);
				}
				}
				setState(56);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(57);
			match(CRLF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Start_lineContext extends ParserRuleContext {
		public Request_lineContext request_line() {
			return getRuleContext(Request_lineContext.class,0);
		}
		public Start_lineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start_line; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).enterStart_line(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).exitStart_line(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof httpVisitor ) return ((httpVisitor<? extends T>)visitor).visitStart_line(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Start_lineContext start_line() throws RecognitionException {
		Start_lineContext _localctx = new Start_lineContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_start_line);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			request_line();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Request_lineContext extends ParserRuleContext {
		public MethodContext method() {
			return getRuleContext(MethodContext.class,0);
		}
		public List<TerminalNode> SP() { return getTokens(httpParser.SP); }
		public TerminalNode SP(int i) {
			return getToken(httpParser.SP, i);
		}
		public Request_targetContext request_target() {
			return getRuleContext(Request_targetContext.class,0);
		}
		public Http_versionContext http_version() {
			return getRuleContext(Http_versionContext.class,0);
		}
		public TerminalNode CRLF() { return getToken(httpParser.CRLF, 0); }
		public Request_lineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_request_line; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).enterRequest_line(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).exitRequest_line(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof httpVisitor ) return ((httpVisitor<? extends T>)visitor).visitRequest_line(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Request_lineContext request_line() throws RecognitionException {
		Request_lineContext _localctx = new Request_lineContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_request_line);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			method();
			setState(62);
			match(SP);
			setState(63);
			request_target();
			setState(64);
			match(SP);
			setState(65);
			http_version();
			setState(66);
			match(CRLF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodContext extends ParserRuleContext {
		public MethodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).enterMethod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).exitMethod(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof httpVisitor ) return ((httpVisitor<? extends T>)visitor).visitMethod(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodContext method() throws RecognitionException {
		MethodContext _localctx = new MethodContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_method);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Request_targetContext extends ParserRuleContext {
		public Origin_formContext origin_form() {
			return getRuleContext(Origin_formContext.class,0);
		}
		public Request_targetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_request_target; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).enterRequest_target(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).exitRequest_target(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof httpVisitor ) return ((httpVisitor<? extends T>)visitor).visitRequest_target(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Request_targetContext request_target() throws RecognitionException {
		Request_targetContext _localctx = new Request_targetContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_request_target);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			origin_form();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Origin_formContext extends ParserRuleContext {
		public Absolute_pathContext absolute_path() {
			return getRuleContext(Absolute_pathContext.class,0);
		}
		public TerminalNode QuestionMark() { return getToken(httpParser.QuestionMark, 0); }
		public QueryContext query() {
			return getRuleContext(QueryContext.class,0);
		}
		public Origin_formContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_origin_form; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).enterOrigin_form(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).exitOrigin_form(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof httpVisitor ) return ((httpVisitor<? extends T>)visitor).visitOrigin_form(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Origin_formContext origin_form() throws RecognitionException {
		Origin_formContext _localctx = new Origin_formContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_origin_form);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			absolute_path();
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QuestionMark) {
				{
				setState(73);
				match(QuestionMark);
				setState(74);
				query();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Absolute_pathContext extends ParserRuleContext {
		public List<TerminalNode> Slash() { return getTokens(httpParser.Slash); }
		public TerminalNode Slash(int i) {
			return getToken(httpParser.Slash, i);
		}
		public List<SegmentContext> segment() {
			return getRuleContexts(SegmentContext.class);
		}
		public SegmentContext segment(int i) {
			return getRuleContext(SegmentContext.class,i);
		}
		public Absolute_pathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_absolute_path; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).enterAbsolute_path(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).exitAbsolute_path(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof httpVisitor ) return ((httpVisitor<? extends T>)visitor).visitAbsolute_path(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Absolute_pathContext absolute_path() throws RecognitionException {
		Absolute_pathContext _localctx = new Absolute_pathContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_absolute_path);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(77);
				match(Slash);
				setState(78);
				segment();
				}
				}
				setState(81); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==Slash );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SegmentContext extends ParserRuleContext {
		public List<PcharContext> pchar() {
			return getRuleContexts(PcharContext.class);
		}
		public PcharContext pchar(int i) {
			return getRuleContext(PcharContext.class,i);
		}
		public SegmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_segment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).enterSegment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).exitSegment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof httpVisitor ) return ((httpVisitor<? extends T>)visitor).visitSegment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SegmentContext segment() throws RecognitionException {
		SegmentContext _localctx = new SegmentContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_segment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALPHA) | (1L << DIGIT) | (1L << Pct_encoded) | (1L << LColumn) | (1L << RColumn) | (1L << SemiColon) | (1L << Equals) | (1L << Period) | (1L << Minus) | (1L << Dot) | (1L << Underscore) | (1L << Tilde) | (1L << ExclamationMark) | (1L << Colon) | (1L << At) | (1L << DollarSign) | (1L << Ampersand) | (1L << SQuote) | (1L << Star) | (1L << Plus))) != 0)) {
				{
				{
				setState(83);
				pchar();
				}
				}
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QueryContext extends ParserRuleContext {
		public List<PcharContext> pchar() {
			return getRuleContexts(PcharContext.class);
		}
		public PcharContext pchar(int i) {
			return getRuleContext(PcharContext.class,i);
		}
		public List<TerminalNode> Slash() { return getTokens(httpParser.Slash); }
		public TerminalNode Slash(int i) {
			return getToken(httpParser.Slash, i);
		}
		public List<TerminalNode> QuestionMark() { return getTokens(httpParser.QuestionMark); }
		public TerminalNode QuestionMark(int i) {
			return getToken(httpParser.QuestionMark, i);
		}
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).exitQuery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof httpVisitor ) return ((httpVisitor<? extends T>)visitor).visitQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_query);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALPHA) | (1L << DIGIT) | (1L << Pct_encoded) | (1L << LColumn) | (1L << RColumn) | (1L << SemiColon) | (1L << Equals) | (1L << Period) | (1L << Minus) | (1L << Dot) | (1L << Underscore) | (1L << Tilde) | (1L << QuestionMark) | (1L << Slash) | (1L << ExclamationMark) | (1L << Colon) | (1L << At) | (1L << DollarSign) | (1L << Ampersand) | (1L << SQuote) | (1L << Star) | (1L << Plus))) != 0)) {
				{
				setState(92);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ALPHA:
				case DIGIT:
				case Pct_encoded:
				case LColumn:
				case RColumn:
				case SemiColon:
				case Equals:
				case Period:
				case Minus:
				case Dot:
				case Underscore:
				case Tilde:
				case ExclamationMark:
				case Colon:
				case At:
				case DollarSign:
				case Ampersand:
				case SQuote:
				case Star:
				case Plus:
					{
					setState(89);
					pchar();
					}
					break;
				case Slash:
					{
					setState(90);
					match(Slash);
					}
					break;
				case QuestionMark:
					{
					setState(91);
					match(QuestionMark);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(96);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Http_versionContext extends ParserRuleContext {
		public Http_nameContext http_name() {
			return getRuleContext(Http_nameContext.class,0);
		}
		public List<TerminalNode> DIGIT() { return getTokens(httpParser.DIGIT); }
		public TerminalNode DIGIT(int i) {
			return getToken(httpParser.DIGIT, i);
		}
		public TerminalNode Dot() { return getToken(httpParser.Dot, 0); }
		public Http_versionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_http_version; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).enterHttp_version(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).exitHttp_version(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof httpVisitor ) return ((httpVisitor<? extends T>)visitor).visitHttp_version(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Http_versionContext http_version() throws RecognitionException {
		Http_versionContext _localctx = new Http_versionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_http_version);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			http_name();
			setState(98);
			match(DIGIT);
			setState(99);
			match(Dot);
			setState(100);
			match(DIGIT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Http_nameContext extends ParserRuleContext {
		public Http_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_http_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).enterHttp_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).exitHttp_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof httpVisitor ) return ((httpVisitor<? extends T>)visitor).visitHttp_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Http_nameContext http_name() throws RecognitionException {
		Http_nameContext _localctx = new Http_nameContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_http_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(T__8);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Header_fieldContext extends ParserRuleContext {
		public Field_nameContext field_name() {
			return getRuleContext(Field_nameContext.class,0);
		}
		public TerminalNode Colon() { return getToken(httpParser.Colon, 0); }
		public Field_valueContext field_value() {
			return getRuleContext(Field_valueContext.class,0);
		}
		public List<TerminalNode> OWS() { return getTokens(httpParser.OWS); }
		public TerminalNode OWS(int i) {
			return getToken(httpParser.OWS, i);
		}
		public Header_fieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_header_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).enterHeader_field(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).exitHeader_field(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof httpVisitor ) return ((httpVisitor<? extends T>)visitor).visitHeader_field(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Header_fieldContext header_field() throws RecognitionException {
		Header_fieldContext _localctx = new Header_fieldContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_header_field);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			field_name();
			setState(105);
			match(Colon);
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OWS) {
				{
				{
				setState(106);
				match(OWS);
				}
				}
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(112);
			field_value();
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OWS) {
				{
				{
				setState(113);
				match(OWS);
				}
				}
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Field_nameContext extends ParserRuleContext {
		public TokenContext token() {
			return getRuleContext(TokenContext.class,0);
		}
		public Field_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).enterField_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).exitField_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof httpVisitor ) return ((httpVisitor<? extends T>)visitor).visitField_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Field_nameContext field_name() throws RecognitionException {
		Field_nameContext _localctx = new Field_nameContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_field_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			token();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TokenContext extends ParserRuleContext {
		public List<TcharContext> tchar() {
			return getRuleContexts(TcharContext.class);
		}
		public TcharContext tchar(int i) {
			return getRuleContext(TcharContext.class,i);
		}
		public TokenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_token; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).enterToken(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).exitToken(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof httpVisitor ) return ((httpVisitor<? extends T>)visitor).visitToken(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TokenContext token() throws RecognitionException {
		TokenContext _localctx = new TokenContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_token);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(121);
				tchar();
				}
				}
				setState(124); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALPHA) | (1L << DIGIT) | (1L << Minus) | (1L << Dot) | (1L << Underscore) | (1L << Tilde) | (1L << ExclamationMark) | (1L << DollarSign) | (1L << Hashtag) | (1L << Ampersand) | (1L << Percent) | (1L << SQuote) | (1L << Star) | (1L << Plus) | (1L << Caret) | (1L << BackQuote) | (1L << VBar))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Field_valueContext extends ParserRuleContext {
		public List<Field_contentContext> field_content() {
			return getRuleContexts(Field_contentContext.class);
		}
		public Field_contentContext field_content(int i) {
			return getRuleContext(Field_contentContext.class,i);
		}
		public List<Obs_foldContext> obs_fold() {
			return getRuleContexts(Obs_foldContext.class);
		}
		public Obs_foldContext obs_fold(int i) {
			return getRuleContext(Obs_foldContext.class,i);
		}
		public Field_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).enterField_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).exitField_value(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof httpVisitor ) return ((httpVisitor<? extends T>)visitor).visitField_value(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Field_valueContext field_value() throws RecognitionException {
		Field_valueContext _localctx = new Field_valueContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_field_value);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(128); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					setState(128);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case ALPHA:
					case DIGIT:
					case VCHAR:
					case OBS_TEXT:
						{
						setState(126);
						field_content();
						}
						break;
					case CRLF:
						{
						setState(127);
						obs_fold();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(130); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Field_contentContext extends ParserRuleContext {
		public List<Field_vcharContext> field_vchar() {
			return getRuleContexts(Field_vcharContext.class);
		}
		public Field_vcharContext field_vchar(int i) {
			return getRuleContext(Field_vcharContext.class,i);
		}
		public List<TerminalNode> SP() { return getTokens(httpParser.SP); }
		public TerminalNode SP(int i) {
			return getToken(httpParser.SP, i);
		}
		public List<TerminalNode> HTAB() { return getTokens(httpParser.HTAB); }
		public TerminalNode HTAB(int i) {
			return getToken(httpParser.HTAB, i);
		}
		public Field_contentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field_content; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).enterField_content(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).exitField_content(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof httpVisitor ) return ((httpVisitor<? extends T>)visitor).visitField_content(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Field_contentContext field_content() throws RecognitionException {
		Field_contentContext _localctx = new Field_contentContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_field_content);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			field_vchar();
			setState(139);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SP || _la==HTAB) {
				{
				setState(134); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(133);
					_la = _input.LA(1);
					if ( !(_la==SP || _la==HTAB) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					setState(136); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==SP || _la==HTAB );
				setState(138);
				field_vchar();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Field_vcharContext extends ParserRuleContext {
		public VCHARContext vCHAR() {
			return getRuleContext(VCHARContext.class,0);
		}
		public Obs_textContext obs_text() {
			return getRuleContext(Obs_textContext.class,0);
		}
		public Field_vcharContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field_vchar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).enterField_vchar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).exitField_vchar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof httpVisitor ) return ((httpVisitor<? extends T>)visitor).visitField_vchar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Field_vcharContext field_vchar() throws RecognitionException {
		Field_vcharContext _localctx = new Field_vcharContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_field_vchar);
		try {
			setState(143);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ALPHA:
			case DIGIT:
			case VCHAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(141);
				vCHAR();
				}
				break;
			case OBS_TEXT:
				enterOuterAlt(_localctx, 2);
				{
				setState(142);
				obs_text();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Obs_textContext extends ParserRuleContext {
		public TerminalNode OBS_TEXT() { return getToken(httpParser.OBS_TEXT, 0); }
		public Obs_textContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_obs_text; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).enterObs_text(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).exitObs_text(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof httpVisitor ) return ((httpVisitor<? extends T>)visitor).visitObs_text(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Obs_textContext obs_text() throws RecognitionException {
		Obs_textContext _localctx = new Obs_textContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_obs_text);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(OBS_TEXT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Obs_foldContext extends ParserRuleContext {
		public TerminalNode CRLF() { return getToken(httpParser.CRLF, 0); }
		public List<TerminalNode> SP() { return getTokens(httpParser.SP); }
		public TerminalNode SP(int i) {
			return getToken(httpParser.SP, i);
		}
		public List<TerminalNode> HTAB() { return getTokens(httpParser.HTAB); }
		public TerminalNode HTAB(int i) {
			return getToken(httpParser.HTAB, i);
		}
		public Obs_foldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_obs_fold; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).enterObs_fold(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).exitObs_fold(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof httpVisitor ) return ((httpVisitor<? extends T>)visitor).visitObs_fold(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Obs_foldContext obs_fold() throws RecognitionException {
		Obs_foldContext _localctx = new Obs_foldContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_obs_fold);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			match(CRLF);
			setState(149); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(148);
				_la = _input.LA(1);
				if ( !(_la==SP || _la==HTAB) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(151); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==SP || _la==HTAB );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PcharContext extends ParserRuleContext {
		public UnreservedContext unreserved() {
			return getRuleContext(UnreservedContext.class,0);
		}
		public TerminalNode Pct_encoded() { return getToken(httpParser.Pct_encoded, 0); }
		public Sub_delimsContext sub_delims() {
			return getRuleContext(Sub_delimsContext.class,0);
		}
		public TerminalNode Colon() { return getToken(httpParser.Colon, 0); }
		public TerminalNode At() { return getToken(httpParser.At, 0); }
		public PcharContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pchar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).enterPchar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).exitPchar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof httpVisitor ) return ((httpVisitor<? extends T>)visitor).visitPchar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PcharContext pchar() throws RecognitionException {
		PcharContext _localctx = new PcharContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_pchar);
		try {
			setState(158);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ALPHA:
			case DIGIT:
			case Minus:
			case Dot:
			case Underscore:
			case Tilde:
				enterOuterAlt(_localctx, 1);
				{
				setState(153);
				unreserved();
				}
				break;
			case Pct_encoded:
				enterOuterAlt(_localctx, 2);
				{
				setState(154);
				match(Pct_encoded);
				}
				break;
			case LColumn:
			case RColumn:
			case SemiColon:
			case Equals:
			case Period:
			case ExclamationMark:
			case DollarSign:
			case Ampersand:
			case SQuote:
			case Star:
			case Plus:
				enterOuterAlt(_localctx, 3);
				{
				setState(155);
				sub_delims();
				}
				break;
			case Colon:
				enterOuterAlt(_localctx, 4);
				{
				setState(156);
				match(Colon);
				}
				break;
			case At:
				enterOuterAlt(_localctx, 5);
				{
				setState(157);
				match(At);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnreservedContext extends ParserRuleContext {
		public TerminalNode ALPHA() { return getToken(httpParser.ALPHA, 0); }
		public TerminalNode DIGIT() { return getToken(httpParser.DIGIT, 0); }
		public TerminalNode Minus() { return getToken(httpParser.Minus, 0); }
		public TerminalNode Dot() { return getToken(httpParser.Dot, 0); }
		public TerminalNode Underscore() { return getToken(httpParser.Underscore, 0); }
		public TerminalNode Tilde() { return getToken(httpParser.Tilde, 0); }
		public UnreservedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unreserved; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).enterUnreserved(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).exitUnreserved(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof httpVisitor ) return ((httpVisitor<? extends T>)visitor).visitUnreserved(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnreservedContext unreserved() throws RecognitionException {
		UnreservedContext _localctx = new UnreservedContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_unreserved);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALPHA) | (1L << DIGIT) | (1L << Minus) | (1L << Dot) | (1L << Underscore) | (1L << Tilde))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Sub_delimsContext extends ParserRuleContext {
		public TerminalNode ExclamationMark() { return getToken(httpParser.ExclamationMark, 0); }
		public TerminalNode DollarSign() { return getToken(httpParser.DollarSign, 0); }
		public TerminalNode Ampersand() { return getToken(httpParser.Ampersand, 0); }
		public TerminalNode SQuote() { return getToken(httpParser.SQuote, 0); }
		public TerminalNode LColumn() { return getToken(httpParser.LColumn, 0); }
		public TerminalNode RColumn() { return getToken(httpParser.RColumn, 0); }
		public TerminalNode Star() { return getToken(httpParser.Star, 0); }
		public TerminalNode Plus() { return getToken(httpParser.Plus, 0); }
		public TerminalNode SemiColon() { return getToken(httpParser.SemiColon, 0); }
		public TerminalNode Period() { return getToken(httpParser.Period, 0); }
		public TerminalNode Equals() { return getToken(httpParser.Equals, 0); }
		public Sub_delimsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sub_delims; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).enterSub_delims(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).exitSub_delims(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof httpVisitor ) return ((httpVisitor<? extends T>)visitor).visitSub_delims(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Sub_delimsContext sub_delims() throws RecognitionException {
		Sub_delimsContext _localctx = new Sub_delimsContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_sub_delims);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LColumn) | (1L << RColumn) | (1L << SemiColon) | (1L << Equals) | (1L << Period) | (1L << ExclamationMark) | (1L << DollarSign) | (1L << Ampersand) | (1L << SQuote) | (1L << Star) | (1L << Plus))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TcharContext extends ParserRuleContext {
		public TerminalNode ExclamationMark() { return getToken(httpParser.ExclamationMark, 0); }
		public TerminalNode DollarSign() { return getToken(httpParser.DollarSign, 0); }
		public TerminalNode Hashtag() { return getToken(httpParser.Hashtag, 0); }
		public TerminalNode Percent() { return getToken(httpParser.Percent, 0); }
		public TerminalNode Ampersand() { return getToken(httpParser.Ampersand, 0); }
		public TerminalNode SQuote() { return getToken(httpParser.SQuote, 0); }
		public TerminalNode Star() { return getToken(httpParser.Star, 0); }
		public TerminalNode Plus() { return getToken(httpParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(httpParser.Minus, 0); }
		public TerminalNode Dot() { return getToken(httpParser.Dot, 0); }
		public TerminalNode Caret() { return getToken(httpParser.Caret, 0); }
		public TerminalNode Underscore() { return getToken(httpParser.Underscore, 0); }
		public TerminalNode BackQuote() { return getToken(httpParser.BackQuote, 0); }
		public TerminalNode VBar() { return getToken(httpParser.VBar, 0); }
		public TerminalNode Tilde() { return getToken(httpParser.Tilde, 0); }
		public TerminalNode DIGIT() { return getToken(httpParser.DIGIT, 0); }
		public TerminalNode ALPHA() { return getToken(httpParser.ALPHA, 0); }
		public TcharContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tchar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).enterTchar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).exitTchar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof httpVisitor ) return ((httpVisitor<? extends T>)visitor).visitTchar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TcharContext tchar() throws RecognitionException {
		TcharContext _localctx = new TcharContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_tchar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALPHA) | (1L << DIGIT) | (1L << Minus) | (1L << Dot) | (1L << Underscore) | (1L << Tilde) | (1L << ExclamationMark) | (1L << DollarSign) | (1L << Hashtag) | (1L << Ampersand) | (1L << Percent) | (1L << SQuote) | (1L << Star) | (1L << Plus) | (1L << Caret) | (1L << BackQuote) | (1L << VBar))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VCHARContext extends ParserRuleContext {
		public TerminalNode ALPHA() { return getToken(httpParser.ALPHA, 0); }
		public TerminalNode DIGIT() { return getToken(httpParser.DIGIT, 0); }
		public TerminalNode VCHAR() { return getToken(httpParser.VCHAR, 0); }
		public VCHARContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vCHAR; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).enterVCHAR(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof httpListener ) ((httpListener)listener).exitVCHAR(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof httpVisitor ) return ((httpVisitor<? extends T>)visitor).visitVCHAR(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VCHARContext vCHAR() throws RecognitionException {
		VCHARContext _localctx = new VCHARContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_vCHAR);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALPHA) | (1L << DIGIT) | (1L << VCHAR))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3-\u00ab\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\3\2\3\2\3\2\3\2\7\2\67\n\2\f\2\16\2:\13\2\3\2\3\2\3\3\3\3\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\5\7N\n\7\3\b\3\b\6\bR\n\b"+
		"\r\b\16\bS\3\t\7\tW\n\t\f\t\16\tZ\13\t\3\n\3\n\3\n\7\n_\n\n\f\n\16\nb"+
		"\13\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\7\rn\n\r\f\r\16\rq"+
		"\13\r\3\r\3\r\7\ru\n\r\f\r\16\rx\13\r\3\16\3\16\3\17\6\17}\n\17\r\17\16"+
		"\17~\3\20\3\20\6\20\u0083\n\20\r\20\16\20\u0084\3\21\3\21\6\21\u0089\n"+
		"\21\r\21\16\21\u008a\3\21\5\21\u008e\n\21\3\22\3\22\5\22\u0092\n\22\3"+
		"\23\3\23\3\24\3\24\6\24\u0098\n\24\r\24\16\24\u0099\3\25\3\25\3\25\3\25"+
		"\3\25\5\25\u00a1\n\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\31\2\2"+
		"\32\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\2\b\3\2\3\n\4\2"+
		"\f\f++\4\2\r\16\27\32\7\2\21\25\35\35  \"\"$&\6\2\r\16\27\32\35\35 )\4"+
		"\2\r\16,,\2\u00a6\2\62\3\2\2\2\4=\3\2\2\2\6?\3\2\2\2\bF\3\2\2\2\nH\3\2"+
		"\2\2\fJ\3\2\2\2\16Q\3\2\2\2\20X\3\2\2\2\22`\3\2\2\2\24c\3\2\2\2\26h\3"+
		"\2\2\2\30j\3\2\2\2\32y\3\2\2\2\34|\3\2\2\2\36\u0082\3\2\2\2 \u0086\3\2"+
		"\2\2\"\u0091\3\2\2\2$\u0093\3\2\2\2&\u0095\3\2\2\2(\u00a0\3\2\2\2*\u00a2"+
		"\3\2\2\2,\u00a4\3\2\2\2.\u00a6\3\2\2\2\60\u00a8\3\2\2\2\628\5\4\3\2\63"+
		"\64\5\30\r\2\64\65\7\26\2\2\65\67\3\2\2\2\66\63\3\2\2\2\67:\3\2\2\28\66"+
		"\3\2\2\289\3\2\2\29;\3\2\2\2:8\3\2\2\2;<\7\26\2\2<\3\3\2\2\2=>\5\6\4\2"+
		">\5\3\2\2\2?@\5\b\5\2@A\7\f\2\2AB\5\n\6\2BC\7\f\2\2CD\5\24\13\2DE\7\26"+
		"\2\2E\7\3\2\2\2FG\t\2\2\2G\t\3\2\2\2HI\5\f\7\2I\13\3\2\2\2JM\5\16\b\2"+
		"KL\7\33\2\2LN\5\22\n\2MK\3\2\2\2MN\3\2\2\2N\r\3\2\2\2OP\7\34\2\2PR\5\20"+
		"\t\2QO\3\2\2\2RS\3\2\2\2SQ\3\2\2\2ST\3\2\2\2T\17\3\2\2\2UW\5(\25\2VU\3"+
		"\2\2\2WZ\3\2\2\2XV\3\2\2\2XY\3\2\2\2Y\21\3\2\2\2ZX\3\2\2\2[_\5(\25\2\\"+
		"_\7\34\2\2]_\7\33\2\2^[\3\2\2\2^\\\3\2\2\2^]\3\2\2\2_b\3\2\2\2`^\3\2\2"+
		"\2`a\3\2\2\2a\23\3\2\2\2b`\3\2\2\2cd\5\26\f\2de\7\16\2\2ef\7\30\2\2fg"+
		"\7\16\2\2g\25\3\2\2\2hi\7\13\2\2i\27\3\2\2\2jk\5\32\16\2ko\7\36\2\2ln"+
		"\7*\2\2ml\3\2\2\2nq\3\2\2\2om\3\2\2\2op\3\2\2\2pr\3\2\2\2qo\3\2\2\2rv"+
		"\5\36\20\2su\7*\2\2ts\3\2\2\2ux\3\2\2\2vt\3\2\2\2vw\3\2\2\2w\31\3\2\2"+
		"\2xv\3\2\2\2yz\5\34\17\2z\33\3\2\2\2{}\5.\30\2|{\3\2\2\2}~\3\2\2\2~|\3"+
		"\2\2\2~\177\3\2\2\2\177\35\3\2\2\2\u0080\u0083\5 \21\2\u0081\u0083\5&"+
		"\24\2\u0082\u0080\3\2\2\2\u0082\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084"+
		"\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\37\3\2\2\2\u0086\u008d\5\"\22"+
		"\2\u0087\u0089\t\3\2\2\u0088\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u0088"+
		"\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008e\5\"\22\2"+
		"\u008d\u0088\3\2\2\2\u008d\u008e\3\2\2\2\u008e!\3\2\2\2\u008f\u0092\5"+
		"\60\31\2\u0090\u0092\5$\23\2\u0091\u008f\3\2\2\2\u0091\u0090\3\2\2\2\u0092"+
		"#\3\2\2\2\u0093\u0094\7-\2\2\u0094%\3\2\2\2\u0095\u0097\7\26\2\2\u0096"+
		"\u0098\t\3\2\2\u0097\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u0097\3\2"+
		"\2\2\u0099\u009a\3\2\2\2\u009a\'\3\2\2\2\u009b\u00a1\5*\26\2\u009c\u00a1"+
		"\7\17\2\2\u009d\u00a1\5,\27\2\u009e\u00a1\7\36\2\2\u009f\u00a1\7\37\2"+
		"\2\u00a0\u009b\3\2\2\2\u00a0\u009c\3\2\2\2\u00a0\u009d\3\2\2\2\u00a0\u009e"+
		"\3\2\2\2\u00a0\u009f\3\2\2\2\u00a1)\3\2\2\2\u00a2\u00a3\t\4\2\2\u00a3"+
		"+\3\2\2\2\u00a4\u00a5\t\5\2\2\u00a5-\3\2\2\2\u00a6\u00a7\t\6\2\2\u00a7"+
		"/\3\2\2\2\u00a8\u00a9\t\7\2\2\u00a9\61\3\2\2\2\228MSX^`ov~\u0082\u0084"+
		"\u008a\u008d\u0091\u0099\u00a0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}