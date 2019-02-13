package com.goodlogic.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class SportsView extends View {

    private static final int RING_WIDTH = 20;
    private static final int RADIUS = 400;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Rect rect = new Rect();
    Paint.FontMetrics fontMetrics = new Paint.FontMetrics();

    public SportsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setTextSize(Utils.dp2px(100));
        paint.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"hwxh.ttf"));
        paint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制环
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(RING_WIDTH);
        canvas.drawCircle(getWidth() /2,getHeight()/2,RADIUS,paint);

        //绘制进度条
        paint.setColor(Color.GREEN);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(getWidth()/2-RADIUS,getHeight()/2-RADIUS,getWidth()/2+RADIUS
        ,getHeight()/2+RADIUS,-90,225,false,paint);

        //绘制文字
        paint.setStyle(Paint.Style.FILL);
//        paint.getTextBounds("abag",0,"abab".length(),rect);
        paint.getFontMetrics(fontMetrics);
        float offset = (fontMetrics.ascent + fontMetrics.descent)/2;
        canvas.drawText("aaaa",getWidth()/2,getHeight()/2 - offset,paint);

        //绘制文字1
        paint.setTextSize(Utils.dp2px(130));
        paint.setTextAlign(Paint.Align.LEFT);
        paint.getTextBounds("aaaa",0,"aaaa".length(),rect);
        canvas.drawText("aaaa",-rect.left,200,paint);

        //绘制文字2
        paint.setTextSize(Utils.dp2px(15));
        canvas.drawText("aaaa",0,200+paint.getFontSpacing(),paint);


    }
}
