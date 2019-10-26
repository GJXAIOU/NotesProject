package com.gjxaiou.dto;

import com.gjxaiou.entity.Shop;
import com.gjxaiou.enums.ShopStateEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 这里是用于在 service 层之前，是添加店铺的返回类型
 * 不能直接已有的实体类 shop，因为操作 shop 的时候必然有一个状态，例如成功？失败？都得记录并且返回给 controller 层处理的。
 * 一方面存储店铺的信息，同时存储其状态值
 * @author GJXAIOU
 * @create 2019-10-18-10:13
 */
@Getter
@Setter
public class ShopExecution {

    // 结果状态
    private int state;

    // 状态标识，解释上面 state 的含义
    private String stateInfo;

    // 店铺数量
    private int count;

    // 操作的shop（增删改店铺的时候用）
    private Shop shop;

    // 获取的shop列表(查询店铺列表的时候用)
    private List<Shop> shopList;

    public ShopExecution() {
    }

    // 店铺操作失败时候使用的构造器
    public ShopExecution(ShopStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    // 店铺操作成功的使用的构造器，返回 shop 对象和状态
    public ShopExecution(ShopStateEnum stateEnum, Shop shop) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shop = shop;
    }

    // 成功的构造器，返回  shop 列表和状态
    public ShopExecution(ShopStateEnum stateEnum, List<Shop> shopList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shopList = shopList;
    }
}
