package com.goodlogic.a11_touch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Stack;

public class TouchView extends View {

    public TouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //getActionMasked 比 getAction新，是从多点触控开始出现
        //getAction 获取ACTION_POINTER_DOWN 与ACTION_POINTER_UP出错
        if(event.getActionMasked() == MotionEvent.ACTION_UP){
            performClick();
        }
        return true;//消费了
    }
}
