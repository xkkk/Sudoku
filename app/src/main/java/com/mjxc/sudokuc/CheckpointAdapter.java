package com.mjxc.sudokuc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mjxc.sudokuc.model.CheckpointBean;

import java.util.List;

/**
 * 作者：xk on 2018/5/9
 * 版本：v1.0
 * 描述：
 */

public class CheckpointAdapter extends RecyclerView.Adapter<CheckpointAdapter.ViewHolder> {

    private Context mContext;
    private List<CheckpointBean> mList;
    private OnItemClickListener mListener;
    public CheckpointAdapter(Context context, List<CheckpointBean> mList) {
        this.mContext = context;
        this.mList = mList;
    }

    @Override
    public CheckpointAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_checkpoint,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CheckpointAdapter.ViewHolder holder, final int position) {
        CheckpointBean item = mList.get(position);
        holder.pointTv.setText(""+item.getCheckpoint());
        holder.timeTv.setText(item.getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener!=null){
                    mListener.onItemClick(position);
                }
            }
        });
        if("CUR".equals(item.getStatus())){
            holder.mLinearLayout.setBackgroundResource(R.mipmap.checkpoint_cur);
        }else {
            holder.mLinearLayout.setBackgroundResource(R.mipmap.checkpoint_pass);
        }
        if("YES".equals(item.getStatus())){
            holder.passIv.setVisibility(View.VISIBLE);
        }else{
            holder.passIv.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }

    public void setItemClickListener(OnItemClickListener mListener){
        this.mListener = mListener;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView pointTv;
        private TextView timeTv;
        private LinearLayout mLinearLayout;
        private ImageView passIv;

        public ViewHolder(View itemView) {
            super(itemView);
            pointTv = itemView.findViewById(R.id.text);
            timeTv = itemView.findViewById(R.id.tv_time);
            mLinearLayout = itemView.findViewById(R.id.ll_bg);
            passIv = itemView.findViewById(R.id.iv_pass);
        }
    }

    interface OnItemClickListener{
        void onItemClick(int position);
    }
}
