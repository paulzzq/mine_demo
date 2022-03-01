package com.zzq.paul_tools.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

/**
 * Created by Administrator on 2018/1/23.
 */

public class LoadingImageView extends android.support.v7.widget.AppCompatImageView {
    private static Bitmap mLoadingBitmap;
    private RotateAnimation mRotateAnim;

    public LoadingImageView(Context context) {
        super(context);
        init(context);
    }

    public LoadingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoadingImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        if (null == mLoadingBitmap || mLoadingBitmap.isRecycled()) {
            mLoadingBitmap = getLoadingBitmap(((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth()/6,Color.WHITE, 7);
        }
        if (null == mRotateAnim) {
            mRotateAnim = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            mRotateAnim.setInterpolator(new LinearInterpolator());
            mRotateAnim.setDuration(1400);
        }
        mRotateAnim.setRepeatCount(10000);
        setImageBitmap(mLoadingBitmap);
        setAnimation(mRotateAnim);
    }

    private Bitmap getLoadingBitmap(int width, int color, int blod) {
        Bitmap bitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setDither(true);
        paint.setFilterBitmap(true);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(blod);
        canvas.save();
        for (int i = 0; i < 12; i++) {
            canvas.save();
            paint.setAlpha(20 * i);
            canvas.rotate(i * 30, width / 2, width / 2);
            canvas.drawLine(width / 2, blod / 2, width / 2, width / 4, paint);
            canvas.restore();
        }
        canvas.restore();
        return bitmap;
    }

    @TargetApi(8)
    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (visibility == VISIBLE) {
            setAnimation(mRotateAnim);
            mRotateAnim.start();
        } else if (visibility == GONE) {
            mRotateAnim.cancel();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (null != mRotateAnim && isShown()) {
            setAnimation(mRotateAnim);
            mRotateAnim.start();
        }
    }

    @TargetApi(8)
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (null != mRotateAnim) {
            mRotateAnim.cancel();
        }
    }
}
