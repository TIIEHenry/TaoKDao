package tiiehenry.code.language.xml;


import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

import tiiehenry.code.IndentStringBuilder;
import tiiehenry.code.antlr4.XMLParser;
import tiiehenry.code.antlr4.XMLParserBaseVisitor;
import tiiehenry.code.language.Antlr4LexTask;

import static tiiehenry.code.antlr4.XMLLexer.Name;
import static tiiehenry.code.antlr4.XMLLexer.SEA_WS;
import static tiiehenry.code.antlr4.XMLLexer.SPECIAL_CLOSE;

public class XmlFormatter extends XMLParserBaseVisitor<Void> {
    private IndentStringBuilder sb;
    private int width;
    private int curPos;
    private boolean shouldNewLine = false;
    public int newPos;

    public XmlFormatter(IndentStringBuilder sb, int width, int curPos) {
        this.sb = sb;
        this.width = width;
        this.curPos = curPos;
        newPos = curPos;
    }

    private void checkNewLine() {
        while (sb.last() == '\n') {
            sb.deAppend();
        }
        sb.append("\n");
    }

    @Override
    public Void visitElement(XMLParser.ElementContext ctx) {
//        checkNewLine();
        sb.append('<');
        sb.indent(width);
        ctx.Name(0).accept(this);
        List<XMLParser.AttributeContext> attrs = ctx.attribute();
        shouldNewLine = attrs.size() > 1;
        for (XMLParser.AttributeContext attr : attrs) {
            attr.accept(this);
        }
        XMLParser.ContentContext content = ctx.content();
        if (content == null) {
            sb.append("/>");
            sb.append("\n");
            sb.deindent(width);
        } else {
            sb.append('>');
            checkNewLine();
//            sb.append("\n");
            content.accept(this);
            checkNewLine();
            sb.deindent(width);
            sb.append("</");
            visitTerminal(ctx.Name(1));
            //ctx.Name(1).accept(this);
            sb.append('>');
//            checkNewLine();
//            sb.append("\n");
        }
        return null;
    }


    @Override
    public Void visitAttribute(XMLParser.AttributeContext ctx) {
        if (shouldNewLine)
            sb.append("\n");
        else
            sb.append(' ');
        ctx.Name().accept(this);
        sb.append('=');
        ctx.STRING().accept(this);
        return null;
    }

    //    int lastType=
    @Override
    public Void visitTerminal(TerminalNode node) {
        if (node == null)
            return null;
        Token token = node.getSymbol();
        String text = token.getText();
        int type = token.getType();
        switch (type) {
            case SEA_WS:
                processWhiteSpace(sb, text);
//                Log.e(TAG, "visitTerminal: "+type );
//                Log.e(TAG, "visitTerminal: "+token.getStartIndex() );
                break;
            case SPECIAL_CLOSE://?>前面
                sb.append(text);
                break;
            case Name://LinearLayout
//                if (text.startsWith("xmlns")) {
//                    sb.append(' ');
//                    sb.deAppend(width + 2);
//                    sb.append(' ');
//                }
                sb.append(text);
                break;
            default:
                sb.append(text);
                break;
        }
        int start = token.getStartIndex();
        int end = token.getStopIndex();
        newPos = Antlr4LexTask.compute(sb.length(), start, end, curPos, newPos);
        return null;
    }

    private static void processWhiteSpace(IndentStringBuilder sb, String text) {
        if (text.contains("\n")) {
            for (char c : text.toCharArray())
                if (c == '\n')
                    sb.append(c);
        } else {
//                Log.e("indentChar", "processWhiteSpace: "+indentChar );
            sb.append(' ');
        }
    }
}
