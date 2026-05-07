// Generated from D:/Android/Projects/ANTLR4_LANGUAGE/antlr4_bash\BashParser.g4 by ANTLR 4.8
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
public class BashParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		VARNAME=1, PUNCS=2, NUM=3, BLANK=4, EQ=5, VAR=6, SQUOTE=7, DQUOTE=8, LPAREN=9, 
		DOLLAR_LPAREN=10, LT_LPAREN=11, GT_LPAREN=12, BACKTICK=13, DOLLAR_DLPAREN=14, 
		DOLLAR_LCURLY=15, ESC_CHAR=16, LCURLY=17, RCURLY=18, SEMI=19, PIPE=20, 
		PIPE_AND=21, LT=22, GT=23, LT_AND=24, GT_AND=25, AND_GT=26, AND_DGT=27, 
		DLT=28, TLT=29, DLT_DASH=30, DGT=31, LTGT=32, GTPIPE=33, AND=34, NL=35, 
		RPAREN=36, DQUOTE_DOLLAR_DLPAREN=37, ARITH_CONTENT=38, DRPAREN=39, COMMA=40, 
		DASH=41, PARAM_EQ=42, QMARK=43, PLUS=44, PERCENT=45, DPERCENT=46, HASH=47, 
		DHASH=48;
	public static final int
		RULE_pipeline = 0, RULE_cmd = 1, RULE_exec_prefix = 2, RULE_assign = 3, 
		RULE_assign_rls = 4, RULE_exec = 5, RULE_prog = 6, RULE_exec_suffix = 7, 
		RULE_redir = 8, RULE_redir_op = 9, RULE_arg = 10, RULE_dquote_str = 11, 
		RULE_squote_str = 12, RULE_subst = 13, RULE_cst = 14, RULE_lpst = 15, 
		RULE_rpst = 16, RULE_arith = 17, RULE_param_exp = 18, RULE_param_exp_op = 19, 
		RULE_grp = 20, RULE_paren_grp = 21, RULE_pure_curly = 22, RULE_curly_grp = 23;
	private static String[] makeRuleNames() {
		return new String[] {
			"pipeline", "cmd", "exec_prefix", "assign", "assign_rls", "exec", "prog", 
			"exec_suffix", "redir", "redir_op", "arg", "dquote_str", "squote_str", 
			"subst", "cst", "lpst", "rpst", "arith", "param_exp", "param_exp_op", 
			"grp", "paren_grp", "pure_curly", "curly_grp"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, "'''", "'\"'", "'('", "'$('", 
			"'<('", "'>('", "'`'", "'$(('", "'${'", null, "'{'", "'}'", "';'", "'|'", 
			"'|&'", "'<'", "'>'", "'<&'", "'>&'", "'&>'", "'&>>'", "'<<'", "'<<<'", 
			"'<<-'", "'>>'", "'<>'", "'>|'", "'&'", "'\n'", "')'", null, null, "'))'", 
			"':'", "'-'", null, "'?'", "'+'", "'%'", "'%%'", "'#'", "'##'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "VARNAME", "PUNCS", "NUM", "BLANK", "EQ", "VAR", "SQUOTE", "DQUOTE", 
			"LPAREN", "DOLLAR_LPAREN", "LT_LPAREN", "GT_LPAREN", "BACKTICK", "DOLLAR_DLPAREN", 
			"DOLLAR_LCURLY", "ESC_CHAR", "LCURLY", "RCURLY", "SEMI", "PIPE", "PIPE_AND", 
			"LT", "GT", "LT_AND", "GT_AND", "AND_GT", "AND_DGT", "DLT", "TLT", "DLT_DASH", 
			"DGT", "LTGT", "GTPIPE", "AND", "NL", "RPAREN", "DQUOTE_DOLLAR_DLPAREN", 
			"ARITH_CONTENT", "DRPAREN", "COMMA", "DASH", "PARAM_EQ", "QMARK", "PLUS", 
			"PERCENT", "DPERCENT", "HASH", "DHASH"
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
	public String getGrammarFileName() { return "BashParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public BashParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class PipelineContext extends ParserRuleContext {
		public CmdContext cmd() {
			return getRuleContext(CmdContext.class,0);
		}
		public PipelineContext pipeline() {
			return getRuleContext(PipelineContext.class,0);
		}
		public TerminalNode PIPE() { return getToken(BashParser.PIPE, 0); }
		public PipelineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pipeline; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).enterPipeline(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).exitPipeline(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BashParserVisitor ) return ((BashParserVisitor<? extends T>)visitor).visitPipeline(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PipelineContext pipeline() throws RecognitionException {
		return pipeline(0);
	}

	private PipelineContext pipeline(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PipelineContext _localctx = new PipelineContext(_ctx, _parentState);
		PipelineContext _prevctx = _localctx;
		int _startState = 0;
		enterRecursionRule(_localctx, 0, RULE_pipeline, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(49);
			cmd();
			}
			_ctx.stop = _input.LT(-1);
			setState(56);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new PipelineContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_pipeline);
					setState(51);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(52);
					match(PIPE);
					setState(53);
					cmd();
					}
					} 
				}
				setState(58);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class CmdContext extends ParserRuleContext {
		public ExecContext exec() {
			return getRuleContext(ExecContext.class,0);
		}
		public Exec_prefixContext exec_prefix() {
			return getRuleContext(Exec_prefixContext.class,0);
		}
		public List<TerminalNode> BLANK() { return getTokens(BashParser.BLANK); }
		public TerminalNode BLANK(int i) {
			return getToken(BashParser.BLANK, i);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).exitCmd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BashParserVisitor ) return ((BashParserVisitor<? extends T>)visitor).visitCmd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_cmd);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BLANK) {
				{
				setState(59);
				match(BLANK);
				}
			}

			setState(69);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(65);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(62);
					exec_prefix(0);
					setState(63);
					match(BLANK);
					}
					break;
				}
				setState(67);
				exec();
				}
				break;
			case 2:
				{
				setState(68);
				exec_prefix(0);
				}
				break;
			}
			setState(72);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(71);
				match(BLANK);
				}
				break;
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

	public static class Exec_prefixContext extends ParserRuleContext {
		public RedirContext redir() {
			return getRuleContext(RedirContext.class,0);
		}
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public Exec_prefixContext exec_prefix() {
			return getRuleContext(Exec_prefixContext.class,0);
		}
		public TerminalNode BLANK() { return getToken(BashParser.BLANK, 0); }
		public Exec_prefixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exec_prefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).enterExec_prefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).exitExec_prefix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BashParserVisitor ) return ((BashParserVisitor<? extends T>)visitor).visitExec_prefix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Exec_prefixContext exec_prefix() throws RecognitionException {
		return exec_prefix(0);
	}

	private Exec_prefixContext exec_prefix(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Exec_prefixContext _localctx = new Exec_prefixContext(_ctx, _parentState);
		Exec_prefixContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_exec_prefix, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(77);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUM:
			case LT:
			case GT:
			case LT_AND:
			case GT_AND:
			case AND_GT:
			case AND_DGT:
			case DLT:
			case TLT:
			case DLT_DASH:
			case DGT:
			case LTGT:
			case GTPIPE:
				{
				setState(75);
				redir();
				}
				break;
			case VARNAME:
				{
				setState(76);
				assign();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
			_ctx.stop = _input.LT(-1);
			setState(90);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Exec_prefixContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_exec_prefix);
					setState(79);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(86);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						setState(81);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==BLANK) {
							{
							setState(80);
							match(BLANK);
							}
						}

						setState(83);
						redir();
						}
						break;
					case 2:
						{
						setState(84);
						match(BLANK);
						setState(85);
						assign();
						}
						break;
					}
					}
					} 
				}
				setState(92);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AssignContext extends ParserRuleContext {
		public TerminalNode VARNAME() { return getToken(BashParser.VARNAME, 0); }
		public TerminalNode EQ() { return getToken(BashParser.EQ, 0); }
		public Assign_rlsContext assign_rls() {
			return getRuleContext(Assign_rlsContext.class,0);
		}
		public AssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).enterAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).exitAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BashParserVisitor ) return ((BashParserVisitor<? extends T>)visitor).visitAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignContext assign() throws RecognitionException {
		AssignContext _localctx = new AssignContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_assign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(VARNAME);
			setState(94);
			match(EQ);
			setState(95);
			assign_rls();
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

	public static class Assign_rlsContext extends ParserRuleContext {
		public List<TerminalNode> VARNAME() { return getTokens(BashParser.VARNAME); }
		public TerminalNode VARNAME(int i) {
			return getToken(BashParser.VARNAME, i);
		}
		public List<TerminalNode> PUNCS() { return getTokens(BashParser.PUNCS); }
		public TerminalNode PUNCS(int i) {
			return getToken(BashParser.PUNCS, i);
		}
		public List<TerminalNode> NUM() { return getTokens(BashParser.NUM); }
		public TerminalNode NUM(int i) {
			return getToken(BashParser.NUM, i);
		}
		public List<Squote_strContext> squote_str() {
			return getRuleContexts(Squote_strContext.class);
		}
		public Squote_strContext squote_str(int i) {
			return getRuleContext(Squote_strContext.class,i);
		}
		public List<TerminalNode> VAR() { return getTokens(BashParser.VAR); }
		public TerminalNode VAR(int i) {
			return getToken(BashParser.VAR, i);
		}
		public List<Dquote_strContext> dquote_str() {
			return getRuleContexts(Dquote_strContext.class);
		}
		public Dquote_strContext dquote_str(int i) {
			return getRuleContext(Dquote_strContext.class,i);
		}
		public List<SubstContext> subst() {
			return getRuleContexts(SubstContext.class);
		}
		public SubstContext subst(int i) {
			return getRuleContext(SubstContext.class,i);
		}
		public Assign_rlsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign_rls; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).enterAssign_rls(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).exitAssign_rls(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BashParserVisitor ) return ((BashParserVisitor<? extends T>)visitor).visitAssign_rls(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Assign_rlsContext assign_rls() throws RecognitionException {
		Assign_rlsContext _localctx = new Assign_rlsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_assign_rls);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(104);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case VARNAME:
						{
						setState(97);
						match(VARNAME);
						}
						break;
					case PUNCS:
						{
						setState(98);
						match(PUNCS);
						}
						break;
					case NUM:
						{
						setState(99);
						match(NUM);
						}
						break;
					case SQUOTE:
						{
						setState(100);
						squote_str();
						}
						break;
					case VAR:
						{
						setState(101);
						match(VAR);
						}
						break;
					case DQUOTE:
						{
						setState(102);
						dquote_str();
						}
						break;
					case DOLLAR_LPAREN:
					case LT_LPAREN:
					case GT_LPAREN:
					case BACKTICK:
					case DOLLAR_DLPAREN:
					case DOLLAR_LCURLY:
						{
						setState(103);
						subst();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(108);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
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

	public static class ExecContext extends ParserRuleContext {
		public ProgContext prog() {
			return getRuleContext(ProgContext.class,0);
		}
		public Exec_suffixContext exec_suffix() {
			return getRuleContext(Exec_suffixContext.class,0);
		}
		public ExecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).enterExec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).exitExec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BashParserVisitor ) return ((BashParserVisitor<? extends T>)visitor).visitExec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExecContext exec() throws RecognitionException {
		ExecContext _localctx = new ExecContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_exec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			prog();
			setState(111);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(110);
				exec_suffix(0);
				}
				break;
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

	public static class ProgContext extends ParserRuleContext {
		public List<TerminalNode> VARNAME() { return getTokens(BashParser.VARNAME); }
		public TerminalNode VARNAME(int i) {
			return getToken(BashParser.VARNAME, i);
		}
		public List<TerminalNode> PUNCS() { return getTokens(BashParser.PUNCS); }
		public TerminalNode PUNCS(int i) {
			return getToken(BashParser.PUNCS, i);
		}
		public List<TerminalNode> NUM() { return getTokens(BashParser.NUM); }
		public TerminalNode NUM(int i) {
			return getToken(BashParser.NUM, i);
		}
		public List<TerminalNode> VAR() { return getTokens(BashParser.VAR); }
		public TerminalNode VAR(int i) {
			return getToken(BashParser.VAR, i);
		}
		public List<Squote_strContext> squote_str() {
			return getRuleContexts(Squote_strContext.class);
		}
		public Squote_strContext squote_str(int i) {
			return getRuleContext(Squote_strContext.class,i);
		}
		public List<Dquote_strContext> dquote_str() {
			return getRuleContexts(Dquote_strContext.class);
		}
		public Dquote_strContext dquote_str(int i) {
			return getRuleContext(Dquote_strContext.class,i);
		}
		public List<SubstContext> subst() {
			return getRuleContexts(SubstContext.class);
		}
		public SubstContext subst(int i) {
			return getRuleContext(SubstContext.class,i);
		}
		public List<TerminalNode> EQ() { return getTokens(BashParser.EQ); }
		public TerminalNode EQ(int i) {
			return getToken(BashParser.EQ, i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).exitProg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BashParserVisitor ) return ((BashParserVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_prog);
		try {
			int _alt;
			setState(148);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VARNAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(113);
				match(VARNAME);
				setState(123);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						setState(121);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case VARNAME:
							{
							setState(114);
							match(VARNAME);
							}
							break;
						case PUNCS:
							{
							setState(115);
							match(PUNCS);
							}
							break;
						case NUM:
							{
							setState(116);
							match(NUM);
							}
							break;
						case VAR:
							{
							setState(117);
							match(VAR);
							}
							break;
						case SQUOTE:
							{
							setState(118);
							squote_str();
							}
							break;
						case DQUOTE:
							{
							setState(119);
							dquote_str();
							}
							break;
						case DOLLAR_LPAREN:
						case LT_LPAREN:
						case GT_LPAREN:
						case BACKTICK:
						case DOLLAR_DLPAREN:
						case DOLLAR_LCURLY:
							{
							setState(120);
							subst();
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						} 
					}
					setState(125);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
				}
				}
				break;
			case PUNCS:
			case NUM:
			case EQ:
			case VAR:
			case SQUOTE:
			case DQUOTE:
			case DOLLAR_LPAREN:
			case LT_LPAREN:
			case GT_LPAREN:
			case BACKTICK:
			case DOLLAR_DLPAREN:
			case DOLLAR_LCURLY:
				enterOuterAlt(_localctx, 2);
				{
				setState(133);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PUNCS:
					{
					setState(126);
					match(PUNCS);
					}
					break;
				case NUM:
					{
					setState(127);
					match(NUM);
					}
					break;
				case EQ:
					{
					setState(128);
					match(EQ);
					}
					break;
				case VAR:
					{
					setState(129);
					match(VAR);
					}
					break;
				case SQUOTE:
					{
					setState(130);
					squote_str();
					}
					break;
				case DQUOTE:
					{
					setState(131);
					dquote_str();
					}
					break;
				case DOLLAR_LPAREN:
				case LT_LPAREN:
				case GT_LPAREN:
				case BACKTICK:
				case DOLLAR_DLPAREN:
				case DOLLAR_LCURLY:
					{
					setState(132);
					subst();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(145);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						setState(143);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case VARNAME:
							{
							setState(135);
							match(VARNAME);
							}
							break;
						case PUNCS:
							{
							setState(136);
							match(PUNCS);
							}
							break;
						case NUM:
							{
							setState(137);
							match(NUM);
							}
							break;
						case EQ:
							{
							setState(138);
							match(EQ);
							}
							break;
						case VAR:
							{
							setState(139);
							match(VAR);
							}
							break;
						case SQUOTE:
							{
							setState(140);
							squote_str();
							}
							break;
						case DQUOTE:
							{
							setState(141);
							dquote_str();
							}
							break;
						case DOLLAR_LPAREN:
						case LT_LPAREN:
						case GT_LPAREN:
						case BACKTICK:
						case DOLLAR_DLPAREN:
						case DOLLAR_LCURLY:
							{
							setState(142);
							subst();
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						} 
					}
					setState(147);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				}
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

	public static class Exec_suffixContext extends ParserRuleContext {
		public RedirContext redir() {
			return getRuleContext(RedirContext.class,0);
		}
		public TerminalNode BLANK() { return getToken(BashParser.BLANK, 0); }
		public ArgContext arg() {
			return getRuleContext(ArgContext.class,0);
		}
		public Exec_suffixContext exec_suffix() {
			return getRuleContext(Exec_suffixContext.class,0);
		}
		public Exec_suffixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exec_suffix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).enterExec_suffix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).exitExec_suffix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BashParserVisitor ) return ((BashParserVisitor<? extends T>)visitor).visitExec_suffix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Exec_suffixContext exec_suffix() throws RecognitionException {
		return exec_suffix(0);
	}

	private Exec_suffixContext exec_suffix(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Exec_suffixContext _localctx = new Exec_suffixContext(_ctx, _parentState);
		Exec_suffixContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_exec_suffix, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(157);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(152);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==BLANK) {
					{
					setState(151);
					match(BLANK);
					}
				}

				setState(154);
				redir();
				}
				break;
			case 2:
				{
				setState(155);
				match(BLANK);
				setState(156);
				arg();
				}
				break;
			}
			}
			_ctx.stop = _input.LT(-1);
			setState(170);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Exec_suffixContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_exec_suffix);
					setState(159);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(166);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
					case 1:
						{
						setState(161);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==BLANK) {
							{
							setState(160);
							match(BLANK);
							}
						}

						setState(163);
						redir();
						}
						break;
					case 2:
						{
						setState(164);
						match(BLANK);
						setState(165);
						arg();
						}
						break;
					}
					}
					} 
				}
				setState(172);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class RedirContext extends ParserRuleContext {
		public TerminalNode NUM() { return getToken(BashParser.NUM, 0); }
		public Redir_opContext redir_op() {
			return getRuleContext(Redir_opContext.class,0);
		}
		public ArgContext arg() {
			return getRuleContext(ArgContext.class,0);
		}
		public TerminalNode BLANK() { return getToken(BashParser.BLANK, 0); }
		public RedirContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_redir; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).enterRedir(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).exitRedir(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BashParserVisitor ) return ((BashParserVisitor<? extends T>)visitor).visitRedir(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RedirContext redir() throws RecognitionException {
		RedirContext _localctx = new RedirContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_redir);
		int _la;
		try {
			setState(186);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUM:
				enterOuterAlt(_localctx, 1);
				{
				setState(173);
				match(NUM);
				setState(174);
				redir_op();
				setState(176);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==BLANK) {
					{
					setState(175);
					match(BLANK);
					}
				}

				setState(178);
				arg();
				}
				break;
			case LT:
			case GT:
			case LT_AND:
			case GT_AND:
			case AND_GT:
			case AND_DGT:
			case DLT:
			case TLT:
			case DLT_DASH:
			case DGT:
			case LTGT:
			case GTPIPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(180);
				redir_op();
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==BLANK) {
					{
					setState(181);
					match(BLANK);
					}
				}

				setState(184);
				arg();
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

	public static class Redir_opContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(BashParser.LT, 0); }
		public TerminalNode GT() { return getToken(BashParser.GT, 0); }
		public TerminalNode LT_AND() { return getToken(BashParser.LT_AND, 0); }
		public TerminalNode GT_AND() { return getToken(BashParser.GT_AND, 0); }
		public TerminalNode AND_GT() { return getToken(BashParser.AND_GT, 0); }
		public TerminalNode AND_DGT() { return getToken(BashParser.AND_DGT, 0); }
		public TerminalNode DLT() { return getToken(BashParser.DLT, 0); }
		public TerminalNode TLT() { return getToken(BashParser.TLT, 0); }
		public TerminalNode DLT_DASH() { return getToken(BashParser.DLT_DASH, 0); }
		public TerminalNode DGT() { return getToken(BashParser.DGT, 0); }
		public TerminalNode LTGT() { return getToken(BashParser.LTGT, 0); }
		public TerminalNode GTPIPE() { return getToken(BashParser.GTPIPE, 0); }
		public Redir_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_redir_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).enterRedir_op(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).exitRedir_op(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BashParserVisitor ) return ((BashParserVisitor<? extends T>)visitor).visitRedir_op(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Redir_opContext redir_op() throws RecognitionException {
		Redir_opContext _localctx = new Redir_opContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_redir_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LT) | (1L << GT) | (1L << LT_AND) | (1L << GT_AND) | (1L << AND_GT) | (1L << AND_DGT) | (1L << DLT) | (1L << TLT) | (1L << DLT_DASH) | (1L << DGT) | (1L << LTGT) | (1L << GTPIPE))) != 0)) ) {
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

	public static class ArgContext extends ParserRuleContext {
		public List<TerminalNode> VARNAME() { return getTokens(BashParser.VARNAME); }
		public TerminalNode VARNAME(int i) {
			return getToken(BashParser.VARNAME, i);
		}
		public List<TerminalNode> PUNCS() { return getTokens(BashParser.PUNCS); }
		public TerminalNode PUNCS(int i) {
			return getToken(BashParser.PUNCS, i);
		}
		public List<TerminalNode> ESC_CHAR() { return getTokens(BashParser.ESC_CHAR); }
		public TerminalNode ESC_CHAR(int i) {
			return getToken(BashParser.ESC_CHAR, i);
		}
		public List<TerminalNode> AND() { return getTokens(BashParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(BashParser.AND, i);
		}
		public List<TerminalNode> EQ() { return getTokens(BashParser.EQ); }
		public TerminalNode EQ(int i) {
			return getToken(BashParser.EQ, i);
		}
		public List<TerminalNode> NUM() { return getTokens(BashParser.NUM); }
		public TerminalNode NUM(int i) {
			return getToken(BashParser.NUM, i);
		}
		public List<Squote_strContext> squote_str() {
			return getRuleContexts(Squote_strContext.class);
		}
		public Squote_strContext squote_str(int i) {
			return getRuleContext(Squote_strContext.class,i);
		}
		public List<TerminalNode> VAR() { return getTokens(BashParser.VAR); }
		public TerminalNode VAR(int i) {
			return getToken(BashParser.VAR, i);
		}
		public List<Dquote_strContext> dquote_str() {
			return getRuleContexts(Dquote_strContext.class);
		}
		public Dquote_strContext dquote_str(int i) {
			return getRuleContext(Dquote_strContext.class,i);
		}
		public List<SubstContext> subst() {
			return getRuleContexts(SubstContext.class);
		}
		public SubstContext subst(int i) {
			return getRuleContext(SubstContext.class,i);
		}
		public List<Pure_curlyContext> pure_curly() {
			return getRuleContexts(Pure_curlyContext.class);
		}
		public Pure_curlyContext pure_curly(int i) {
			return getRuleContext(Pure_curlyContext.class,i);
		}
		public ArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).enterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).exitArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BashParserVisitor ) return ((BashParserVisitor<? extends T>)visitor).visitArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgContext arg() throws RecognitionException {
		ArgContext _localctx = new ArgContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_arg);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(201); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					setState(201);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case VARNAME:
						{
						setState(190);
						match(VARNAME);
						}
						break;
					case PUNCS:
						{
						setState(191);
						match(PUNCS);
						}
						break;
					case ESC_CHAR:
						{
						setState(192);
						match(ESC_CHAR);
						}
						break;
					case AND:
						{
						setState(193);
						match(AND);
						}
						break;
					case EQ:
						{
						setState(194);
						match(EQ);
						}
						break;
					case NUM:
						{
						setState(195);
						match(NUM);
						}
						break;
					case SQUOTE:
						{
						setState(196);
						squote_str();
						}
						break;
					case VAR:
						{
						setState(197);
						match(VAR);
						}
						break;
					case DQUOTE:
						{
						setState(198);
						dquote_str();
						}
						break;
					case DOLLAR_LPAREN:
					case LT_LPAREN:
					case GT_LPAREN:
					case BACKTICK:
					case DOLLAR_DLPAREN:
					case DOLLAR_LCURLY:
						{
						setState(199);
						subst();
						}
						break;
					case LCURLY:
					case RCURLY:
						{
						setState(200);
						pure_curly();
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
				setState(203); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
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

	public static class Dquote_strContext extends ParserRuleContext {
		public List<TerminalNode> DQUOTE() { return getTokens(BashParser.DQUOTE); }
		public TerminalNode DQUOTE(int i) {
			return getToken(BashParser.DQUOTE, i);
		}
		public List<TerminalNode> VARNAME() { return getTokens(BashParser.VARNAME); }
		public TerminalNode VARNAME(int i) {
			return getToken(BashParser.VARNAME, i);
		}
		public List<TerminalNode> NUM() { return getTokens(BashParser.NUM); }
		public TerminalNode NUM(int i) {
			return getToken(BashParser.NUM, i);
		}
		public List<TerminalNode> PUNCS() { return getTokens(BashParser.PUNCS); }
		public TerminalNode PUNCS(int i) {
			return getToken(BashParser.PUNCS, i);
		}
		public List<TerminalNode> LT() { return getTokens(BashParser.LT); }
		public TerminalNode LT(int i) {
			return getToken(BashParser.LT, i);
		}
		public List<TerminalNode> GT() { return getTokens(BashParser.GT); }
		public TerminalNode GT(int i) {
			return getToken(BashParser.GT, i);
		}
		public List<TerminalNode> VAR() { return getTokens(BashParser.VAR); }
		public TerminalNode VAR(int i) {
			return getToken(BashParser.VAR, i);
		}
		public List<SubstContext> subst() {
			return getRuleContexts(SubstContext.class);
		}
		public SubstContext subst(int i) {
			return getRuleContext(SubstContext.class,i);
		}
		public Dquote_strContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dquote_str; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).enterDquote_str(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).exitDquote_str(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BashParserVisitor ) return ((BashParserVisitor<? extends T>)visitor).visitDquote_str(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Dquote_strContext dquote_str() throws RecognitionException {
		Dquote_strContext _localctx = new Dquote_strContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_dquote_str);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			match(DQUOTE);
			setState(215);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VARNAME) | (1L << PUNCS) | (1L << NUM) | (1L << VAR) | (1L << DOLLAR_LPAREN) | (1L << LT_LPAREN) | (1L << GT_LPAREN) | (1L << BACKTICK) | (1L << DOLLAR_DLPAREN) | (1L << DOLLAR_LCURLY) | (1L << LT) | (1L << GT))) != 0)) {
				{
				setState(213);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case VARNAME:
					{
					setState(206);
					match(VARNAME);
					}
					break;
				case NUM:
					{
					setState(207);
					match(NUM);
					}
					break;
				case PUNCS:
					{
					setState(208);
					match(PUNCS);
					}
					break;
				case LT:
					{
					setState(209);
					match(LT);
					}
					break;
				case GT:
					{
					setState(210);
					match(GT);
					}
					break;
				case VAR:
					{
					setState(211);
					match(VAR);
					}
					break;
				case DOLLAR_LPAREN:
				case LT_LPAREN:
				case GT_LPAREN:
				case BACKTICK:
				case DOLLAR_DLPAREN:
				case DOLLAR_LCURLY:
					{
					setState(212);
					subst();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(217);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(218);
			match(DQUOTE);
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

	public static class Squote_strContext extends ParserRuleContext {
		public List<TerminalNode> SQUOTE() { return getTokens(BashParser.SQUOTE); }
		public TerminalNode SQUOTE(int i) {
			return getToken(BashParser.SQUOTE, i);
		}
		public List<TerminalNode> VARNAME() { return getTokens(BashParser.VARNAME); }
		public TerminalNode VARNAME(int i) {
			return getToken(BashParser.VARNAME, i);
		}
		public List<TerminalNode> PUNCS() { return getTokens(BashParser.PUNCS); }
		public TerminalNode PUNCS(int i) {
			return getToken(BashParser.PUNCS, i);
		}
		public List<TerminalNode> NUM() { return getTokens(BashParser.NUM); }
		public TerminalNode NUM(int i) {
			return getToken(BashParser.NUM, i);
		}
		public Squote_strContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_squote_str; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).enterSquote_str(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).exitSquote_str(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BashParserVisitor ) return ((BashParserVisitor<? extends T>)visitor).visitSquote_str(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Squote_strContext squote_str() throws RecognitionException {
		Squote_strContext _localctx = new Squote_strContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_squote_str);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			match(SQUOTE);
			setState(224);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VARNAME) | (1L << PUNCS) | (1L << NUM))) != 0)) {
				{
				{
				setState(221);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VARNAME) | (1L << PUNCS) | (1L << NUM))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(226);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(227);
			match(SQUOTE);
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

	public static class SubstContext extends ParserRuleContext {
		public CstContext cst() {
			return getRuleContext(CstContext.class,0);
		}
		public LpstContext lpst() {
			return getRuleContext(LpstContext.class,0);
		}
		public RpstContext rpst() {
			return getRuleContext(RpstContext.class,0);
		}
		public ArithContext arith() {
			return getRuleContext(ArithContext.class,0);
		}
		public Param_expContext param_exp() {
			return getRuleContext(Param_expContext.class,0);
		}
		public SubstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subst; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).enterSubst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).exitSubst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BashParserVisitor ) return ((BashParserVisitor<? extends T>)visitor).visitSubst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubstContext subst() throws RecognitionException {
		SubstContext _localctx = new SubstContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_subst);
		try {
			setState(234);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DOLLAR_LPAREN:
			case BACKTICK:
				enterOuterAlt(_localctx, 1);
				{
				setState(229);
				cst();
				}
				break;
			case LT_LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(230);
				lpst();
				}
				break;
			case GT_LPAREN:
				enterOuterAlt(_localctx, 3);
				{
				setState(231);
				rpst();
				}
				break;
			case DOLLAR_DLPAREN:
				enterOuterAlt(_localctx, 4);
				{
				setState(232);
				arith();
				}
				break;
			case DOLLAR_LCURLY:
				enterOuterAlt(_localctx, 5);
				{
				setState(233);
				param_exp();
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

	public static class CstContext extends ParserRuleContext {
		public TerminalNode DOLLAR_LPAREN() { return getToken(BashParser.DOLLAR_LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(BashParser.RPAREN, 0); }
		public PipelineContext pipeline() {
			return getRuleContext(PipelineContext.class,0);
		}
		public List<TerminalNode> BACKTICK() { return getTokens(BashParser.BACKTICK); }
		public TerminalNode BACKTICK(int i) {
			return getToken(BashParser.BACKTICK, i);
		}
		public CstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cst; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).enterCst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).exitCst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BashParserVisitor ) return ((BashParserVisitor<? extends T>)visitor).visitCst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CstContext cst() throws RecognitionException {
		CstContext _localctx = new CstContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_cst);
		int _la;
		try {
			setState(246);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DOLLAR_LPAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(236);
				match(DOLLAR_LPAREN);
				setState(238);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VARNAME) | (1L << PUNCS) | (1L << NUM) | (1L << BLANK) | (1L << EQ) | (1L << VAR) | (1L << SQUOTE) | (1L << DQUOTE) | (1L << DOLLAR_LPAREN) | (1L << LT_LPAREN) | (1L << GT_LPAREN) | (1L << BACKTICK) | (1L << DOLLAR_DLPAREN) | (1L << DOLLAR_LCURLY) | (1L << LT) | (1L << GT) | (1L << LT_AND) | (1L << GT_AND) | (1L << AND_GT) | (1L << AND_DGT) | (1L << DLT) | (1L << TLT) | (1L << DLT_DASH) | (1L << DGT) | (1L << LTGT) | (1L << GTPIPE))) != 0)) {
					{
					setState(237);
					pipeline(0);
					}
				}

				setState(240);
				match(RPAREN);
				}
				break;
			case BACKTICK:
				enterOuterAlt(_localctx, 2);
				{
				setState(241);
				match(BACKTICK);
				setState(243);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
				case 1:
					{
					setState(242);
					pipeline(0);
					}
					break;
				}
				setState(245);
				match(BACKTICK);
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

	public static class LpstContext extends ParserRuleContext {
		public TerminalNode LT_LPAREN() { return getToken(BashParser.LT_LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(BashParser.RPAREN, 0); }
		public PipelineContext pipeline() {
			return getRuleContext(PipelineContext.class,0);
		}
		public LpstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lpst; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).enterLpst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).exitLpst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BashParserVisitor ) return ((BashParserVisitor<? extends T>)visitor).visitLpst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LpstContext lpst() throws RecognitionException {
		LpstContext _localctx = new LpstContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_lpst);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248);
			match(LT_LPAREN);
			setState(250);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VARNAME) | (1L << PUNCS) | (1L << NUM) | (1L << BLANK) | (1L << EQ) | (1L << VAR) | (1L << SQUOTE) | (1L << DQUOTE) | (1L << DOLLAR_LPAREN) | (1L << LT_LPAREN) | (1L << GT_LPAREN) | (1L << BACKTICK) | (1L << DOLLAR_DLPAREN) | (1L << DOLLAR_LCURLY) | (1L << LT) | (1L << GT) | (1L << LT_AND) | (1L << GT_AND) | (1L << AND_GT) | (1L << AND_DGT) | (1L << DLT) | (1L << TLT) | (1L << DLT_DASH) | (1L << DGT) | (1L << LTGT) | (1L << GTPIPE))) != 0)) {
				{
				setState(249);
				pipeline(0);
				}
			}

			setState(252);
			match(RPAREN);
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

	public static class RpstContext extends ParserRuleContext {
		public TerminalNode GT_LPAREN() { return getToken(BashParser.GT_LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(BashParser.RPAREN, 0); }
		public PipelineContext pipeline() {
			return getRuleContext(PipelineContext.class,0);
		}
		public RpstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rpst; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).enterRpst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).exitRpst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BashParserVisitor ) return ((BashParserVisitor<? extends T>)visitor).visitRpst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RpstContext rpst() throws RecognitionException {
		RpstContext _localctx = new RpstContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_rpst);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			match(GT_LPAREN);
			setState(256);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VARNAME) | (1L << PUNCS) | (1L << NUM) | (1L << BLANK) | (1L << EQ) | (1L << VAR) | (1L << SQUOTE) | (1L << DQUOTE) | (1L << DOLLAR_LPAREN) | (1L << LT_LPAREN) | (1L << GT_LPAREN) | (1L << BACKTICK) | (1L << DOLLAR_DLPAREN) | (1L << DOLLAR_LCURLY) | (1L << LT) | (1L << GT) | (1L << LT_AND) | (1L << GT_AND) | (1L << AND_GT) | (1L << AND_DGT) | (1L << DLT) | (1L << TLT) | (1L << DLT_DASH) | (1L << DGT) | (1L << LTGT) | (1L << GTPIPE))) != 0)) {
				{
				setState(255);
				pipeline(0);
				}
			}

			setState(258);
			match(RPAREN);
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

	public static class ArithContext extends ParserRuleContext {
		public TerminalNode DOLLAR_DLPAREN() { return getToken(BashParser.DOLLAR_DLPAREN, 0); }
		public TerminalNode DRPAREN() { return getToken(BashParser.DRPAREN, 0); }
		public PipelineContext pipeline() {
			return getRuleContext(PipelineContext.class,0);
		}
		public ArithContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arith; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).enterArith(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).exitArith(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BashParserVisitor ) return ((BashParserVisitor<? extends T>)visitor).visitArith(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArithContext arith() throws RecognitionException {
		ArithContext _localctx = new ArithContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_arith);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			match(DOLLAR_DLPAREN);
			setState(262);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VARNAME) | (1L << PUNCS) | (1L << NUM) | (1L << BLANK) | (1L << EQ) | (1L << VAR) | (1L << SQUOTE) | (1L << DQUOTE) | (1L << DOLLAR_LPAREN) | (1L << LT_LPAREN) | (1L << GT_LPAREN) | (1L << BACKTICK) | (1L << DOLLAR_DLPAREN) | (1L << DOLLAR_LCURLY) | (1L << LT) | (1L << GT) | (1L << LT_AND) | (1L << GT_AND) | (1L << AND_GT) | (1L << AND_DGT) | (1L << DLT) | (1L << TLT) | (1L << DLT_DASH) | (1L << DGT) | (1L << LTGT) | (1L << GTPIPE))) != 0)) {
				{
				setState(261);
				pipeline(0);
				}
			}

			setState(264);
			match(DRPAREN);
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

	public static class Param_expContext extends ParserRuleContext {
		public TerminalNode DOLLAR_LCURLY() { return getToken(BashParser.DOLLAR_LCURLY, 0); }
		public TerminalNode VARNAME() { return getToken(BashParser.VARNAME, 0); }
		public TerminalNode RCURLY() { return getToken(BashParser.RCURLY, 0); }
		public Param_exp_opContext param_exp_op() {
			return getRuleContext(Param_exp_opContext.class,0);
		}
		public Assign_rlsContext assign_rls() {
			return getRuleContext(Assign_rlsContext.class,0);
		}
		public TerminalNode BLANK() { return getToken(BashParser.BLANK, 0); }
		public TerminalNode HASH() { return getToken(BashParser.HASH, 0); }
		public Param_expContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).enterParam_exp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).exitParam_exp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BashParserVisitor ) return ((BashParserVisitor<? extends T>)visitor).visitParam_exp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Param_expContext param_exp() throws RecognitionException {
		Param_expContext _localctx = new Param_expContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_param_exp);
		int _la;
		try {
			setState(281);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(266);
				match(DOLLAR_LCURLY);
				setState(267);
				match(VARNAME);
				setState(274);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << PERCENT) | (1L << DPERCENT) | (1L << HASH) | (1L << DHASH))) != 0)) {
					{
					setState(268);
					param_exp_op();
					setState(270);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==BLANK) {
						{
						setState(269);
						match(BLANK);
						}
					}

					setState(272);
					assign_rls();
					}
				}

				setState(276);
				match(RCURLY);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(277);
				match(DOLLAR_LCURLY);
				setState(278);
				match(HASH);
				setState(279);
				match(VARNAME);
				setState(280);
				match(RCURLY);
				}
				break;
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

	public static class Param_exp_opContext extends ParserRuleContext {
		public TerminalNode COMMA() { return getToken(BashParser.COMMA, 0); }
		public TerminalNode DASH() { return getToken(BashParser.DASH, 0); }
		public TerminalNode EQ() { return getToken(BashParser.EQ, 0); }
		public TerminalNode QMARK() { return getToken(BashParser.QMARK, 0); }
		public TerminalNode PLUS() { return getToken(BashParser.PLUS, 0); }
		public TerminalNode PERCENT() { return getToken(BashParser.PERCENT, 0); }
		public TerminalNode DPERCENT() { return getToken(BashParser.DPERCENT, 0); }
		public TerminalNode HASH() { return getToken(BashParser.HASH, 0); }
		public TerminalNode DHASH() { return getToken(BashParser.DHASH, 0); }
		public Param_exp_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param_exp_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).enterParam_exp_op(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).exitParam_exp_op(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BashParserVisitor ) return ((BashParserVisitor<? extends T>)visitor).visitParam_exp_op(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Param_exp_opContext param_exp_op() throws RecognitionException {
		Param_exp_opContext _localctx = new Param_exp_opContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_param_exp_op);
		int _la;
		try {
			setState(289);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COMMA:
				enterOuterAlt(_localctx, 1);
				{
				setState(283);
				match(COMMA);
				setState(284);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << DASH) | (1L << QMARK) | (1L << PLUS))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case PERCENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(285);
				match(PERCENT);
				}
				break;
			case DPERCENT:
				enterOuterAlt(_localctx, 3);
				{
				setState(286);
				match(DPERCENT);
				}
				break;
			case HASH:
				enterOuterAlt(_localctx, 4);
				{
				setState(287);
				match(HASH);
				}
				break;
			case DHASH:
				enterOuterAlt(_localctx, 5);
				{
				setState(288);
				match(DHASH);
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

	public static class GrpContext extends ParserRuleContext {
		public Paren_grpContext paren_grp() {
			return getRuleContext(Paren_grpContext.class,0);
		}
		public Curly_grpContext curly_grp() {
			return getRuleContext(Curly_grpContext.class,0);
		}
		public GrpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).enterGrp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).exitGrp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BashParserVisitor ) return ((BashParserVisitor<? extends T>)visitor).visitGrp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GrpContext grp() throws RecognitionException {
		GrpContext _localctx = new GrpContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_grp);
		try {
			setState(293);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(291);
				paren_grp();
				}
				break;
			case LCURLY:
				enterOuterAlt(_localctx, 2);
				{
				setState(292);
				curly_grp();
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

	public static class Paren_grpContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(BashParser.LPAREN, 0); }
		public PipelineContext pipeline() {
			return getRuleContext(PipelineContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(BashParser.RPAREN, 0); }
		public Paren_grpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paren_grp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).enterParen_grp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).exitParen_grp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BashParserVisitor ) return ((BashParserVisitor<? extends T>)visitor).visitParen_grp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Paren_grpContext paren_grp() throws RecognitionException {
		Paren_grpContext _localctx = new Paren_grpContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_paren_grp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(295);
			match(LPAREN);
			setState(296);
			pipeline(0);
			setState(297);
			match(RPAREN);
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

	public static class Pure_curlyContext extends ParserRuleContext {
		public TerminalNode LCURLY() { return getToken(BashParser.LCURLY, 0); }
		public TerminalNode RCURLY() { return getToken(BashParser.RCURLY, 0); }
		public Pure_curlyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pure_curly; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).enterPure_curly(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).exitPure_curly(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BashParserVisitor ) return ((BashParserVisitor<? extends T>)visitor).visitPure_curly(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Pure_curlyContext pure_curly() throws RecognitionException {
		Pure_curlyContext _localctx = new Pure_curlyContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_pure_curly);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			_la = _input.LA(1);
			if ( !(_la==LCURLY || _la==RCURLY) ) {
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

	public static class Curly_grpContext extends ParserRuleContext {
		public TerminalNode LCURLY() { return getToken(BashParser.LCURLY, 0); }
		public PipelineContext pipeline() {
			return getRuleContext(PipelineContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(BashParser.SEMI, 0); }
		public TerminalNode RCURLY() { return getToken(BashParser.RCURLY, 0); }
		public TerminalNode BLANK() { return getToken(BashParser.BLANK, 0); }
		public Curly_grpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_curly_grp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).enterCurly_grp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BashParserListener ) ((BashParserListener)listener).exitCurly_grp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BashParserVisitor ) return ((BashParserVisitor<? extends T>)visitor).visitCurly_grp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Curly_grpContext curly_grp() throws RecognitionException {
		Curly_grpContext _localctx = new Curly_grpContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_curly_grp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(301);
			match(LCURLY);
			setState(302);
			pipeline(0);
			setState(303);
			match(SEMI);
			setState(305);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BLANK) {
				{
				setState(304);
				match(BLANK);
				}
			}

			setState(307);
			match(RCURLY);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 0:
			return pipeline_sempred((PipelineContext)_localctx, predIndex);
		case 2:
			return exec_prefix_sempred((Exec_prefixContext)_localctx, predIndex);
		case 7:
			return exec_suffix_sempred((Exec_suffixContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean pipeline_sempred(PipelineContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean exec_prefix_sempred(Exec_prefixContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean exec_suffix_sempred(Exec_suffixContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\62\u0138\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\7\29\n\2\f\2\16\2<\13\2\3\3\5\3?\n\3\3\3\3\3"+
		"\3\3\5\3D\n\3\3\3\3\3\5\3H\n\3\3\3\5\3K\n\3\3\4\3\4\3\4\5\4P\n\4\3\4\3"+
		"\4\5\4T\n\4\3\4\3\4\3\4\5\4Y\n\4\7\4[\n\4\f\4\16\4^\13\4\3\5\3\5\3\5\3"+
		"\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6k\n\6\f\6\16\6n\13\6\3\7\3\7\5\7r\n"+
		"\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b|\n\b\f\b\16\b\177\13\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\5\b\u0088\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b"+
		"\u0092\n\b\f\b\16\b\u0095\13\b\5\b\u0097\n\b\3\t\3\t\5\t\u009b\n\t\3\t"+
		"\3\t\3\t\5\t\u00a0\n\t\3\t\3\t\5\t\u00a4\n\t\3\t\3\t\3\t\5\t\u00a9\n\t"+
		"\7\t\u00ab\n\t\f\t\16\t\u00ae\13\t\3\n\3\n\3\n\5\n\u00b3\n\n\3\n\3\n\3"+
		"\n\3\n\5\n\u00b9\n\n\3\n\3\n\5\n\u00bd\n\n\3\13\3\13\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u00cc\n\f\r\f\16\f\u00cd\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\7\r\u00d8\n\r\f\r\16\r\u00db\13\r\3\r\3\r\3\16\3\16"+
		"\7\16\u00e1\n\16\f\16\16\16\u00e4\13\16\3\16\3\16\3\17\3\17\3\17\3\17"+
		"\3\17\5\17\u00ed\n\17\3\20\3\20\5\20\u00f1\n\20\3\20\3\20\3\20\5\20\u00f6"+
		"\n\20\3\20\5\20\u00f9\n\20\3\21\3\21\5\21\u00fd\n\21\3\21\3\21\3\22\3"+
		"\22\5\22\u0103\n\22\3\22\3\22\3\23\3\23\5\23\u0109\n\23\3\23\3\23\3\24"+
		"\3\24\3\24\3\24\5\24\u0111\n\24\3\24\3\24\5\24\u0115\n\24\3\24\3\24\3"+
		"\24\3\24\3\24\5\24\u011c\n\24\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u0124"+
		"\n\25\3\26\3\26\5\26\u0128\n\26\3\27\3\27\3\27\3\27\3\30\3\30\3\31\3\31"+
		"\3\31\3\31\5\31\u0134\n\31\3\31\3\31\3\31\2\5\2\6\20\32\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36 \"$&(*,.\60\2\6\3\2\30#\3\2\3\5\5\2\7\7++-.\3"+
		"\2\23\24\2\u0174\2\62\3\2\2\2\4>\3\2\2\2\6L\3\2\2\2\b_\3\2\2\2\nl\3\2"+
		"\2\2\fo\3\2\2\2\16\u0096\3\2\2\2\20\u0098\3\2\2\2\22\u00bc\3\2\2\2\24"+
		"\u00be\3\2\2\2\26\u00cb\3\2\2\2\30\u00cf\3\2\2\2\32\u00de\3\2\2\2\34\u00ec"+
		"\3\2\2\2\36\u00f8\3\2\2\2 \u00fa\3\2\2\2\"\u0100\3\2\2\2$\u0106\3\2\2"+
		"\2&\u011b\3\2\2\2(\u0123\3\2\2\2*\u0127\3\2\2\2,\u0129\3\2\2\2.\u012d"+
		"\3\2\2\2\60\u012f\3\2\2\2\62\63\b\2\1\2\63\64\5\4\3\2\64:\3\2\2\2\65\66"+
		"\f\4\2\2\66\67\7\26\2\2\679\5\4\3\28\65\3\2\2\29<\3\2\2\2:8\3\2\2\2:;"+
		"\3\2\2\2;\3\3\2\2\2<:\3\2\2\2=?\7\6\2\2>=\3\2\2\2>?\3\2\2\2?G\3\2\2\2"+
		"@A\5\6\4\2AB\7\6\2\2BD\3\2\2\2C@\3\2\2\2CD\3\2\2\2DE\3\2\2\2EH\5\f\7\2"+
		"FH\5\6\4\2GC\3\2\2\2GF\3\2\2\2HJ\3\2\2\2IK\7\6\2\2JI\3\2\2\2JK\3\2\2\2"+
		"K\5\3\2\2\2LO\b\4\1\2MP\5\22\n\2NP\5\b\5\2OM\3\2\2\2ON\3\2\2\2P\\\3\2"+
		"\2\2QX\f\4\2\2RT\7\6\2\2SR\3\2\2\2ST\3\2\2\2TU\3\2\2\2UY\5\22\n\2VW\7"+
		"\6\2\2WY\5\b\5\2XS\3\2\2\2XV\3\2\2\2Y[\3\2\2\2ZQ\3\2\2\2[^\3\2\2\2\\Z"+
		"\3\2\2\2\\]\3\2\2\2]\7\3\2\2\2^\\\3\2\2\2_`\7\3\2\2`a\7\7\2\2ab\5\n\6"+
		"\2b\t\3\2\2\2ck\7\3\2\2dk\7\4\2\2ek\7\5\2\2fk\5\32\16\2gk\7\b\2\2hk\5"+
		"\30\r\2ik\5\34\17\2jc\3\2\2\2jd\3\2\2\2je\3\2\2\2jf\3\2\2\2jg\3\2\2\2"+
		"jh\3\2\2\2ji\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2m\13\3\2\2\2nl\3\2\2"+
		"\2oq\5\16\b\2pr\5\20\t\2qp\3\2\2\2qr\3\2\2\2r\r\3\2\2\2s}\7\3\2\2t|\7"+
		"\3\2\2u|\7\4\2\2v|\7\5\2\2w|\7\b\2\2x|\5\32\16\2y|\5\30\r\2z|\5\34\17"+
		"\2{t\3\2\2\2{u\3\2\2\2{v\3\2\2\2{w\3\2\2\2{x\3\2\2\2{y\3\2\2\2{z\3\2\2"+
		"\2|\177\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\u0097\3\2\2\2\177}\3\2\2\2\u0080"+
		"\u0088\7\4\2\2\u0081\u0088\7\5\2\2\u0082\u0088\7\7\2\2\u0083\u0088\7\b"+
		"\2\2\u0084\u0088\5\32\16\2\u0085\u0088\5\30\r\2\u0086\u0088\5\34\17\2"+
		"\u0087\u0080\3\2\2\2\u0087\u0081\3\2\2\2\u0087\u0082\3\2\2\2\u0087\u0083"+
		"\3\2\2\2\u0087\u0084\3\2\2\2\u0087\u0085\3\2\2\2\u0087\u0086\3\2\2\2\u0088"+
		"\u0093\3\2\2\2\u0089\u0092\7\3\2\2\u008a\u0092\7\4\2\2\u008b\u0092\7\5"+
		"\2\2\u008c\u0092\7\7\2\2\u008d\u0092\7\b\2\2\u008e\u0092\5\32\16\2\u008f"+
		"\u0092\5\30\r\2\u0090\u0092\5\34\17\2\u0091\u0089\3\2\2\2\u0091\u008a"+
		"\3\2\2\2\u0091\u008b\3\2\2\2\u0091\u008c\3\2\2\2\u0091\u008d\3\2\2\2\u0091"+
		"\u008e\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0090\3\2\2\2\u0092\u0095\3\2"+
		"\2\2\u0093\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0097\3\2\2\2\u0095"+
		"\u0093\3\2\2\2\u0096s\3\2\2\2\u0096\u0087\3\2\2\2\u0097\17\3\2\2\2\u0098"+
		"\u009f\b\t\1\2\u0099\u009b\7\6\2\2\u009a\u0099\3\2\2\2\u009a\u009b\3\2"+
		"\2\2\u009b\u009c\3\2\2\2\u009c\u00a0\5\22\n\2\u009d\u009e\7\6\2\2\u009e"+
		"\u00a0\5\26\f\2\u009f\u009a\3\2\2\2\u009f\u009d\3\2\2\2\u00a0\u00ac\3"+
		"\2\2\2\u00a1\u00a8\f\4\2\2\u00a2\u00a4\7\6\2\2\u00a3\u00a2\3\2\2\2\u00a3"+
		"\u00a4\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a9\5\22\n\2\u00a6\u00a7\7"+
		"\6\2\2\u00a7\u00a9\5\26\f\2\u00a8\u00a3\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a9"+
		"\u00ab\3\2\2\2\u00aa\u00a1\3\2\2\2\u00ab\u00ae\3\2\2\2\u00ac\u00aa\3\2"+
		"\2\2\u00ac\u00ad\3\2\2\2\u00ad\21\3\2\2\2\u00ae\u00ac\3\2\2\2\u00af\u00b0"+
		"\7\5\2\2\u00b0\u00b2\5\24\13\2\u00b1\u00b3\7\6\2\2\u00b2\u00b1\3\2\2\2"+
		"\u00b2\u00b3\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b5\5\26\f\2\u00b5\u00bd"+
		"\3\2\2\2\u00b6\u00b8\5\24\13\2\u00b7\u00b9\7\6\2\2\u00b8\u00b7\3\2\2\2"+
		"\u00b8\u00b9\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bb\5\26\f\2\u00bb\u00bd"+
		"\3\2\2\2\u00bc\u00af\3\2\2\2\u00bc\u00b6\3\2\2\2\u00bd\23\3\2\2\2\u00be"+
		"\u00bf\t\2\2\2\u00bf\25\3\2\2\2\u00c0\u00cc\7\3\2\2\u00c1\u00cc\7\4\2"+
		"\2\u00c2\u00cc\7\22\2\2\u00c3\u00cc\7$\2\2\u00c4\u00cc\7\7\2\2\u00c5\u00cc"+
		"\7\5\2\2\u00c6\u00cc\5\32\16\2\u00c7\u00cc\7\b\2\2\u00c8\u00cc\5\30\r"+
		"\2\u00c9\u00cc\5\34\17\2\u00ca\u00cc\5.\30\2\u00cb\u00c0\3\2\2\2\u00cb"+
		"\u00c1\3\2\2\2\u00cb\u00c2\3\2\2\2\u00cb\u00c3\3\2\2\2\u00cb\u00c4\3\2"+
		"\2\2\u00cb\u00c5\3\2\2\2\u00cb\u00c6\3\2\2\2\u00cb\u00c7\3\2\2\2\u00cb"+
		"\u00c8\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00ca\3\2\2\2\u00cc\u00cd\3\2"+
		"\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\27\3\2\2\2\u00cf\u00d9"+
		"\7\n\2\2\u00d0\u00d8\7\3\2\2\u00d1\u00d8\7\5\2\2\u00d2\u00d8\7\4\2\2\u00d3"+
		"\u00d8\7\30\2\2\u00d4\u00d8\7\31\2\2\u00d5\u00d8\7\b\2\2\u00d6\u00d8\5"+
		"\34\17\2\u00d7\u00d0\3\2\2\2\u00d7\u00d1\3\2\2\2\u00d7\u00d2\3\2\2\2\u00d7"+
		"\u00d3\3\2\2\2\u00d7\u00d4\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d6\3\2"+
		"\2\2\u00d8\u00db\3\2\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da"+
		"\u00dc\3\2\2\2\u00db\u00d9\3\2\2\2\u00dc\u00dd\7\n\2\2\u00dd\31\3\2\2"+
		"\2\u00de\u00e2\7\t\2\2\u00df\u00e1\t\3\2\2\u00e0\u00df\3\2\2\2\u00e1\u00e4"+
		"\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\u00e5\3\2\2\2\u00e4"+
		"\u00e2\3\2\2\2\u00e5\u00e6\7\t\2\2\u00e6\33\3\2\2\2\u00e7\u00ed\5\36\20"+
		"\2\u00e8\u00ed\5 \21\2\u00e9\u00ed\5\"\22\2\u00ea\u00ed\5$\23\2\u00eb"+
		"\u00ed\5&\24\2\u00ec\u00e7\3\2\2\2\u00ec\u00e8\3\2\2\2\u00ec\u00e9\3\2"+
		"\2\2\u00ec\u00ea\3\2\2\2\u00ec\u00eb\3\2\2\2\u00ed\35\3\2\2\2\u00ee\u00f0"+
		"\7\f\2\2\u00ef\u00f1\5\2\2\2\u00f0\u00ef\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1"+
		"\u00f2\3\2\2\2\u00f2\u00f9\7&\2\2\u00f3\u00f5\7\17\2\2\u00f4\u00f6\5\2"+
		"\2\2\u00f5\u00f4\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7"+
		"\u00f9\7\17\2\2\u00f8\u00ee\3\2\2\2\u00f8\u00f3\3\2\2\2\u00f9\37\3\2\2"+
		"\2\u00fa\u00fc\7\r\2\2\u00fb\u00fd\5\2\2\2\u00fc\u00fb\3\2\2\2\u00fc\u00fd"+
		"\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u00ff\7&\2\2\u00ff!\3\2\2\2\u0100\u0102"+
		"\7\16\2\2\u0101\u0103\5\2\2\2\u0102\u0101\3\2\2\2\u0102\u0103\3\2\2\2"+
		"\u0103\u0104\3\2\2\2\u0104\u0105\7&\2\2\u0105#\3\2\2\2\u0106\u0108\7\20"+
		"\2\2\u0107\u0109\5\2\2\2\u0108\u0107\3\2\2\2\u0108\u0109\3\2\2\2\u0109"+
		"\u010a\3\2\2\2\u010a\u010b\7)\2\2\u010b%\3\2\2\2\u010c\u010d\7\21\2\2"+
		"\u010d\u0114\7\3\2\2\u010e\u0110\5(\25\2\u010f\u0111\7\6\2\2\u0110\u010f"+
		"\3\2\2\2\u0110\u0111\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0113\5\n\6\2\u0113"+
		"\u0115\3\2\2\2\u0114\u010e\3\2\2\2\u0114\u0115\3\2\2\2\u0115\u0116\3\2"+
		"\2\2\u0116\u011c\7\24\2\2\u0117\u0118\7\21\2\2\u0118\u0119\7\61\2\2\u0119"+
		"\u011a\7\3\2\2\u011a\u011c\7\24\2\2\u011b\u010c\3\2\2\2\u011b\u0117\3"+
		"\2\2\2\u011c\'\3\2\2\2\u011d\u011e\7*\2\2\u011e\u0124\t\4\2\2\u011f\u0124"+
		"\7/\2\2\u0120\u0124\7\60\2\2\u0121\u0124\7\61\2\2\u0122\u0124\7\62\2\2"+
		"\u0123\u011d\3\2\2\2\u0123\u011f\3\2\2\2\u0123\u0120\3\2\2\2\u0123\u0121"+
		"\3\2\2\2\u0123\u0122\3\2\2\2\u0124)\3\2\2\2\u0125\u0128\5,\27\2\u0126"+
		"\u0128\5\60\31\2\u0127\u0125\3\2\2\2\u0127\u0126\3\2\2\2\u0128+\3\2\2"+
		"\2\u0129\u012a\7\13\2\2\u012a\u012b\5\2\2\2\u012b\u012c\7&\2\2\u012c-"+
		"\3\2\2\2\u012d\u012e\t\5\2\2\u012e/\3\2\2\2\u012f\u0130\7\23\2\2\u0130"+
		"\u0131\5\2\2\2\u0131\u0133\7\25\2\2\u0132\u0134\7\6\2\2\u0133\u0132\3"+
		"\2\2\2\u0133\u0134\3\2\2\2\u0134\u0135\3\2\2\2\u0135\u0136\7\24\2\2\u0136"+
		"\61\3\2\2\2.:>CGJOSX\\jlq{}\u0087\u0091\u0093\u0096\u009a\u009f\u00a3"+
		"\u00a8\u00ac\u00b2\u00b8\u00bc\u00cb\u00cd\u00d7\u00d9\u00e2\u00ec\u00f0"+
		"\u00f5\u00f8\u00fc\u0102\u0108\u0110\u0114\u011b\u0123\u0127\u0133";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}