package com.pankajrawat.homescreen;

import android.content.Context;
import android.graphics.Interpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

public class ScrollerSpeed extends Scroller {

    private int mDuration = 5000;

    public ScrollerSpeed(Context context, DecelerateInterpolator decelerateInterpolator) {
        super(context);
    }

    public ScrollerSpeed(Context context, Interpolator interpolator) {
        super(context, (android.view.animation.Interpolator) interpolator);
    }

    public ScrollerSpeed(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, (android.view.animation.Interpolator) interpolator, flywheel);
    }


    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }
}

