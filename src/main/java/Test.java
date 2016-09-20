import com.oooo.service.TestAopService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by Administrator on 2016/9/18.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        /*User user = context.getBean(User.class, "user");
        System.out.println(user.getId());*/
        TestAopService testAopService = context.getBean(TestAopService.class,"testAopService");
        testAopService.service();
        System.out.println(testAopService.getId());

       /* Reader reader = Resources.getResourceAsReader("Configuration.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sessionFactory.openSession();
        UserDao userDao = session.getMapper(UserDao.class);
        //User user = session.selectOne("com.oooo.dao.UserDao.getById",1);
        User user = userDao.getById(1);
        System.out.println(user.getName());
        user.setName("bbbbc");
        userDao.update(user);
        session.commit();*/
    }
}
