package tiiehenry.code.language.html;


import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

import tiiehenry.code.IndentStringBuilder;
import tiiehenry.code.praser.BlockLine;
import tiiehenry.code.praser.SymbolPair;
import tiiehenry.code.praser.Span;
import tiiehenry.code.praser.Variable;
import tiiehenry.code.antlr4.HTMLLexer;
import tiiehenry.code.antlr4.HTMLParser;
import tiiehenry.code.language.Antlr4LexTask;
import tiiehenry.code.language.FileItem;
import tiiehenry.code.language.Language;
import tiiehenry.code.language.css.CssLanguage;
import tiiehenry.code.language.css.CssLexTask;
import tiiehenry.code.language.java.JavaLanguage;
import tiiehenry.code.language.java.JavaLexTask;
import tiiehenry.code.language.javascript.JavascriptLanguage;
import tiiehenry.code.language.javascript.JavascriptLexTask;
import tiiehenry.code.view.ColorScheme;

import static org.antlr.v4.runtime.Recognizer.EOF;
import static tiiehenry.code.antlr4.HTMLLexer.ATTVALUE_VALUE;
import static tiiehenry.code.antlr4.HTMLLexer.DTD;
import static tiiehenry.code.antlr4.HTMLLexer.HTML_COMMENT;
import static tiiehenry.code.antlr4.HTMLLexer.HTML_CONDITIONAL_COMMENT;
import static tiiehenry.code.antlr4.HTMLLexer.SCRIPTLET;
import static tiiehenry.code.antlr4.HTMLLexer.SCRIPT_BODY;
import static tiiehenry.code.antlr4.HTMLLexer.SCRIPT_OPEN;
import static tiiehenry.code.antlr4.HTMLLexer.SCRIPT_SHORT_BODY;
import static tiiehenry.code.antlr4.HTMLLexer.SEA_WS;
import static tiiehenry.code.antlr4.HTMLLexer.STYLE_BODY;
import static tiiehenry.code.antlr4.HTMLLexer.STYLE_OPEN;
import static tiiehenry.code.antlr4.HTMLLexer.STYLE_SHORT_BODY;
import static tiiehenry.code.antlr4.HTMLLexer.TAG_CLOSE;
import static tiiehenry.code.antlr4.HTMLLexer.TAG_EQUALS;
import static tiiehenry.code.antlr4.HTMLLexer.TAG_NAME;
import static tiiehenry.code.antlr4.HTMLLexer.TAG_OPEN;
import static tiiehenry.code.antlr4.HTMLLexer.TAG_SLASH;
import static tiiehenry.code.antlr4.HTMLLexer.TAG_SLASH_CLOSE;
import static tiiehenry.code.antlr4.HTMLLexer.XML_DECLARATION;

public class HtmlLexTask extends Antlr4LexTask<HTMLLexer> {
    protected final HTMLParser parser;
    private final HTMLLexer lexer;
    protected HTMLParser.HtmlDocumentContext document;

    protected JavascriptLexTask javascriptLexTask = (JavascriptLexTask) JavascriptLanguage.getInstance().newLexTask();
    protected JavaLexTask javaLexTask = (JavaLexTask) JavaLanguage.getInstance().newLexTask();
    protected CssLexTask cssLexTask = (CssLexTask) CssLanguage.getInstance().newLexTask();
    protected FileItem item;

    public HtmlLexTask(FileItem item) {
        this(HtmlLanguage.getInstance());
        this.item = item;
    }

    public HtmlLexTask(Language language) {
        super(language);

        setKeywords(HTMLLexer.VOCABULARY);
        parser = new HTMLParser(null);
        lexer = new HTMLLexer(null);
        if (item != null) {
            parser.removeErrorListeners();
            parser.addErrorListener(item);
        }
        document = null;
    }

    @Override
    public void tokenizeIn(List<Span> tokens, List<BlockLine> lines, List<SymbolPair> pairs, List<Variable> variables, HTMLLexer lexer, int fromIndex, int fromLineOffset, int fromLine, String code) {
        ArrayList<BlockLine> lineTagStacks = new ArrayList<>(8196);
        ArrayList<BlockLine> lineScriptStacks = new ArrayList<>(8196);
        ArrayList<BlockLine> lineStyleStacks = new ArrayList<>(8196);
        ArrayList<Variable> variableStacks = new ArrayList<>(8196);
        clearFuncName();
        int lastIndex = fromIndex;
        int lastline = fromLine;
        int lastlineOffset = fromLineOffset;
        lastType = 0;
        while (!abort) {
            Token token = lexer.nextToken();
            int tokenType = token.getType();
            if (tokenType == EOF) {
                break;
            }
            int startIndex = fromIndex + token.getStartIndex();
            int stopIndex = fromIndex + token.getStopIndex() + 1;
            int len = stopIndex - startIndex;
            int line = fromLine + token.getLine();
            int lineOffset = token.getCharPositionInLine();

            if (startIndex != lastIndex) {
                addTokenSpan(tokens,  lastline, lastlineOffset, lastIndex, startIndex - lastIndex, ColorScheme.Colorable.TEXT);
            }
            lastIndex = stopIndex;
            lastline = line;
            lastlineOffset = lineOffset;

            switch (tokenType) {
                case TAG_OPEN:
                    openLineStack(lineTagStacks, line, startIndex);
                    openVariableStack(variableStacks, startIndex);
                    break;
                case TAG_CLOSE:
                case TAG_SLASH_CLOSE:
                    closeLineStack(lineTagStacks, line + 1, startIndex, lines);
                    closeVariableStack(variableStacks, startIndex, variables);
                    break;
                case ATTVALUE_VALUE:
                    addPair(pairs, startIndex, line, stopIndex - 1, line);
                    break;
            }
            addVariable(token, tokenType);

            switch (tokenType) {
                case TAG_NAME:
                    if (lastType == TAG_OPEN || lastType == TAG_SLASH) {
                        addVariableStack(variableStacks, Variable.Type.Func_Internal, token.getText());
                    } else {
                        addVariableStack(variableStacks, Variable.Type.Func_User, token.getText());
                    }
                    break;
                case SCRIPT_OPEN:
                    openLineStack(lineScriptStacks, line, startIndex);
                    break;
                case STYLE_OPEN:
                    openLineStack(lineStyleStacks, line, startIndex);
                    break;
                case STYLE_BODY:
                    int styleLen = "</style>".length();
                    String sstyle = token.getText().substring(0, len - styleLen);
                    String[] sstylearray = sstyle.split("\n");
                    int lineEnd_Style = line + sstylearray.length;
                    closeLineStack(lineStyleStacks, lineEnd_Style - 1, stopIndex, lines);
                    if (sstyle.length() > 0) {
                        cssLexTask.tokenize(tokens, lines, pairs, variables, sstyle, startIndex, lineOffset, line - 1, false);
                        addTokenSpan(tokens,  lineEnd_Style, sstylearray[sstylearray.length - 1].length(), stopIndex - styleLen, styleLen, ColorScheme.Colorable.KEYWORD);
                        continue;
                    }
                    break;
                case SCRIPT_BODY:
                    int scriptLen = "</script>".length();
                    String sscript = token.getText().substring(0, len - scriptLen);
                    String[] sscriptarray = sscript.split("\n");
                    int lineEnd_Script = line + sscriptarray.length;
                    closeLineStack(lineScriptStacks, lineEnd_Script - 1, stopIndex, lines);
                    if (sscript.length() > 0) {
                        javascriptLexTask.tokenize(tokens, lines, pairs, variables, sscript, startIndex, lineOffset, line - 1, false);
                        addTokenSpan(tokens,  lineEnd_Script, sscriptarray[sscriptarray.length - 1].length(), stopIndex - scriptLen, scriptLen, ColorScheme.Colorable.KEYWORD);
                        continue;
                    }
                    break;
                case SCRIPTLET:
                    String text=token.getText();
//                    if (text.startsWith("<%="))
//                        break;
                    int startLen = "<%".length();
                    if (text.startsWith("<%!"))
                        startLen++;
                    int endLen = "%>".length();
                    String sscriptlet =text .substring(startLen, len - endLen);
                    String[] sscriptletarray = sscriptlet.split("\n");
                    if (sscriptlet.length() > 0) {
                        int lineEnd_Scriptlet = line + sscriptletarray.length;
                        openLineStack(lineScriptStacks, line, startIndex);
                        closeLineStack(lineScriptStacks, lineEnd_Scriptlet - 1, stopIndex - endLen, lines);
                        addTokenSpan(tokens, line, lineOffset, startIndex, startLen, ColorScheme.Colorable.KEYWORD);
                        javaLexTask.tokenize(tokens, lines, pairs, variables, sscriptlet, startIndex + startLen, lineOffset, line - 1, false);
                        addTokenSpan(tokens, lineEnd_Scriptlet, sscriptletarray[sscriptletarray.length - 1].length(), stopIndex - endLen, endLen, ColorScheme.Colorable.KEYWORD);
                        continue;
                    }
                    break;
            }
            addTokenSpan(tokens, token, tokenType, startIndex, line, lineOffset, len);

            if (tokenType != SEA_WS)
                lastType = tokenType;
        }
        addFuncName("layout_:");
        updateFuncName();
    }

    private int lastType = 0;

    @Override
    protected ColorScheme.Colorable getTokenColorable(Integer tokenType) {
        ColorScheme.Colorable type;
        switch (tokenType) {
            case XML_DECLARATION://<? ?>
            case DTD://<! >
                type = ColorScheme.Colorable.PACKAGE;
                break;
            case HTML_COMMENT:
                type = ColorScheme.Colorable.COMMENT_REGION;
                break;
            case HTML_CONDITIONAL_COMMENT:
            case ATTVALUE_VALUE://包括"
                type = ColorScheme.Colorable.LITERAL_STRING;
                break;
            case TAG_OPEN:// <   </也会被解析
            case TAG_SLASH:// /
            case TAG_CLOSE:// >
            case TAG_SLASH_CLOSE:// />
                type = ColorScheme.Colorable.SEPARATOR;
                break;
            case TAG_NAME:
                if (lastType == TAG_OPEN || lastType == TAG_SLASH) {
                    type = ColorScheme.Colorable.KEYWORD;
                } else {
                    type = ColorScheme.Colorable.LITERAL_FUNC;
                }
                break;
            case STYLE_OPEN:
            case STYLE_BODY:
            case SCRIPT_OPEN:
            case SCRIPT_BODY:
            case SCRIPTLET:
                type = ColorScheme.Colorable.KEYWORD;
                break;
            case STYLE_SHORT_BODY:
            case SCRIPT_SHORT_BODY:
                type = ColorScheme.Colorable.INTERNAL;
                break;
            case TAG_EQUALS:
                type = ColorScheme.Colorable.SYMBOL;
                break;
            default:
                type = ColorScheme.Colorable.TEXT;
                break;
        }
        return type;
    }

    @Override
    protected boolean canAnalysis() {
        return true;
    }

    @Override
    public boolean canFormat() {
        return false;
    }

    @Override
    protected int formatInThread(StringBuilder sb, IndentStringBuilder isb, HTMLLexer lexer, CharSequence input, int width, int curPos) {
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        parser.setTokenStream(tokens);
        HtmlFormatter f = new HtmlFormatter(this, isb, width, curPos);
        f.format(parser);
        sb.append(isb);
        return f.newPos;
    }


    @Override
    protected void parse(HTMLLexer lexer) {
        CommonTokenStream ctokens = new CommonTokenStream(lexer);
        parser.setTokenStream(ctokens);
        document = parser.htmlDocument();
    }

    @Override
    protected ParseTree getTree() {
        return document;
    }

    @Override
    protected HTMLLexer generateLexer() {
        return new HTMLLexer(null);
    }

}

