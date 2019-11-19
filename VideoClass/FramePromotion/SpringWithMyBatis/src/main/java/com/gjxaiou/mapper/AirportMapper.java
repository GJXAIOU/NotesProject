package com.gjxaiou.mapper;

import com.gjxaiou.pojo.Airport;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-09-23-18:28
 */
public interface AirportMapper {
    @Select("select * from airport")
    List<Airport> selAll();
}
