package com.mjxc.sudokuc;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import cn.waps.AppConnect;

/**
 * 作者：xk on 2017/8/7
 * 版本：v1.0
 * 描述：
 */

public class GameActivity extends AppCompatActivity {
    public static final String LEVEL = "level";//游戏等级 简单：0  中等：1  困难：2  骨灰：3
    private String level = "0";
    private boolean isCheck =false;
    private TextView textView;
    private Timer mTimer;
    private int cnt = 0;
    private AlertDialog  mAlertDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        if(getIntent()!=null){
            level = getIntent().getStringExtra(LEVEL);
        }
        final SudokuView view = (SudokuView) findViewById(R.id.sudokuview);
        LinearLayout adlayout =(LinearLayout)findViewById(R.id.AdLinearLayout);
        AppConnect.getInstance(this).showBannerAd(this, adlayout);
        textView = (TextView) findViewById(R.id.timer);
        view.setLevel(Integer.valueOf(level),1);
        findViewById(R.id.btn_check).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.check(!view.isCheck);
            }
        });
        view.setOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(final int levelUp) {
                mTimer.cancel();
                mTimer.purge();

                onFinish(levelUp, view);

            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimer == null) {
                    mTimer = new Timer();
                    mTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            mHandler.sendEmptyMessage(1);
                        }
                    }, 0, 1000);
                }
            }
        });
    }
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                String time = getStringTime(cnt++);
                Log.i("GameActivity","cnt = "+cnt);
                textView.setText(time);
            }
        }
    };

    private void onFinish(final int levelUp, final SudokuView view) {
        if(mAlertDialog==null) {
            AlertDialog.Builder  mBuilder = new AlertDialog.Builder(GameActivity.this);
            //    设置Title的内容
            mBuilder.setTitle("恭喜通关");
            //    设置Content来显示一个信息
            mBuilder.setMessage("是否开始下一局？");
            //    设置一个PositiveButton
            mBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    view.setLevel(Integer.valueOf(level), levelUp);
                    dialog.dismiss();
                    onViewSet();
                }
            });
            //    设置一个NegativeButton
            mBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            mAlertDialog = mBuilder.create();
        }
        //    显示出该对话框
        if(!mAlertDialog.isShowing()){
            mAlertDialog.show();
        }
    }

    private void onViewSet(){
        textView.setText("00:00:00");
        mTimer = null;
        cnt=0;
    }

    private String getStringTime(int cnt) {
        int hour = cnt/3600;
        int min = cnt % 3600 / 60;
        int second = cnt % 60;
        return String.format(Locale.CHINA,"%02d:%02d:%02d",hour,min,second);
    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        onViewSet();
        super.onDestroy();
    }
}

