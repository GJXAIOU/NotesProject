package com.gjxaiou.dao;

import com.gjxaiou.entity.Area;

import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-10-12-14:21
 */

public interface AreaDao {
    /**
     * 列出区域列表
     * @return areaList
     */
    List<Area> queryArea();
}
