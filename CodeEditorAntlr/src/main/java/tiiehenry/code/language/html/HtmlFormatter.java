package tiiehenry.code.language.html;

import android.util.Log;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

import tiiehenry.code.format.FormatCallback;
import tiiehenry.code.IndentStringBuilder;
import tiiehenry.code.antlr4.HTMLParser;
import tiiehenry.code.antlr4.HTMLParserBaseVisitor;
import tiiehenry.code.language.Antlr4LexTask;
import tiiehenry.code.language.Language;
import tiiehenry.code.language.css.CssLanguage;
import tiiehenry.code.language.css.CssLexTask;
import tiiehenry.code.language.java.JavaLanguage;
import tiiehenry.code.language.java.JavaLexTask;
import tiiehenry.code.language.javascript.JavascriptLanguage;
import tiiehenry.code.language.javascript.JavascriptLexTask;

import static tiiehenry.code.antlr4.HTMLParser.SEA_WS;
import static tiiehenry.code.antlr4.HTMLParser.ScriptletContext;
import static tiiehenry.code.antlr4.HTMLParser.TAG_NAME;

public class HtmlFormatter extends HTMLParserBaseVisitor<String> {
    private IndentStringBuilder sb;
    private int width;
    private int curPos;
    private boolean shouldNewLine = false;
    int newPos;


    private JavascriptLexTask javascriptLexTask;
    private JavaLexTask javaLexTask;
    private CssLexTask cssLexTask;

    public HtmlFormatter(HtmlLexTask lexTask, IndentStringBuilder sb, int width, int curPos) {
        this.sb = sb;
        this.width = width;
        this.curPos = curPos;
        newPos = curPos;
        javascriptLexTask = lexTask.javascriptLexTask;
        javaLexTask = lexTask.javaLexTask;
        cssLexTask = lexTask.cssLexTask;
    }


public void format(HTMLParser parser){

    parser.htmlDocument().accept(this);
    parser.htmlElements().accept(this);
    parser.htmlElement().accept(this);
    parser.htmlContent().accept(this);
    parser.htmlAttribute().accept(this);
    parser.htmlAttributeName().accept(this);
    parser.htmlAttributeValue().accept(this);
    parser.htmlTagName().accept(this);
    parser.htmlChardata().accept(this);
    parser.htmlMisc().accept(this);
    parser.htmlComment().accept(this);
    parser.xhtmlCDATA().accept(this);
    parser.dtd().accept(this);
    parser.xml().accept(this);
    parser.scriptlet().accept(this);
    parser.script().accept(this);
    parser.style().accept(this);
}
    @Override
    public String visitTerminal(TerminalNode node) {
        if (node == null)
            return null;
        Token token = node.getSymbol();
        String text = token.getText();
        int type = token.getType();
        switch (type) {
            case SEA_WS:
                processWhiteSpace(sb, text);
                break;
            case TAG_NAME:
                sb.append(text);
                break;
            default:
                sb.append(text);
                break;
        }
        Log.e("HtmlFormatter", "visitTerminal: "+text );
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

    @Override
    public String visitHtmlDocument(HTMLParser.HtmlDocumentContext ctx) {
        if (ctx.scriptlet() != null)
            for (ScriptletContext scriptlet : ctx.scriptlet()) {
                scriptlet.accept(this);
            }
        if (ctx.SEA_WS() != null)
            for (TerminalNode SEA_WS : ctx.SEA_WS()) {
                SEA_WS.accept(this);
            }
        if (ctx.xml() != null)
            ctx.xml().accept(this);
        if (ctx.dtd() != null)
            ctx.dtd().accept(this);
        if (ctx.htmlElements() != null)
            for (HTMLParser.HtmlElementsContext htmlElements : ctx.htmlElements()) {
                htmlElements.accept(this);
            }
        return null;
    }

    @Override
    public String visitHtmlElements(HTMLParser.HtmlElementsContext ctx) {
        if (ctx.htmlElement() != null)
            ctx.htmlElement().accept(this);
        if (ctx.htmlMisc() != null) {
            for (HTMLParser.HtmlMiscContext htmlMisc : ctx.htmlMisc()) {
                htmlMisc.accept(this);
            }
        }
        return null;
    }

    @Override
    public String visitHtmlElement(HTMLParser.HtmlElementContext ctx) {
        if (ctx.TAG_OPEN() != null)
            for (TerminalNode TAG_OPEN : ctx.TAG_OPEN()) {
                TAG_OPEN.accept(this);
            }

        if (ctx.htmlTagName() != null)
            for (HTMLParser.HtmlTagNameContext htmlTagName : ctx.htmlTagName()) {
                htmlTagName.accept(this);
            }

        if (ctx.TAG_CLOSE() != null)
            for (TerminalNode TAG_CLOSE : ctx.TAG_CLOSE()) {
                TAG_CLOSE.accept(this);
            }
        if (ctx.htmlContent() != null)
            ctx.htmlContent().accept(this);
        visitNode(ctx.TAG_SLASH());

        if (ctx.htmlAttribute() != null) {
            for (HTMLParser.HtmlAttributeContext htmlAttribute : ctx.htmlAttribute()) {
                htmlAttribute.accept(this);
            }
        }
        visitNode(ctx.TAG_SLASH_CLOSE());

        if (ctx.scriptlet() != null) {
//            sb.deindent(width);
            ctx.scriptlet().accept(this);
        }
        if (ctx.script() != null) {
//            sb.deindent(width);
            ctx.script().accept(this);
        }
        if (ctx.style() != null) {
//            sb.deindent(width);
            ctx.style().accept(this);
        }
        if (false) {
            sb.append('<');
            sb.indent(width);
            if (ctx.htmlTagName().size() > 0)
                ctx.htmlTagName(0).accept(this);
            List<HTMLParser.HtmlAttributeContext> attrs = ctx.htmlAttribute();
            shouldNewLine = attrs.size() > 1;
            for (HTMLParser.HtmlAttributeContext attr : attrs) {
                attr.accept(this);
            }

            HTMLParser.HtmlContentContext content = ctx.htmlContent();
            if (content == null) {
                sb.append("/>");
                sb.deindent(width);
            } else {
                sb.append('>');
                content.accept(this);
                sb.deindent(width);
                sb.append("</");
//            visitTerminal();
                //ctx.Name(1).accept(this);
                if (ctx.htmlTagName().size() > 1)
                    ctx.htmlTagName(1).accept(this);
                sb.append('>');
            }
        }

        return null;
    }

    @Override
    public String visitHtmlContent(HTMLParser.HtmlContentContext ctx) {
        if (ctx.htmlChardata() != null) {
            for (HTMLParser.HtmlChardataContext htmlChardata : ctx.htmlChardata()) {
                htmlChardata.accept(this);
            }
        }
        if (ctx.htmlElement() != null) {
            for (HTMLParser.HtmlElementContext htmlElement : ctx.htmlElement()) {
                htmlElement.accept(this);
            }
        }
        if (ctx.xhtmlCDATA() != null) {
            for (HTMLParser.XhtmlCDATAContext xhtmlCDATA : ctx.xhtmlCDATA()) {
                xhtmlCDATA.accept(this);
            }
        }
        if (ctx.htmlComment() != null) {
            for (HTMLParser.HtmlCommentContext htmlComment : ctx.htmlComment()) {
                htmlComment.accept(this);
            }
        }
        return null;
    }

    @Override
    public String visitHtmlAttribute(HTMLParser.HtmlAttributeContext ctx) {
//        if (shouldNewLine)
//            sb.append("\n");
//        else
//            sb.append(' ');
        visitRuleNode(ctx.htmlAttributeName());
        visitNode(ctx.TAG_EQUALS());
        visitRuleNode(ctx.htmlAttributeValue());
        return null;
    }

    @Override
    public String visitHtmlAttributeName(HTMLParser.HtmlAttributeNameContext ctx) {
        sb.append(" ");
        sb.append(ctx.TAG_NAME().getSymbol().getText());
        return null;
    }

    @Override
    public String visitHtmlAttributeValue(HTMLParser.HtmlAttributeValueContext ctx) {
        sb.append(ctx.ATTVALUE_VALUE().getSymbol().getText());
        return null;
    }

    @Override
    public String visitHtmlTagName(HTMLParser.HtmlTagNameContext ctx) {
        visitNode(ctx.TAG_NAME());
        return null;
    }


    private void visitNode(TerminalNode node) {
        if (node != null)
            node.accept(this);
    }

    private void visitRuleNode(ParserRuleContext node) {
        if (node != null)
            node.accept(this);
    }

    @Override
    public String visitHtmlChardata(HTMLParser.HtmlChardataContext ctx) {
        visitNode(ctx.HTML_TEXT());
        visitNode(ctx.SEA_WS());
        return null;
    }

    @Override
    public String visitHtmlMisc(HTMLParser.HtmlMiscContext ctx) {
        visitRuleNode(ctx.htmlComment());
        if (ctx.SEA_WS() != null)
            sb.append(ctx.SEA_WS().getSymbol().getText());
        return null;
    }

    @Override
    public String visitHtmlComment(HTMLParser.HtmlCommentContext ctx) {
        if (ctx.HTML_COMMENT() != null)
            sb.append(ctx.HTML_COMMENT().getSymbol().getText());
        if (ctx.HTML_CONDITIONAL_COMMENT() != null)
            sb.append(ctx.HTML_CONDITIONAL_COMMENT().getSymbol().getText());
        return null;
    }

    @Override
    public String visitXhtmlCDATA(HTMLParser.XhtmlCDATAContext ctx) {
        visitNode(ctx.CDATA());
        return null;
    }

    @Override
    public String visitDtd(HTMLParser.DtdContext ctx) {
        visitNode(ctx.DTD());
        return null;
    }

    @Override
    public String visitXml(HTMLParser.XmlContext ctx) {
        visitNode(ctx.XML_DECLARATION());
        return null;
    }

    @Override
    public String visitScriptlet(HTMLParser.ScriptletContext ctx) {
        if (ctx.SCRIPTLET() != null) {
            final String input = ctx.SCRIPTLET().getText();
            Log.e("visitScriptlet", "visitScriptlet: " + input);
            int startLen = "<%".length();
            if (input.startsWith("<%!"))
                startLen++;
            int endLen = "%>".length();
            startLen = 0;
            endLen = 0;
            String sscriptlet = input.substring(startLen, input.length() - endLen).trim();
            if (sscriptlet.length() > 0) {
                javaLexTask.formatWithCallbackInThread(sscriptlet, width, 0, new FormatCallback() {
                    @Override
                    public void onDone(String s, int newPos) {
                        final String javaIndentStr = JavaLanguage.getInstance().getIndentStr(width);
                        String indentStrLast = Language.getIndentStr(javaIndentStr, sb.indentLevel - 2);
                        String indentStr = indentStrLast + javaIndentStr;
                        s = "<%!" + "\n" + indentStr + s.replaceAll("\n", "\n" + indentStr).trim() + "\n" + indentStrLast + "%>";
                        sb.append(s);
                    }

                    @Override
                    public void onError(String s, String errorMsg) {
                        sb.append(input);
                    }
                });
            } else {
                sb.append(input.trim());
            }
        }
        return "";
    }

    @Override
    public String visitScript(HTMLParser.ScriptContext ctx) {
        if (ctx.SCRIPT_OPEN() != null)
            sb.append(ctx.SCRIPT_OPEN().getSymbol().getText());
        if (ctx.SCRIPT_SHORT_BODY() != null)
            sb.append(ctx.SCRIPT_SHORT_BODY().getSymbol().getText());
        if (ctx.SCRIPT_BODY() != null) {
            final String input = ctx.SCRIPT_BODY().getSymbol().getText();
            int scriptLen = "</script>".length();
            String sscript = input.substring(0, input.length() - scriptLen).trim();
            if (sscript.length() > 0) {
                javascriptLexTask.formatWithCallbackInThread(sscript, width, 0, new FormatCallback() {
                    @Override
                    public void onDone(String s, int newPos) {
                        final String jsIndentStr = JavascriptLanguage.getInstance().getIndentStr(width);
                        String indentStrLast = Language.getIndentStr(jsIndentStr, sb.indentLevel - 2);
                        String indentStr = indentStrLast + jsIndentStr;
                        s = "\n" + indentStr + s.replaceAll("\n", "\n" + indentStr).trim() + "\n" + indentStrLast + "</script>";
                        sb.append(s);
                    }

                    @Override
                    public void onError(String s, String errorMsg) {
                        sb.append(input);
                    }
                });
            } else {
                sb.append(input.trim());
            }
        }
        return null;
    }

    @Override
    public String visitStyle(HTMLParser.StyleContext ctx) {
        final String cssIndentStr = CssLanguage.getInstance().getIndentStr(width);
        if (ctx.STYLE_OPEN() != null)
            sb.append(ctx.STYLE_OPEN().getSymbol().getText());
        if (ctx.STYLE_SHORT_BODY() != null)
            sb.append(ctx.STYLE_SHORT_BODY().getSymbol().getText());
        if (ctx.STYLE_BODY() != null) {
            final String input = ctx.STYLE_BODY().getSymbol().getText();
            int styleLen = "</style>".length();
            final String sstyle = input.substring(0, input.length() - styleLen).trim();
            if (sstyle.length() > 0) {
                cssLexTask.formatWithCallbackInThread(sstyle, width, 0, new FormatCallback() {
                    @Override
                    public void onDone(String s, int newPos) {
                        String indentStrLast = Language.getIndentStr(cssIndentStr, sb.indentLevel - 2);
                        String indentStr = indentStrLast + cssIndentStr;
                        s = "\n" + indentStr + s.replaceAll("\n", "\n" + indentStr).trim() + "\n" + indentStrLast + "</style>";
                        sb.append(s);
                    }

                    @Override
                    public void onError(String s, String errorMsg) {
                        sb.append(input);
                    }
                });
            } else {
                sb.append(input.trim());
            }
        }
        return "";
    }
}

