package tiiehenry.code.language.cpp;


import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

import java.lang.Override;
import java.util.List;

import tiiehenry.code.format.FormatCallback;
import tiiehenry.code.antlr4.CPP14Lexer;
import tiiehenry.code.antlr4.CPP14Parser;
import tiiehenry.code.language.Antlr4LexTask;
import tiiehenry.code.language.Language;
import tiiehenry.code.praser.BlockLine;
import tiiehenry.code.praser.Span;
import tiiehenry.code.praser.SymbolPair;
import tiiehenry.code.praser.Variable;
import tiiehenry.code.view.ColorScheme;

import static tiiehenry.code.antlr4.CPP14Lexer.Class;
import static tiiehenry.code.antlr4.CPP14Lexer.Double;
import static tiiehenry.code.antlr4.CPP14Lexer.Enum;
import static tiiehenry.code.antlr4.CPP14Lexer.Float;
import static tiiehenry.code.antlr4.CPP14Lexer.Long;
import static tiiehenry.code.antlr4.CPP14Lexer.Override;
import static tiiehenry.code.antlr4.CPP14Lexer.Short;
import static tiiehenry.code.antlr4.CPP14Lexer.*;


public class CppLexTask extends Antlr4LexTask<CPP14Lexer> {
    private CPP14Parser.TranslationunitContext unit;
    private final CPP14Parser parser;

    public CppLexTask(Language language) {
        super(language);
//        setKeywords(CPP14Lexer.VOCABULARY);

        parser = new CPP14Parser(null);
    }

    @Override
    protected CPP14Lexer generateLexer() {
        return new CPP14Lexer(null);
    }

    @java.lang.Override
    public void tokenizeIn(List<Span> tokens, List<BlockLine> lines, List<SymbolPair> pairs, List<Variable> variables, CPP14Lexer lexer, int fromIndex, int fromLineOffset, int fromLine, String code) {
        unit = null;
        int lastIndex = fromIndex;
        int lastline = fromLine;
        int lastlineOffset = fromLineOffset;
        int lastType = 0;
        while (!abort) {
            Token token = lexer.nextToken();

            int tokenType = token.getType();
//            String text = token.getText();

            if (tokenType ==EOF)
                break;
            int startIndex = fromIndex + token.getStartIndex();
            int stopIndex = fromIndex + token.getStopIndex() + 1;
            int len = stopIndex - startIndex;
            int line = fromLine + token.getLine();
            int lineOffset = token.getCharPositionInLine();

            if (startIndex != lastIndex)
                addTokenSpan(tokens, lastline,lastlineOffset,lastIndex,startIndex - lastIndex, ColorScheme.Colorable.TEXT);
            lastIndex = stopIndex;
            lastline = line;
            lastlineOffset = lineOffset;

            makeTokenVariableRegion(tokenType, LeftBrace, RightBrace, variables, variableStacks, startIndex);
            addVariable(token, tokenType);

            addTokenPair(tokenType, LeftBrace, RightBrace, pairs, pairCurlyStacks, startIndex, line);
            addTokenPair(tokenType, LeftParen, RightParen, pairs, pairParenStacks, startIndex, line);
            addTokenPair(tokenType, LeftBracket, RightBracket, pairs, pairBrackStacks, startIndex, line);
            if (tokenType == Stringliteral) {
                addPair(pairs,startIndex, line, stopIndex - 1, line);
            }
            addTokenLine(tokenType, LeftBrace, RightBrace, lines, lineCurlyStacks, startIndex, line);
            addTokenLine(tokenType, LeftParen, RightParen, lines, lineParenStacks, startIndex, line);
//            addTokenLine(token, tokenType, LeftBracket, RightBracket, lines, lineBracketStacks, startIndex, line);

             addTokenSpan(tokens,token,tokenType,startIndex,line,lineOffset,len);
        }
    }

    @Override
    protected ColorScheme.Colorable getTokenColorable(Integer tokenType) {
        ColorScheme.Colorable type;
        switch (tokenType) {
            case Alignas:
            case Alignof:
            case Asm:
            case Auto:
            case Break:
            case Class:
            case Const:
            case Catch:
            case Case:
            case Constexpr:
            case Const_cast:
            case Continue:
            case Decltype:
            case Default:
            case Delete:
            case Do:
            case Dynamic_cast:
            case Else:
            case Enum:
            case Explicit:
            case Export:
            case Extern:
            case Final:
            case For:
            case Friend:
            case Goto:
            case If:
            case Inline:
            case Mutable:
            case Namespace:
            case New:
            case Noexcept:
            case Operator:
            case Override:
            case Private:
            case Protected:
            case Public:
            case Register:
            case Reinterpret_cast:
            case Return:
            case Sizeof:
            case Signed:
            case Static:
            case Static_cast:
            case Static_assert:
            case Struct:
            case Switch:
            case Template:
            case This:
            case Throw:
            case Thread_local:
            case Try:
            case Typedef:
            case Typeid_:
            case Typename_:
            case Unsigned:
            case Union:
            case Using:
            case Virtual:
            case Volatile:
            case While:
                type = ColorScheme.Colorable.KEYWORD;
                break;
            case Bool:
            case Char:
            case Char16:
            case Char32:
            case Double:
            case Float:
            case Int:
            case Long:
            case Short:
            case Wchar:
            case True:
            case False:
            case Nullptr:
                type = ColorScheme.Colorable.BASIC_TYPE;
                break;
            case LeftBrace:
            case RightBrace:
            case LeftParen:
            case LeftBracket:
            case RightParen:
            case RightBracket:
            case Dot:
            case Semi:
            case Comma:
            case Doublecolon:
                type = ColorScheme.Colorable.SEPARATOR;
                break;
            case Plus:
            case Minus:
            case Star:
            case Div:
            case Mod:
            case Caret:
            case And:
            case Or:
            case Tilde:
            case Not:
            case Assign:
            case Less:
            case Greater:
            case PlusAssign:
            case MinusAssign:
            case StarAssign:
            case DivAssign:
            case ModAssign:
            case XorAssign:
            case AndAssign:
            case OrAssign:
            case LeftShift:
            case LeftShiftAssign:
            case Equal:
            case NotEqual:
            case LessEqual:
            case GreaterEqual:
            case AndAnd:
            case OrOr:
            case PlusPlus:
            case MinusMinus:
            case Arrow:
            case ArrowStar:
            case Question:
            case Colon:
            case DotStar:
            case Ellipsis:
                type = ColorScheme.Colorable.SYMBOL;
                break;
            case Integerliteral:
            case Decimalliteral:
            case Octalliteral:
            case Hexadecimalliteral:
            case Binaryliteral:
            case Integersuffix:
            case Characterliteral:
            case Floatingliteral:
                type = ColorScheme.Colorable.LITERAL_NUM;
                break;
            case Stringliteral:
                type = ColorScheme.Colorable.LITERAL_STRING;
                break;
            case BlockComment:
                type = ColorScheme.Colorable.COMMENT_REGION;
                break;
            case LineComment:
                type = ColorScheme.Colorable.COMMENT_SINGLE;
                break;
            case MultiLineMacro:
            case IFDEF:
            case Directive:
                type = ColorScheme.Colorable.PACKAGE;
                break;
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
    protected boolean canAnalysis() {
        return true;
    }

    @Override
    protected void parse(CPP14Lexer lexer) {
        CommonTokenStream tks = new CommonTokenStream(lexer);
        parser.setTokenStream(tks);
        unit = parser.translationunit();
    }


    @Override
    protected ParseTree getTree() {
        return unit;
    }

    @Override
    public boolean canFormat() {
        return true;
    }

    @Override
    public synchronized void format(String input, int width, int curPos, FormatCallback callback) throws Exception {
            Lexer lexer = generateLexer();
            lexer.setInputStream(CharStreams.fromString(input));
            CharSequence newStr = CppFormatter.getInstance().format(lexer, getLanguage().indentChar, width);
            callback.onDone(newStr.toString(), curPos);
    }

    @Override
    public int createAutoIndent(CharSequence subSequence) {
        Lexer lexer = generateLexer();
        lexer.setInputStream(CharStreams.fromString(subSequence.toString()));
        return CppFormatter.getInstance().createAutoIndent(lexer);
    }
}
