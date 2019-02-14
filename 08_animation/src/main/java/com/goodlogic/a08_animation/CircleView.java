package com.goodlogic.a08_animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CircleView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float radius = Utils.dp2px(50);

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        invalidate();
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    {
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(getWidth()/2,getHeight()/2,radius,paint);
    }
}
