package com.gjxaiou.dao;


import com.gjxaiou.entity.Area;

import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-10-29-21:53
 */
public interface AreaDao {
    /**
     *  列出所有的区域列表
     * @return
     */
    List<Area> queryArea();
}
