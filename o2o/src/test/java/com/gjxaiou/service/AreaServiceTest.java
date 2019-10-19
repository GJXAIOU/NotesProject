package com.gjxaiou.service;

import com.gjxaiou.BaseTest;
import com.gjxaiou.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author GJXAIOU
 * @create 2019-10-17-9:26
 */
public class AreaServiceTest extends BaseTest {
    // 当 AreaServiceTest 类需要 areaService，在运行的时候，spring就会自动注入其实现类；（其实现类上有 @Service 标签）
    @Autowired
    private AreaService areaService;

    @Test
    public void getAreaList(){
        List<Area> areaList = areaService.getAreaList();
        // 排序之后看第一个是不是 西苑
        assertEquals("西苑",areaList.get(0).getAreaName());
    }
}
