package com.gjxaiou.service.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gjxaiou.cache.JedisUtil;
import com.gjxaiou.dao.AreaDao;
import com.gjxaiou.entity.Area;
import com.gjxaiou.exception.AreaOperationException;
import com.gjxaiou.service.AreaService;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-10-31-16:08
 */
@Service
public class AreaServiceImpl implements AreaService {
    /**
     *  Service 层依赖于 Dao 层，同时使用 @Autowired 表示希望 Spring 容器在程序运行时候能后将 AreaDao 自动注入；
     */
    @Autowired
    private AreaDao areaDao;
    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Autowired
    private JedisUtil.Strings jedisStrings;

    private static Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);

    @Override
    @Transactional
    public List<Area> getAreaList() {
        // key
        String key = AREA_LIST_KEY;
        List<Area> areaList = null;
        ObjectMapper mapper = new ObjectMapper();
        // 如果Redis中未存在key
        if (!jedisKeys.exists(key)) {
            // 数据库中获取区域列表
            areaList = areaDao.queryArea();
            String jsonString = null;
            // 将list转换为String
            try {
                jsonString = mapper.writeValueAsString(areaList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw new AreaOperationException(e.getMessage());
            }
            jedisStrings.set(key, jsonString);
        }
        // Redis中存在key，则取出
        else {
            // 将String转换为List
            String jsonString = jedisStrings.get(key);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Area.class);
            try {
                areaList = mapper.readValue(jsonString, javaType);
            } catch (JsonParseException e) {
                e.printStackTrace();
                throw new AreaOperationException(e.getMessage());
            } catch (JsonMappingException e) {
                e.printStackTrace();
                throw new AreaOperationException(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                throw new AreaOperationException(e.getMessage());
            }
        }
        return areaList;
    }
}
