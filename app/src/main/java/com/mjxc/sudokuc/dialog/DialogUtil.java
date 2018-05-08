package com.mjxc.sudokuc.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

/**
 * 作者：xk on 2018/5/8
 * 版本：v1.0
 * 描述：
 */

public class DialogUtil {


    /**
     * @param activity
     * @param firstListener
     * @param secondListener
     * 恭喜通关弹窗
     */
    public static void showNextDialog(Activity activity, final DialogInterface.OnClickListener firstListener, final DialogInterface.OnClickListener secondListener){
        AlertDialog.Builder  mBuilder = new AlertDialog.Builder(activity);
        //    设置Title的内容
        mBuilder.setTitle("恭喜通关");
        //    设置Content来显示一个信息
        mBuilder.setMessage("是否开始下一局？");
        //    设置一个PositiveButton
        mBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (firstListener != null) {
                    firstListener.onClick(dialog, which);
                }
            }
        });
        //    设置一个NegativeButton
        mBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (secondListener != null) {
                    secondListener.onClick(dialog, which);
                }
            }
        });
    }

    /**
     * @param activity
     * @param firstListener
     * @param secondListener
     * 暂停弹窗
     */
    public static void showPauseDialog(Activity activity, @NonNull String message, final DialogInterface.OnClickListener firstListener, final DialogInterface.OnClickListener secondListener){
        AlertDialog.Builder  mBuilder = new AlertDialog.Builder(activity);
        //    设置Title的内容
        mBuilder.setTitle("提示");
        //    设置Content来显示一个信息
        mBuilder.setMessage(message);
        //    设置一个PositiveButton
        mBuilder.setPositiveButton("回到主页", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (firstListener != null) {
                    firstListener.onClick(dialog, which);
                }
            }
        });
        //    设置一个NegativeButton
        mBuilder.setNegativeButton("回到游戏", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (secondListener != null) {
                    secondListener.onClick(dialog, which);
                }
            }
        });
        mBuilder.show();
    }

}
