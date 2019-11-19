package com.taotao.service;

import com.taotao.common.EUDataGridResult;
import com.taotao.pojo.TbItem;

/**
 * @author GJXAIOU
 * @create 2019-10-06-13:25
 */
public interface ItemService {
    TbItem getItemById(long itemId);
    EUDataGridResult getItemList(int page, int rows);
}
