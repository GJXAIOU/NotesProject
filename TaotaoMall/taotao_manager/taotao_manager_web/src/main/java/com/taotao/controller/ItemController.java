package com.taotao.controller;

import com.taotao.common.EUDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author GJXAIOU
 * @create 2019-10-06-13:35
 */

/**
 * 加上该注解，就可以将该类交给 springmvc 容器去管理
 */
@Controller
public class ItemController {
    /**
     * 这里使用 spring 中的 @Autowired 按照类型进行自动装配；
     *      含义等效于：ItemService itemService = new ItemServiceImpl();
     * 如果有多个实现类，则首先根据类型匹配，然后根据名称，可以将对象名改为对应实现类的名字即可区分；
      */

    @Autowired
    private ItemService itemService;

    /**
     * 这里使用 restful 传值方式：@RequestMapping中一定和请求的格式是对应的，
     * 这里使用 @PathVariable 默认按照参数名称去寻找，如果传入参数名称和控制器中参数不同，使用@PathVariable("传入的参数名")
     */
    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId){
        TbItem tbItem = itemService.getItemById(itemId);
        return tbItem;
    }

    /**
     * 在方法上面加入：@ResponseBody 表示恒不跳转；
     *     如果返回值满足：key-value 形式（即返回值为对象或者 map），会自动将响应头设置成为：application/json;charset=utf-8
     *          将转换之后的内容以输出流的形式返回；
     *     如果返回值不满足：key-value 形式（例如返回 string ），会自动将响应头设置成为：text/html；
     *          将方法的返回值以流的形式直接输出；
     */
    @RequestMapping("/item/list")
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows) {
        EUDataGridResult result = itemService.getItemList(page, rows);
        return result;
    }

}
