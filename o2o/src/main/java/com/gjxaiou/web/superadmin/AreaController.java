package com.gjxaiou.web.superadmin;
import java.util.ArrayList;
import	java.util.HashMap;

import com.gjxaiou.entity.Area;
import com.gjxaiou.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author GJXAIOU
 * @create 2019-10-17-9:41
 * 超级管理员用于 controller 权限
 */
// 表明这个在 spring 容器下，这是一个 controller（spring 注解）
@Controller
// 设置调用的 URL 路径，用于调用该 controller 下的方法(springmvc 注解)
@RequestMapping("/superAdmin")
public class AreaController {
    Logger logger = LoggerFactory.getLogger(AreaController.class);

    // 创建 service 层实体类并交给 spring 管理，使用到 areaService 的时候将其实现类注入
    @Autowired
    private AreaService areaService;

    @RequestMapping(value="/listArea",method = RequestMethod.GET)
    // 将返回值 Map 转换为 JSON 格式，同时页面恒不跳转
    @ResponseBody
    private Map<String, Object> listArea(){
        // 首先在方法开始前可以记录开始时间
        logger.info("======start=======");
        long startTime = System.currentTimeMillis();

        // 存放方法的返回值
        Map<String, Object> modelMap = new HashMap<>();
        // 获取 service 层返回的区域列表
        List<Area> list = new ArrayList<Area>();

        try {
            list = areaService.getAreaList();
            modelMap.put("rows",list);
            modelMap.put("total", list.size());
        }catch (Exception e){
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }

        // 在方法结尾再次获取时间，这里有意的加入一些错误信息
        logger.error("test error");
        long endTime = System.currentTimeMillis();
        // {} 是占位符
        logger.debug("costTime:[{} ms]",endTime - startTime);
        logger.info("====end=====");

        return modelMap;
    }
}
