import com.gjxaiou.pojo.User;
import com.gjxaiou.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.type.spi.TypeConfigurationAware;
import org.junit.jupiter.api.Test;

/**
 * @author GJXAIOU
 * @create 2019-09-28-19:17
 */
public class TransactionTest {
    @Test
    public void StandardTransaction(){
        SessionFactory sessionFactory = null;
        Transaction transaction = null;
        Session session = null;

        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            User user = new User();
            user.setUsername("GJXaiou");
            user.setPassword("GJXaiou");
            user.setAddress("鼓楼");

            session.save(user);

            // int i = 10/0;

            // 提交事务
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
            // 事务回滚
            transaction.rollback();
        }finally {
            // 关闭操作
            session.close();
            sessionFactory.close();
        }
    }
}
