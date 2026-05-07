package tiiehenry.code.language.markdown;

import android.graphics.Rect;

import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;

import tiiehenry.code.praser.BlockLine;
import tiiehenry.code.praser.SymbolPair;
import tiiehenry.code.praser.Span;
import tiiehenry.code.praser.Variable;
import tiiehenry.code.antlr4.MarkdownLexer;
import tiiehenry.code.language.Antlr4LexTask;
import tiiehenry.code.language.Language;
import tiiehenry.code.language.css.CssLanguage;
import tiiehenry.code.language.css.CssLexTask;
import tiiehenry.code.view.ColorScheme;

import static tiiehenry.code.antlr4.MarkdownLexer.*;

public class MarkdownLexTask extends Antlr4LexTask<MarkdownLexer> {
    private final MarkdownLexer lexer;


    CssLexTask cssLexTask = (CssLexTask) CssLanguage.getInstance().newLexTask();

    public MarkdownLexTask(Language language) {
        super(language);

        setKeywords(MarkdownLexer.VOCABULARY);
        lexer = new MarkdownLexer(null);
    }
    ArrayList<Rect> lineTagStacks = new ArrayList<>(8196);
    ArrayList<Rect> lineStyleStacks = new ArrayList<>(8196);

    @Override
    public void tokenizeIn(List<Span> tokens, List<BlockLine> lines, List<SymbolPair> pairs, List<Variable> variables, MarkdownLexer lexer, int fromIndex, int fromLineOffset, int fromLine, String code) {
        lineTagStacks.clear();
        lineStyleStacks.clear();
        clearFuncName();
        int lastIndex = fromIndex;
        int lastline = fromLine;
        int lastlineOffset = fromLineOffset;
        int lastType = 0;
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
                addTokenSpan(tokens,  lastline,lastlineOffset,lastIndex,startIndex - lastIndex, ColorScheme.Colorable.TEXT);
            }
            lastIndex = stopIndex;
            lastline = line;
            lastlineOffset = lineOffset;

             addTokenSpan(tokens,token, tokenType, startIndex, line, lineOffset, len);
        }
        updateFuncName();
    }


    @Override
    protected void addVariable(Token token, Integer tokenType) {

    }

    @Override
    protected ColorScheme.Colorable getTokenColorable(Integer tokenType) {
        ColorScheme.Colorable type;
        switch (tokenType) {
            case TITLE:
                type = ColorScheme.Colorable.KEYWORD;
                break;
            case SINGLE_COMMENT:
                type = ColorScheme.Colorable.COMMENT_SINGLE;
                break;
            case MULTI_COMMENT:
                type = ColorScheme.Colorable.COMMENT_REGION;
                break;
            case CODE_SINGLE:
                type = ColorScheme.Colorable.KEYWORD_SECOND;
                break;
            case TEXT_BOLDITALIC:
                type = ColorScheme.Colorable.COMMENT_REGION;
                break;
            case TEXT_BOLD:
                type = ColorScheme.Colorable.KEYWORD;
                break;
            case TEXT_ITALIC:
                type = ColorScheme.Colorable.COMMENT_REGION;
                break;
            case TEXT_STRIKETHROUGH:
                type = ColorScheme.Colorable.PACKAGE;
                break;
            case TABLE:
                type = ColorScheme.Colorable.SYMBOL;
                break;
            case QUOTE_TEXT:
                type = ColorScheme.Colorable.PACKAGE;
                break;
            case QUOTE_PICTURE:
            case QUOTE_LINK:
                type = ColorScheme.Colorable.LINK;
                break;
            case LIST_DISRODER:
            case LIST_INRODER:
                type = ColorScheme.Colorable.INTERNAL;
                break;
            case DTD://<! >
                type = ColorScheme.Colorable.PACKAGE;
                break;
            case HTML_COMMENT:
                type = ColorScheme.Colorable.COMMENT_REGION;
                break;
            case HTML_CONDITIONAL_COMMENT:
                type = ColorScheme.Colorable.LITERAL_STRING;
                break;

            default:
                type = ColorScheme.Colorable.TEXT;
                break;
        }
        return type;
    }

    @Override
    protected void parse(MarkdownLexer lexer) {

    }

    @Override
    protected MarkdownLexer generateLexer() {
        return new MarkdownLexer(null);
    }

}

