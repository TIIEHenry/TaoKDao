package tiiehenry.code.language.kotlin;

import tiiehenry.code.language.Antlr4SampleFormatter;

import static tiiehenry.code.antlr4.KotlinLexer.LCURL;
import static tiiehenry.code.antlr4.KotlinLexer.LPAREN;
import static tiiehenry.code.antlr4.KotlinLexer.LineStrExprStart;
import static tiiehenry.code.antlr4.KotlinLexer.NL;
import static tiiehenry.code.antlr4.KotlinLexer.RCURL;
import static tiiehenry.code.antlr4.KotlinLexer.RETURN;
import static tiiehenry.code.antlr4.KotlinLexer.RETURN_AT;
import static tiiehenry.code.antlr4.KotlinLexer.RPAREN;
import static tiiehenry.code.antlr4.KotlinLexer.WS;

public class KotlinFormatter extends Antlr4SampleFormatter {

    private static class SingletonInner {
        private static Antlr4SampleFormatter formatter = new KotlinFormatter();
    }

    public static Antlr4SampleFormatter getInstance() {
        return SingletonInner.formatter;
    }

    private KotlinFormatter() {
        formatIndentLine.add(NL);
        formatIndentWS.add(WS);

        autoIndentIncrease.add(LPAREN);
        autoIndentDecrease.add(RPAREN);
        autoIndentIncrease.add(LCURL);
        autoIndentDecrease.add(RETURN);
        autoIndentDecrease.add(RETURN_AT);

        formatIndentIncreaseDef.add(LCURL);
        formatIndentIncreaseDef.add(LineStrExprStart);
        formatIndentDecreaseDef.add(RCURL);

        formatIndentIncreaseAfter.add(LCURL);
        formatIndentIncreaseAfter.add(LineStrExprStart);
        formatIndentDecreaseBefore.add(RCURL);

    }

}
