package com.goodlogic.a08_animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CountryView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
        invalidate();
    }

    String province = "北京市";

    public CountryView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    {
        paint.setTextSize(Utils.dp2px(100));
        paint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText(province,getWidth()/2,getHeight()/2,paint);
    }
}
