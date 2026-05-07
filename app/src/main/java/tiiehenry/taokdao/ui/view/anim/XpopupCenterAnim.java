package tiiehenry.taokdao.ui.view.anim;

import android.view.animation.DecelerateInterpolator;

import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

import com.lxj.xpopup.animator.PopupAnimator;

public class XpopupCenterAnim extends PopupAnimator {
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
