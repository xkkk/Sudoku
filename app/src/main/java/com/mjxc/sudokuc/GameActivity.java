package com.mjxc.sudokuc;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mjxc.sudokuc.dialog.DialogUtil;
import com.mjxc.sudokuc.dialog.SuccessDialog;
import com.mjxc.sudokuc.dialog.TipsDialog;
import com.mjxc.sudokuc.widget.SudokuView;

import cn.waps.AppConnect;

/**
 * 作者：xk on 2017/8/7
 * 版本：v1.0
 * 描述：
 */

public class GameActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String LEVEL = "level";//游戏等级 简单：0  中等：1  困难：2  骨灰：3
    public static final String CHECKPOINT = "checkpoint";
    private String level = "0";
    private int checkpoint = 1;
    private Chronometer mChronometer;
    private long mRecordTime = 0L;
    private boolean isStart = false;
    private SuccessDialog  mAlertDialog;
    private TextView mPauseTv;
    private TextView mReplayTv;
    private SudokuView mSudokuView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        if(getIntent()!=null){
            level = getIntent().getStringExtra(LEVEL);
            checkpoint = getIntent().getIntExtra(CHECKPOINT,1);
        }
        mSudokuView = findViewById(R.id.sudokuview);
        mPauseTv = findViewById(R.id.pause);
        mPauseTv.setOnClickListener(this);
        mReplayTv = findViewById(R.id.replay);
        mReplayTv.setOnClickListener(this);
        LinearLayout adlayout =findViewById(R.id.AdLinearLayout);
        AppConnect.getInstance(this).showBannerAd(this, adlayout);
        mChronometer = findViewById(R.id.timer);
        mSudokuView.setLevel(Integer.valueOf(level),checkpoint);
        findViewById(R.id.btn_check).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSudokuView.check(!mSudokuView.isCheck);
            }
        });
        mSudokuView.setOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(final int levelUp) {
                checkpoint ++;
                mChronometer.stop();
                onFinish(checkpoint, mSudokuView);

            }
        });
        mSudokuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isStart){
                    isStart = true;
                    mChronometer.setBase(SystemClock.elapsedRealtime());
                    mChronometer.start();
                }
            }
        });
    }

    private void onFinish(final int levelUp, final SudokuView view) {
        if(mAlertDialog==null){
             mAlertDialog =  DialogUtil.showNextDialog(GameActivity.this, mChronometer.getText().toString(), new SuccessDialog.OnSuccessDialogBtnListener() {
                @Override
                public void onSelectHurdle() {
                    finish();
                }

                @Override
                public void onHome() {
                    finish();
                }

                @Override
                public void onNext() {
                    view.setLevel(Integer.valueOf(level), levelUp);
                    resetView();
                }

                @Override
                public void share() {
                    Toast.makeText(GameActivity.this,"分享给好友",Toast.LENGTH_SHORT).show();
                }
            });
        }
        if(!mAlertDialog.isShowing()){
            mAlertDialog.show();
        }
    }

    private void resetView(){
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mRecordTime = 0L;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DialogUtil.showExitDialog(this,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pause:
                mRecordTime = SystemClock.elapsedRealtime();
                mChronometer.stop();
                DialogUtil.showPauseDialog(GameActivity.this, "暂停游戏", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(mRecordTime!=0){
                            mChronometer.setBase(mChronometer.getBase()+(SystemClock.elapsedRealtime() - mRecordTime));
                            mChronometer.start();
                        }
                    }
                });
                break;
            case R.id.replay:
                DialogUtil.showReplayDialog(GameActivity.this, new TipsDialog.OnBtnClickListener() {
                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onConfirm() {
                        mSudokuView.setLevel(Integer.valueOf(level), checkpoint);
                       resetView();
                    }
                });
                break;
        }
    }
}

