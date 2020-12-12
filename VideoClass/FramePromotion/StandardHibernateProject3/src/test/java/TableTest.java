import com.gjxaiou.pojo.Customer;
import com.gjxaiou.pojo.LinkMan;
import com.gjxaiou.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

/**
 * @author GJXAIOU
 * @create 2019-09-28-20:48
 */
public class TableTest {
    @Test
    public void createOneToMany(){
        Session session = HibernateUtil.getSessionObject();
        Transaction transaction = session.beginTransaction();

        // 创建一个客户
        Customer customer = new Customer();
        customer.setCustName("Alibaba");
        customer.setCustLevel("100");
        customer.setCustMobile("110");

        // 创建两个联系人
        LinkMan linkMan1 = new LinkMan();
        linkMan1.setLkmName("GJXAIOU");
        LinkMan linkMan2 = new LinkMan();
        linkMan2.setLkmName("gjxaiou");
        linkMan2.setLkmGender("man");

        // 建立关系
        customer.getSetLinkMan().add(linkMan1);
        customer.getSetLinkMan().add(linkMan2);
        linkMan1.setCustomer(customer);
        linkMan2.setCustomer(customer);

        session.save(customer);
        session.save(linkMan1);
        session.save(linkMan2);

        transaction.commit();
    }

    @Test
    public void createTable(){
        Session session = HibernateUtil.getSessionObject();
        Transaction transaction = session.beginTransaction();

        transaction.commit();
    }
}
