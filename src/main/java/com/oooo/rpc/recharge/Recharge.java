package com.oooo.rpc.recharge;

/**
 * Created by chenpan on 17-1-16.
 */
public class Recharge {
    private long accountId;
    private int diamondNum;

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public int getDiamondNum() {
        return diamondNum;
    }

    public void setDiamondNum(int diamondNum) {
        this.diamondNum = diamondNum;
    }
}
