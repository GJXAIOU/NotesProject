package com.gjxaiou.dao;

import com.gjxaiou.entity.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author GJXAIOU
 * @create 2019-11-05-15:31
 */

public interface HeadLineDao {
    /**
     * 根据传入的头条名字查询
     * @param headLineCondition
     * @return
     */
    List<HeadLine> queryHeadLine(@Param("headLineCondition") HeadLine headLineCondition);

    /**
     * 添加首页头条
     *
     * @param headLine
     * @return
     */
    int insertHeadLine(HeadLine headLine);


    /**
     * 根据Id列表查询头条信息列表
     *
     * @param lineIdList
     * @return
     */
    List<HeadLine> selectHeadLineByIds(List<Long> lineIdList);

    /**
     * 根据头条Id查询头条信息
     *
     * @param lineId
     * @return
     */
    HeadLine selectHeadLineById(long lineId);

    /**
     * 更新头条信息
     *
     * @param headLine
     * @return
     */
    int updateHeadLine(HeadLine headLine);

    /**
     * 根据Id删除头条
     *
     * @param headLineId
     * @return
     */
    int deleteHeadLine(long headLineId);

    /**
     * 批量删除头条记录
     *
     * @param lineIdList
     * @return
     */
    int batchDeleteHeadLine(List<Long> lineIdList);
}
