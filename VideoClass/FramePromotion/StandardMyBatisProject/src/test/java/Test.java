import com.gjxaiou.mapper.PeopleMapper;
import com.gjxaiou.pojo.Flower;

import com.gjxaiou.pojo.People;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;


import	java.util.Iterator;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author GJXAIOU
 * @create 2019-09-22-11:05
 */
public class Test {
    public static Logger logger = Logger.getLogger(Test.class);
    public static void main(String[] args) throws IOException {

        // 首先加载配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("MyBatis.xml");

        // 使用工厂设计模式，创建工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        // 生产 sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

//        Flower flower = new Flower();
//        flower.setId(2);
//
//        // 接收根据 Id 返回的对象
//        List<Flower> flowerList = sqlSession.selectList("MyFlower.selectAll");
//        Iterator<Flower> iterator = flowerList.iterator();
//        while (iterator.hasNext()){
//            System.out.println(((Flower)iterator.next()).toString());
//        }
//
//         // 获取同样 Id 数据数目
//        int countFlower = sqlSession.selectOne("MyFlower.countSelectById", flower);
//        System.out.println(countFlower);
//
//        //
//        Map<Object, Object> newNameMap = sqlSession.selectMap("MyFlower.selectByColumn", "newName");
//        System.out.println(newNameMap);
//
//
//
//        int insert = sqlSession.insert("MyFlower.insert", flower);
//        if (insert != 0){
//            System.out.println("插入成功");
//        }
//
//
//        flower.setId(3);
//        flower.setName("白玉兰");
//        int update = sqlSession.update("MyFlower.update", flower);
//        sqlSession.commit();


        PeopleMapper peopleMapper = sqlSession.getMapper(PeopleMapper.class);
        List<People> peopleList = peopleMapper.selectAll();
        Iterator<People> peopleIterator = peopleList.iterator();
        if (peopleIterator.hasNext()){
            System.out.println((People) peopleIterator.next());
        }
        System.out.println("------");


        // 多参数
        PeopleMapper mapper = sqlSession.getMapper(PeopleMapper.class);
        List<People> peopleList1 = mapper.selectByGenderAndScore("男", 99);
        Iterator<People> iterator2 = peopleList1.iterator();
        if (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }
        System.out.println("----------");


        // 动态 SQL
        List<People> peopleList2 = mapper.DynamicSelect("男", 76);
        Iterator<People> iterator3 = peopleList2.iterator();
        if (iterator3.hasNext()){
            System.out.println((People)iterator3.next());
        }
        System.out.println("------------");

        for (People people : peopleList2) {
            System.out.println(people);
        }


        sqlSession.close();

    }
}
