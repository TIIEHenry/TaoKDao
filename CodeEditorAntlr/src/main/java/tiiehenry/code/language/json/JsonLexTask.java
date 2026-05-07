package tiiehenry.code.language.json;


import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;

import tiiehenry.code.IndentStringBuilder;
import tiiehenry.code.antlr4.JSONLexer;
import tiiehenry.code.antlr4.JSONParser;
import tiiehenry.code.language.Antlr4LexTask;
import tiiehenry.code.language.Language;
import tiiehenry.code.praser.BlockLine;
import tiiehenry.code.praser.Span;
import tiiehenry.code.praser.SymbolPair;
import tiiehenry.code.praser.Variable;
import tiiehenry.code.view.ColorScheme;

import static org.antlr.v4.runtime.Token.EOF;
import static tiiehenry.code.antlr4.JSONLexer.COLON;
import static tiiehenry.code.antlr4.JSONLexer.COMMA;
import static tiiehenry.code.antlr4.JSONLexer.FALSE;
import static tiiehenry.code.antlr4.JSONLexer.LBRACE;
import static tiiehenry.code.antlr4.JSONLexer.LBRACK;
import static tiiehenry.code.antlr4.JSONLexer.NULL;
import static tiiehenry.code.antlr4.JSONLexer.NUMBER;
import static tiiehenry.code.antlr4.JSONLexer.RBRACE;
import static tiiehenry.code.antlr4.JSONLexer.RBRACK;
import static tiiehenry.code.antlr4.JSONLexer.STRING;
import static tiiehenry.code.antlr4.JSONLexer.TRUE;
import static tiiehenry.code.antlr4.JSONLexer.WS;

public class JsonLexTask extends Antlr4LexTask<JSONLexer> {
    private final JSONParser parser;
    private JSONParser.JsonContext json;

    public JsonLexTask(Language language) {
        super(language);
        parser = new JSONParser(null);
        json = null;
    }

    @Override
    protected JSONLexer generateLexer() {
        return new JSONLexer(null);
    }

    @Override
    public void tokenizeIn(List<Span> tokens, List<BlockLine> lines, List<SymbolPair> pairs, List<Variable> variables, JSONLexer lexer, int fromIndex, int fromLineOffset, int fromLine, String code) {
        key = false;
        isArray = false;
        json = null;

        int lastIndex = fromIndex;
        int lastline = fromLine;
        int lastlineOffset = fromLineOffset;
        while (!abort) {
            Token token = lexer.nextToken();
            int tokenType = token.getType();
            if (tokenType == EOF)
                break;
            int startIndex = fromIndex + token.getStartIndex();
            int stopIndex = fromIndex + token.getStopIndex() + 1;
            int len = stopIndex - startIndex;
            int line = fromLine + token.getLine();
            int lineOffset = token.getCharPositionInLine();

            if (startIndex != lastIndex)
                addTokenSpan(tokens,  lastline, lastlineOffset, lastIndex, startIndex - lastIndex, ColorScheme.Colorable.TEXT);
            lastIndex = stopIndex;
            lastline = line;
            lastlineOffset = lineOffset;
            makeTokenVariableRegion(tokenType, LBRACE, RBRACE, variables, variableStacks, startIndex);
            makeTokenVariableRegion(tokenType, LBRACK, RBRACK, variables, variableStacks, startIndex);
            addVariable(token, tokenType);

            addTokenPair(tokenType, LBRACE, RBRACE, pairs, pairCurlyStacks, startIndex, line);
            addTokenPair(tokenType, LBRACK, RBRACK, pairs, pairBrackStacks, startIndex, line);
            if (tokenType == STRING) {
                addPair(pairs, startIndex, line, stopIndex - 1, line);
            }

            addTokenLine(tokenType, LBRACE, RBRACE, lines, lineCurlyStacks, startIndex, line);
            addTokenLine(tokenType, LBRACK, RBRACK, lines, lineCurlyStacks, startIndex, line);

            addTokenSpan(tokens, token, tokenType, startIndex, line, lineOffset, len);
        }
    }


    private boolean key = false;
    private boolean isArray = false;

    @Override
    protected ColorScheme.Colorable getTokenColorable(Integer tokenType) {
        ColorScheme.Colorable type;
        switch (tokenType) {
            case LBRACE:
                isArray = false;
                key = true;
                type = ColorScheme.Colorable.SEPARATOR;
                break;
            case COMMA:
                key = true;
                type = ColorScheme.Colorable.SEPARATOR;
                break;
            case LBRACK:
                isArray = true;
                type = ColorScheme.Colorable.SEPARATOR;
                break;
            case RBRACE:
            case RBRACK:
            case COLON:
                isArray = false;
                key = false;
                type = ColorScheme.Colorable.SEPARATOR;
                break;
            case TRUE:
            case FALSE:
            case NULL:
                type = ColorScheme.Colorable.BASIC_TYPE;
                break;
            case NUMBER:
                type = ColorScheme.Colorable.LITERAL_NUM;
                break;
            case STRING:
                if (key && !isArray)
                    type = ColorScheme.Colorable.KEYWORD;
                else
                    type = ColorScheme.Colorable.LITERAL_STRING;
                break;
            default:
                type = ColorScheme.Colorable.TEXT;
                break;
        }
        return type;
    }

    @Override
    protected void parse(JSONLexer lexer) {
        CommonTokenStream tks = new CommonTokenStream(lexer);
        parser.setTokenStream(tks);
        json = parser.json();
    }

    @Override
    protected void addVariable(Token token, Integer tokenType) {

    }

    @Override
    public boolean canFormat() {
        return true;
    }

    @Override
    protected int formatInThread(StringBuilder sb, IndentStringBuilder isb, JSONLexer lexer, CharSequence input, int width, int curPos) {
        int newPos = -1;
        int start = -1;
        while (true) {
            CommonToken token = (CommonToken) lexer.nextToken();
            int tokenType = token.getType();
            if (tokenType == -1)
                break;
            switch (tokenType) {
                case LBRACE:
                    isb.append("{\n");
                    isb.indent(width);
                    break;
                case COMMA:
                    isb.append(",\n");
                    break;
                case LBRACK:
                    isb.append("[\n");
                    isb.indent(width);
                    break;
                case RBRACE:
                    isb.deindent(width);
                    isb.append("\n}");
                    break;
                case RBRACK:
                    isb.deindent(width);
                    isb.append("\n]");
                    break;
                case COLON:
                    isb.append(" : ");
                    break;
                case WS:
                    break;
                default:
                    isb.append(token.getText());
                    break;
            }
            int end = token.getStopIndex();
            newPos = Antlr4LexTask.compute(isb.length(), start, end, curPos, newPos);
            start = end;
        }
        sb.append(isb.toString());
        return newPos;
    }

//    @Override
//    public synchronized void format(String input, int width, int curPos, FormatCallback callback) throws Exception {
//        JSONLexer lexer = generateLexer();
//        lexer.setInputStream(CharStreams.fromString(input));
//        CharSequence newStr = JsonFormatter.getInstance().format(lexer, getLanguage().indentChar, width);
//        callback.onDone(newStr.toString(), curPos);
//    }

    @Override
    public int createAutoIndent(CharSequence subSequence) {
        JSONLexer lexer = generateLexer();
        lexer.setInputStream(CharStreams.fromString(subSequence.toString()));
        return JsonFormatter.getInstance().createAutoIndent(lexer);
    }

    @Override
    protected ParseTree getTree() {
        return json;
    }

}
