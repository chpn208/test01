package com.oooo.service;

import com.oooo.dao.RechargeSendDao;
import com.oooo.model.RechargeSend;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by chenpan on 16-12-31.
 */
public class RechargeSendService {
    @Autowired
    RechargeSendDao rechargeSendDao;

    public List<RechargeSend> getRechargeSends(){
        List<RechargeSend> rechargeSends = rechargeSendDao.getRechargeSends();
        return rechargeSends;
    }

    public RechargeSend updateRechargeSend(RechargeSend rechargeSends){
        return rechargeSendDao.updateRechargeSend(rechargeSends);
    }

}
