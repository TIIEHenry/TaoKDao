package tiiehenry.code.praser.prog;

import android.graphics.Paint;

public class PaintCollection {
    private final Paint _brush;
    private final Paint _brushPair;
    private final Paint _brushPairText;
    private final Paint _brushLine;
    private final Paint _brushLineHighLignt;
    private final Paint _brushLineErr;
    protected static int BASE_TEXT_SIZE_PIXELS = 16;

    public PaintCollection(){

        _brush = new Paint();
        _brush.setAntiAlias(true);
        _brush.setTextSize(BASE_TEXT_SIZE_PIXELS);
        _brushLine = new Paint();
        _brushLine.setAntiAlias(true);
        _brushLine.setTextSize(BASE_TEXT_SIZE_PIXELS);
        _brushLineHighLignt = new Paint();
        _brushLineHighLignt.setAntiAlias(true);
        _brushLineErr = new Paint();
        _brushLineErr.setAntiAlias(true);
        _brushLineErr.setTextSize(BASE_TEXT_SIZE_PIXELS);
        _brushPair = new Paint();
        _brushPair.setAntiAlias(true);
        _brushPair.setTextSize(BASE_TEXT_SIZE_PIXELS);
        _brushPairText = new Paint();
        _brushPairText.setAntiAlias(true);
        _brushPairText.setTextSize(BASE_TEXT_SIZE_PIXELS);
    }
}
