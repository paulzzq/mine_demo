package com.zzq.paul_tools.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

public class DraTextView extends View {

    // 画笔
    private Paint mPaint = new Paint();

    public DraTextView(Context context) {
        super(context);
    }

    public DraTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DraTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DraTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        TextPaint tp = new TextPaint();
        tp.setColor(Color.BLUE);
        tp.setStyle(Paint.Style.FILL);
        tp.setTextSize(50);
        String message = "8月30日中午，法制晚报·看法新闻记者从中国电信、中国联通、中国移动获悉，";
        StaticLayout myStaticLayout = new StaticLayout(message, tp, 30, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        myStaticLayout.draw(canvas);
//        canvas.restore();

//　　
//　　
//　　
//         +
//                "三大运营商将从9月1日起全面取消手机国内长途费和漫游费（不含港澳台，下同），比原计划的10月1日提前一个月完成。用户无需申请，自动生效。";
//
//　　
//　　
    }
}
