package com.mjxc.sudokuc.model;

/**
 * 作者：xk on 2018/5/9
 * 版本：v1.0
 * 描述：
 */

public class CheckpointBean {

    //关卡号
    private String id;
    //所用时间
    private String time;
    //状态；已通关，当前，未通关
    private String status;


    public CheckpointBean(String id, String time, String status) {
        this.id = id;
        this.time = time;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
