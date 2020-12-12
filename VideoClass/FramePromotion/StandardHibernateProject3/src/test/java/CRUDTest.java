import com.gjxaiou.pojo.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

import java.io.Serializable;

/**
 * @author GJXAIOU
 * @create 2019-09-28-14:07
 */

public class CRUDTest {
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

    @Test
    public void selectTest(){
        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, 1);
        System.out.println(user);

        transaction.commit();

        session.close();
        sessionFactory.close();
    }

    @Test
    public void updateTest(){
        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, 1);
        user.setUsername("gjxaiou");
        session.update(user);

        transaction.commit();

        session.close();
        sessionFactory.close();
    }

    @Test
    public void deleteTest(){
        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, 1);
        session.delete(user);

        transaction.commit();

        session.close();
        sessionFactory.close();
    }

    @Test
    public void stateTest(){
        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        User user = new User();
        // 瞬时态对象：没有持久化标识 OID，没有被 session 管理

        user.setUsername("persistent");
        Serializable id = session.save(user);
        // 这里是持久态对象：有持久化标识 OID，被 session 管理

        transaction.commit();

        session.close();
        sessionFactory.close();
        System.out.println(user);
        // 这里是托管态对象：有持久化标识 OID，没有被 session 管理
    }
}
