package com.gjxaiou.mapper;
import	java.util.List;

import com.gjxaiou.pojo.People;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-09-22-20:28
 */
public interface PeopleMapper {
    List<People> selectAll();

    List<People> selectByGenderAndScore(String gender, int score);

    List<People> selectByGenderAndScore1(@Param("gender") String gender, @Param("score") int score);

    List<People> DynamicSelect(@Param("gender") String gender, @Param("score") int score);
}
