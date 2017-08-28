package com.zonlinks.giantbing.guangzhouboard;


import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by P on 2017/8/28.
 */

public class MyTestView extends CustomBaseView {

    public MyTestView(Context context) {
        super(context);
    }

    public MyTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mDefaultTextPaint.setColor(0xff000000);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 获取View宽高与画笔并根据此绘制内容

        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawCircle(0,0,100,mDefaultTextPaint);
    }
}
