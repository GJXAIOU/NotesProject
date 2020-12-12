package service;

import pojo.Flower;

import java.sql.SQLException;
import java.util.List;


/**
 * @author GJXAIOU
 * @create 2019-09-12-20:30
 */
public interface FlowerService {
    /**
     * @return Flower
     */
    List<Flower> show() throws SQLException;

    /**
     * @param flower
     * @return
     */
    int add(Flower flower) throws SQLException;
}
