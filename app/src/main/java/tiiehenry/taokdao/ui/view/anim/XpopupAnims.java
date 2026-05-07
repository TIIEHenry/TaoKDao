package tiiehenry.taokdao.ui.view.anim;

import android.view.View;
import android.view.animation.DecelerateInterpolator;

import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.animator.PopupAnimator;
import com.lxj.xpopup.enums.PopupAnimation;

public class XpopupAnims {


    public static class ScaleAlphaAnimator extends PopupAnimator {
        public static float fromScaleX = 0.8f;
        public static float fromScaleY = 0.8f;
        public static float fromAlpha = 0f;

        public ScaleAlphaAnimator(PopupAnimation popupAnimation) {
            super(null, popupAnimation);
        }

        public ScaleAlphaAnimator(View target, PopupAnimation popupAnimation) {
            super(target, popupAnimation);
        }

        @Override
        public void initAnimator() {
            targetView.setScaleX(fromScaleX);
            targetView.setScaleY(fromScaleY);
            targetView.setAlpha(fromAlpha);

            // 设置动画参考点
            targetView.post(() -> applyPivot());
        }

        /**
         * 根据不同的PopupAnimation来设定对应的pivot
         */
        private void applyPivot() {
            switch (popupAnimation) {
                case ScaleAlphaFromCenter:
                    targetView.setPivotX(targetView.getMeasuredWidth() / 2);
                    targetView.setPivotY(targetView.getMeasuredHeight() / 2);
                    break;
                case ScaleAlphaFromLeftTop:
                    targetView.setPivotX(0);
                    targetView.setPivotY(0);
                    break;
                case ScaleAlphaFromRightTop:
                    targetView.setPivotX(targetView.getMeasuredWidth());
                    targetView.setPivotY(0f);
                    break;
                case ScaleAlphaFromLeftBottom:
                    targetView.setPivotX(0f);
                    targetView.setPivotY(targetView.getMeasuredHeight());
                    break;
                case ScaleAlphaFromRightBottom:
                    targetView.setPivotX(targetView.getMeasuredWidth());
                    targetView.setPivotY(targetView.getMeasuredHeight());
                    break;
            }

        }

        @Override
        public void animateShow() {
            targetView.animate().scaleX(1f).scaleY(1f).alpha(1f)
                    .setDuration(XPopup.getAnimationDuration())
                    .setInterpolator(new LinearOutSlowInInterpolator())
                    .start();
        }

        @Override
        public void animateDismiss() {
            targetView.animate().scaleX(fromScaleX).scaleY(fromScaleY).alpha(0).setDuration(XPopup.getAnimationDuration())
                    .setInterpolator(new LinearOutSlowInInterpolator()).start();
        }
    }

    public static class Center extends PopupAnimator {
        public static int animateDuration = 200;

        @Override
        public void initAnimator() {
            targetView.setScaleX(0.7f);
            targetView.setScaleY(0.7f);
            targetView.setAlpha(0.1f);
        }

        @Override
        public void animateShow() {
            targetView.animate()
                    .scaleX(1).scaleY(1)
                    .alpha(1)
                    .setInterpolator(new DecelerateInterpolator())
                    .setDuration(animateDuration)
                    .start();
        }

        @Override
        public void animateDismiss() {
            targetView.animate()
                    .scaleX(0.6f).scaleY(0.6f)
                    .alpha(0)
                    .setInterpolator(new LinearOutSlowInInterpolator())
                    .setDuration(animateDuration)
                    .start();
        }
    }

}
