package tiiehenry.code.language.cpp;

import org.antlr.v4.runtime.Lexer;

import tiiehenry.code.language.Antlr4SampleFormatter;

import static tiiehenry.code.antlr4.CPP14Lexer.Case;
import static tiiehenry.code.antlr4.CPP14Lexer.Default;
import static tiiehenry.code.antlr4.CPP14Lexer.LeftBrace;
import static tiiehenry.code.antlr4.CPP14Lexer.LeftParen;
import static tiiehenry.code.antlr4.CPP14Lexer.Newline;
import static tiiehenry.code.antlr4.CPP14Lexer.Return;
import static tiiehenry.code.antlr4.CPP14Lexer.RightBrace;
import static tiiehenry.code.antlr4.CPP14Lexer.RightParen;
import static tiiehenry.code.antlr4.CPP14Lexer.Whitespace;

public class CppFormatter extends Antlr4SampleFormatter {

    private static class SingletonInner {
        private static Antlr4SampleFormatter formatter = new CppFormatter();
    }

    public static Antlr4SampleFormatter getInstance() {
        return SingletonInner.formatter;
    }

    private CppFormatter() {
        formatIndentLine.add(Newline);
        formatIndentWS.add(Whitespace);

        autoIndentIncrease.add(LeftParen);
        autoIndentDecrease.add(RightParen);
        autoIndentIncrease.add(LeftBrace);
        autoIndentDecrease.add(Return);

        formatIndentIncreaseDef.add(LeftBrace);
        formatIndentDecreaseDef.add(RightBrace);

        formatIndentIncreaseAfter.add(LeftBrace);
        formatIndentDecreaseBefore.add(RightBrace);
//        formatIndentDecreaseAfter.add(CloseBrace);

//        formatIndentDecreaseIncrease.add(Else);
        formatIndentDecreaseIncrease.add(Case);
        formatIndentDecreaseIncrease.add(Default);
    }


}
