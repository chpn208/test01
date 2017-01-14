import com.google.common.collect.Maps;
import com.oooo.model.AgentRechargeInfo;
import com.oooo.model.Notice;
import com.oooo.model.PlayerRechargeInfo;
import com.oooo.service.AgentService;
import com.oooo.service.NoticeService;
import com.oooo.service.PlayerRechargeInfoService;
import com.oooo.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by chenpan on 17-1-4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationContext.xml")
public class TestContext {
    @Autowired
    PlayerRechargeInfoService playerRechargeInfoService;
    @Autowired
    UserService userService;

    @Autowired
    AgentService agentService;

    @Autowired
    NoticeService noticeService;
    @org.junit.Test
    public void test(){
       /* Map<String,Integer> parameterMap = Maps.newHashMap();
       *//* parameterMap.put("playerId",111);
        parameterMap.put("startNum",0);
        parameterMap.put("endNum",10);*/
        //playerRechargeInfoService.getUsers();
        userService.findById(1);
/*
        Map<String,Integer> parameterMap = Maps.newHashMap();
        parameterMap.put("playerId",1112);
        parameterMap.put("startNum",0);
        parameterMap.put("endNum",10);
        //playerRechargeInfoService.getUsers();
        

        List<PlayerRechargeInfo> a = playerRechargeInfoService.getByPage(parameterMap);
        int count = playerRechargeInfoService.getCount(parameterMap);
        System.out.println(count);*/

        Notice a = noticeService.findByType(1);
        System.out.println(a.getContent());
        a.setContent("sfafsdk");
        noticeService.update(a);
        System.out.println(a.getContent());

    }
}
