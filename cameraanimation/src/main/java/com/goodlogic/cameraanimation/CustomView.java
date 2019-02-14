package com.goodlogic.cameraanimation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    {
        bitmap = Utils.getAvatar(300,getContext());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.clipOutRect(100,100,200,200);

        //屏幕坐标系和Canvas坐标系，按照Canvas坐标系倒序绘制
//        canvas.translate(200,0);
//        canvas.rotate(45,bitmap.getWidth()/2+200,bitmap.getHeight()/2);
//        canvas.drawBitmap(bitmap,0,0,paint);


        canvas.rotate(45,bitmap.getWidth()/2+200,bitmap.getHeight()/2);
        canvas.translate(200,0);
        canvas.scale(2,2);
        canvas.drawBitmap(bitmap,0,0,paint);

    }
}
