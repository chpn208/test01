import jdk.nashorn.internal.runtime.ListAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/9/18.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        List<String> a = Arrays.asList("aaaa", "bb", "ccc");
        System.out.println(a);
        /*a.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });*/
        a.sort((e1,e2)-> {return e1.length() - e2.length();});
        a.forEach((String b) -> {
            System.out.println(b);
        });

        Arrays.asList(new String[]{"a","b","c"}).sort((String x,String b)->{
            return x.length() - b.length();
        });


        Runnable r = ()-> System.out.println("123");
        Executors.callable(()-> System.out.println(123));
    }

    public static int add(int a,int b){
        return a+b;
    }
}

