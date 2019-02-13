package com.goodlogic.drawing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

public class AvatarView extends View {

    private static final float WIDTH = Utils.dp2px(300);
    private static final float PADING = Utils.dp2px(50);
    private static final float END = Utils.dp2px(10);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    RectF saveArea = new RectF();

    Bitmap bitmap;

    public AvatarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    {
        bitmap = Utils.getAvatar((int)WIDTH,getContext());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        saveArea.set(PADING,PADING,PADING+WIDTH,PADING+WIDTH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawOval(saveArea,paint);
        int saved = canvas.saveLayer(saveArea,paint);
        canvas.drawOval(PADING+END,PADING+END,PADING+WIDTH-END,PADING+WIDTH-END,paint);
        paint.setXfermode(xfermode);
        canvas.drawBitmap(bitmap,PADING,PADING,paint);
        paint.setXfermode(null);
        canvas.restoreToCount(saved);

    }


}
