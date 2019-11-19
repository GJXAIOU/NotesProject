package com.gjxaiou.web.shopAdmin;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gjxaiou.dto.ImageHolder;
import com.gjxaiou.dto.ProductExecution;
import com.gjxaiou.entity.Product;
import com.gjxaiou.entity.ProductCategory;
import com.gjxaiou.entity.Shop;
import com.gjxaiou.enums.ProductStateEnum;
import com.gjxaiou.exception.ProductOperationException;
import com.gjxaiou.service.ProductCategoryService;
import com.gjxaiou.service.ProductService;
import com.gjxaiou.util.CodeUtil;
import com.gjxaiou.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GJXAIOU
 * @create 2019-11-04-22:21
 */
@Controller
@RequestMapping("/shopAdmin")
public class ProductManagerController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;
    /**
     * 支持上传商品详情图最大数量为 6
     */
    private static final int IMAGE_MAX_UPDATE_COUNT = 6;

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addProduct(HttpServletRequest request) throws ProductOperationException {
        Map<String, Object> modelMap = new HashMap<>();

        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "验证码输入错误");
            return modelMap;
        }

        // 获取前端参数的变量初始化，包括商品、缩略图、详情图列表实体类
        Product product = null;
        ImageHolder thumbnail = null;
        List<ImageHolder> productImgList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        String productStr = HttpServletRequestUtil.getString(request, "productStr");
        CommonsMultipartResolver commonsMultipartResolver =
                new CommonsMultipartResolver(request.getSession().getServletContext());

        // 若请求中存在文件流，则取出相关的文件（包括缩略图和详情图）
        try {
            if (commonsMultipartResolver.isMultipart(request)) {
                thumbnail = handleImage(request, productImgList);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", "上传图片不能为空");
                return modelMap;
            }
        } catch (IOException e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }

        // 获取前端传过来的表单 String 流，并将其转换为 Product 实体类
        try {
            product = objectMapper.readValue(productStr, Product.class);
        } catch (JsonParseException e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        } catch (JsonMappingException e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        } catch (IOException e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }

        // 如果 Product 信息、缩略图以及详情图均为空，则进行商品添加操作
        if (product != null && thumbnail != null && productImgList.size() > 0) {
            try {
                // 从 Session 中获取当前店铺的 Id 并赋值给 Product，减少对前端数据的依赖
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                product.setShop(currentShop);
                ProductExecution productExecution = productService.addProduct(product, thumbnail,
                        productImgList);

                if (productExecution.getState() == ProductStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", productExecution.toString());
                }
            } catch (ProductOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入商品信息");
        }
        return modelMap;
    }

    /**
     * 处理图片
     *
     * @param request
     * @param productImgList
     * @return
     * @throws IOException
     */
    private ImageHolder handleImage(HttpServletRequest request, List<ImageHolder> productImgList) throws IOException {
        MultipartHttpServletRequest multipartHttpServletRequest =
                (MultipartHttpServletRequest) request;
        ImageHolder thumbnail = null;

        // 取出缩略图并构建 ImageHolder 对象
        CommonsMultipartFile thumbnailFile =
                (CommonsMultipartFile) multipartHttpServletRequest.getFile("thumbnail");
        thumbnail = new ImageHolder(thumbnailFile.getInputStream(),
                thumbnailFile.getOriginalFilename());
        // 取出详情图列表并构建List<ImageHolder>列表对象，最多支持6张图片上传
        for (int i = 0; i < IMAGE_MAX_UPDATE_COUNT; i++) {
            CommonsMultipartFile productImgFile =
                    (CommonsMultipartFile) multipartHttpServletRequest.getFile("productImg" + i);
            if (productImgFile != null) {
                // 若取出的详情图片文件流不为空，则将其加入详情图列表
                ImageHolder productImg = new ImageHolder(productImgFile.getInputStream(),
                        productImgFile.getOriginalFilename());
                productImgList.add(productImg);
            } else {
                // 若取出的详情图片文件流为空，则终止循环
                break;
            }
        }
        return thumbnail;
    }


    /**
     * 通过商品 Id 获取商品信息
     *
     * @param productId
     * @return
     */
    @RequestMapping(value = "/getProductById", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getProductById(@RequestParam Long productId) {
        Map<String, Object> modelMap = new HashMap<>();
        if (productId > 0) {
            Product product = productService.getProductById(productId);
            // 显示该商品所在店铺的商品类别
            if (product != null) {
                List<ProductCategory> productCategoryList =
                        productCategoryService.getProductCategoryList(product.getShop().getShopId());
                modelMap.put("product", product);
                modelMap.put("productCategoryList", productCategoryList);
                modelMap.put("success", true);
            } else {
                modelMap.put("product", null);
                modelMap.put("productCategoryList", null);
                modelMap.put("success", true);
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty productId");
        }
        return modelMap;
    }


    /**
     * 修改店铺信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/modifyProduct", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> modifyProduct(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();

        // 区分是商品编辑还是商品的上下架
        boolean statusChange = HttpServletRequestUtil.getBoolean(request, "statusChange");

        if (!statusChange && !CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "验证码输入错误");
            return modelMap;
        }
        // 接受前端参数变量初始化，包括商品、缩略图、详情图列表
        ObjectMapper mapper = new ObjectMapper();
        Product product = null;
        ImageHolder thumbnail = null;
        List<ImageHolder> productImgList = new ArrayList<>();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        // 若请求中存在文件流，则去除相关的文件

        try {
            if (multipartResolver.isMultipart(request)) {
                thumbnail = handleImage(request, productImgList);
            }
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        try {
            String productStr = HttpServletRequestUtil.getString(request, "productStr");
            // 尝试获取前端传过来的表单string流，并将其转换成Product实体类
            product = mapper.readValue(productStr, Product.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        // 若Product信息、缩略图以及详情图里诶包为空，则开始进行商品添加操作
        if (product != null) {
            try {
                // 从session中获取当前店铺的id并赋值给product，减少对前端数据的依赖
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                product.setShop(currentShop);
                // 进行商品更新
                ProductExecution pe = productService.modifyProduct(product, thumbnail,
                        productImgList);
                if (pe.getState() == ProductStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pe.getStateInfo());
                }
            } catch (ProductOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入商品信息");
        }
        return modelMap;
    }

    @ResponseBody
    @RequestMapping(value = "/getProductListByShop", method = RequestMethod.GET)
    public Map<String, Object> getProductListByShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();

        // 获取前台传过来的页码以及每页要求返回的商品数
        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        // 从 session 中获取店铺信息
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        if ((pageIndex > -1) && (pageSize > -1) && (currentShop != null) && (currentShop.getShopId() != null)) {
            // 获取传入的检索条件
            long productCategoryId = HttpServletRequestUtil.getLong(request, "productCategoryId");
            String productName = HttpServletRequestUtil.getString(request, "productName");
            Product productCondition = compactProductCondition4Search(currentShop.getShopId(),
                    productCategoryId,
                    productName);
            // 传入查询条件以及分页信息查询，返回响应商品列表及总数
            ProductExecution pe = productService.getProductList(productCondition, pageIndex,
                    pageSize);
            modelMap.put("productList", pe.getProductList());
            modelMap.put("count", pe.getCount());
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty pageSize or pageIndex or shopId");
        }
        return modelMap;
    }

    /**
     * 组合查询条件
     *
     * @param shopId
     * @param productCategoryId
     * @param productName
     * @return
     */
    private Product compactProductCondition4Search(long shopId, long productCategoryId,
                                                   String productName) {
        Product productCondition = new Product();
        Shop shop = new Shop();
        shop.setShopId(shopId);
        productCondition.setShop(shop);
        if (productCategoryId != -1L) {
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductCategoryId(productCategoryId);
            productCondition.setProductCategory(productCategory);
        }
        if (productName != null && !productName.equalsIgnoreCase("null")) {
            productCondition.setProductName(productName);
        }
        return productCondition;
    }
}

