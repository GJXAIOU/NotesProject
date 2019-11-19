package dao;

import pojo.Flower;

import java.sql.SQLException;
import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-09-12-19:07
 */
public interface FlowerDao {


    /**
     * @return 查询的结果对象
     */
    List<Flower> selectAll() throws SQLException;

    /**
     * @param flower
     * @return
     */
    int addFlower(Flower flower) throws SQLException;


}
