package com.goodlogic.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class TestView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();
    PathMeasure pathMeasure;

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        path.reset();
        path.addRect(getWidth()/2-150,getHeight()/2 - 300,
                getWidth()/2+150,getHeight()/2,Path.Direction.CCW);
        path.addCircle(getWidth()/2,getHeight()/2,150,Path.Direction.CCW);
        path.addCircle(getWidth()/2,getHeight()/2,400,Path.Direction.CW);

//        pathMeasure = new PathMeasure(path,false);
//        pathMeasure.getLength();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawLine(100,100,200,200,paint);
//        canvas.drawCircle(getWidth()/2,getHeight()/2,Utils.dp2px(150),paint);

        path.setFillType(Path.FillType.INVERSE_WINDING);
        canvas.drawPath(path,paint);


    }

}
