package tiiehenry.code.language.javascript;

import tiiehenry.code.language.Antlr4SampleFormatter;

import static tiiehenry.code.antlr4.JavaScriptLexer.Case;
import static tiiehenry.code.antlr4.JavaScriptLexer.CloseBrace;
import static tiiehenry.code.antlr4.JavaScriptLexer.CloseParen;
import static tiiehenry.code.antlr4.JavaScriptLexer.Default;
import static tiiehenry.code.antlr4.JavaScriptLexer.Else;
import static tiiehenry.code.antlr4.JavaScriptLexer.LineTerminator;
import static tiiehenry.code.antlr4.JavaScriptLexer.OpenBrace;
import static tiiehenry.code.antlr4.JavaScriptLexer.OpenParen;
import static tiiehenry.code.antlr4.JavaScriptLexer.Return;
import static tiiehenry.code.antlr4.JavaScriptLexer.WhiteSpaces;

public class JavascriptFormatter extends Antlr4SampleFormatter {

    private static class SingletonInner {
        private static Antlr4SampleFormatter formatter = new JavascriptFormatter();
    }

    public static Antlr4SampleFormatter getInstance() {
        return SingletonInner.formatter;
    }

    private JavascriptFormatter() {
        formatIndentLine.add(LineTerminator);
        formatIndentWS.add(WhiteSpaces);

        autoIndentIncrease.add(OpenParen);
        autoIndentDecrease.add(CloseParen);
        autoIndentIncrease.add(OpenBrace);
        autoIndentDecrease.add(Return);

        formatIndentIncreaseDef.add(OpenBrace);
        formatIndentDecreaseDef.add(CloseBrace);

        formatIndentIncreaseAfter.add(OpenBrace);
        formatIndentDecreaseBefore.add(CloseBrace);

        formatIndentIncreaseDef.add(OpenParen);
        formatIndentDecreaseDef.add(CloseParen);

        formatIndentIncreaseAfter.add(OpenParen);
        formatIndentDecreaseBefore.add(CloseParen);
//        formatIndentDecreaseAfter.add(CloseBrace);

        formatIndentDecreaseIncrease.add(Else);
        formatIndentDecreaseIncrease.add(Case);
        formatIndentDecreaseIncrease.add(Default);


    }


}

