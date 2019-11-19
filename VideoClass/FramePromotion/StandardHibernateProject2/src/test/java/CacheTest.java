import com.gjxaiou.pojo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

/**
 * @author GJXAIOU
 * @create 2019-09-28-18:59
 */
public class CacheTest {
    @Test
    public void checkCache(){
        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        User user1 = session.get(User.class, 2);
        System.out.println(user1);

        User user2 = session.get(User.class, 2);
        System.out.println(user2);

        System.out.println(user1.equals(user2));


        transaction.commit();

        session.close();
        sessionFactory.close();

    }
}
