import com.gjxaiou.pojo.Flower;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author GJXAIOU
 * @create 2019-08-29-13:53
 */
public class FlowerTest {
    public static void main(String[] args) throws IOException {
        // 加载 xml 文件流
        InputStream is = Resources.getResourceAsStream("MyBatis.xml");
        //使用工厂设计模式
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        //生产SqlSession
        SqlSession session=factory.openSession();

        List<Flower> list = session.selectList("a.b.selAll");
        for (Flower flower : list) {
            System.out.println(flower.toString());
        }

        int count = session.selectOne("a.b.selById");
		System.out.println(count);

        //把数据库中哪个列的值当作map的key
        Map<Object, Object> map = session.selectMap("a.b.selectMap", "name");
        System.out.println(map);

        session.close();
    }
}
