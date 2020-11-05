import com.gui.dao.UserDao;
import com.gui.domain.QueryVo;
import com.gui.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MybatisTest {
    private InputStream is;
    private SqlSession session;
    private UserDao userDao;

    @Before//用于在测试方法之前执行
    public void init() throws IOException {
        //1.读取配置文件
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        //3.获取SqlSession对象
        session = ssf.openSession();
        //4.获取dao的代理对象
        userDao = session.getMapper(UserDao.class);
    }
    @Test
    public void testFindAll() throws Exception{
            /*//1.读取配置文件
            InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
            //2.创建SqlSessionFactory工厂
            SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
            SqlSessionFactory ssf = ssfb.build(inputStream);
            //3.使用工厂生产SqlSession对象
            SqlSession session = ssf.openSession();
            //4.使用SqlSession创建Dao接口的代理对象
            UserDao userDao =session.getMapper(UserDao.class);*/
            //5.使用代理对象执行方法(代理模式)
            List<User> users = userDao.findAll();
            for (User user: users){
                System.out.println(user);
            }
            /*//6.释放资源
            session.close();
            inputStream.close();*/
    }

    @After
    public void destroy() throws IOException {
        //提交事务
        session.commit();
        //6.释放资源
        session.close();
        is.close();
    }
    @Test
    public void testSaveUser() throws IOException {
        User user = new User();
        user.setUsername("蓝天白云");
        user.setAddress("北京市昌平区");
        user.setGender("f");
        user.setBirthday(new Date());

        //执行保存方法
        userDao.saveUser(user);
        /*
        * 在插入的过程中发生了回滚：Rolling back JDBC Connection；使得insert不会成功。
        * 所以要需要提交事务，让其生效，结果rolling back会变为：Committing JDBC Connection
        * */
        //session.commit();
    }

    @Test
    public void testUserUpdate(){
        User user = new User();
        user.setId(42);
        user.setUsername("肇源");
        user.setAddress("山西省太原市");

        userDao.userUpdate(user);
    }

    @Test
    public void testUserDelete(){

        userDao.userDelete(48);
    }
    @Test
    public void testFindById(){

        System.out.println(userDao.findById(42));
    }
    @Test
    public void testFindByName(){

        List<User> users = userDao.findByName("%蓝天%");
        /*for (int i = 0; i < users.toArray().length; i++){
            System.out.println(users.toArray()[i]);
        }*/
        for (User user: users) {
            System.out.println(user);
        }
    }
    @Test
    public void testFindTotal(){

        System.out.println(userDao.findTotal());
    }
    @Test
    public void testFindByVo(){
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("%蓝天%");
        vo.setUser(user);
        List<User> users = userDao.findUserByVo(vo);
        /*for (int i = 0; i < users.toArray().length; i++){
            System.out.println(users.toArray()[i]);
        }*/
        for (User user1: users) {
            System.out.println(user1);
        }
    }

    @Test
    public void testFindUserByCondition(){
        User user = new User();
        user.setUsername("蓝天白云");
        user.setId(47);
        user.setGender("m");

        List<User> list = userDao.findUserByCondition(user);

        for (User u: list) {
            System.out.println(u);
        }
    }
    @Test
    public void testFindUserInIds(){
        QueryVo vo = new QueryVo();
        List<Integer> list = new ArrayList<Integer>();
        list.add(42);
        list.add(46);
        //list.add(45);

        vo.setIds(list);

        List<User> users = userDao.findUserInIds(vo);

        for (User u: users) {
            System.out.println(u);
        }
    }
}
