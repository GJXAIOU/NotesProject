package com.gjxaiou.dao;

import com.gjxaiou.pojo.Flower;

import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-08-29-16:00
 */
public interface FlowerDao {
    /**
     * 查询所有数据
     * @return 查询结果
     */
    List<Flower> selectAll();

    /**
     *  新增数据
     * @param flower 输入对象
     * @return 修改的行数
     */
    int AddFlower(Flower flower);

}
