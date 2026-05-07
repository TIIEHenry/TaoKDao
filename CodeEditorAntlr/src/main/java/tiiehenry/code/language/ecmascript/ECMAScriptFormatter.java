package tiiehenry.code.language.ecmascript;


import tiiehenry.code.language.Antlr4SampleFormatter;

import static tiiehenry.code.antlr4.ECMAScriptLexer.Case;
import static tiiehenry.code.antlr4.ECMAScriptLexer.CloseBrace;
import static tiiehenry.code.antlr4.ECMAScriptLexer.CloseParen;
import static tiiehenry.code.antlr4.ECMAScriptLexer.Default;
import static tiiehenry.code.antlr4.ECMAScriptLexer.Else;
import static tiiehenry.code.antlr4.ECMAScriptLexer.LineTerminator;
import static tiiehenry.code.antlr4.ECMAScriptLexer.OpenBrace;
import static tiiehenry.code.antlr4.ECMAScriptLexer.OpenParen;
import static tiiehenry.code.antlr4.ECMAScriptLexer.Return;
import static tiiehenry.code.antlr4.ECMAScriptLexer.WhiteSpaces;

public class ECMAScriptFormatter extends Antlr4SampleFormatter {

    private static class SingletonInner {
        private static Antlr4SampleFormatter formatter = new ECMAScriptFormatter();
    }

    public static Antlr4SampleFormatter getInstance() {
        return SingletonInner.formatter;
    }

    private ECMAScriptFormatter() {
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


