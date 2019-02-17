package com.example.a09_bitmap_drawable;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

@SuppressLint("AppCompatCustomView")
public class MaterialEditText extends EditText {
    private static final float TEXT_SIZE = Utils.dp2px(12);
    private static final float TEXT_MARGIN = Utils.dp2px(8);
    private static final int TEXT_VERTICAL_OFFSET = (int) Utils.dp2px(22);
    private static final int TEXT_HORIZONTAL_OFFSET = (int) Utils.dp2px(5);
    private static final int TEXT_ANIMATION_OFFSET = (int) Utils.dp2px(16);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    boolean floatingLableShown;
    ObjectAnimator animator;

    public float getFloatingLableFraction() {
        return floatingLableFraction;
    }

    public void setFloatingLableFraction(float floatingLableFraction) {
        this.floatingLableFraction = floatingLableFraction;
        invalidate();
    }

    float floatingLableFraction;

    public MaterialEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    {
        setPadding(getPaddingLeft(), (int) (getPaddingTop()+TEXT_SIZE + TEXT_MARGIN),getPaddingRight(),getPaddingBottom());
        paint.setTextSize(TEXT_SIZE);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (floatingLableShown && TextUtils.isEmpty(s)){
                    floatingLableShown = false;
                    getAnimator().reverse();
                }else if (!floatingLableShown && !TextUtils.isEmpty(s)){
                    floatingLableShown = true;
                    getAnimator().start();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private ObjectAnimator getAnimator(){
        if(animator == null){
            animator = ObjectAnimator.ofFloat(MaterialEditText.this,
                    "floatingLableFraction",0,1);
        }
        return animator;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setAlpha((int) (0xff * floatingLableFraction));
        float extraOffset = TEXT_ANIMATION_OFFSET * (1- floatingLableFraction);
        canvas.drawText(getHint().toString(),TEXT_HORIZONTAL_OFFSET,TEXT_VERTICAL_OFFSET + extraOffset,paint);


    }
}
