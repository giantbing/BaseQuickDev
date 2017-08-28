package com.zonlinks.giantbing.guangzhouboard;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;

import static android.R.attr.path;

/**
 * Created by P on 2017/8/28.
 */

public class MyTestView extends CustomBaseView {
    private Paint stokePaint ;
    public MyTestView(Context context) {
        super(context);
    }

    public MyTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        stokePaint = new Paint();
        mDefaultTextPaint.setColor(0xff01F022);
        stokePaint.setColor(0xff000000);
        stokePaint.setStrokeWidth(4);
        stokePaint.setStyle(Paint.Style.STROKE);
        stokePaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 获取View宽高与画笔并根据此绘制内容
        Path arc_p = new Path();
        Path rect_p = new Path();
        canvas.translate(mWidth / 2, mHeight / 2);
        RectF oval = new RectF(-150,-150,150,150);
        RectF bottom = new RectF(-170,20,170,0);
        arc_p.addArc(oval,0,-180);
        rect_p.addRect(bottom,Path.Direction.CW);
        canvas.drawPath(arc_p,mDefaultTextPaint);
        canvas.drawPath(rect_p,mDefaultTextPaint);
        canvas.drawArc(oval,0,-180,true,stokePaint);
        canvas.drawRect(bottom,stokePaint);
    }
}
