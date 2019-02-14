package com.goodlogic.a08_animation;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CameraView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Camera camera = new Camera();

    float topFlip = 0;
    float buttomFlip = 0;
    float flipRotation = 0;

    public float getTopFlip() {
        return topFlip;
    }

    public void setTopFlip(float topFlip) {
        this.topFlip = topFlip;
        invalidate();
    }

    public float getButtomFlip() {
        return buttomFlip;
    }

    public void setButtomFlip(float buttomFlip) {
        this.buttomFlip = buttomFlip;
        invalidate();
    }

    public float getFlipRotation() {
        return flipRotation;
    }

    public void setFlipRotation(float flipRotation) {
        this.flipRotation = flipRotation;
        invalidate();
    }

    public CameraView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    {

        camera.setLocation(0,0,Utils.getZForCamera());//一个英寸等于72个像素，-8 = -8 * 72
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制上半部分
        canvas.save();
        canvas.translate(100 + 800/2,100+800/2);
        canvas.rotate(-flipRotation);
        camera.save();
        camera.rotateX(topFlip);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.clipRect(-800,-800,800,0);
        canvas.rotate(flipRotation);
        canvas.translate(-(100 + 800/2),-(100+800/2));
        canvas.drawBitmap(Utils.getAvatar(800,getContext()),100,100,paint);
        canvas.restore();

        //绘制下半部分
        canvas.translate(100 + 800/2,100+800/2);
        canvas.rotate(-flipRotation);
        camera.save();
        camera.rotateX(buttomFlip);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.clipRect(-800,0,800,800);
        canvas.rotate(flipRotation);

        canvas.translate(-(100 + 800/2),-(100+800/2));
        canvas.drawBitmap(Utils.getAvatar(800,getContext()),100,100,paint);

    }


}
