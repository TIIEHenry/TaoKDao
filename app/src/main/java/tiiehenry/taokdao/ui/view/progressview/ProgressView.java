package tiiehenry.taokdao.ui.view.progressview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ProgressView extends View {


    /**
     * 常量
     *
     * @param context
     */
    public static final int CIRCLE_RADIUS = 4;
    public static final int STROKE_RADIUS = 9;
    public static final int STROKE_WIDTH = 3;
    public static final int LINE_PADDING = 5;
    public static final int TEXT_PADDING_TOP = 10;
    public static final int TEXT_SIZE_NORMAL = 13;
    public static final int STATE_DONE = 142;
    public static final int STATE_DOING = 143;
    public static final int STATE_TODO = 144;
    /**
     * 绘制的笔
     */
    private Paint mPaint;
    /**
     * 控件的宽高
     */
    private int mWidth;
    private int mHeight;
    /**
     * 圆的半径
     */
    private int circleRadius;
    /**
     * 圆弧的半径
     */
    private int strokeRadius;
    /**
     * 圆弧的宽度
     */
    private int strokeWidth;
    /**
     * 线与圆弧之间的距离
     */
    private int linePadding;
    /**
     * 选中和没选中的颜色
     */
    private int colorDoing = Color.WHITE;
    private int colorDone = Color.WHITE;
    private int colorTodo = Color.parseColor("#ECA6AD");
    /**
     * 文字的大小
     */
    private int textSizeNormal;
    /**
     * 文字距离顶部的距离
     */
    private int textPaddingTop;
    /**
     * 个数
     */
    private int circleCount = 0;
    /**
     * 文本的信息
     */
    private Paint.FontMetricsInt fontMetricsInt;
    /**
     * 圆的y轴
     */
    private int circlePointY;
    /**
     * 根据宽度算出平均分得的宽度
     */
    private int everyWidth;
    /**
     * 数据
     */
    private List<Model> mModels = new ArrayList<>();

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initValues();
        initPaint();
    }

    /**
     * 配置一些初始值
     */
    private void initValues() {
        circleRadius = Utils.dip2px(getContext(), CIRCLE_RADIUS);
        strokeRadius = Utils.dip2px(getContext(), STROKE_RADIUS);
        strokeWidth = Utils.dip2px(getContext(), STROKE_WIDTH);
        linePadding = Utils.dip2px(getContext(), LINE_PADDING);
        textPaddingTop = Utils.dip2px(getContext(), TEXT_PADDING_TOP);
        textSizeNormal = Utils.sp2px(getContext(), TEXT_SIZE_NORMAL);
    }

    /**
     * 配置画笔
     */
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setAntiAlias(true);
//        抗齿距
        mPaint.setTextSize(textSizeNormal);
        fontMetricsInt = mPaint.getFontMetricsInt();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        configValues();
    }

    /**
     * 配置需要用到的数据
     */
    private void configValues() {
        mWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        mHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();

        circlePointY = mHeight / 2;
        everyWidth = mWidth / (circleCount + 1);
    }

    public void setData(List<Model> models) {
        mModels = models;
        fillInfo();
        invalidate();
    }

    private void fillInfo() {
        circleCount = mModels.size();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (circleCount == 0)
            return;
        for (int i = 0; i < circleCount; i++) {
            int color = getColor(i);
            boolean doing = isDoing(i);
            drawCircleWithParam(canvas, everyWidth * (i + 1), circlePointY, doing, color, mModels.get(i).name, i % 2 == 0);
            if (i != circleCount - 1) {
                drawLine(canvas, everyWidth * (i + 1), everyWidth * (i + 2), getColorForLine(i + 1));
            }
        }
    }


    private int getColor(int i) {
        Model model = mModels.get(i);
        switch (model.state) {
            case STATE_TODO:
                return colorTodo;
            case STATE_DOING:
                return colorDoing;
            default:
                return colorDone;
        }
    }

    private int getColorForLine(int i) {
        Model model = mModels.get(i);
        if (model.state == STATE_TODO) {
            return colorTodo;
        }
        return colorDone;
    }

    private boolean isDoing(int i) {
        Model model = mModels.get(i);
        return model.state == STATE_DOING;
    }

    /**
     * 画线
     *
     * @param canvas
     * @param circleStartX
     * @param mayEndX
     * @param color
     */
    private void drawLine(Canvas canvas, int circleStartX, int mayEndX, int color) {

        int startX = circleStartX + strokeRadius + strokeWidth + linePadding;
        int endX = mayEndX - strokeWidth - strokeRadius - linePadding;
        int startY = mHeight / 2;

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(color);
        canvas.drawLine(startX, startY, endX, startY, mPaint);
    }

    /**
     * 画圆
     *
     * @param canvas
     * @param circleX
     * @param circleY
     * @param doing   DOING
     * @param color
     * @param value
     * @param b
     */
    private void drawCircleWithParam(Canvas canvas, int circleX, int circleY, boolean doing, int color, String value, boolean b) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(colorDone);
        canvas.drawCircle(circleX, circleY, circleRadius, mPaint);

        String[] split = value.split("\n");
        String maxLine = "";
        for (String s : split) {
            if (s.length() > maxLine.length())
                maxLine = s;
        }
        int textWidth = (int) mPaint.measureText(maxLine, 0, maxLine.length());
        float textHeight = mPaint.measureText("M");
        int textStartX = circleX - textWidth / 2;
        int textStartY = circleY + textPaddingTop + (Math.abs(fontMetricsInt.bottom) + Math.abs(fontMetricsInt.top)) / 2;
        int textStartYUp = circleY - textPaddingTop - (Math.abs(fontMetricsInt.bottom) + Math.abs(fontMetricsInt.top)) / 2;
        textStartY += strokeRadius + strokeWidth;
        textStartYUp -= strokeRadius + strokeWidth;
        float offsetY = 0;
        b = true;
        if (doing) {
            if (b) {
                for (String s : split) {
                    float offsetX = (maxLine.length() - s.length()) * textWidth / 2f;
                    canvas.drawText(s, textStartX + offsetX, textStartY + offsetY, mPaint);
                    offsetY += textHeight;
                }
            } else {
                for (int i = split.length - 1; i >= 0; i--) {
                    String s = split[i];
                    float offsetX = (maxLine.length() - s.length()) * textWidth / 2f;
                    canvas.drawText(s, textStartX + offsetX, textStartYUp - offsetY, mPaint);
                    offsetY += textHeight;
                }
            }
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(color);
            canvas.drawCircle(circleX, circleY, strokeRadius, mPaint);
        }
    }


    public void setColorTodo(int color) {
        this.colorTodo = color;
    }

    public void setColorDoing(int color) {
        this.colorDoing = color;
    }

    public void setColorDone(int color) {
        this.colorDone = color;
    }

    public static class Model {
        public String name;
        public int state;  // BEFORE STARTING AFTER

        public Model(String name, int state) {
            this.name = name;
            this.state = state;
        }
    }

    public static class Utils {
        /**
         * dp2px
         */
        public static int dip2px(Context context, float dpValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dpValue * scale + 0.5f);
        }

        /**
         * px2dp
         */
        public static int px2dip(Context context, float pxValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (pxValue / scale + 0.5f);
        }

        /**
         * 将px值转换为sp值，保证文字大小不变
         *
         * @return
         */
        public static int px2sp(Context context, float pxValue) {
            final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
            return (int) (pxValue / fontScale + 0.5f);
        }

        /**
         * 将sp值转换为px值，保证文字大小不变
         */
        public static int sp2px(Context context, float spValue) {
            final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
            return (int) (spValue * fontScale + 0.5f);
        }
    }
}