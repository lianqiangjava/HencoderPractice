package com.goodlogic.a12_scalable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;

import androidx.core.view.GestureDetectorCompat;

public class ScalableImageView extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener, Runnable {
    private static final float IMAGE_WIDTH = Utils.dp2px(300);
    private static final float OVER_SCALE_FACTION = 1.5f;

    Bitmap bitmap;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    float offsetX;
    float offsetY;
    float originalOffsetX;
    float originalOffsetY;
    float smallScale;
    float bigScale;
    boolean big;
    float scaleFraction; //0 - 1
    ObjectAnimator scaleAnimator;

    GestureDetectorCompat detector;
    OverScroller scroller;

    public ScalableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        bitmap = Utils.getAvatar((int) IMAGE_WIDTH,context);
        detector = new GestureDetectorCompat(context,this);
        scroller = new OverScroller(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        originalOffsetX = (getWidth() - bitmap.getWidth()) /2f;
        originalOffsetY = (getHeight() - bitmap.getHeight()) /2f;

        if ((float)bitmap.getWidth() / bitmap.getHeight() > (float)getWidth()/getHeight()){
            smallScale = (float)getWidth() / bitmap.getWidth();
            bigScale = (float)getHeight()/bitmap.getHeight() * OVER_SCALE_FACTION;
        }else {
            smallScale = (float)getHeight() / bitmap.getHeight();
            bigScale = (float)getWidth()/bitmap.getWidth() * OVER_SCALE_FACTION;
        }

    }

    public float getScaleFraction() {
        return scaleFraction;
    }

    public void setScaleFraction(float scaleFraction) {
        this.scaleFraction = scaleFraction;
        invalidate();
    }

    private ObjectAnimator getScaleAnimator(){
        if (scaleAnimator == null){
            scaleAnimator = ObjectAnimator.ofFloat(this,"scaleFraction",0,1);
            scaleAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {

                }
            });
        }
        return scaleAnimator;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(offsetX * scaleFraction,offsetY * scaleFraction);
        float scale = smallScale + (bigScale - smallScale) * scaleFraction;
        canvas.scale(scale,scale,getWidth()/2f,getHeight()/2f);
        canvas.drawBitmap(bitmap,originalOffsetX,originalOffsetY,paint);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;//只有它的返回值有用，下面方法返回值没用
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent down, MotionEvent event, float distanceX, float distanceY) {
        if (big){
            offsetX -= distanceX;
            offsetX = Math.min(offsetX,(bitmap.getWidth() * bigScale - getWidth())/2);
            offsetX = Math.max(offsetX,-(bitmap.getWidth() * bigScale - getWidth())/2);

            offsetY -= distanceY;
            offsetY = Math.min(offsetY,(bitmap.getHeight() * bigScale - getHeight())/2);
            offsetY = Math.max(offsetY,-(bitmap.getHeight() * bigScale - getHeight())/2);
            invalidate();
        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent down, MotionEvent event, float velocityX, float velocityY) {
        if (big){
            scroller.fling((int)offsetX,(int)offsetY,(int)velocityX,(int)velocityY,
                    -(int)(bitmap.getWidth() * bigScale -getWidth())/2,
                    (int)(bitmap.getWidth() * bigScale -getWidth())/2,
                    -(int)(bitmap.getHeight() * bigScale -getHeight())/2,
                    (int)(bitmap.getHeight() * bigScale  -getHeight())/2,
                    100,100);

            postOnAnimation(this);

        }
        return false;
    }


    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        big = !big;
        if(big){
            getScaleAnimator().start();
        }else {
            getScaleAnimator().reverse();
        }
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public void run() {
        if(scroller.computeScrollOffset()){
            offsetX = scroller.getCurrX();
            offsetY = scroller.getCurrY();
            invalidate();
            postOnAnimation(this);
        }
    }
}