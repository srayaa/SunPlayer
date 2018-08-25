package com.player.sunplayer.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class MarqueeText extends AppCompatTextView {
    public boolean isFocused() {
        return true;
    }

    public MarqueeText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
