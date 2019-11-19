package com.gjxaiou.dao;

import com.gjxaiou.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-10-31-20:13
 */
public interface ShopDao {
    /**
     * 通过 shopId 查询店铺
     * @param shopId
     * @return
     */
    Shop queryByShopId(long shopId);

    /**
     * 返回与该店铺状态相同的店铺数
     * @param shopCondition
     * @return
     */
    int queryShopCount(@Param("shopCondition") Shop shopCondition);

    /**
     * 新增店铺
     * @param shopDao
     * @return 影响的行数
     */
    int insertShop(Shop shopDao);

    /**
     *  更新店铺
     * @param shop
     * @return
     */
    int updateShop(Shop shop);

    /**
     * 带有分页功能的查询商铺列表 。 可输入的查询条件：商铺名（要求模糊查询） 区域Id 商铺状态 商铺类别 owner
     * (注意在sqlmapper中按照前端入参拼装不同的查询语句)
     * @param shopCondition
     * @param rowIndex：从第几行开始取
     * @param pageSize：返回多少行数据（页面上的数据量）
     *            比如 rowIndex为1,pageSize为5 即为 从第一行开始取，取5行数据
     * @return
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition,
                             @Param("rowIndex") int rowIndex,
                             @Param("pageSize") int pageSize);

}
