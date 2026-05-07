//package tiiehenry.code.language.bash;
//
//import android.graphics.Rect;
//
//import org.antlr.v4.runtime.CommonTokenStream;
//import org.antlr.v4.runtime.Token;
//import org.antlr.v4.runtime.tree.ParseTree;
//
//import java.util.List;
//
//import tiiehenry.code.praser.BlockLine;
//import tiiehenry.code.praser.SymbolPair;
//import tiiehenry.code.praser.Span;
//import tiiehenry.code.praser.Variable;
//import tiiehenry.code.antlr4.BashLexer;
//import tiiehenry.code.antlr4.BashParser;
//import tiiehenry.code.language.Antlr4LexTask;
//import tiiehenry.code.language.Language;
//import tiiehenry.code.view.ColorScheme;
//
//import static org.antlr.v4.runtime.Recognizer.EOF;
//import static tiiehenry.code.antlr4.BashLexer.LPAREN;
//import static tiiehenry.code.antlr4.BashLexer.*;
//
//
//public class BashLexTask extends Antlr4LexTask<BashLexer> {
//    private final BashParser parser;
//
//    public BashLexTask(Language language) {
//        super(language);
//        setKeywords(BashLexer.VOCABULARY);
//        parser = new BashParser(null);
//    }
//
//    @Override
//    public void tokenizeIn(List<Span> tokens, List<BlockLine> lines, List<SymbolPair> pairs, List<Variable> variables, BashLexer lexer, int fromIndex, int fromLineOffset, int fromLine) {
//        int lastIndex = fromIndex;
//        int lastline = fromLine;
//        int lastlineOffset = fromLineOffset;
//        while (!abort) {
//            Token token = lexer.nextToken();
//            int tokenType = token.getType();
//            if (tokenType == EOF)
//                break;
//            int startIndex = fromIndex + token.getStartIndex();
//            int stopIndex = fromIndex + token.getStopIndex() + 1;
//            int len = stopIndex - startIndex;
//            int line = fromLine + token.getLine();
//            int lineOffset = token.getCharPositionInLine();
//
//            if (startIndex != lastIndex)
//                addTokenSpan(tokens, lastline, lastlineOffset, lastIndex, startIndex - lastIndex, ColorScheme.Colorable.TEXT);
//            lastIndex = stopIndex;
//            lastline = line;
//            lastlineOffset = lineOffset;
//
//
//            makeTokenVariableRegion(tokenType, LBRACE, RBRACE, variables, variableStacks, startIndex);
//            addVariable(token, tokenType);
//
//            addTokenPair(tokenType, LBRACE, RBRACE, pairs, pairCurlyStacks, startIndex, line);
//            addTokenPair(tokenType, LPAREN, RPAREN, pairs, pairParenStacks, startIndex, line);
//            addTokenPair(tokenType, LBRACK, RBRACK, pairs, pairBrackStacks, startIndex, line);
//            switch (tokenType) {
//                case StringLiteral:
//                case CharacterLiteral:
//                    addPair(pairs, startIndex, line, stopIndex - 1, line);
//                    break;
//            }
//
//            addTokenLine(tokenType, LBRACE, RBRACE, lines, lineCurlyStacks, startIndex, line);
//            addTokenLine(tokenType, LPAREN, RPAREN, lines, lineParenStacks, startIndex, line);
//            addTokenLine(tokenType, LBRACK, RBRACK, lines, lineBracketStacks, startIndex, line);
//
//            addTokenSpan(tokens, token, tokenType, startIndex, line, lineOffset, len);
//        }
//    }
//
//    @Override
//    public BashLexer generateLexer() {
//        return new BashLexer(null);
//    }
//
//
//    @Override
//    protected ColorScheme.Colorable getTokenColorable(Integer tokenType) {
//        ColorScheme.Colorable type;
//        switch (tokenType) {
//            case PUBLIC:
//            case ABSTRACT:
//            case ASSERT:
//            case BREAK:
//            case CASE:
//            case CATCH:
//            case CONTINUE:
//            case CLASS:
//            case CONST:
//            case DEFAULT:
//            case DO:
//            case ELSE:
//            case ENUM:
//            case EXTENDS:
//            case FINAL:
//            case FINALLY:
//            case FOR:
//            case GOTO:
//            case IF:
//            case IMPLEMENTS:
//            case INSTANCEOF:
//            case INTERFACE:
//            case NATIVE:
//            case NEW:
//            case PRIVATE:
//            case PROTECTED:
//            case RETURN:
//            case STATIC:
//            case STRICTFP:
//            case SWITCH:
//            case SYNCHRONIZED:
//            case THROW:
//            case THROWS:
//            case TRANSIENT:
//            case TRY:
//            case VOLATILE:
//            case WHILE:
//                type = ColorScheme.Colorable.KEYWORD;
//                break;
//            case SUPER:
//            case THIS:
//                type = ColorScheme.Colorable.KEYWORD_SECOND;
//                break;
//            case IMPORT:
//            case PACKAGE:
//                type = ColorScheme.Colorable.KEYWORD;
//                imp = true;
//                break;
//            case IntegerLiteral:
//            case FloatingPointLiteral:
//                type = ColorScheme.Colorable.LITERAL_NUM;
//                break;
//            case StringLiteral:
//            case CharacterLiteral:
//                type = ColorScheme.Colorable.LITERAL_STRING;
//                break;
//            case BooleanLiteral:
//            case NUM:
//                type = ColorScheme.Colorable.BASIC_TYPE;
//                break;
//            case SEMI:
//                type = ColorScheme.Colorable.SEPARATOR;
//                imp = false;
//                break;
//            case ARROW:
//            case COLONCOLON:
//            case ELLIPSIS:
//            case ASSIGN:
//            case GT:
//            case LT:
//            case BANG:
//            case TILDE:
//            case QUESTION:
//            case COLON:
//            case EQUAL:
//            case LE:
//            case GE:
//            case NOTEQUAL:
//            case AND:
//            case OR:
//            case INC:
//            case DEC:
//            case ADD:
//            case SUB:
//            case DIV:
//            case BITOR:
//            case BITAND:
//            case CARET:
//            case MOD:
//            case ADD_ASSIGN:
//            case SUB_ASSIGN:
//            case MUL_ASSIGN:
//            case DIV_ASSIGN:
//            case AND_ASSIGN:
//            case OR_ASSIGN:
//            case XOR_ASSIGN:
//            case MOD_ASSIGN:
//            case LSHIFT_ASSIGN:
//            case RSHIFT_ASSIGN:
//            case URSHIFT_ASSIGN:
//            case AT:
//            case COMMA:
//            case DOT:
//                type = ColorScheme.Colorable.SYMBOL;
//                break;
//            case LBRACE://{
//            case RBRACE:
//            case LPAREN:
//            case RPAREN:
//            case LBRACK://(
//            case RBRACK:
//                type = ColorScheme.Colorable.SEPARATOR;
//                break;
//            case MUL:
//                if (imp)
//                    type = ColorScheme.Colorable.PACKAGE;
//                else
//                    type = ColorScheme.Colorable.SYMBOL;
//                break;
//            case Identifier:
//                if (imp)
//                    type = ColorScheme.Colorable.PACKAGE;
//                else {
//                    type = ColorScheme.Colorable.LITERAL_VAR;
//                }
//                break;
//            case LINE_COMMENT:
//                type = ColorScheme.Colorable.COMMENT_SINGLE;
//                break;
//            case COMMENT:
//                type = ColorScheme.Colorable.COMMENT_REGION;
//                break;
//            case 0:
//                type = ColorScheme.Colorable.ERROR;
//                break;
//            default:
//                type = ColorScheme.Colorable.TEXT;
//                break;
//        }
//        return type;
//    }
//
//    @Override
//    protected void parse(BashLexer lexer) {
//        CommonTokenStream tokens = new CommonTokenStream(lexer);
//        parser.setTokenStream(tokens);
//    }
//
//    @Override
//    protected ParseTree getTree() {
//        return parser.getRuleContext();
//    }
///*
//    @Override
//    public boolean canFormat() {
//        return true;
//    }
//
//    @Override
//    public synchronized void format(String input, int width, int curPos, FormatCallback callback) {
//        try {
//            Lexer lexer = generateLexer();
//            lexer.setInputStream(CharStreams.fromString(input));
//            CharSequence newStr = BashFormatter.getInstance().format(lexer, getLanguage().indentChar, width);
//            callback.onDone(true, newStr.toString(), curPos, "");
//        } catch (Exception e) {
//            e.printStackTrace();
//            callback.onDone(false, input, curPos, e.getMessage());
//        }
//    }
//
//    @Override
//    public int createAutoIndent(CharSequence subSequence) {
//        Lexer lexer = generateLexer();
//        lexer.setInputStream(CharStreams.fromString(subSequence.toString()));
//        return BashFormatter.getInstance().createAutoIndent(lexer);
//    }*/
//}
