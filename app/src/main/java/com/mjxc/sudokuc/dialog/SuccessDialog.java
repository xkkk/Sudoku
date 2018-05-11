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
import android.widget.ImageView;
import android.widget.TextView;

import com.mjxc.sudokuc.R;

/**
 * 作者：xk on 2018/5/11
 * 版本：v1.0
 * 描述：
 */

public class SuccessDialog extends Dialog {
    private Context context;
    private OnSuccessDialogBtnListener mListener;
    private String curTime ;

    public SuccessDialog(@NonNull Context context,String curTime) {
        super(context,R.style.dialog_custom);
        this.context = context;
        this.curTime = curTime;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置为居中
        setContentView(R.layout.dialog_success);

        WindowManager windowManager = ((Activity)context).getWindowManager();
        final Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        lp.width = display.getWidth()*4/5; // 设置dialog宽度为屏幕的4/5
//        lp.height = display.getWidth()*4/5;
        getWindow().setAttributes(lp);
        setCanceledOnTouchOutside(true);// 点击Dialog外部消失

        TextView tv_cur = findViewById(R.id.tv_curtime);
        TextView tv_his = findViewById(R.id.tv_histime);
        ImageView iv_select = findViewById(R.id.iv_select);
        ImageView iv_home = findViewById(R.id.iv_home);
        ImageView iv_continue = findViewById(R.id.iv_continue);
        ImageView iv_share = findViewById(R.id.iv_share);
        tv_cur.setText(curTime);
        tv_his.setText(curTime);
        iv_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener!=null){
                    mListener.onSelectHurdle();
                }
                dismiss();
            }
        });

        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener!=null){
                    mListener.onHome();
                }
                dismiss();
            }
        });
        iv_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener!=null){
                    mListener.onNext();
                }
                dismiss();
            }
        });

        iv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener!=null){
                    mListener.share();
                }
                dismiss();
            }
        });
    }




    public void setOnBtnListener(OnSuccessDialogBtnListener mListener){
        this.mListener = mListener;
    }

   public interface OnSuccessDialogBtnListener{
       void  onSelectHurdle();
       void onHome();
       void onNext();
       void share();
    }
}
