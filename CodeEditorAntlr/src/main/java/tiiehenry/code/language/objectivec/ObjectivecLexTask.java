package tiiehenry.code.language.objectivec;


import org.antlr.v4.runtime.Token;

import java.util.List;

import tiiehenry.code.praser.BlockLine;
import tiiehenry.code.praser.SymbolPair;
import tiiehenry.code.praser.Span;
import tiiehenry.code.praser.Variable;
import tiiehenry.code.antlr4.ObjectiveCLexer;
import tiiehenry.code.language.Antlr4LexTask;
import tiiehenry.code.language.Language;
import tiiehenry.code.view.ColorScheme;

import static tiiehenry.code.antlr4.ObjectiveCLexer.ATOMIC;
import static tiiehenry.code.antlr4.ObjectiveCLexer.AUTO;
import static tiiehenry.code.antlr4.ObjectiveCLexer.AUTORELEASEPOOL;
import static tiiehenry.code.antlr4.ObjectiveCLexer.BINARY_LITERAL;
import static tiiehenry.code.antlr4.ObjectiveCLexer.BOOL;
import static tiiehenry.code.antlr4.ObjectiveCLexer.BOOL_;
import static tiiehenry.code.antlr4.ObjectiveCLexer.BREAK;
import static tiiehenry.code.antlr4.ObjectiveCLexer.BYCOPY;
import static tiiehenry.code.antlr4.ObjectiveCLexer.BYREF;
import static tiiehenry.code.antlr4.ObjectiveCLexer.CASE;
import static tiiehenry.code.antlr4.ObjectiveCLexer.CATCH;
import static tiiehenry.code.antlr4.ObjectiveCLexer.CHAR;
import static tiiehenry.code.antlr4.ObjectiveCLexer.CHARACTER_LITERAL;
import static tiiehenry.code.antlr4.ObjectiveCLexer.CLASS;
import static tiiehenry.code.antlr4.ObjectiveCLexer.COMPLEX;
import static tiiehenry.code.antlr4.ObjectiveCLexer.CONST;
import static tiiehenry.code.antlr4.ObjectiveCLexer.CONTINUE;
import static tiiehenry.code.antlr4.ObjectiveCLexer.Class;
import static tiiehenry.code.antlr4.ObjectiveCLexer.DECIMAL_LITERAL;
import static tiiehenry.code.antlr4.ObjectiveCLexer.DEFAULT;
import static tiiehenry.code.antlr4.ObjectiveCLexer.DO;
import static tiiehenry.code.antlr4.ObjectiveCLexer.DOUBLE;
import static tiiehenry.code.antlr4.ObjectiveCLexer.DYNAMIC;
import static tiiehenry.code.antlr4.ObjectiveCLexer.ELIPSIS;
import static tiiehenry.code.antlr4.ObjectiveCLexer.ELSE;
import static tiiehenry.code.antlr4.ObjectiveCLexer.ENCODE;
import static tiiehenry.code.antlr4.ObjectiveCLexer.END;
import static tiiehenry.code.antlr4.ObjectiveCLexer.ENUM;
import static tiiehenry.code.antlr4.ObjectiveCLexer.EOF;
import static tiiehenry.code.antlr4.ObjectiveCLexer.EXTERN;
import static tiiehenry.code.antlr4.ObjectiveCLexer.FALSE;
import static tiiehenry.code.antlr4.ObjectiveCLexer.FINALLY;
import static tiiehenry.code.antlr4.ObjectiveCLexer.FLOAT;
import static tiiehenry.code.antlr4.ObjectiveCLexer.FLOATING_POINT_LITERAL;
import static tiiehenry.code.antlr4.ObjectiveCLexer.FOR;
import static tiiehenry.code.antlr4.ObjectiveCLexer.GOTO;
import static tiiehenry.code.antlr4.ObjectiveCLexer.HEX_LITERAL;
import static tiiehenry.code.antlr4.ObjectiveCLexer.ID;
import static tiiehenry.code.antlr4.ObjectiveCLexer.IF;
import static tiiehenry.code.antlr4.ObjectiveCLexer.IMAGINERY;
import static tiiehenry.code.antlr4.ObjectiveCLexer.IMP;
import static tiiehenry.code.antlr4.ObjectiveCLexer.IMPLEMENTATION;
import static tiiehenry.code.antlr4.ObjectiveCLexer.IMPORT;
import static tiiehenry.code.antlr4.ObjectiveCLexer.IN;
import static tiiehenry.code.antlr4.ObjectiveCLexer.INLINE;
import static tiiehenry.code.antlr4.ObjectiveCLexer.INOUT;
import static tiiehenry.code.antlr4.ObjectiveCLexer.INT;
import static tiiehenry.code.antlr4.ObjectiveCLexer.INTERFACE;
import static tiiehenry.code.antlr4.ObjectiveCLexer.LONG;
import static tiiehenry.code.antlr4.ObjectiveCLexer.LP;
import static tiiehenry.code.antlr4.ObjectiveCLexer.MULTI_COMMENT;
import static tiiehenry.code.antlr4.ObjectiveCLexer.NIL;
import static tiiehenry.code.antlr4.ObjectiveCLexer.NO;
import static tiiehenry.code.antlr4.ObjectiveCLexer.NONATOMIC;
import static tiiehenry.code.antlr4.ObjectiveCLexer.NULL;
import static tiiehenry.code.antlr4.ObjectiveCLexer.OCTAL_LITERAL;
import static tiiehenry.code.antlr4.ObjectiveCLexer.ONEWAY;
import static tiiehenry.code.antlr4.ObjectiveCLexer.OPTIONAL;
import static tiiehenry.code.antlr4.ObjectiveCLexer.OUT;
import static tiiehenry.code.antlr4.ObjectiveCLexer.PACKAGE;
import static tiiehenry.code.antlr4.ObjectiveCLexer.PRIVATE;
import static tiiehenry.code.antlr4.ObjectiveCLexer.PROPERTY;
import static tiiehenry.code.antlr4.ObjectiveCLexer.PROTECTED;
import static tiiehenry.code.antlr4.ObjectiveCLexer.PROTOCOL;
import static tiiehenry.code.antlr4.ObjectiveCLexer.PROTOCOL_;
import static tiiehenry.code.antlr4.ObjectiveCLexer.PUBLIC;
import static tiiehenry.code.antlr4.ObjectiveCLexer.REGISTER;
import static tiiehenry.code.antlr4.ObjectiveCLexer.REQUIRED;
import static tiiehenry.code.antlr4.ObjectiveCLexer.RESTRICT;
import static tiiehenry.code.antlr4.ObjectiveCLexer.RETAIN;
import static tiiehenry.code.antlr4.ObjectiveCLexer.RETURN;
import static tiiehenry.code.antlr4.ObjectiveCLexer.SEL;
import static tiiehenry.code.antlr4.ObjectiveCLexer.SELECTOR;
import static tiiehenry.code.antlr4.ObjectiveCLexer.SELF;
import static tiiehenry.code.antlr4.ObjectiveCLexer.SHORT;
import static tiiehenry.code.antlr4.ObjectiveCLexer.SIGNED;
import static tiiehenry.code.antlr4.ObjectiveCLexer.SINGLE_COMMENT;
import static tiiehenry.code.antlr4.ObjectiveCLexer.SIZEOF;
import static tiiehenry.code.antlr4.ObjectiveCLexer.STATIC;
import static tiiehenry.code.antlr4.ObjectiveCLexer.STRING_END;
import static tiiehenry.code.antlr4.ObjectiveCLexer.STRING_NEWLINE;
import static tiiehenry.code.antlr4.ObjectiveCLexer.STRING_START;
import static tiiehenry.code.antlr4.ObjectiveCLexer.STRING_VALUE;
import static tiiehenry.code.antlr4.ObjectiveCLexer.STRUCT;
import static tiiehenry.code.antlr4.ObjectiveCLexer.SUPER;
import static tiiehenry.code.antlr4.ObjectiveCLexer.SWITCH;
import static tiiehenry.code.antlr4.ObjectiveCLexer.SYNCHRONIZED;
import static tiiehenry.code.antlr4.ObjectiveCLexer.SYNTHESIZE;
import static tiiehenry.code.antlr4.ObjectiveCLexer.THROW;
import static tiiehenry.code.antlr4.ObjectiveCLexer.TRUE;
import static tiiehenry.code.antlr4.ObjectiveCLexer.TRY;
import static tiiehenry.code.antlr4.ObjectiveCLexer.TYPEDEF;
import static tiiehenry.code.antlr4.ObjectiveCLexer.TYPEOF;
import static tiiehenry.code.antlr4.ObjectiveCLexer.UNION;
import static tiiehenry.code.antlr4.ObjectiveCLexer.VOID;
import static tiiehenry.code.antlr4.ObjectiveCLexer.VOLATILE;
import static tiiehenry.code.antlr4.ObjectiveCLexer.WHILE;
import static tiiehenry.code.antlr4.ObjectiveCLexer.YES;

public class ObjectivecLexTask extends Antlr4LexTask<ObjectiveCLexer> {
    public ObjectivecLexTask(Language language) {
        super(language);
        setKeywords(ObjectiveCLexer.VOCABULARY);
    }

    @Override
    protected ObjectiveCLexer generateLexer() {
        return new ObjectiveCLexer(null);
    }


    @Override
    public void tokenizeIn(List<Span> tokens, List<BlockLine> lines, List<SymbolPair> pairs, List<Variable> variables, ObjectiveCLexer lexer, int fromIndex, int fromLineOffset, int fromLine, String code) {
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


            addTokenPair(tokenType, STRING_START, STRING_END, pairs, pairCurlyStacks, startIndex, line);
            if (tokenType == CHARACTER_LITERAL) {
                addPair(pairs,startIndex, line, stopIndex - 1, line);
            }
             addTokenSpan(tokens,token, tokenType, startIndex, line, lineOffset, len);
        }
    }

    @Override
    protected ColorScheme.Colorable getTokenColorable(Integer tokenType) {
        ColorScheme.Colorable type;
        switch (tokenType) {
            case AUTO:
            case BREAK:
            case CASE:
            case CONST:
            case CONTINUE:
            case DEFAULT:
            case DO:
            case ELSE:
            case ENUM:
            case EXTERN:
            case FOR:
            case GOTO:
            case IF:
            case INLINE:
            case REGISTER:
            case RESTRICT:
            case RETURN:
            case SIGNED:
            case SIZEOF:
            case STATIC:
            case STRUCT:
            case SWITCH:
            case TYPEDEF:
            case TYPEOF:
            case UNION:
            case VOLATILE:
            case WHILE:
            case ID:
            case SELF:
            case SUPER:
            case BYCOPY:
            case BYREF:
            case IMP:
            case IN:
            case OUT:
            case INOUT:
            case ONEWAY:
            case ATOMIC:
            case NONATOMIC:
            case RETAIN:
                type = ColorScheme.Colorable.KEYWORD;
                break;
            case CHAR:
            case DOUBLE:
            case FLOAT:
            case INT:
            case LONG:
            case SHORT:
            case VOID:
            case BOOL_:
            case COMPLEX:
            case IMAGINERY:
            case BOOL:
            case Class:
            case PROTOCOL_:
            case SEL:
                type = ColorScheme.Colorable.BASIC_TYPE;
                break;
            case TRUE:
            case FALSE:
            case NO:
            case YES:
            case NIL:
            case NULL:
            case CHARACTER_LITERAL:
            case STRING_START:
            case STRING_NEWLINE:
            case STRING_VALUE:
            case STRING_END:
            case HEX_LITERAL:
            case OCTAL_LITERAL:
            case BINARY_LITERAL:
            case DECIMAL_LITERAL:
            case FLOATING_POINT_LITERAL:
                type = ColorScheme.Colorable.LITERAL_FUNC;
                break;
            case AUTORELEASEPOOL:
            case CATCH:
            case CLASS:
            case DYNAMIC:
            case END:
            case ENCODE:
            case FINALLY:
            case IMPLEMENTATION:
            case INTERFACE:
            case IMPORT:
            case PACKAGE:
            case PROTOCOL:
            case OPTIONAL:
            case PRIVATE:
            case PROTECTED:
            case PUBLIC:
            case PROPERTY:
            case REQUIRED:
            case SELECTOR:
            case SYNCHRONIZED:
            case SYNTHESIZE:
            case THROW:
            case TRY:
                type = ColorScheme.Colorable.PACKAGE;
                break;
            case SINGLE_COMMENT:
                type = ColorScheme.Colorable.COMMENT_SINGLE;
                break;
            case MULTI_COMMENT:
                type = ColorScheme.Colorable.COMMENT_REGION;
                break;
            default:
                if (tokenType >= LP && tokenType <= ELIPSIS)
                    type = ColorScheme.Colorable.SYMBOL;
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
    protected void parse(ObjectiveCLexer lexer) {

    }

}
