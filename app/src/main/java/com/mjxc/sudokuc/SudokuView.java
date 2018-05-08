package com.mjxc.sudokuc;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 作者：xk on 2017/8/7
 * 版本：v1.0
 * 描述：
 */

public class SudokuView extends View {

    private final String TAG = "SudokuView";
    /**
     * 单位格子宽，高
     */
    private float width;
    private float height;

    private Paint mPaint;

    /**
     * 题面
     */
    private String question = null;
    /**
     * 选中的格子
     */
    private Table mTable = null;

    /**
     * 坐标点
     */
    private Point mPoint = null;
    /**
     * 标准答案
     */
    private String answer = null;
    /**
     * 未完成的答案
     */
    private String tempAnswer = null;
    /**
     * 选中的某个数字
     */
    private char marketNum;
    private OnSuccessListener listener;


    public SudokuView(Context context) {
        this(context,null);
    }

    public SudokuView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public SudokuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //初始化绘制九宫格和初始数字
            for (int i = 0; i < 9; i++) {
                mPaint.setStrokeWidth(i % 3 == 0 ? 10 : 1);
                mPaint.setColor(getResources().getColor(R.color.table_line_color));
                canvas.drawLine(0, i * height, getWidth(), i * height, mPaint);
                canvas.drawLine(i * width, 0, i * width, getWidth(), mPaint);
            }
            mPaint.setStrokeWidth(10);
            canvas.drawLine(0, 9 * height, getWidth(), 9 * height, mPaint);

            //绘制底部9个数字
            for (int i = 0; i < 9; i++) {
                float cy = height * 9 + height / 2+50;
                float cx = width / 2 + width * i;
                mPaint.setStyle(Paint.Style.FILL);
                mPaint.setColor(getResources().getColor(R.color.ball_bg_color));
                canvas.drawCircle(cx, cy, width / 2, mPaint);
                mPaint.setColor(getResources().getColor(R.color.colorPrimary));
                mPaint.setTextSize(width * 0.6f);
                mPaint.setTypeface(Typeface.DEFAULT_BOLD);
                mPaint.setTextAlign(Paint.Align.CENTER);
                canvas.drawText(String.valueOf(i + 1), cx, cy + height / 4, mPaint);
            }


            //绘制棋盘上的数字
            char[] chars = question.toCharArray();
            char[] chars2 = tempAnswer.toCharArray();
            for (int i = 0; i < chars2.length; i++) {
                if (chars2[i] != '0') {
                    int row = i / 9;//行
                    int column = i % 9;//列
                    if(isCheck){
                        if(chars2[i]!=answer.toCharArray()[i]){
                            mPaint.setColor(getResources().getColor(R.color.colorAccent));
                            mPaint.setStyle(Paint.Style.FILL);
                            canvas.drawRect(column*width,row*height,column*width+width,row*height+height,mPaint);
                        }
                    }
                    if(chars2[i]!=chars[i]){//说明是新填入的数字
                        mPaint.setColor(getResources().getColor(R.color.num_color));
                    }else {
                        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
                    }
                    mPaint.setStyle(Paint.Style.FILL);
                    mPaint.setTextSize(60f);
                    //
                    canvas.drawText(String.valueOf(chars2[i]), column * width + width / 2, row * height + height / 2 + height/6 , mPaint);
                    if(chars2[i]==marketNum){
                        mPaint.setColor(getResources().getColor(R.color.choose_rect_color));
                        mPaint.setStyle(Paint.Style.STROKE);
                        canvas.drawRect(column*width,row*height,column*width+width,row*height+height,mPaint);
                    }
                }
            }


            //选择某一表格准备填数字
        if(mTable != null){
            mPaint.setColor(getResources().getColor(R.color.choose_rect_color));
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(mTable.column*width,mTable.row*height,mTable.column*width+width,mTable.row*height+height,mPaint);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if(y<9*height){//棋盘
            mTable = getLocation(x,y);
            invalidate();
        }else if(y>9*height&&y<10*height){//底部待选项
            mPoint = getNumber(x,y);
            int num = (int) mPoint.x;
            if(mTable!=null){
                setAnswer(num,mTable.row,mTable.column);
            }
        }
        return super.onTouchEvent(event);
    }

    private Point getNumber(float x, float y) {
       return new Point(x/width+1,y/height);
    }


    public void setOnSuccessListener( OnSuccessListener listener){
        this.listener = listener;
    }

    /**
     * w:当前view的宽度
     * h:当前view的高度
     *
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //计算当前单元格的宽度和高度
        this.width = w / 9f;
        this.height = w / 9f;
        Log.i(TAG,"width = "+width+",height = "+height);
        super.onSizeChanged(w, h, oldw, oldh);
    }



    public void setAnswer(int num,int row, int column){
        char[] chars = tempAnswer.toCharArray();
        int index = row*9+column;
        chars[index] = (char) (num+48);
        isCheck = false;
        tempAnswer = String.copyValueOf(chars);

        Log.i(TAG,"question = "+tempAnswer);
        Log.i(TAG,"chars = "+chars[index]);
        if(isSuccess()){
//            Toast.makeText(getContext(),"恭喜通关",Toast.LENGTH_SHORT).show();
            if(listener!=null){
                listener.onSuccess(2);
            }
        }
            invalidate();
    }

    public void setLevel(int level,int num){
        switch (level){
            case 0:
            case 1:
                setQuestion("simple",num);
                tempAnswer = question;
                break;
            case 2:
            case 3:
                setQuestion("hard",num);
                tempAnswer = question;
                break;
        }
        invalidate();
    }



    private void setQuestion(String levelStr, int num ){
        int resInt = getContext().getResources().getIdentifier("question_"+levelStr+"_"+num,"string","com.mjxc.sudokuc");
        int answerResInt = getContext().getResources().getIdentifier("answer_"+levelStr+"_"+num,"string","com.mjxc.sudokuc");
        question = getContext().getString(resInt);
        answer = getContext().getString(answerResInt);
        tempAnswer = question;
    }

    private Table getLocation(float x,float y){
        int row = (int) (y/height);
        int column = (int) (x/width);
        int index = row*9+column;
        marketNum = question.charAt(index);
        Log.i(TAG,"the number is "+marketNum);
        return marketNum=='0'?new Table(row,column):null;
    }


    class Point{
        private float x;
        private float y;

        public Point(float x,float y){
            this.x = x;
            this.y= y;
        }
    }

    class Table{
        private int  row;
        private int column;
        private String num;

        public Table(int row,int column){
            this.row = row;
            this.column= column;
        }
    }

    private boolean  isSuccess(){
        return tempAnswer.equals(answer);
    }

    boolean isCheck = false;
    public void check(boolean isCheck){
        this.isCheck = isCheck;
        invalidate();
    }

}
