import com.oooo.model.BroadCast;
import com.oooo.model.Permissions;
import com.oooo.service.*;
import com.oooo.util.Constant;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    BroadCastService broadCastService;
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
        Constant.getInstance().initPermissionMap();
        Map<String, Permissions> permissionMap = Constant.getInstance().getPermissionsUrlMap();

        List<Map.Entry<String, Permissions>> myPermissionsMap =
                permissionMap.entrySet().stream().filter((e) -> (e.getValue().getLevel() < 8)).collect(Collectors.toList());
        System.out.println(myPermissionsMap);
    }


    @org.junit.Test
    public void testBroadCast(){
        BroadCast broadCast = new BroadCast();
        broadCast.setContent("fafafsaf");
        broadCast.setDesc("dfafasf");
        broadCastService.add(broadCast);
        BroadCast a = broadCastService.findById(1);
        a.setContent("bbbbbbbbbbb");
        broadCastService.update(a);
        System.out.println(a);
    }
}
