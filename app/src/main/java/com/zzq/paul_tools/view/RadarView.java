package com.zzq.paul_tools.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.zzq.paul_tools.R;
import com.zzq.paul_tools.bean.SpiderDataBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

/**
 * @author zhuzaiqing
 * @describe 绘制雷达  蜘蛛网
 * @time 2018/11/12 16:16
 */
public class RadarView extends View {

    private int count = 6; //数据个数
    private float angle = (float) (Math.PI * 2 / count);
    private float radius; //网格最大半径
    private int centerX; //中心X
    private int centerY; //中心Y
    private String[] titles = {"a", "b", "c", "d", "e", "f"};
    private double[] data = {100, 60, 60, 60, 30, 40}; //各维度分值
    private Paint mainPaint; //雷达区画笔
    private Paint valuePaint; //数据区画笔
    private Paint textPaint; //文本画笔

    private List<SpiderDataBean> lists;

    private int textColor;
    private int coverColor;
    private int spiderColor;
    private int maxValue;

    private Context context;

    public RadarView(Context context) {
        super(context, null);
    }

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RadarView);
        textColor = typedArray.getColor(R.styleable.RadarView_text_color, Color.BLACK);
        coverColor = typedArray.getColor(R.styleable.RadarView_cover_color, Color.RED);
        spiderColor = typedArray.getColor(R.styleable.RadarView_spider_color, Color.BLUE);
        maxValue = typedArray.getInt(R.styleable.RadarView_max_value, 100);
        init();
    }

    private void init() {
        //雷达区画笔
        mainPaint = new Paint();
        mainPaint.setStrokeWidth(2);
        mainPaint.setColor(spiderColor);
        mainPaint.setAntiAlias(true);
        mainPaint.setStyle(Paint.Style.STROKE);
        //数据区画笔
        valuePaint = new Paint();
        valuePaint.setStrokeWidth(2);
        valuePaint.setColor(coverColor);
        valuePaint.setAntiAlias(true);
        valuePaint.setStyle(Paint.Style.STROKE);
        //文本画笔
        textPaint = new Paint();
        textPaint.setStrokeWidth(2);
        textPaint.setTextSize(40);
        textPaint.setColor(textColor);
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);

        lists = new ArrayList<>();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.e("eee","---w:"+w);
        Log.e("eee","---h:"+h);
        radius = Math.min(h, w) / 2 * 0.9f; //中心坐标
        centerX = w / 2;
        centerY = h / 2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawPolygon(canvas);
        drawLines(canvas);
        drawText(canvas);
        drawRegion(canvas);
    }

    /**
     * 绘制正多边形
     */
    private void drawPolygon(Canvas canvas) {
        lists.clear();
        canvas.drawColor(Color.WHITE);
        Path path = new Path();
        float r = radius / (count - 1);  //r是蜘蛛网之间的间距
        for (int i = 1; i < count; i++) {
            float curR = r * i;//当前半径
            path.reset();
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    path.moveTo(centerX + curR, centerY);
                    SpiderDataBean bean = new SpiderDataBean();
                    bean.setX(centerX + curR);
                    bean.setY(centerY);
                    bean.setValue(maxValue / (count - 1) * i);
                    lists.add(bean);
                } else {
                    //根据半径，计算出蜘蛛丝上每个点的坐标
                    float x = (float) (centerX + curR * Math.cos(angle * j));
                    float y = (float) (centerY + curR * Math.sin(angle * j));
                    path.lineTo(x, y);


                    SpiderDataBean bean = new SpiderDataBean();
                    bean.setX(x);
                    bean.setY(y);
                    bean.setValue(maxValue / (count - 1) * i);
                    lists.add(bean);
                }
            }
            path.close();//闭合路径
            canvas.drawPath(path, mainPaint);
        }
    }


    /**
     * 绘制直线
     */
    private void drawLines(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < count; i++) {
            path.reset();
            path.moveTo(centerX, centerY);
            float x = (float) (centerX + radius * Math.cos(angle * i));
            float y = (float) (centerY + radius * Math.sin(angle * i));
            path.lineTo(x, y);
            canvas.drawPath(path, mainPaint);
        }
    }

    /**
     * 绘制文字
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        for (int i = 0; i < count; i++) {
            float x = (float) (centerX + (radius + fontHeight / 2) * Math.cos(angle * i));
            float y = (float) (centerY + (radius + fontHeight / 2) * Math.sin(angle * i));
            if (angle * i >= 0 && angle * i <= Math.PI / 2) {//第4象限
                canvas.drawText(titles[i], x, y, textPaint);
            } else if (angle * i >= 3 * Math.PI / 2 && angle * i <= Math.PI * 2) {//第3象限
                canvas.drawText(titles[i], x, y, textPaint);
            } else if (angle * i > Math.PI / 2 && angle * i <= Math.PI) {//第2象限
                float dis = textPaint.measureText(titles[i]);//文本长度
                canvas.drawText(titles[i], x - dis, y, textPaint);
            } else if (angle * i >= Math.PI && angle * i < 3 * Math.PI / 2) {//第1象限
                float dis = textPaint.measureText(titles[i]);//文本长度
                canvas.drawText(titles[i], x - dis, y, textPaint);
            }
        }
    }

    /**
     * 绘制区域
     *
     * @param canvas
     */
    private void drawRegion(Canvas canvas) {
        Path path = new Path();
        valuePaint.setAlpha(255);
        for (int i = 0; i < count; i++) {
            double percent = data[i] / maxValue;
            float x = (float) (centerX + radius * Math.cos(angle * i) * percent);
            float y = (float) (centerY + radius * Math.sin(angle * i) * percent);
            if (i == 0) {
                path.moveTo(x, centerY);
            } else {
                path.lineTo(x, y);
            }

            SpiderDataBean bean = new SpiderDataBean();
            bean.setX(x);
            bean.setY(y);
            bean.setValue(data[i]);
            lists.add(bean);

            //绘制小圆点
            canvas.drawCircle(x, y, 10, valuePaint);
        }
        //先描边绘制
        path.close();
        valuePaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, valuePaint);

        valuePaint.setAlpha(127);
        //绘制填充区域
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, valuePaint);
    }


    //设置数值
    public void setData(double[] data) {
        this.data = data;
    }

    private float getStatusBarHeight() {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        float height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

        @Override
        public boolean onTouchEvent (MotionEvent event){
            float x = event.getX();
            float y = event.getY();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    for (int i = 0; i < lists.size(); i++) {
                        float valueX = lists.get(i).getX();
                        float valueY = lists.get(i).getY();
                        if ((valueX - 20) < x && x < (valueX + 20)) {
                            if ((valueY - 20) < y && y < (valueY + 20)) {
                                Toast.makeText(context, lists.get(i).getValue() + "", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    break;
                case MotionEvent.ACTION_MOVE:

                    break;
                case MotionEvent.ACTION_UP:

                    break;
            }
            return super.onTouchEvent(event);

        }
    }

