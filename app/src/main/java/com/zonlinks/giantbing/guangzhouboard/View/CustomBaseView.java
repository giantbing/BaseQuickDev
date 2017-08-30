package com.zonlinks.giantbing.guangzhouboard.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by P on 2017/8/28.
 */

public class CustomBaseView extends View {
    protected int mWidth;
    protected int mHeight;
    protected Paint mDefaultTextPaint;

    public CustomBaseView(Context context) {
        super(context);
    }

    public CustomBaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mDefaultTextPaint = new Paint();
        mDefaultTextPaint.setAntiAlias(true);
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }


}
