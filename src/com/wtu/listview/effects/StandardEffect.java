package com.wtu.listview.effects;

import android.view.View;

import com.nineoldandroids.view.ViewPropertyAnimator;
import com.wtu.listview.JazzyEffect;

public class StandardEffect implements JazzyEffect {

    @Override
    public void initView(View item, int position, int scrollDirection) {
        // no op
    }

    @Override
    public void setupAnimation(View item, int position, int scrollDirection, ViewPropertyAnimator animator) {
        // no op
    }

}
