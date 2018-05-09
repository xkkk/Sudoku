package com.mjxc.sudokuc.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.mjxc.sudokuc.R;

/**
 * 作者：xk on 2018/5/9
 * 版本：v1.0
 * 描述：
 */

public class TipsDialog extends Dialog {

    TextView mCancelIv;
    TextView mConfirmIv;
    Context context;
    OnBtnClickListener mListener;

    TipsDialog(@NonNull Context context) {
        super(context,R.style.dialog_custom);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置为居中
        setContentView(R.layout.dialog_pause);
        mCancelIv = findViewById(R.id.tv_cancel);
        mConfirmIv = findViewById(R.id.tv_confirm);
        WindowManager windowManager = ((Activity)context).getWindowManager();
        final Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth()*4/5; // 设置dialog宽度为屏幕的4/5
        lp.height = display.getWidth()*4/5;
        getWindow().setAttributes(lp);
        setCanceledOnTouchOutside(true);// 点击Dialog外部消失

        mCancelIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener!=null){
                    mListener.onCancel();
                }
                dismiss();
            }
        });

        mConfirmIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener!=null){
                    mListener.onConfirm();
                }
                dismiss();
            }
        });
    }


    public void setOnBtnListener(OnBtnClickListener mListener){
        this.mListener = mListener;
    }

    public interface OnBtnClickListener{
        void onCancel();
        void onConfirm();
    }
}
