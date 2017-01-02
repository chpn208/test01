package com.oooo.dao;

import com.oooo.model.RechargeSend;

import java.util.List;

/**
 * Created by chenpan on 16-12-31.
 */
public interface RechargeSendDao {
    public List<RechargeSend> getRechargeSends();
    public RechargeSend updateRechargeSend(RechargeSend rechargeSend);
}
