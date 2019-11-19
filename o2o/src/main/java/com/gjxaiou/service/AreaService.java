package com.gjxaiou.service;

import com.gjxaiou.entity.Area;

import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-10-31-16:06
 */
public interface AreaService {
    public static final String AREA_LIST_KEY = "areaList";
    List<Area> getAreaList();
}
