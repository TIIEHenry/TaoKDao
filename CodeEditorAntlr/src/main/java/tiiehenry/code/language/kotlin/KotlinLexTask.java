package tiiehenry.code.language.kotlin;


import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;

import tiiehenry.code.format.FormatCallback;
import tiiehenry.code.praser.BlockLine;
import tiiehenry.code.praser.SymbolPair;
import tiiehenry.code.praser.Span;
import tiiehenry.code.praser.Variable;
import tiiehenry.code.antlr4.KotlinLexer;
import tiiehenry.code.antlr4.KotlinParser;
import tiiehenry.code.language.Antlr4LexTask;
import tiiehenry.code.language.Language;
import tiiehenry.code.view.ColorScheme;

import static tiiehenry.code.antlr4.KotlinLexer.*;

/**
 * kotlin formal
 */
public class KotlinLexTask extends Antlr4LexTask<KotlinLexer> {
    private final KotlinParser parser;

    public KotlinLexTask(Language language) {
        super(language);
        setKeywords(KotlinLexer.VOCABULARY);
        parser = new KotlinParser(null);
    }

    @Override
    public KotlinLexer generateLexer() {
        return new KotlinLexer(null);
    }


    @Override
    public void tokenizeIn(List<Span> tokens, List<BlockLine> lines, List<SymbolPair> pairs, List<Variable> variables, KotlinLexer lexer, int fromIndex, int fromLineOffset, int fromLine, String code) {
        int lastIndex = fromIndex;
        int lastline = fromLine;
        int lastlineOffset = fromLineOffset;
        int lastType = 0;
        boolean isimp = false;
        boolean isQUOTE_OPEN = false;
        boolean isTRIPLE_QUOTE_OPEN = false;
        boolean isLineStrExprStart = false;

        while (!abort) {
            Token token = lexer.nextToken();
            int tokenType = token.getType();
            if (tokenType == EOF)
                return;
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


            if (isQUOTE_OPEN) {
                if (tokenType == QUOTE_CLOSE) {
                    isQUOTE_OPEN = false;
                } else {
                    if (tokenType != LineStrExprStart && tokenType != RCURL) {
                        if (!isLineStrExprStart) {
                            addTokenSpan(tokens,  line, lineOffset, startIndex, len, ColorScheme.Colorable.LITERAL_STRING);
                            continue;
                        }
                    } else {
                        if (tokenType == LineStrExprStart) {
                            isLineStrExprStart = true;
                        } else if (tokenType == RCURL)
                            isLineStrExprStart = false;
                    }
                }
            }
            if (isTRIPLE_QUOTE_OPEN) {
                if (tokenType == TRIPLE_QUOTE_CLOSE) {
                    isTRIPLE_QUOTE_OPEN = false;
                } else {
                    addTokenSpan(tokens,  line, lineOffset, startIndex, len, ColorScheme.Colorable.LITERAL_STRING);
                    continue;
                }
            }
            if (isimp) {
                if (tokenType == NL) {
                    isimp = false;
                } else {
                    addTokenSpan(tokens,  line, lineOffset, startIndex, len, ColorScheme.Colorable.PACKAGE);
                    continue;
                }
            }
            switch (tokenType) {
                case LineStrExprStart:
                    openPairStack(pairCurlyStacks, line, startIndex+1);
                    openLineStack(lineCurlyStacks, line, startIndex+1);
                    break;
                case LCURL:
                    openVariableStack(variableStacks, startIndex);
                    openPairStack(pairCurlyStacks, line, startIndex);
                    openLineStack(lineCurlyStacks, line, startIndex);
                    break;
                case RCURL:
                    closeVariableStack(variableStacks, startIndex, variables);
                    closePairStack(pairCurlyStacks, line, startIndex, pairs);
                    closeLineStack(lineCurlyStacks, line, startIndex, lines);
                    break;
                case IMPORT:
                case PACKAGE:
                    isimp = true;
                    break;
                case QUOTE_OPEN:
                    isQUOTE_OPEN = true;
                    break;
                case TRIPLE_QUOTE_OPEN:
                    isTRIPLE_QUOTE_OPEN = true;
                    break;
                case CharacterLiteral:
                    addPair(pairs, startIndex, line, stopIndex - 1, line);
                    break;
            }

            addVariable(token, tokenType);

            addTokenPair(tokenType, LPAREN, RPAREN, pairs, pairParenStacks, startIndex, line);
            addTokenPair(tokenType, LANGLE, RANGLE, pairs, pairAngeleStacks, startIndex, line);
            addTokenPair(tokenType, LSQUARE, RSQUARE, pairs, pairBrackStacks, startIndex, line);
            addTokenPair(tokenType, QUOTE_OPEN, QUOTE_CLOSE, pairs, pairQuoteStacks, startIndex, line);


            addTokenLine(tokenType, LPAREN, RPAREN, lines, lineParenStacks, startIndex, line);
            addTokenLine(tokenType, LSQUARE, RSQUARE, lines, lineBracketStacks, startIndex, line);

            addTokenSpan(tokens, token, tokenType, startIndex, line, lineOffset, len);
        }
    }


    @Override
    protected ColorScheme.Colorable getTokenColorable(Integer tokenType) {
        ColorScheme.Colorable type;
        switch (tokenType) {
            case ANNOTATION:
            case AT_FILE:
            case AT_FIELD:
            case AT_PROPERTY:
            case AT_GET:
            case AT_SET:
            case AT_RECEIVER:
            case AT_PARAM:
            case AT_SETPARAM:
            case AT_DELEGATE:
            case ABSTRACT:
            case AS:
            case BREAK:
            case BY:
            case CATCH:
            case CONTINUE:
            case CONSTRUCTOR:
            case COMPANION:
            case CLASS:
            case CONST:
            case DYNAMIC:
            case DO:
            case DATA:
            case ELSE:
            case ENUM:
            case FINAL:
            case FINALLY:
            case FOR:
            case FUN:
            case GETTER:
            case SETTER:
            case IF:
            case IS:
            case IN:
            case NOT_IS:
            case NOT_IN:
            case INIT:
            case INTERFACE:
            case OBJECT:
            case OUT:
            case PUBLIC:
            case PRIVATE:
            case PROTECTED:
            case INTERNAL:
            case RETURN:
            case SEALED:
            case THROW:
            case TYPEOF:
            case TYPE_ALIAS:
            case TRY:
            case VAL:
            case VAR:
            case WHILE:
            case WHERE:
            case WHEN:
            case INNER:
            case TAILREC:
            case OPERATOR:
            case INLINE:
            case INFIX:
            case EXTERNAL:
            case SUSPEND:
            case OVERRIDE:
            case OPEN:
            case LATEINIT:
            case VARARG:
            case NOINLINE:
            case CROSSINLINE:
            case REIFIED:
            case EXPECT:
            case ACTUAL:
                type = ColorScheme.Colorable.KEYWORD;
                break;
            case RETURN_AT:
            case CONTINUE_AT:
            case BREAK_AT:
            case THIS_AT:
            case SUPER_AT:
            case SUPER:
            case THIS:
                type = ColorScheme.Colorable.KEYWORD_SECOND;
                break;
            case IMPORT:
            case PACKAGE:
                type = ColorScheme.Colorable.KEYWORD;
                break;
            case FloatLiteral:
            case DoubleLiteral:
            case LongLiteral:
            case IntegerLiteral:
            case HexLiteral:
            case BinLiteral:
            case RealLiteral:
            case ShebangLine:
            case FieldIdentifier://$a
                type = ColorScheme.Colorable.LITERAL_NUM;
                break;
            case QUOTE_OPEN:
                type = ColorScheme.Colorable.LITERAL_STRING;
                break;
            case TRIPLE_QUOTE_OPEN:
                type = ColorScheme.Colorable.LITERAL_STRING;
                break;
            case CharacterLiteral://'
            case QUOTE_CLOSE:
            case TRIPLE_QUOTE_CLOSE:
                type = ColorScheme.Colorable.LITERAL_STRING;
                break;
            case BooleanLiteral:
            case NullLiteral:
                type = ColorScheme.Colorable.BASIC_TYPE;
                break;
//                case IdentifierAt:
            case LineStrExprStart://${
            case LCURL:
            case RCURL:
            case LPAREN:
            case RPAREN:
            case LSQUARE:
            case RSQUARE:
            case LANGLE:
            case RANGLE:
                type = ColorScheme.Colorable.SEPARATOR;
                break;
            //\n
            case RESERVED:
            case DOT:
            case COMMA:
            case MULT:
            case MOD:
            case DIV:
            case ADD:
            case SUB:
            case INCR:
            case DECR:
            case CONJ:
            case DISJ:
            case EXCL_WS:
            case EXCL_NO_WS:
            case COLON:
            case SEMICOLON:
            case ASSIGNMENT:
            case ADD_ASSIGNMENT:
            case SUB_ASSIGNMENT:
            case MULT_ASSIGNMENT:
            case DIV_ASSIGNMENT:
            case MOD_ASSIGNMENT:
            case ARROW:
            case DOUBLE_ARROW:
            case RANGE:
            case COLONCOLON:
            case DOUBLE_SEMICOLON:
            case HASH:
            case AT:
            case AT_WS:
            case QUEST_WS:
            case QUEST_NO_WS:
            case LE:
            case GE:
            case EXCL_EQ:
            case EXCL_EQEQ:
            case AS_SAFE:
            case EQEQ:
            case EQEQEQ:
            case SINGLE_QUOTE:
                type = ColorScheme.Colorable.SYMBOL;
                break;
            case Identifier:
                type = ColorScheme.Colorable.LITERAL_VAR;
                break;
            case LineComment:
                type = ColorScheme.Colorable.COMMENT_SINGLE;
                break;
            case Inside_Comment:
            case DelimitedComment:
                type = ColorScheme.Colorable.COMMENT_REGION;
                break;
            case ErrorCharacter:
            case 0:
                type = ColorScheme.Colorable.ERROR;
                break;
            //
//                UNICODE_CLASS_LL=150
//                UNICODE_CLASS_LM=151
//                UNICODE_CLASS_LO=152
//                UNICODE_CLASS_LT=153
//                UNICODE_CLASS_LU=154
//                UNICODE_CLASS_ND=155
//                UNICODE_CLASS_NL=156
//                Inside_WS
//                Inside_NL
//                LineStrRef=161
//                LineStrText=162
//                LineStrEscapedChar=163
//                MultiLineStringQuote=166
//                MultiLineStrRef=167
//                MultiLineStrText=168
//                MultiLineStrExprStart=169
            default:
                type = ColorScheme.Colorable.TEXT;
                break;
        }
        return type;
    }

    @Override
    protected void addVariable(Token token, Integer tokenType) {
        if (tokenType == Identifier)
            addVariableStack(variableStacks, Variable.Type.Var, token.getText());
    }

    @Override
    protected void parse(KotlinLexer lexer) {
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        parser.setTokenStream(tokens);

    }

    @Override
    protected ParseTree getTree() {
        return parser.getRuleContext();
    }

    @Override
    public boolean canFormat() {
        return true;
    }

    @Override
    public synchronized void format(String input, int width, int curPos, FormatCallback callback) throws Exception {
        Lexer lexer = generateLexer();
        lexer.setInputStream(CharStreams.fromString(input));
        CharSequence newStr = KotlinFormatter.getInstance().format(lexer, getLanguage().indentChar, width);
        callback.onDone(newStr.toString(), curPos);
    }

    @Override
    public int createAutoIndent(CharSequence subSequence) {
        Lexer lexer = generateLexer();
        lexer.setInputStream(CharStreams.fromString(subSequence.toString()));
        return KotlinFormatter.getInstance().createAutoIndent(lexer);
    }
}
