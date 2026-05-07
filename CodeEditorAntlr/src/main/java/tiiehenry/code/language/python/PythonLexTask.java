package tiiehenry.code.language.python;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

import java.util.List;

import tiiehenry.code.antlr4.PythonLexer;
import tiiehenry.code.antlr4.PythonParser;
import tiiehenry.code.antlr4.PythonVersion;
import tiiehenry.code.language.Antlr4LexTask;
import tiiehenry.code.language.Language;
import tiiehenry.code.praser.BlockLine;
import tiiehenry.code.praser.Span;
import tiiehenry.code.praser.SymbolPair;
import tiiehenry.code.praser.Variable;
import tiiehenry.code.view.ColorScheme;

import static org.antlr.v4.runtime.Recognizer.EOF;
import static tiiehenry.code.antlr4.PythonLexer.*;


public class PythonLexTask extends Antlr4LexTask<PythonLexer> {
    private final PythonParser parser;

    public PythonLexTask(Language language) {
        super(language);
        setKeywords(PythonParser.VOCABULARY);
        PythonLexer.TabSize=4;
        parser = new PythonParser(null);
        parser.Version= PythonVersion.Autodetect;
    }

    @Override
    public PythonLexer generateLexer() {
        return new PythonLexer(null);
    }

    @Override
    public void tokenizeIn(List<Span> tokens, List<BlockLine> lines, List<SymbolPair> pairs, List<Variable> variables, PythonLexer lexer, int fromIndex, int fromLineOffset, int fromLine, String code) {
        int lastIndex = fromIndex;
        int lastline = fromLine;
        int lastlineOffset = fromLineOffset;
        int lastType = 0;
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


            makeTokenVariableRegion(tokenType, OPEN_BRACE, CLOSE_BRACE, variables, variableStacks, startIndex);
            addVariable(token, tokenType);

            addTokenPair(tokenType, OPEN_BRACE, CLOSE_BRACE, pairs, pairCurlyStacks, startIndex, line);
            addTokenPair(tokenType, OPEN_PAREN, CLOSE_PAREN, pairs, pairParenStacks, startIndex, line);
            addTokenPair(tokenType, OPEN_BRACKET, CLOSE_BRACKET, pairs, pairParenStacks, startIndex, line);
            switch (tokenType) {
                case STRING:
                    addPair(pairs, startIndex, line, stopIndex - 1, line);
                    break;
            }
            addTokenLine(tokenType, OPEN_BRACE, CLOSE_BRACE, lines, lineCurlyStacks, startIndex, line);
            addTokenLine(tokenType, OPEN_PAREN, CLOSE_PAREN, lines, lineParenStacks, startIndex, line);
            addTokenLine(tokenType, OPEN_BRACKET, CLOSE_BRACKET, lines, lineBracketStacks, startIndex, line);
            addTokenSpan(tokens, token, tokenType, startIndex, line, lineOffset, len);
        }
    }

    @Override
    protected ColorScheme.Colorable getTokenColorable(Integer tokenType) {
        ColorScheme.Colorable type;
        switch (tokenType) {
            case DEF:
            case RETURN:
            case RAISE:
            case FROM:
            case IMPORT:
            case NONLOCAL:
            case AS:
            case GLOBAL:
            case ASSERT:
            case IF:
            case ELIF:
            case ELSE:
            case WHILE:
            case FOR:
            case IN:
            case TRY:
            case FINALLY:
            case WITH:
            case EXCEPT:
            case LAMBDA:
            case OR:
            case AND:
            case NOT:
            case IS:
            case CLASS:
            case YIELD:
            case DEL:
            case PASS:
            case CONTINUE:
            case BREAK:
            case ASYNC:
            case AWAIT:
            case PRINT:
            case EXEC:
                type = ColorScheme.Colorable.KEYWORD;
                break;
            case DECIMAL_INTEGER:
            case OCT_INTEGER:
            case HEX_INTEGER:
            case BIN_INTEGER:
            case IMAG_NUMBER:
            case FLOAT_NUMBER:
                type = ColorScheme.Colorable.LITERAL_NUM;
                break;
            case STRING:
                type = ColorScheme.Colorable.LITERAL_STRING;
                break;
            case NONE:
            case TRUE:
            case FALSE:
                type = ColorScheme.Colorable.BASIC_TYPE;
                break;
            case OPEN_PAREN:
            case CLOSE_PAREN:
            case COLON://:
            case OPEN_BRACKET:
            case CLOSE_BRACKET:
            case OPEN_BRACE:
            case CLOSE_BRACE:
                type = ColorScheme.Colorable.SEPARATOR;
                break;
            case DOT:
            case ELLIPSIS:
            case REVERSE_QUOTE:
            case STAR:
            case COMMA:
//            case COLON:
            case SEMI_COLON:
            case POWER:
            case ASSIGN:
            case OR_OP:
            case XOR:
            case AND_OP:
            case LEFT_SHIFT:
            case RIGHT_SHIFT:
            case ADD:
            case MINUS:
            case DIV:
            case MOD:
            case IDIV:
            case NOT_OP:
            case LESS_THAN:
            case GREATER_THAN:
            case EQUALS:
            case GT_EQ:
            case LT_EQ:
            case NOT_EQ_1:
            case NOT_EQ_2:
            case AT:
            case ARROW:
            case ADD_ASSIGN:
            case SUB_ASSIGN:
            case MULT_ASSIGN:
            case AT_ASSIGN:
            case DIV_ASSIGN:
            case MOD_ASSIGN:
            case AND_ASSIGN:
            case OR_ASSIGN:
            case XOR_ASSIGN:
            case LEFT_SHIFT_ASSIGN:
            case RIGHT_SHIFT_ASSIGN:
            case POWER_ASSIGN:
            case IDIV_ASSIGN:
                type = ColorScheme.Colorable.SYMBOL;
                break;
            case LINE_JOIN:
                type = ColorScheme.Colorable.SYMBOL;
                break;
            case NAME:
                type = ColorScheme.Colorable.LITERAL_VAR;
                break;
            case COMMENT:
                type = ColorScheme.Colorable.COMMENT_SINGLE;
                break;
            case WS:
            default:
                type = ColorScheme.Colorable.TEXT;
                break;
        }
        return type;
    }

    @Override
    protected void addVariable(Token token, Integer tokenType) {
        if (tokenType == NAME)
            addVariableStack(variableStacks, Variable.Type.Var, token.getText());
    }

    @Override
    protected void parse(PythonLexer lexer) {
        CommonTokenStream tks = new CommonTokenStream(lexer);
        parser.setTokenStream(tks);
    }


}
