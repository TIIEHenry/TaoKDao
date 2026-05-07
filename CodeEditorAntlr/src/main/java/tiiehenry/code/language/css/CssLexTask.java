package tiiehenry.code.language.css;


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
import tiiehenry.code.antlr4.Css3Lexer;
import tiiehenry.code.antlr4.Css3Parser;
import tiiehenry.code.language.Antlr4LexTask;
import tiiehenry.code.language.Language;
import tiiehenry.code.view.ColorScheme;

import static tiiehenry.code.antlr4.Css3Lexer.And;
import static tiiehenry.code.antlr4.Css3Lexer.BitOr;
import static tiiehenry.code.antlr4.Css3Lexer.Calc;
import static tiiehenry.code.antlr4.Css3Lexer.Cdc;
import static tiiehenry.code.antlr4.Css3Lexer.Cdo;
import static tiiehenry.code.antlr4.Css3Lexer.Charset;
import static tiiehenry.code.antlr4.Css3Lexer.Colon;
import static tiiehenry.code.antlr4.Css3Lexer.Comma;
import static tiiehenry.code.antlr4.Css3Lexer.Comment;
import static tiiehenry.code.antlr4.Css3Lexer.CounterStyle;
import static tiiehenry.code.antlr4.Css3Lexer.DashMatch;
import static tiiehenry.code.antlr4.Css3Lexer.Dimension;
import static tiiehenry.code.antlr4.Css3Lexer.Dot;
import static tiiehenry.code.antlr4.Css3Lexer.DxImageTransform;
import static tiiehenry.code.antlr4.Css3Lexer.FontFace;
import static tiiehenry.code.antlr4.Css3Lexer.FontFeatureValues;
import static tiiehenry.code.antlr4.Css3Lexer.From;
import static tiiehenry.code.antlr4.Css3Lexer.Function;
import static tiiehenry.code.antlr4.Css3Lexer.Greater;
import static tiiehenry.code.antlr4.Css3Lexer.Hash;
import static tiiehenry.code.antlr4.Css3Lexer.Import;
import static tiiehenry.code.antlr4.Css3Lexer.Includes;
import static tiiehenry.code.antlr4.Css3Lexer.Keyframes;
import static tiiehenry.code.antlr4.Css3Lexer.LBrace;
import static tiiehenry.code.antlr4.Css3Lexer.LBrack;
import static tiiehenry.code.antlr4.Css3Lexer.LParen;
import static tiiehenry.code.antlr4.Css3Lexer.Media;
import static tiiehenry.code.antlr4.Css3Lexer.MediaOnly;
import static tiiehenry.code.antlr4.Css3Lexer.Minus;
import static tiiehenry.code.antlr4.Css3Lexer.Namespace;
import static tiiehenry.code.antlr4.Css3Lexer.Not;
import static tiiehenry.code.antlr4.Css3Lexer.Number;
import static tiiehenry.code.antlr4.Css3Lexer.Or;
import static tiiehenry.code.antlr4.Css3Lexer.Page;
import static tiiehenry.code.antlr4.Css3Lexer.Percentage;
import static tiiehenry.code.antlr4.Css3Lexer.Plus;
import static tiiehenry.code.antlr4.Css3Lexer.PrefixMatch;
import static tiiehenry.code.antlr4.Css3Lexer.PseudoNot;
import static tiiehenry.code.antlr4.Css3Lexer.RBrace;
import static tiiehenry.code.antlr4.Css3Lexer.RBrack;
import static tiiehenry.code.antlr4.Css3Lexer.RParen;
import static tiiehenry.code.antlr4.Css3Lexer.Semi;
import static tiiehenry.code.antlr4.Css3Lexer.Star;
import static tiiehenry.code.antlr4.Css3Lexer.String;
import static tiiehenry.code.antlr4.Css3Lexer.SubstringMatch;
import static tiiehenry.code.antlr4.Css3Lexer.SuffixMatch;
import static tiiehenry.code.antlr4.Css3Lexer.Supports;
import static tiiehenry.code.antlr4.Css3Lexer.Tilde;
import static tiiehenry.code.antlr4.Css3Lexer.To;
import static tiiehenry.code.antlr4.Css3Lexer.UnderScroll;
import static tiiehenry.code.antlr4.Css3Lexer.Uri;
import static tiiehenry.code.antlr4.Css3Lexer.Var;
import static tiiehenry.code.antlr4.Css3Lexer.Viewport;


public class CssLexTask extends Antlr4LexTask<Css3Lexer> {
    private Css3Parser.StylesheetContext unit;
    private final Css3Parser parser;

    public CssLexTask(Language language) {
        super(language);
        setKeywords(Css3Lexer.VOCABULARY);

        parser = new Css3Parser(null);
    }

    public void tokenizeIn(List<Span> tokens, List<BlockLine> lines, List<SymbolPair> pairs, List<Variable> variables, Css3Lexer lexer, int fromIndex, int fromLineOffset, int fromLine, java.lang.String code) {
        unit = null;
        int lastIndex = fromIndex;
        int lastline = fromLine;
        int lastlineOffset = fromLineOffset;
        while (!abort) {
            Token token = lexer.nextToken();
            int tokenType = token.getType();
            if (tokenType == -1)
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

            makeTokenVariableRegion(tokenType, LBrace, RBrace, variables, variableStacks, startIndex);
            addVariable(token, tokenType);

            addTokenPair(tokenType, LBrace, RBrace, pairs, pairCurlyStacks, startIndex, line);
            addTokenPair(tokenType, LParen, RParen, pairs, pairParenStacks, startIndex, line);
            addTokenPair(tokenType, LBrack, RBrack, pairs, pairBrackStacks, startIndex, line);
            if (tokenType == String) {
                addPair(pairs,startIndex, line, stopIndex - 1, line);
            }

            addTokenLine(tokenType, LBrace, RBrace, lines, lineCurlyStacks, startIndex, line);
            addTokenLine(tokenType, LParen, RParen, lines, lineParenStacks, startIndex, line);
//            addTokenLine(token, tokenType, LeftBracket, RightBracket, lines, lineBracketStacks, startIndex, line);

             addTokenSpan(tokens,token, tokenType, startIndex, line, lineOffset, len);
        }
    }

    @Override
    protected ColorScheme.Colorable getTokenColorable(Integer tokenType) {
        ColorScheme.Colorable type;
        switch (tokenType) {
            case Comment:
                type = ColorScheme.Colorable.COMMENT_SINGLE;
                break;
            case LParen:
            case RParen:
            case LBrace:
            case RBrace:
            case LBrack:
            case RBrack:
            case Colon:
            case Comma:
            case Semi:
            case Dot:
            case UnderScroll:
                type = ColorScheme.Colorable.SEPARATOR;
                break;
            case String:
                type = ColorScheme.Colorable.LITERAL_STRING;
                break;
            case Number:
            case Uri:
            case Dimension:
            case Percentage:
            case Hash:
                type = ColorScheme.Colorable.LITERAL_FUNC;
                break;
            case MediaOnly:
            case Not:
            case And:
            case PseudoNot:
            case Or:
            case FontFace:
            case Supports:
            case Keyframes:
            case From:
            case To:
            case Viewport:
            case CounterStyle:
            case FontFeatureValues:
            case Media:
            case Import:
            case Page:
            case Namespace:
            case Charset:
                type = ColorScheme.Colorable.KEYWORD;
                break;
            case Calc:
            case DxImageTransform:
            case Var:
            case Function:
                type = ColorScheme.Colorable.LITERAL_FUNC;
                break;
            case Plus:
            case Minus:
            case Greater:
            case Tilde:
            case PrefixMatch:
            case SuffixMatch:
            case SubstringMatch:
            case Star:
            case BitOr:
            case Cdo:
            case Cdc:
            case Includes:
            case DashMatch:
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
    protected void parse(Css3Lexer lexer) {
        CommonTokenStream tks = new CommonTokenStream(lexer);
        parser.setTokenStream(tks);
        unit = parser.stylesheet();
    }

    @Override
    protected ParseTree getTree() {
        return unit;
    }

    @Override
    public Css3Lexer generateLexer() {
        return new Css3Lexer(null);
    }

    @Override
    public boolean canFormat() {
        return true;
    }

    @Override
    public synchronized void format(String input, int width, int curPos, FormatCallback callback) throws Exception {
            Lexer lexer = generateLexer();
            lexer.setInputStream(CharStreams.fromString(input));
            CharSequence newStr = CssFormatter.getInstance().format(lexer, getLanguage().indentChar, width);
            callback.onDone(newStr.toString(), curPos);
    }

    @Override
    public int createAutoIndent(CharSequence subSequence) {
        Lexer lexer = generateLexer();
        lexer.setInputStream(CharStreams.fromString(subSequence.toString()));
        return CssFormatter.getInstance().createAutoIndent(lexer);
    }
}
