import com.google.inject.internal.Maps;
import com.oooo.model.PlayerRechargeInfo;
import com.oooo.model.User;
import com.oooo.service.PlayerRechargeInfoService;
import com.oooo.service.UserService;
import org.apache.commons.lang3.RandomUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
    @org.junit.Test
    public void test(){
       /* Map<String,Integer> parameterMap = Maps.newHashMap();
       *//* parameterMap.put("playerId",111);
        parameterMap.put("startNum",0);
        parameterMap.put("endNum",10);*/
        //playerRechargeInfoService.getUsers();
        int[] numbers = new int[]{100,200,500};
        for (int i = 0; i < 100; i++) {
            PlayerRechargeInfo info = new PlayerRechargeInfo();
            info.setAgentId(71118);
            info.setPlayerId(1112);
            int random = RandomUtils.nextInt(0,3);
            int number = numbers[random];
            info.setRechargeNum(number);
            int sendNum = number/10;
            info.setSendNum(sendNum);
            info.setRechargeTime(new Date());
            playerRechargeInfoService.add(info);
        }

        List<PlayerRechargeInfo> a = playerRechargeInfoService.getAll();
        System.out.println(a.size());

    }
}
