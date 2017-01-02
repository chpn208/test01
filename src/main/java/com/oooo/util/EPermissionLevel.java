package com.oooo.util;

/**
 * Created by chenpan on 17-1-1.
 */
public enum EPermissionLevel {
    AGENT_LEVEL_1(1,10),
    AGENT_LEVEL_2(2,9),
    AGENT_LEVEL_3(3,8),
    AGENT_LEVEL_4(4,7),
    AGENT_LEVEL_5(5,6)
    ;
    private int level;
    private int level_value;//对应数据库中的值

    private EPermissionLevel(int level,int level_value){
        this.level = level;
        this.level_value = level_value;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel_value() {
        return level_value;
    }

    public void setLevel_value(int level_value) {
        this.level_value = level_value;
    }
}
