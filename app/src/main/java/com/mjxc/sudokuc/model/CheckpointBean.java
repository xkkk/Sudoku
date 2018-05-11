package com.mjxc.sudokuc.model;

import org.litepal.crud.DataSupport;

/**
 * 作者：xk on 2018/5/9
 * 版本：v1.0
 * 描述：
 */

public class CheckpointBean extends DataSupport{

    //关卡号
    private int checkpoint;
    //所用时间
    private String time;
    //状态；已通关，当前，未通关
    private String status;
    private String question;
    private String answer;
    /**
     * 难度等级
     */
    private String level;


    public int getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(int checkpoint) {
        this.checkpoint = checkpoint;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public CheckpointBean(int checkpoint, String time, String status) {
        this.checkpoint = checkpoint;
        this.time = time;
        this.status = status;
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
