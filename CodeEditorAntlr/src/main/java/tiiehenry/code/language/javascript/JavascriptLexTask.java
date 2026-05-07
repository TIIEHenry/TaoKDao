package tiiehenry.code.language.javascript;


import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;

import tiiehenry.code.format.FormatCallback;
import tiiehenry.code.praser.BlockLine;
import tiiehenry.code.praser.SymbolPair;
import tiiehenry.code.praser.Span;
import tiiehenry.code.praser.Variable;
import tiiehenry.code.antlr4.JavaScriptLexer;
import tiiehenry.code.antlr4.JavaScriptParser;
import tiiehenry.code.language.Antlr4LexTask;
import tiiehenry.code.language.Language;
import tiiehenry.code.view.ColorScheme;

import static tiiehenry.code.antlr4.JavaScriptLexer.Class;
import static tiiehenry.code.antlr4.JavaScriptLexer.Enum;
import static tiiehenry.code.antlr4.JavaScriptLexer.Package;
import static tiiehenry.code.antlr4.JavaScriptLexer.Void;
import static tiiehenry.code.antlr4.JavaScriptLexer.*;

public class JavascriptLexTask extends Antlr4LexTask<JavaScriptLexer> {
    private JavaScriptParser.ProgramContext program;
    private final JavaScriptParser parser;

    public JavascriptLexTask(Language language) {
        super(language);
        setKeywords(JavaScriptLexer.VOCABULARY);
        parser = new JavaScriptParser(null);
    }

    @Override
    public JavaScriptLexer generateLexer() {
        JavaScriptLexer lexer= new JavaScriptLexer(null);
        lexer.setUseStrictDefault(false);
        return lexer;
    }


    @Override
    public void tokenizeIn(List<Span> tokens, List<BlockLine> lines, List<SymbolPair> pairs, List<Variable> variables, JavaScriptLexer lexer, int fromIndex, int fromLineOffset, int fromLine, String code) {
        program = null;
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
                    addTokenSpan(tokens,  lastline,lastlineOffset,lastIndex,startIndex - lastIndex, ColorScheme.Colorable.TEXT);
                lastIndex = stopIndex;
                lastline = line;
                lastlineOffset = lineOffset;

                makeTokenVariableRegion(tokenType, OpenBrace, CloseBrace, variables, variableStacks, startIndex);
                addVariable(token, tokenType);

                addTokenPair(tokenType, OpenBrace, CloseBrace, pairs, pairCurlyStacks, startIndex, line);
                addTokenPair(tokenType, OpenParen, CloseParen, pairs, pairParenStacks, startIndex, line);
                addTokenPair(tokenType, OpenBracket, CloseBracket, pairs, pairBrackStacks, startIndex, line);
                switch (tokenType) {
                    case TemplateStringLiteral:
                    case StringLiteral:
                        addPair(pairs,startIndex, line, stopIndex - 1, line);
                        break;
                }
                addTokenLine(tokenType, OpenBrace, CloseBrace, lines, lineCurlyStacks, startIndex, line);
                addTokenLine(tokenType, OpenParen, CloseParen, lines, lineParenStacks, startIndex, line);
                addTokenLine(tokenType, OpenBracket, CloseBracket, lines, lineBracketStacks, startIndex, line);
                 addTokenSpan(tokens,token, tokenType, startIndex, line, lineOffset, len);
        }
    }


    @Override
    protected void addVariable(Token token, Integer tokenType) {
        if (tokenType == Identifier)
            addVariableStack(variableStacks, Variable.Type.Var, token.getText());
    }


    @Override
    protected ColorScheme.Colorable getTokenColorable(Integer tokenType) {
        ColorScheme.Colorable type;
        switch (tokenType) {
            case SingleLineComment:
                type = ColorScheme.Colorable.COMMENT_SINGLE;
                break;
            case MultiLineComment:
            case HtmlComment:
            case CDataComment:
                type = ColorScheme.Colorable.COMMENT_REGION;
                break;
            case OpenBracket:
            case OpenParen:
            case OpenBrace:
            case CloseBracket:
            case CloseParen:
            case CloseBrace:
            case SemiColon:
            case Comma:
            case Colon:
                type = ColorScheme.Colorable.SEPARATOR;
                break;
            case Assign:
            case QuestionMark:
            case Ellipsis:
            case Dot:
            case PlusPlus:
            case MinusMinus:
            case Plus:
            case Minus:
            case BitNot:
            case Not:
            case Multiply:
            case Divide:
            case Modulus:
            case RightShiftArithmetic:
            case LeftShiftArithmetic:
            case RightShiftLogical:
            case LessThan:
            case MoreThan:
            case LessThanEquals:
            case GreaterThanEquals:
            case Equals_:
            case NotEquals:
            case IdentityEquals:
            case IdentityNotEquals:
            case BitAnd:
            case BitXOr:
            case BitOr:
            case And:
            case Or:
            case MultiplyAssign:
            case DivideAssign:
            case ModulusAssign:
            case PlusAssign:
            case MinusAssign:
            case LeftShiftArithmeticAssign:
            case RightShiftArithmeticAssign:
            case RightShiftLogicalAssign:
            case BitAndAssign:
            case BitXorAssign:
            case BitOrAssign:
            case ARROW:
                type = ColorScheme.Colorable.SYMBOL;
                break;
            case NullLiteral:
                type = ColorScheme.Colorable.BASIC_TYPE;
                break;
            case BooleanLiteral:
            case DecimalLiteral:
            case HexIntegerLiteral:
            case OctalIntegerLiteral:
            case OctalIntegerLiteral2:
            case BinaryIntegerLiteral:
                type = ColorScheme.Colorable.LITERAL_NUM;
                break;
            case TemplateStringLiteral:
            case StringLiteral:
                type = ColorScheme.Colorable.LITERAL_STRING;
                break;
            case Break:
            case Do:
            case Instanceof:
            case Typeof:
            case Case:
            case Else:
            case New:
            case Var:
            case Catch:
            case Finally:
            case Return:
            case Void:
            case Continue:
            case For:
            case Switch:
            case While:
            case Debugger:
            case Function:
            case This:
            case With:
            case Default:
            case If:
            case Throw:
            case Delete:
            case In:
            case Try:
            case Class:
            case Enum:
            case Extends:
            case Super:
            case Const:
            case Export:
            case Import:
            case Implements:
            case Let:
            case Private:
            case Public:
            case Interface:
            case Package:
            case Protected:
            case Static:
            case Yield:
                type = ColorScheme.Colorable.KEYWORD;
                break;
            case UnexpectedCharacter:
                type = ColorScheme.Colorable.ERROR;
                break;
            case Identifier:
                type = ColorScheme.Colorable.LITERAL_VAR;
                break;
            default:
                type = ColorScheme.Colorable.TEXT;
                break;
        }
        return type;
    }


    @Override
    protected void parse(JavaScriptLexer lexer) {
        CommonTokenStream tks = new CommonTokenStream(lexer);
        parser.setTokenStream(tks);
        program = parser.program();
    }


    @Override
    protected ParseTree getTree() {
        return program;
    }

    @Override
    public boolean canFormat() {
        return true;
    }

    @Override
    public synchronized void format(String input, int width, int curPos, FormatCallback callback) throws Exception {
            JavaScriptLexer lexer = generateLexer();
            lexer.setInputStream(CharStreams.fromString(input));
            CharSequence newStr = JavascriptFormatter.getInstance().format(lexer, getLanguage().indentChar, width);
            callback.onDone(newStr.toString(), curPos);
    }

    @Override
    public int createAutoIndent(CharSequence subSequence) {
        JavaScriptLexer lexer = generateLexer();
        lexer.setInputStream(CharStreams.fromString(subSequence.toString()));
        return JavascriptFormatter.getInstance().createAutoIndent(lexer);
    }
}
