package com.example.a09_bitmap_drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class DrawableView extends View {
    Drawable drawable;

    public DrawableView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    {
        drawable = new MeshDrawable();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }
}
