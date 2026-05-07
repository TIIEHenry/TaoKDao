package tiiehenry.code.language.smali;

import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.jf.dexlib2.Opcodes;

import java.util.List;

import tiiehenry.code.IndentStringBuilder;
import tiiehenry.code.praser.BlockLine;
import tiiehenry.code.praser.SymbolPair;
import tiiehenry.code.praser.Span;
import tiiehenry.code.praser.Variable;
import tiiehenry.code.antlr4.SmaliLexer;
import tiiehenry.code.antlr4.SmaliParser;
import tiiehenry.code.language.Antlr4LexTask;
import tiiehenry.code.language.FileItem;
import tiiehenry.code.language.Language;
import tiiehenry.code.view.ColorScheme;

import static tiiehenry.code.antlr4.SmaliLexer.ACCESS_SPEC;
import static tiiehenry.code.antlr4.SmaliLexer.ANNOTATION_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.ANNOTATION_VISIBILITY;
import static tiiehenry.code.antlr4.SmaliLexer.ARRAY_DATA_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.ARRAY_DESCRIPTOR;
import static tiiehenry.code.antlr4.SmaliLexer.ARROW;
import static tiiehenry.code.antlr4.SmaliLexer.AT;
import static tiiehenry.code.antlr4.SmaliLexer.BOOL_LITERAL;
import static tiiehenry.code.antlr4.SmaliLexer.BYTE_LITERAL;
import static tiiehenry.code.antlr4.SmaliLexer.CATCHALL_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.CATCH_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.CHAR_LITERAL;
import static tiiehenry.code.antlr4.SmaliLexer.CLASS_DESCRIPTOR;
import static tiiehenry.code.antlr4.SmaliLexer.CLASS_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.CLOSE_BRACE;
import static tiiehenry.code.antlr4.SmaliLexer.CLOSE_PAREN;
import static tiiehenry.code.antlr4.SmaliLexer.COLON;
import static tiiehenry.code.antlr4.SmaliLexer.COMMA;
import static tiiehenry.code.antlr4.SmaliLexer.DOTDOT;
import static tiiehenry.code.antlr4.SmaliLexer.DOUBLE_LITERAL;
import static tiiehenry.code.antlr4.SmaliLexer.DOUBLE_LITERAL_OR_ID;
import static tiiehenry.code.antlr4.SmaliLexer.END_ANNOTATION_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.END_ARRAY_DATA_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.END_FIELD_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.END_LOCAL_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.END_METHOD_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.END_PACKED_SWITCH_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.END_PARAMETER_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.END_SPARSE_SWITCH_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.END_SUBANNOTATION_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.ENUM_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.EOF;
import static tiiehenry.code.antlr4.SmaliLexer.EPILOGUE_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.EQUAL;
import static tiiehenry.code.antlr4.SmaliLexer.FIELD_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.FLOAT_LITERAL;
import static tiiehenry.code.antlr4.SmaliLexer.FLOAT_LITERAL_OR_ID;
import static tiiehenry.code.antlr4.SmaliLexer.IMPLEMENTS_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.INSTRUCTION_FORMAT10t;
import static tiiehenry.code.antlr4.SmaliLexer.INSTRUCTION_FORMAT35c_CALL_SITE;
import static tiiehenry.code.antlr4.SmaliLexer.INSTRUCTION_FORMAT3rc_CALL_SITE;
import static tiiehenry.code.antlr4.SmaliLexer.INSTRUCTION_FORMAT51l;
import static tiiehenry.code.antlr4.SmaliLexer.INVALID_TOKEN;
import static tiiehenry.code.antlr4.SmaliLexer.LINE_COMMENT;
import static tiiehenry.code.antlr4.SmaliLexer.LINE_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.LOCALS_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.LOCAL_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.LONG_LITERAL;
import static tiiehenry.code.antlr4.SmaliLexer.METHOD_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.NEGATIVE_INTEGER_LITERAL;
import static tiiehenry.code.antlr4.SmaliLexer.NULL_LITERAL;
import static tiiehenry.code.antlr4.SmaliLexer.OPEN_BRACE;
import static tiiehenry.code.antlr4.SmaliLexer.OPEN_PAREN;
import static tiiehenry.code.antlr4.SmaliLexer.PACKED_SWITCH_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.PARAMETER_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.POSITIVE_INTEGER_LITERAL;
import static tiiehenry.code.antlr4.SmaliLexer.PRIMITIVE_LIST;
import static tiiehenry.code.antlr4.SmaliLexer.PRIMITIVE_TYPE;
import static tiiehenry.code.antlr4.SmaliLexer.PROLOGUE_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.REGISTER;
import static tiiehenry.code.antlr4.SmaliLexer.REGISTERS_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.RESTART_LOCAL_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.SHORT_LITERAL;
import static tiiehenry.code.antlr4.SmaliLexer.SIMPLE_NAME;
import static tiiehenry.code.antlr4.SmaliLexer.SOURCE_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.SPARSE_SWITCH_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.STRING_LITERAL;
import static tiiehenry.code.antlr4.SmaliLexer.SUBANNOTATION_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.SUPER_DIRECTIVE;
import static tiiehenry.code.antlr4.SmaliLexer.TYPE_LIST;
import static tiiehenry.code.antlr4.SmaliLexer.VOID_TYPE;
import static tiiehenry.code.antlr4.SmaliLexer.WHITE_SPACE;

public class SmaliLexTask extends Antlr4LexTask<SmaliLexer> {
    private final SmaliParser parser;
    private final CommonTokenStream tks;
    private final SmaliLexer lexer;
    private FileItem item;
    private SmaliParser.SmaliContext smali;
    private boolean hasMethodHandle = false;

    public SmaliLexTask(Language language) {
        super(language);
        parser = new SmaliParser(null);
        lexer = new SmaliLexer(null);
        tks = new CommonTokenStream(lexer);
    }

    public SmaliLexTask(FileItem item) {
        this(SmaliLanguage.getInstance());
        this.item = item;
        if (item != null) {
            parser.removeErrorListeners();
            parser.addErrorListener(item);
        }
        smali = null;
    }

    @Override
    protected ParseTree getTree() {
        return smali;
    }

    @Override
    protected void stopLast() {
//        ReferenceFinder.INSTANCE().stop();
    }

   /* public ClassDef getClassDef() {
        if (parser.getNumberOfSyntaxErrors() > 0)
            return null;
        return ClassMaker.make(smali, tks, getCodes());
    }*/

    @Override
    public boolean isError(int index) {
        FileItem item = this.item;
        if (item == null)
            return false;
        return item.isError(index);
    }

    @Override
    protected boolean canAnalysis() {
        return true;
    }

    @Override
    protected void parse(CodePointCharStream i) {
        lexer.setInputStream(i);
        parse(lexer);
    }

    @Override
    protected void parse(SmaliLexer lexer) {
        tks.setTokenSource(lexer);
        parser.setTokenStream(tks);
        SmaliParser.SmaliContext ctx = parser.smali();
        smali = ctx;
        if (parser.getNumberOfSyntaxErrors() == 0 && item != null) {
//            Parser.findTypes(ctx, tks, true);
//            ReferenceFinder.INSTANCE().visit(item, ctx, tks, false, true);
        }
    }

    public Opcodes getCodes() {
        return Opcodes.forApi(hasMethodHandle ? 26 : 14);
    }

    @Override
    protected SmaliLexer generateLexer() {
        SmaliLexer smaliLexer = new SmaliLexer(null);
        if (item != null) {
            smaliLexer.removeErrorListeners();
            smaliLexer.addErrorListener(item);
        }
        return smaliLexer;
    }

    @Override
    public void tokenizeIn(List<Span> tokens, List<BlockLine> lines, List<SymbolPair> pairs, List<Variable> variables, SmaliLexer lexer, int fromIndex, int fromLineOffset, int fromLine) {
        isLabel = false;
        if (item != null) {
            item.reset();
        }
        boolean hasMethodHandle = false;

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
                 addTokenSpan(tokens,lastline,lastlineOffset,lastIndex,startIndex - lastIndex, ColorScheme.Colorable.TEXT);
            lastIndex = stopIndex;
            lastline = line;
            lastlineOffset = lineOffset;

            addTokenPair(tokenType, OPEN_BRACE, CLOSE_BRACE, pairs, pairCurlyStacks, startIndex, line);
            addTokenPair(tokenType, OPEN_PAREN, CLOSE_PAREN, pairs, pairParenStacks, startIndex, line);
            switch (tokenType) {
                case STRING_LITERAL:
                case CHAR_LITERAL:
                    addPair(pairs,startIndex, line, stopIndex - 1, line);
                    break;
            }
            addTokenLine(tokenType, OPEN_BRACE, CLOSE_BRACE, lines, lineCurlyStacks, startIndex, line);
            addTokenLine(tokenType, OPEN_PAREN, CLOSE_PAREN, lines, lineParenStacks, startIndex, line);
             addTokenSpan(tokens,token, tokenType, startIndex, line, lineOffset, len);
        }
        this.hasMethodHandle = hasMethodHandle;

    }

   private boolean isLabel = false;
    @Override
    protected ColorScheme.Colorable getTokenColorable(Integer tokenType) {
        ColorScheme.Colorable type;
        switch (tokenType) {
            case ANNOTATION_DIRECTIVE:
            case ARRAY_DATA_DIRECTIVE:
            case CATCHALL_DIRECTIVE:
            case CATCH_DIRECTIVE:
            case CLASS_DIRECTIVE:
            case END_ANNOTATION_DIRECTIVE:
            case END_ARRAY_DATA_DIRECTIVE:
            case END_FIELD_DIRECTIVE:
            case END_LOCAL_DIRECTIVE:
            case END_PARAMETER_DIRECTIVE:
            case END_METHOD_DIRECTIVE:
            case END_PACKED_SWITCH_DIRECTIVE:
            case END_SPARSE_SWITCH_DIRECTIVE:
            case END_SUBANNOTATION_DIRECTIVE:
            case ENUM_DIRECTIVE:
            case EPILOGUE_DIRECTIVE:
            case FIELD_DIRECTIVE:
            case IMPLEMENTS_DIRECTIVE:
            case LINE_DIRECTIVE:
            case LOCAL_DIRECTIVE:
            case LOCALS_DIRECTIVE:
            case METHOD_DIRECTIVE:
            case PACKED_SWITCH_DIRECTIVE:
            case PARAMETER_DIRECTIVE:
            case PROLOGUE_DIRECTIVE:
            case REGISTERS_DIRECTIVE:
            case RESTART_LOCAL_DIRECTIVE:
            case SOURCE_DIRECTIVE:
            case SPARSE_SWITCH_DIRECTIVE:
            case SUBANNOTATION_DIRECTIVE:
            case SUPER_DIRECTIVE:
                type = ColorScheme.Colorable.PACKAGE;
                break;
            case ACCESS_SPEC:
            case ANNOTATION_VISIBILITY:
                type = ColorScheme.Colorable.KEYWORD;
                break;
            case CLASS_DESCRIPTOR:
            case VOID_TYPE:
            case PRIMITIVE_TYPE:
            case PRIMITIVE_LIST:
            case TYPE_LIST:
            case ARRAY_DESCRIPTOR:
                type = ColorScheme.Colorable.BASIC_TYPE;
                break;
            case LINE_COMMENT:
                type = ColorScheme.Colorable.COMMENT_SINGLE;
                break;
            case INSTRUCTION_FORMAT3rc_CALL_SITE:
            case INSTRUCTION_FORMAT35c_CALL_SITE:
                hasMethodHandle = true;
                type = ColorScheme.Colorable.KEYWORD;
                break;
            case INVALID_TOKEN:
                type = ColorScheme.Colorable.ERROR;
                break;
            case BOOL_LITERAL:
            case BYTE_LITERAL:
            case CHAR_LITERAL:
            case DOUBLE_LITERAL:
            case DOUBLE_LITERAL_OR_ID:
            case FLOAT_LITERAL_OR_ID:
            case FLOAT_LITERAL:
            case LONG_LITERAL:
            case NULL_LITERAL:
            case POSITIVE_INTEGER_LITERAL:
            case NEGATIVE_INTEGER_LITERAL:
            case SHORT_LITERAL:
            case STRING_LITERAL:
                type = ColorScheme.Colorable.LITERAL_FUNC;
                break;
            case ARROW:
            case AT:
            case COMMA:
            case DOTDOT:
            case EQUAL:
            case REGISTER:
                type = ColorScheme.Colorable.SYMBOL;
                break;
            case OPEN_BRACE:
            case OPEN_PAREN:
            case CLOSE_BRACE:
            case CLOSE_PAREN:
                type = ColorScheme.Colorable.SEPARATOR;
                break;
            case COLON:
                isLabel = true;
                type = ColorScheme.Colorable.SYMBOL;
                break;
            case SIMPLE_NAME:
                if (isLabel)
                    type = ColorScheme.Colorable.SYMBOL;
                else
                    type = ColorScheme.Colorable.TEXT;
                isLabel = false;
                break;
            case WHITE_SPACE:
                isLabel = false;
            default:
                if (tokenType >= INSTRUCTION_FORMAT10t && tokenType <= INSTRUCTION_FORMAT51l)
                    type = ColorScheme.Colorable.KEYWORD;
                else
                    type = ColorScheme.Colorable.TEXT;
                break;
        }
        return type;
    }

    @Override
    protected void addVariable(Token token, Integer tokenType) {

    }

    @Override
    public boolean canFormat() {
        return true;
    }

    @Override
    protected int formatInThread(StringBuilder sb, IndentStringBuilder isb, SmaliLexer lexer, CharSequence input, int width, int curPos) {
        int newPos = -1;
        int lastDirectiveType = 0;
        int start = 0;
        while (true) {
            Token token = lexer.nextToken();
            int type = token.getType();
            if (type == EOF)
                break;
            String text = token.getText();
            switch (type) {
                case ANNOTATION_DIRECTIVE:
                    if (lastDirectiveType == FIELD_DIRECTIVE ||
                            lastDirectiveType == LOCAL_DIRECTIVE ||
                            lastDirectiveType == PARAMETER_DIRECTIVE)
                        isb.indent(width);
                    lastDirectiveType = 0;
                    isb.append(text);
                    isb.indent(width);
                    break;
                case SUBANNOTATION_DIRECTIVE:
                case METHOD_DIRECTIVE:
                case PACKED_SWITCH_DIRECTIVE:
                case ARRAY_DATA_DIRECTIVE:
                case SPARSE_SWITCH_DIRECTIVE:
                case OPEN_BRACE:
                    isb.append(text);
                    isb.indent(width);
                    break;
                case FIELD_DIRECTIVE:
                case LOCAL_DIRECTIVE:
                case PARAMETER_DIRECTIVE:
                    isb.append(text);
                    lastDirectiveType = type;
                    break;
                case END_ANNOTATION_DIRECTIVE:
                case END_SUBANNOTATION_DIRECTIVE:
                case END_FIELD_DIRECTIVE:
                case END_METHOD_DIRECTIVE:
                case END_PACKED_SWITCH_DIRECTIVE:
                case END_ARRAY_DATA_DIRECTIVE:
                case END_SPARSE_SWITCH_DIRECTIVE:
                case END_LOCAL_DIRECTIVE:
                case END_PARAMETER_DIRECTIVE:
                case CLOSE_BRACE:
                    isb.deindent(width);
                    isb.append(text);
                    break;
                case STRING_LITERAL:
                    SmaliFormatter.processStringOrChar(isb, text, true);
                    break;
                case CHAR_LITERAL:
                    SmaliFormatter.processStringOrChar(isb, text, false);
                    break;
                case WHITE_SPACE:
                    SmaliFormatter.processWhiteSpace(isb, text);
                    break;
                default:
                    isb.append(text);
            }

            if (type != FIELD_DIRECTIVE ||
                    type != LOCAL_DIRECTIVE ||
                    type != PARAMETER_DIRECTIVE)
                lastDirectiveType = 0;
            int end = token.getStopIndex() + 1;
            newPos = Antlr4LexTask.compute(isb.length(), start, end, curPos, newPos);
            start = end;
        }
        if (newPos == -1)
            newPos = isb.length() - 1;
        sb.append(isb.toString());
        return newPos;
    }


}
