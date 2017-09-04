package com.zonlinks.giantbing.guangzhouboard.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//http://blog.csdn.net/cuiran/article/details/17226009
//http://www.cnblogs.com/vaiyanzi/archive/2011/12/06/2277791.html
//http://blog.csdn.net/fengyoujie/article/details/7296941
public class VerticalScrollTextView extends TextView {
    //必须写死宽度，这里300px
    private float step =0f;
    private Paint mPaint=new Paint();

    private String text;
    private float width;

    private float height;

    private List<String> textList = new ArrayList<>();    //分行保存textview的显示信息。

    public VerticalScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VerticalScrollTextView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);

        height = MeasureSpec.getSize(heightMeasureSpec);

//        Log.i("aaaa","width:"+width+"---"+"height:"+height);

        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode != MeasureSpec.EXACTLY) {
            throw new IllegalStateException("ScrollLayout only canmCurScreen run at EXACTLY mode!");
        }

        text=getText().toString();
        if(text==null|text.length()==0){
            return ;
        }

        //下面的代码是根据宽度和字体大小，来计算textview显示的行数。
        textList.clear();
//        StringBuilder builder =null;
//        for(int i=0;i<text.length();i++){
//            if(i%12 == 0){  //需要根据字体大小，和宽度设置一行显示的字数
//                builder = new StringBuilder();
//            }
//             if(i%12 <= 11){
//                 builder.append(text.charAt(i));
//             }
//             if(i%12 == 11){
//                 textList.add(builder.toString());
//             }
//        }
        float length = 0;
        mPaint.setTextSize(30f);//设置字体大小
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<text.length();i++){
            if(length<width){
                char c = text.charAt(i);
                if (c!='\n'){
                    builder.append(c);
                    length += mPaint.measureText(text.substring(i, i+1));
                    if(i==text.length()-1){
                        textList.add(builder.toString());
                    }
                }else {//遇到换行符就换行
                    textList.add(builder.toString());
                    builder.delete(0,i);
                    length = 0;
                }

            }else{
                textList.add(builder.toString().substring(0,builder.toString().length()-1));
                builder.delete(0, builder.length()-1) ;
                length= mPaint.measureText(text.substring(i, i+1));
                i--;
            }

        }
//        Log.i("aaaa","size:"+textList.size());
    }


    //下面代码是利用上面计算的显示行数，将文字画在画布上，实时更新。
    @Override
    public void onDraw(Canvas canvas) {
        if(textList.size()==0)  return;
//       TypedValue.COMPLEX_UNIT_PX

        mPaint.setColor(Color.BLACK);

        int lineHeight = getLineHeight();//获取行高

//        Log.i("aaaa","lineHeight:"+lineHeight);
        if (lineHeight*textList.size()>height-lineHeight*2-30){
            for(int i = 0; i < textList.size(); i++) {
//        	canvas.drawText(textList.get(i), 0, this.getHeight()+(i+1)*mPaint.getTextSize()-step+30, mPaint); // 30==>40
//            canvas.drawText(textList.get(i), 0, this.getHeight()+(i+1)*mPaint.getTextSize()*3/2-step+30, mPaint); // 30==>40
                canvas.drawText(textList.get(i), 0, (i+1)*mPaint.getTextSize()*3/2-step+10, mPaint); // 30==>40
//                Log.i("aaaa","总行高"+lineHeight*textList.size());
            }


            invalidate();

            step = step+0.7f;   //绘制速度
            if (step >= this.getHeight()+textList.size()*mPaint.getTextSize()) {
                step = 0;
            }
        }else {
            for(int i = 0; i < textList.size(); i++) {
//        	canvas.drawText(textList.get(i), 0, this.getHeight()+(i+1)*mPaint.getTextSize()-step+30, mPaint); // 30==>40
//            canvas.drawText(textList.get(i), 0, this.getHeight()+(i+1)*mPaint.getTextSize()*3/2-step+30, mPaint); // 30==>40
                canvas.drawText(textList.get(i), 0, (i+1)*mPaint.getTextSize()*3/2-step+10, mPaint); // 30==>40
//                Log.i("aaaa","总行高"+lineHeight*textList.size());
            }
        }



    }

    public void onDestory(){
        textList.clear();
    }


}
