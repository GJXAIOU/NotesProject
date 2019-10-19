package com.gjxaiou.service.impl;

import com.gjxaiou.dao.AreaDao;
import com.gjxaiou.entity.Area;
import com.gjxaiou.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-10-17-9:18
 */
@Service
public class AreaServiceImpl implements AreaService {
    // 因为 service 层依赖 Dao 层，因此需要定义一个 Dao
    // 表示希望 Spring 容器在程序运行的时候自动将 AreaDao 的实现注入其中
    @Autowired
    private AreaDao areaDao;
    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }
}
