package com.gjxaiou.service;

import com.gjxaiou.pojo.People;

import java.io.IOException;
import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-08-30-10:44
 */
public interface PeopleService {
    /**
     * @return 数据库中所有数据
     */
    List<People> selectAll() throws IOException;
}
