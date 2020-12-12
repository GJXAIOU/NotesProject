package service.impl;

import dao.FlowerDao;
import dao.impl.FlowerDaoImpl;
import pojo.Flower;
import service.FlowerService;

import dao.FlowerDao;

import java.sql.SQLException;
import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-09-12-20:31
 */
public class FlowerServiceImpl implements FlowerService {
    private FlowerDao flowerDao = new FlowerDaoImpl();

    public List<Flower> show() throws SQLException {
        return flowerDao.selectAll();
    }
    public int add(Flower flower) throws SQLException {
        return flowerDao.addFlower(flower);
    }
}
