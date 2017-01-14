import com.google.common.collect.Maps;
import com.oooo.util.HttpUtil;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by chenpan on 17-1-13.
 */
public class TestHttp {
    @org.junit.Test
    public void test() throws UnsupportedEncodingException {
        System.out.println(123);
        Map params = Maps.newHashMap();
        params.put("name","aaa");
        HttpUtil.sendPost("http://localhost:8080/hessian/zoneInfo.hpp", params,"utf-8");
//        HttpUtil.sendGet("http://localhost:8080/hessian/zoneInfo.hpp","utf-8");
    }
}
