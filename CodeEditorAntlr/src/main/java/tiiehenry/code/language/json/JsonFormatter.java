package tiiehenry.code.language.json;


import tiiehenry.code.language.Antlr4SampleFormatter;

import static tiiehenry.code.antlr4.JSONLexer.LBRACE;
import static tiiehenry.code.antlr4.JSONLexer.LBRACK;
import static tiiehenry.code.antlr4.JSONLexer.RBRACE;
import static tiiehenry.code.antlr4.JSONLexer.RBRACK;
import static tiiehenry.code.antlr4.JSONLexer.WS;
public class JsonFormatter extends Antlr4SampleFormatter {

    private static class SingletonInner {
        private static Antlr4SampleFormatter formatter = new JsonFormatter();
    }

    public static Antlr4SampleFormatter getInstance() {
        return SingletonInner.formatter;
    }

    private JsonFormatter() {
//        formatIndentLine.add(LineTerminator);
        formatIndentWS.add(WS);

        autoIndentIncrease.add(LBRACE);
        autoIndentDecrease.add(RBRACE);

        formatIndentIncreaseDef.add(LBRACE);
        formatIndentDecreaseDef.add(RBRACE);

        formatIndentIncreaseAfter.add(LBRACE);
        formatIndentDecreaseBefore.add(RBRACE);

        formatIndentIncreaseDef.add(LBRACK);
        formatIndentDecreaseDef.add(RBRACK);

        formatIndentIncreaseAfter.add(LBRACK);
        formatIndentDecreaseBefore.add(RBRACK);
//        formatIndentDecreaseAfter.add(CloseBrace);

    }


}