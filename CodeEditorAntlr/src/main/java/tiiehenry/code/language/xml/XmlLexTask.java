package tiiehenry.code.language.xml;


import org.antlr.v4.runtime.CodePointCharStream;
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
import tiiehenry.code.antlr4.XMLLexer;
import tiiehenry.code.antlr4.XMLParser;
import tiiehenry.code.language.Antlr4LexTask;
import tiiehenry.code.language.FileItem;
import tiiehenry.code.language.Language;
import tiiehenry.code.view.ColorScheme;

import static org.antlr.v4.runtime.Recognizer.EOF;
import static tiiehenry.code.antlr4.XMLLexer.*;

public class XmlLexTask extends Antlr4LexTask<XMLLexer> {
    protected final XMLParser parser;
    private final XMLLexer lexer;
    protected XMLParser.DocumentContext document;

    protected FileItem item;

    public XmlLexTask(FileItem item) {
        this(XmlLanguage.getInstance());
        this.item = item;

    }

    public XmlLexTask(Language language) {
        super(language);
        setKeywords(XMLLexer.VOCABULARY);
        parser = new XMLParser(null);
        lexer = new XMLLexer(null);
        if (item != null) {
            parser.removeErrorListeners();
            parser.addErrorListener(item);
        }
        document = null;
    }


    @Override
    public void tokenizeIn(List<Span> tokens, List<BlockLine> lines, List<SymbolPair> pairs, List<Variable> variables, XMLLexer lexer, int fromIndex, int fromLineOffset, int fromLine, String code) {
        ArrayList<BlockLine> lineStacks = lineParenStacks;
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
                addTokenSpan(tokens,  lastline,lastlineOffset,lastIndex,startIndex - lastIndex, ColorScheme.Colorable.TEXT);
            }
            lastIndex = stopIndex;
            lastline = line;
            lastlineOffset = lineOffset;


            switch (tokenType) {
                case OPEN:// <   </也会被解析
                    openVariableStack(variableStacks, startIndex);
                    lineStacks.add(new BlockLine(startIndex, line, 0, line));
                    break;
                case SLASH:// /
                    int size = lineStacks.size();
                    if (lastType == OPEN) {
                        lineStacks.remove(size - 1);
                        size--;
                    }
                    if (size > 0) {
                        BlockLine rect = lineStacks.remove(size - 1);
                        rect.endLine = line;
                        rect.endIndex = startIndex - 1;
                        if (rect.endLine - rect.startLine > 1)
                            lines.add(rect);
                    }
                    break;
                case CLOSE:
                    closeVariableStack(variableStacks, startIndex, variables);
                    break;
                case SLASH_CLOSE:// />
                    closeVariableStack(variableStacks, startIndex, variables);
                    int size2 = lineStacks.size();
                    if (size2 > 0) {
                        BlockLine rect = lineStacks.remove(size2 - 1);
                        rect.endLine = line + 1;
                        rect.endIndex = startIndex - 1;
                        if (rect.endLine - rect.startLine > 1)
                            lines.add(rect);
                    }
                    break;
                case Name:
                    if (lastType == OPEN || lastType == SLASH) {
                        addVariableStack(variableStacks, Variable.Type.Func_Internal, token.getText());
                    } else {
                        addVariableStack(variableStacks, Variable.Type.Func_User, token.getText());
                    }
                    break;
                case STRING:
                    addPair(pairs,startIndex, line, stopIndex - 1, line);
                    break;
            }

             addTokenSpan(tokens,token, tokenType, startIndex, line, lineOffset, len);
            if (tokenType != SEA_WS)
                lastType = tokenType;
        }
        addFuncName("android:");
        addFuncName("app:");
        addFuncName("tools:");
        addFuncName("layout_:");
        updateFuncName();
    }

    private int lastType = 0;

    @Override
    protected ColorScheme.Colorable getTokenColorable(Integer tokenType) {
        ColorScheme.Colorable type;
        switch (tokenType) {
            case XMLDeclOpen:// <?
            case DTD:
            case SPECIAL_CLOSE:// ?>
                type = ColorScheme.Colorable.PACKAGE;
                break;
            case COMMENT:
                type = ColorScheme.Colorable.COMMENT_REGION;
                break;
            case OPEN:// <   </也会被解析
            case SLASH:// /
            case CLOSE:// >
            case SLASH_CLOSE:// />
                type = ColorScheme.Colorable.SEPARATOR;
                break;
            case Name:
                if (lastType == OPEN || lastType == SLASH) {
                    type = ColorScheme.Colorable.KEYWORD;
                } else {
                    type = ColorScheme.Colorable.LITERAL_FUNC;
                }
                break;
            case STRING://包括"
                type = ColorScheme.Colorable.LITERAL_STRING;
                break;
            case EQUALS:
                type = ColorScheme.Colorable.SYMBOL;
                break;
            default:
                type = ColorScheme.Colorable.TEXT;
                break;
        }
        return type;
    }


    @Override
    protected void parse(XMLLexer lexer) {
        if (item != null)
            item.reset();
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        parser.setTokenStream(tokens);
        document = parser.document();
    }

    protected void parse(XMLLexer lexer, int endType) {
        while (true) {
            if (lexer.nextToken().getType() == endType)
                break;
        }
    }

    @Override
    protected boolean canAnalysis() {
        return item != null;
    }

    @Override
    protected ParseTree getTree() {
        return document;
    }


    @Override
    public boolean canFormat() {
        return true;
    }

    @Override
    protected void parse(CodePointCharStream i) {
        lexer.setInputStream(i);
        parse(lexer);
    }


    @Override
    public boolean isError(int index) {
        FileItem item = this.item;
        if (item == null)
            return false;
        return item.isError(index);
    }


    @Override
    protected int formatInThread(StringBuilder sb, IndentStringBuilder isb, XMLLexer lexer, CharSequence input, int width, int curPos) {
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        parser.setTokenStream(tokens);
        XmlFormatter f = new XmlFormatter(isb, width, curPos);
        parser.document().accept(f);
        sb.append(isb);
        if (sb.length() == 0)
            sb.append(input);
        return f.newPos;
    }

    @Override
    public XMLLexer generateLexer() {
        return new XMLLexer(null);
    }
}

