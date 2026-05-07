package tiiehenry.taokdao.ui.view;


import android.content.Context;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.xuexiang.xui.widget.popupwindow.popup.XUIBasePopup;

import tiiehenry.ideditor.R;

/**
 * 提供一个浮层，支持自定义浮层的内容，支持在指定 {@link View} 的任一方向旁边展示该浮层，支持自定义浮层出现/消失的动画。
 *
 * @author xuexiang
 * @since 2019/1/14 下午10:00
 */
public class UIPopup extends XUIBasePopup {
    public static final int ANIM_GROW_FROM_LEFT = 1;
    public static final int ANIM_GROW_FROM_RIGHT = 2;
    public static final int ANIM_GROW_FROM_CENTER = 3;

    public static final int DIRECTION_TOP = 0;
    public static final int DIRECTION_BOTTOM = 1;
    public static final int DIRECTION_NONE = 2;
    protected int mAnimStyle;
    protected int mDirection;
    protected int mX = -1;
    protected int mY = -1;
    protected int mArrowCenter;
    // 该PopupWindow的View距离屏幕左右的最小距离
    private int mPopupLeftRightMinMargin = 0;
    // 该PopupWindow的View距离屏幕上下的最小距离
    private int mPopupTopBottomMinMargin = 0;
    private int mPreferredDirection;
    // 计算位置后的偏移x值
    private int mOffsetX = 0;
    // 计算位置后的偏移y值，当浮层在View的上方时使用
    private int mOffsetYWhenTop = 0;
    // 计算位置后的偏移y值，当浮层在View的下方时使用
    private int mOffsetYWhenBottom = 0;
    private int containerDrawableRes = -1;

    public UIPopup(Context context) {
        this(context, DIRECTION_NONE);
    }


    public UIPopup(Context context, int preferredDirection) {
        super(context);
        mAnimStyle = ANIM_GROW_FROM_CENTER;
        mPreferredDirection = preferredDirection;
        mDirection = mPreferredDirection;
    }


    public void setPopupLeftRightMinMargin(int popupLeftRightMinMargin) {
        mPopupLeftRightMinMargin = popupLeftRightMinMargin;
    }

    public void setPopupTopBottomMinMargin(int popupTopBottomMinMargin) {
        mPopupTopBottomMinMargin = popupTopBottomMinMargin;
    }

    /**
     * 设置根据计算得到的位置后的偏移值
     */
    public UIPopup setPositionOffsetX(int offsetX) {
        mOffsetX = offsetX;
        return this;
    }


    /**
     * 设置根据计算得到的位置后的偏移值
     *
     * @param offsetYWhenTop mDirection!=DIRECTION_BOTTOM 时的 offsetY
     */
    public UIPopup setPositionOffsetYWhenTop(int offsetYWhenTop) {
        mOffsetYWhenTop = offsetYWhenTop;
        return this;
    }


    /**
     * 设置根据计算得到的位置后的偏移值
     *
     * @param offsetYWhenBottom mDirection==DIRECTION_BOTTOM 时的 offsetY
     */
    public UIPopup setPositionOffsetYWhenBottom(int offsetYWhenBottom) {
        mOffsetYWhenBottom = offsetYWhenBottom;
        return this;
    }

    /**
     * 设置弹出的方向
     *
     * @param preferredDirection
     * @return
     */
    public UIPopup setPreferredDirection(int preferredDirection) {
        mPreferredDirection = preferredDirection;
        return this;
    }

    @Override
    protected Point onShow(View attachedView) {
        calculatePosition(attachedView);
        int[] attachedViewLocation = new int[2];
        attachedView.getLocationOnScreen(attachedViewLocation);
        int center = attachedViewLocation[0] + attachedView.getWidth() / 2;
//        Log.e("uipopup", "center: "+center );
        initAnimationStyle(mScreenSize.x, mArrowCenter);
        switch (mAnimStyle) {
            case ANIM_GROW_FROM_LEFT:
                mX = center + mWindowWidth - attachedView.getWidth() / 2;
                break;
            case ANIM_GROW_FROM_RIGHT:
                mX = center - mWindowWidth + attachedView.getWidth() / 2;
                break;
            case ANIM_GROW_FROM_CENTER:
                break;
        }
        int offsetY = 0;
        if (mDirection == DIRECTION_TOP) {
            offsetY = mOffsetYWhenTop;
        } else if (mDirection == DIRECTION_BOTTOM) {
            offsetY = mOffsetYWhenBottom;
        }
        return new Point(mX + mOffsetX, mY + offsetY);
    }

    private void calculatePosition(View attachedView) {
        if (attachedView != null) {
            int[] attachedViewLocation = new int[2];
            attachedView.getLocationOnScreen(attachedViewLocation);
            mArrowCenter = attachedViewLocation[0] + attachedView.getWidth() / 2;
            if (mArrowCenter < mScreenSize.x / 2) {
                //描点在左侧
                if (mArrowCenter - mWindowWidth / 2 > mPopupLeftRightMinMargin) {
                    mX = mArrowCenter - mWindowWidth / 2;
                } else {
                    mX = mPopupLeftRightMinMargin;
                }
            } else {//描点在右侧
                if (mArrowCenter + mWindowWidth / 2 < mScreenSize.x - mPopupLeftRightMinMargin) {
                    mX = mArrowCenter - mWindowWidth / 2;
                } else {
                    mX = mScreenSize.x - mPopupLeftRightMinMargin - mWindowWidth;
                }
            }
            //实际的方向和期望的方向可能不一致，每次都需要重新
            mDirection = mPreferredDirection;
            switch (mPreferredDirection) {
                case DIRECTION_TOP:
                    mY = attachedViewLocation[1] - mWindowHeight;
                    if (mY < mPopupTopBottomMinMargin) {
                        mY = attachedViewLocation[1] + attachedView.getHeight();
                        mDirection = DIRECTION_BOTTOM;
                    }
                    break;
                case DIRECTION_BOTTOM:
                    mY = attachedViewLocation[1] + attachedView.getHeight();
                    if (mY > mScreenSize.y - mPopupTopBottomMinMargin) {
                        mY = attachedViewLocation[1] - mWindowHeight;
                        mDirection = DIRECTION_TOP;
                    }
                    break;
                case DIRECTION_NONE:
                    // 默认Y值与attachedView的Y值相同
                    mY = attachedViewLocation[1];
                    break;
            }
        } else {
            mX = (mScreenSize.x - mWindowWidth) / 2;
            mY = (mScreenSize.y - mWindowHeight) / 2;
            mDirection = DIRECTION_NONE;
        }
    }

    /**
     * init animation style
     *
     * @param screenWidth screen width
     * @param requestedX  distance from left edge
     */
    private void initAnimationStyle(int screenWidth, int requestedX) {
        boolean onTop = mDirection == DIRECTION_TOP;
        switch (mAnimStyle) {
            case ANIM_GROW_FROM_LEFT:
                getPopupWindow().setAnimationStyle(onTop ? R.style.Animation_Popup_LeftTop : R.style.Animation_Popup_LeftBottom);
                break;

            case ANIM_GROW_FROM_RIGHT:
                getPopupWindow().setAnimationStyle(onTop ? R.style.Animation_Popup_RightTop : R.style.Animation_Popup_RightBottom);
                break;

            case ANIM_GROW_FROM_CENTER:
                getPopupWindow().setAnimationStyle(onTop ? R.style.Animation_MaterialDialog : R.style.Animation_MaterialDialog);
                break;
        }
    }

    /**
     * 菜单弹出动画
     *
     * @param animStyle 默认是 ANIM_AUTO
     */
    public void setAnimStyle(int animStyle) {
        mAnimStyle = animStyle;
    }

    public void setBoxBackground(int resId) {
        this.containerDrawableRes = resId;
    }

    @Override
    public void setContentView(View root) {
        ViewGroup container = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.ui_layout_popup, null, false);
        FrameLayout box = container.findViewById(R.id.box);
        box.addView(root);
        if (containerDrawableRes != -1) {
            box.setBackgroundResource(containerDrawableRes);
        }
        super.setContentView(container);
    }


    private void setViewVisibility(View view, boolean visible) {
        if (view != null) {
            view.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    public ViewGroup.LayoutParams generateLayoutParam(int width, int height) {
        return new FrameLayout.LayoutParams(width, height);
    }

}
