package com.goodlogic.drawing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

public class ImageTextView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Rect rect = new Rect();
    Paint.FontMetrics fontMetrics = new Paint.FontMetrics();
    TextPaint textPaint = new TextPaint();
    Bitmap bitmap;
    float[] cutWidth = new float[1];

    public ImageTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    {
        bitmap = Utils.getAvatar((int)Utils.dp2px(100),getContext());
        paint.setTextSize(Utils.dp2px(22));
        paint.getFontMetrics(fontMetrics);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        StaticLayout staticLayout = new StaticLayout("使用Camera做选中使用Camera做选中使用Camera做选中使用Camera做选中使用Camera做选中" +
//                "使用Camera做选中使用Camera做选中使用Camera做选中使用Camera做选中使用Camera做选中",textPaint,getWidth(),Layout.Alignment.ALIGN_NORMAL,
//                1,0,false);
//        staticLayout.draw(canvas);

        String text = "使用发发使用发发使用发发1使用发发使用22" +
                "发发使用发发使用发发使用发1发使33用fawefawefawefwaefwaef22445521发发使用" +
                "发发使用发发5使用发发使用发6发使用发发使用7发发使用发发8发发使用发发5使用发发使用发6发使用发发使用7发发使用发发8" +
                "发发使用发发5使用发发使用发6发使用发发使用7发发使用发发8发发使用发发5使用发发使用发6发使用发发使用7发发使用发发6";
        canvas.drawBitmap(bitmap,getWidth() - Utils.dp2px(100), Utils.dp2px(100),paint);

        int index = 0;
        int oldIndex = 0;
        int pos = 0;

        float des = 0;

        do {

            if( 100 - rect.top<=Math.abs(des) && Math.abs(des) < bitmap.getHeight()+ 100){
                index = paint.breakText(text,oldIndex,text.length(),true,getWidth() - Utils.dp2px(100),cutWidth);
            }else {
                index = paint.breakText(text,oldIndex,text.length(),true,getWidth(),cutWidth);
            }

            paint.getTextBounds(text,oldIndex,oldIndex+ index,rect);
            canvas.drawText(text,oldIndex,oldIndex+ index,0,50 + paint.getFontSpacing() * pos,paint);

            pos+=1;
            oldIndex += index;
            des+=rect.top;

        }while (oldIndex<text.length());
    }
}
