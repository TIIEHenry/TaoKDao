package tiiehenry.code.language.java;

import tiiehenry.code.language.Antlr4SampleFormatter;

import static tiiehenry.code.antlr4.Java9Lexer.LBRACE;
import static tiiehenry.code.antlr4.Java9Lexer.LPAREN;
import static tiiehenry.code.antlr4.Java9Lexer.NL;
import static tiiehenry.code.antlr4.Java9Lexer.RBRACE;
import static tiiehenry.code.antlr4.Java9Lexer.RETURN;
import static tiiehenry.code.antlr4.Java9Lexer.RPAREN;
import static tiiehenry.code.antlr4.Java9Lexer.WS;

public class JavaFormatter extends Antlr4SampleFormatter {

    private static class SingletonInner {
        private static Antlr4SampleFormatter formatter = new JavaFormatter();
    }

    public static Antlr4SampleFormatter getInstance() {
        return SingletonInner.formatter;
    }

    private JavaFormatter() {
        formatIndentLine.add(NL);
        formatIndentWS.add(WS);

        autoIndentIncrease.add(LPAREN);
        autoIndentDecrease.add(RPAREN);
        autoIndentIncrease.add(LBRACE);
        autoIndentDecrease.add(RETURN);

        formatIndentIncreaseDef.add(LBRACE);
        formatIndentDecreaseDef.add(RBRACE);

        formatIndentIncreaseAfter.add(LBRACE);
        formatIndentDecreaseBefore.add(RBRACE);

    }

}
