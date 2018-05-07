package com.mjxc.sudokuc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * 作者：xk on 2018/3/10
 * 版本：v1.0
 * 描述：
 */

public class MainActivity extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_simple).setOnClickListener(this);
        findViewById(R.id.btn_normal).setOnClickListener(this);
        findViewById(R.id.btn_hard).setOnClickListener(this);
        findViewById(R.id.help).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){

            case R.id.btn_hard:
                intent = new Intent(MainActivity.this,GameActivity.class);
                intent.putExtra(GameActivity.LEVEL,"2");
                break;
            case R.id.btn_normal:
                intent = new Intent(MainActivity.this,GameActivity.class);
                intent.putExtra(GameActivity.LEVEL,"1");
                break;
            case R.id.btn_simple:
                intent = new Intent(MainActivity.this,GameActivity.class);
                intent.putExtra(GameActivity.LEVEL,"0");
                break;
            case R.id.help:
                intent = new Intent(MainActivity.this,HelpActivity.class);
                break;
        }
        if(intent!=null){
            startActivity(intent);
        }
    }
}
