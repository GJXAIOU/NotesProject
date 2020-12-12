import com.gjxaiou.pojo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

/**
 * @author GJXAIOU
 * @create 2019-09-28-14:07
 */

public class AddTest {
    @Test
    public void addTest(){
        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        User user = new User();
        user.setUsername("GJXAIOU");
        user.setPassword("GJXAIOU");
        user.setAddress("江苏");
        session.save(user);

        transaction.commit();

        session.close();
        sessionFactory.close();
    }
}
