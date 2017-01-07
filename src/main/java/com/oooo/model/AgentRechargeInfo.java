package com.oooo.model;

import java.util.Date;

/**
 * Created by chenpan on 17-1-4.
 */
public class AgentRechargeInfo {
    private int id;
    private int userId;//me
    private int rechargeNum;
    private int sendNum;
    private int rechargeId;//other
    private Date time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRechargeNum() {
        return rechargeNum;
    }

    public void setRechargeNum(int rechargeNum) {
        this.rechargeNum = rechargeNum;
    }

    public int getSendNum() {
        return sendNum;
    }

    public void setSendNum(int sendNum) {
        this.sendNum = sendNum;
    }

    public int getRechargeId() {
        return rechargeId;
    }

    public void setRechargeId(int rechargeId) {
        this.rechargeId = rechargeId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
