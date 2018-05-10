package com.mjxc.sudokuc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mjxc.sudokuc.widget.ForceMapView;

/**
 * 作者：xk on 2018/3/30
 * 版本：v1.0
 * 描述：
 */

public class RankActivity extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ForceMapView view = new ForceMapView(this);
        setContentView(view);

    }
}
