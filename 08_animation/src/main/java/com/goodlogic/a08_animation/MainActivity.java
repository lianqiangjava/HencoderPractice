package com.goodlogic.a08_animation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

public class MainActivity extends AppCompatActivity {

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = findViewById(R.id.view);
//        view.animate()
//                .translationX(Utils.dp2px(400))
//                .setInterpolator(new AccelerateInterpolator())
//                .setStartDelay(1000)
//                .setDuration(2000)
//                .start();

//        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"radius",Utils.dp2px(150));
//        animator.setDuration(2000);
//        animator.start();

        //自定义属性动画
//        ObjectAnimator bottomFlipAnimator = ObjectAnimator.ofFloat(view,"buttomFlip",45);
//        bottomFlipAnimator.setDuration(1500);
//
//        ObjectAnimator flipRotationAnimator = ObjectAnimator.ofFloat(view,"flipRotation",270);
//        flipRotationAnimator.setDuration(1500);
//
//        ObjectAnimator topFlipAnimator = ObjectAnimator.ofFloat(view,"topFlip",-45);
//        flipRotationAnimator.setDuration(1500);
//
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playSequentially(bottomFlipAnimator,flipRotationAnimator,topFlipAnimator);
//        animatorSet.start();

        //多个动画同时执行
//        PropertyValuesHolder bottomFlipHolder = PropertyValuesHolder.ofFloat("buttomFlip",
//                45);
//        PropertyValuesHolder flipRotationHolder = PropertyValuesHolder.ofFloat("flipRotation",
//                270);
//        PropertyValuesHolder topFlipHolder = PropertyValuesHolder.ofFloat("topFlip",
//                -45);
//        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view,bottomFlipHolder,flipRotationHolder,topFlipHolder);
//        objectAnimator.setDuration(2000);
//        objectAnimator.start();

        //单个属性多个控制
//        float length = Utils.dp2px(300);
//        Keyframe keyframe1 = Keyframe.ofFloat(0,0);
//        Keyframe keyframe2 = Keyframe.ofFloat(0.2f,0.4f* length);
//        Keyframe keyframe3 = Keyframe.ofFloat(0.8f,0.6f * length);
//        Keyframe keyframe4 = Keyframe.ofFloat(1,1 * length);
//
//        PropertyValuesHolder valuesHolder = PropertyValuesHolder.ofKeyframe("translationX",
//                keyframe1,keyframe2,keyframe3,keyframe4);
//        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view,valuesHolder);
//        animator.setDuration(2000);
//        animator.start();


        //自定义TypeEvaluator
//        Point targetPoint = new Point((int)Utils.dp2px(300),(int)Utils.dp2px(200));
//        ObjectAnimator animator = ObjectAnimator.ofObject(view,"point",new PointEvaluator(),targetPoint);
//        animator.setStartDelay(1000);
//        animator.setDuration(2000);
//        animator.start();

        ObjectAnimator animator = ObjectAnimator.ofObject(view,"province",new ProvinceEvaluator(),"澳门");
        animator.setStartDelay(1000);
        animator.setDuration(3000);
        animator.start();

    }

    class PointEvaluator implements TypeEvaluator<Point>{
        @Override
        public Point evaluate(float fraction, Point startValue, Point endValue) {
            //(1,1) (5,5) fraction:0.2 x:1+(5-1) * 0.2 y:1+(5-1) * 0.2
            float x = startValue.x + (endValue.x - startValue.x) * fraction;
            float y = startValue.y + (endValue.y - startValue.y) * fraction;
            return new Point((int)x,(int)y);
        }
    }

    class ProvinceEvaluator implements TypeEvaluator<String>{
        @Override
        public String evaluate(float fraction, String startValue, String endValue) {
            //初始值:北京市   终点值:上海市   fraction 0.5
            int startIndex = ProvinceUtil.provinces.indexOf(startValue);
            int endIndex = ProvinceUtil.provinces.indexOf(endValue);
            int index = (int) (startIndex + (endIndex - startIndex) * fraction);

            return ProvinceUtil.provinces.get(index);
        }
    }
}
