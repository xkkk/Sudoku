package com.mjxc.sudokuc.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：xk on 2018/5/10
 * 版本：v1.0
 * 描述：战力图
 */

public class ForceMapView extends View {


    private Paint mPaint;
    private int height = 1080;
    private int width = 1080;
    //标准边长（最内层）
    private final int LENGTH = 200;
    private final int OFFSET = 40;
    private final int STROKEWIDTH = 10;
    public ForceMapView(Context context) {
        this(context,null);
    }

    public ForceMapView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public ForceMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //正方形
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(STROKEWIDTH);
//        canvas.drawRect(0,0,LENGTH,LENGTH,mPaint);

        for (int i = 3; i >0 ; i--) {
            canvas.drawRect(OFFSET*(3-i),OFFSET*(3-i),OFFSET*(3-i)+LENGTH+OFFSET*i,OFFSET*(3-i)+LENGTH+OFFSET*i,mPaint);
        }




//        canvas.drawRect(100,100,220,220,mPaint);
//        canvas.drawRect(100,100,2400,2400,mPaint);

    }


//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        this.width = w;
//        this.height = height;
//        super.onSizeChanged(w, h, oldw, oldh);
//    }
}
