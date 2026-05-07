package tiiehenry.code.language.css;

import tiiehenry.code.language.Antlr4SampleFormatter;

import static tiiehenry.code.antlr4.Css3Lexer.LBrace;
import static tiiehenry.code.antlr4.Css3Lexer.LParen;
import static tiiehenry.code.antlr4.Css3Lexer.Newline;
import static tiiehenry.code.antlr4.Css3Lexer.RBrace;
import static tiiehenry.code.antlr4.Css3Lexer.RParen;
import static tiiehenry.code.antlr4.Css3Lexer.WSpace;


public class CssFormatter extends Antlr4SampleFormatter {

    private static class SingletonInner {
        private static Antlr4SampleFormatter formatter = new CssFormatter();
    }

    public static Antlr4SampleFormatter getInstance() {
        return SingletonInner.formatter;
    }

    private CssFormatter() {
        formatIndentLine.add(Newline);
        formatIndentWS.add(WSpace);

        autoIndentIncrease.add(LParen);
        autoIndentDecrease.add(RParen);
        autoIndentIncrease.add(LBrace);

        formatIndentIncreaseDef.add(LBrace);
        formatIndentDecreaseDef.add(RBrace);

        formatIndentIncreaseAfter.add(LBrace);
        formatIndentDecreaseBefore.add(RBrace);

    }

}
