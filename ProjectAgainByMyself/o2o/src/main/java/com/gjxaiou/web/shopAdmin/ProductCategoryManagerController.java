package com.gjxaiou.web.shopAdmin;


import com.gjxaiou.dto.ProductCategoryExecution;
import com.gjxaiou.dto.Result;
import com.gjxaiou.entity.ProductCategory;
import com.gjxaiou.entity.Shop;
import com.gjxaiou.enums.ProductCategoryStateEnum;
import com.gjxaiou.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GJXAIOU
 * @create 2019-11-04-15:45
 */
@Controller
@RequestMapping("/shopAdmin")
public class ProductCategoryManagerController {
    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 获取该店铺下所有商品类别
     * @param request
     * @return
     */
    @RequestMapping(value="/getProductCategoryList", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request){
        // 存放结果
        List<ProductCategory> productCategoryList = null;
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");

        if (currentShop != null & currentShop.getShopId() != 0){
            productCategoryList =
                    productCategoryService.getProductCategoryList(currentShop.getShopId());
            return new Result<>(true,productCategoryList);
        }else {
           return new Result<>(false,ProductCategoryStateEnum.INNER_ERROR.getState() ,
                   ProductCategoryStateEnum.INNER_ERROR.getStateInfo());
        }
    }

    /**
     * 批量插入商品类别
     * @param productCategoryList
     * @param request
     * @return
     */
    // TODO: 2019/11/4 这里的 @RequestBody 是什么意思？为什么参数中还要 list
    @RequestMapping(value = "/batchAddProductCategory", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> batchAddProductCategory(@RequestBody List<ProductCategory> productCategoryList,
                                                       HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        for (ProductCategory productCategory : productCategoryList) {
            productCategory.setShopId(currentShop.getShopId());
        }
        if (productCategoryList != null && productCategoryList.size() > 0){
            try{
                ProductCategoryExecution productCategoryExecution =
                        productCategoryService.batchAddProductCategory(productCategoryList);
                if (productCategoryExecution.getState() == ProductCategoryStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", productCategoryExecution.getStateInfo());
                }
            }catch (Exception e){
                modelMap.put("success",false);
                modelMap.put("errMsg", e.toString());
            }
        }else {
            modelMap.put("success", false);
            modelMap.put("errMsg","请至少输入一个商品类别");
        }

        return modelMap;
    }


    /**
     * 移除特定的店铺中的商品类别
     * @param productCategoryId
     * @param request
     * @return
     */
    @RequestMapping(value = "/removeProductCategory")
    @ResponseBody
    public Map<String, Object> removeProductCategory(Long productCategoryId, HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        if (productCategoryId != null && productCategoryId > 0) {
            try {
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");

                ProductCategoryExecution productCategoryExecution =
                        productCategoryService.deleteProductCategory(productCategoryId,
                                currentShop.getShopId());
                if (productCategoryExecution.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", productCategoryExecution.getStateInfo());
                }
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
            }
        }else {
            modelMap.put("success", false);
            modelMap.put("errMsg","请至少选择一个商品类别");
        }
       return modelMap;
    }
}
