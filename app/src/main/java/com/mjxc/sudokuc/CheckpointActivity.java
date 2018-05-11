package com.mjxc.sudokuc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mjxc.sudokuc.model.CheckpointBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：xk on 2018/3/30
 * 版本：v1.0
 * 描述：
 */

public class CheckpointActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<CheckpointBean> mList;
    private String level;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_checkpoint);
        mRecyclerView = findViewById(R.id.recyclerview);
        if(getIntent()!=null){
            level = getIntent().getStringExtra(GameActivity.LEVEL);
        }
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        createData();
        CheckpointAdapter checkpointAdapter = new CheckpointAdapter(this,mList);
        mRecyclerView.setAdapter(checkpointAdapter);
        checkpointAdapter.setItemClickListener(new CheckpointAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                int id = mList.get(position).getCheckpoint();
//                Toast.makeText(CheckpointActivity.this,"第"+id+"关",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CheckpointActivity.this,GameActivity.class);
                intent.putExtra(GameActivity.LEVEL,level);
                intent.putExtra(GameActivity.CHECKPOINT,id);
                startActivity(intent);
            }
        });
    }

    private void createData(){
        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CheckpointBean bean = new CheckpointBean(i+1,"00:00","NO");
            mList.add(bean);
        }
    }
}
