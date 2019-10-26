package com.gjxaiou.web.shopadmin;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gjxaiou.dto.ImageHolder;
import com.gjxaiou.dto.ShopExecution;
import com.gjxaiou.entity.Area;
import com.gjxaiou.entity.PersonInfo;
import com.gjxaiou.entity.Shop;
import com.gjxaiou.entity.ShopCategory;
import com.gjxaiou.enums.ShopStateEnum;
import com.gjxaiou.exception.ShopOperationException;
import com.gjxaiou.service.AreaService;
import com.gjxaiou.service.ShopCategoryService;
import com.gjxaiou.service.ShopService;
import com.gjxaiou.util.CodeUtil;
import com.gjxaiou.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author GJXAIOU
 * @create 2019-10-18-17:21
 */
// 因为属于 spring 中的 controller
@Controller
// 指定访问路径
@RequestMapping("/shopAdmin")
public class ShopManagerController {
    @Autowired
    private ShopService shopService;

    /**
     * 实现店铺注册
     *
     * @param request ：前端传进来的 Request 参数 httpServletRequest，表示客户端的请求,当客户端通过
     *                Http协议访问服务器，其请求头中信息都封装在该对象中，通过该对象提供的方法可以获取客户端请求的所有信息
     *                本例中，当用户注册用户，需要在前端页面中填店铺信息，店铺信息都会保存在 request 中
     * @return ：返回一下必须的键值对结果
     */
    // 该方法的访问路径，因为是表单，所以使用 POST
    @RequestMapping(value = "/registerShop", method = RequestMethod.POST)
    // 自动将返回结果转换为 JSON 字符串，同时页面不跳转
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();

        // 首先判断验证码是否正确
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入验证码错误");
            return modelMap;
        }

        // 1.接收并转换相应的参数（将店铺信息接收下来，然后转换为实体类），包括店铺信息以及图片信息
        // 具体 Jackson 使用说明：https://github.com/FasterXML/jackson-databind
        // 这里的 shopStr 是与前端约定好的，从前端传入 shopStr 字符串
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        // 定义一个 Shop 实体类对象 shop 接收转换后的对象
        Shop shop = null;
        try {
            // 将转换之后的字符串 shopStr，转换为 Shop 实体类，赋值给 shop 对象
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            // 转换失败之后，将错误和错误信息返回前端
            modelMap.put("sucess", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }

        // 下面开始处理图片，这里使用 spring 自带的CommonsMultipartFile
        CommonsMultipartFile shopImg = null;
        // 文件解析器，解析 request 中的文件对象，从request 的本次会话的上下文获取文件内容
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        // 判断 request 是不是有上传的文件流，如果有就将 request转换为该类型的对象:multipartHttpServletRequest
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest =
                    (MultipartHttpServletRequest) request;
            // 从该对象中提取文件流，同时强转为 spring 能够处理的文件流 ； shopImg 为前端传过来的；
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest
                    .getFile("shopImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "上传图片不能为空");
            return modelMap;
        }


        // 2. 注册店铺
        if (shop != null && shopImg != null) {
            // 店主的信息从 session 获取，即从 Session 中获取登录信息；
            // 这里的 user 是因为注册店铺之前需要先登录，登录就可以将信息以 key 和 value 的形式存储，key 为 user；
            PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
            owner.setUserId(1L);
            shop.setOwner(owner);
            ShopExecution se = null;
            try {
                se = shopService.addShop(shop, new ImageHolder(shopImg.getInputStream(),
                        shopImg.getOriginalFilename()));
                if (se.getState() == ShopStateEnum.CHECK.getState()) {
                    modelMap.put("success", true);
                    // 若shop创建成功，则加入session中，作为权限使用，保存一个店铺列表，就是该用户可以修改的店铺
                        // 从 session 从取出该用户可以操作的店铺列表
                    List<Shop> shopList = (List<Shop>) request.getSession().getAttribute(
                            "shopList");
                    // 如果是第一次创建，就创建一个 list 列表，如果不是第一次就直接存入
                    if (shopList == null || shopList.size() == 0){
                        shopList = new ArrayList <Shop>();
                    }
                    shopList.add(se.getShop());
                    request.getSession().setAttribute("shopList", shopList);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", se.getStateInfo());
                }
            } catch (ShopOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }
            return modelMap;
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺信息");
            return modelMap;
        }
    }



    /**
     *  店铺信息编辑
     * @param request  从 request 中获取 shopId
     * @return
     */
    @RequestMapping(value = "/getShopById", method = RequestMethod.GET)
    // 自动将返回结果转换为 JSON 字符串
    @ResponseBody
    private Map<String, Object> getShopById(HttpServletRequest request){
        // 存放返回值
        Map<String,Object> modelMap = new HashMap<String, Object> ();
        Long shopId = HttpServletRequestUtil.getLong(request,"shopId");
        // 判断前端传进来的 shopId 是否合规
        if (shopId > -1){
            try {
                Shop shop = shopService.getByShopId(shopId);
                // 因为 店铺名称、店铺分类、是不可以修改的，只能修改 所属区域、详细信息、联系电话、缩略图、店铺简介等等
                // 因为所属区域列表需要从前端获取
                List<Area> areaList = areaService.getAreaList();
                modelMap.put("shop", shop);
                modelMap.put("areaList", areaList);
                modelMap.put("success", true);
            }catch (Exception e){
                modelMap.put("success", "false");
                modelMap.put("errMsg", e.toString());
            }
        }else {
            modelMap.put("success", "false");
            modelMap.put("errMsg","empty shopId");
        }
        return modelMap;
    }


    @RequestMapping(value = "/modifyShop", method = RequestMethod.POST)
    // 自动将返回结果转换为 JSON 字符串
    @ResponseBody
    private Map<String, Object> modifyShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入验证码错误");
            return modelMap;
        }

        // 1.接收并转换相应的参数（将店铺信息接收下来，然后转换为实体类），包括店铺信息以及图片信息
        // 具体 Jackson 使用说明：https://github.com/FasterXML/jackson-databind
        // 这里的 shopStr 是与前端约定好的，从前端传入 shopStr 字符串
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        // 定义一个 shop 实体类对象 shop 接收转换后的对象
        Shop shop = null;
        try {
            // 将转换之后的字符串 shopStr，转换为 Shop 实体类，赋值给 shop 对象
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            // 转换失败之后，将错误和错误信息返回前端
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }

        // 下面开始处理图片，这里使用 spring 自带的CommonsMultipartFile
        CommonsMultipartFile shopImg = null;
        // 文件解析器，解析 request 中的文件对象，从request 的本次会话的上下文获取文件内容
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        // 判断 request 是不是有上传的文件流，如果有就将 request转换为该类型的对象:multipartHttpServletRequest
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest =
                    (MultipartHttpServletRequest) request;
            // 从该对象中提取文件流，同时强转为 spring 能够处理的文件流 ； shopImg 为前端传过来的；
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest
                    .getFile("shopImg");
        }

        // 2. 修改店铺信息
        //图片可以上传或者不上传（上面一段的 else 因此被删了，下面参数中的 shopId 是不能为空的）
        if (shop != null && shop.getShopId() != null) {
            // 店主的信息从 session 获取；
            ShopExecution se = null;
            try {
                // 如果上传的图片为空，不做处理，反之需要处理图片
                if (shopImg == null){
                    se = shopService.modifyShop(shop, new ImageHolder(null, null));
                }else {
                    se = shopService.modifyShop(shop, new ImageHolder(shopImg.getInputStream(),
                            shopImg.getOriginalFilename()));
                }
                se = shopService.modifyShop(shop, new ImageHolder(shopImg.getInputStream(),
                        shopImg.getOriginalFilename()));
                if (se.getState() == ShopStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                    // 若shop创建成功，则加入session中，作为权限使用
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", se.getStateInfo());
                }
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }
            return modelMap;
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺Id");
            return modelMap;
        }
    }


    /**
     *
     */
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/getShopInitInfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopInitInfo() {
        // 定义返回值,同时需要两个 List 分别接收 shopCategory 和 area 信息
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<ShopCategory> shopCategoryList = new ArrayList<>();
        List<Area> areaList = new ArrayList<>();
        try {
            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList = areaService.getAreaList();
            modelMap.put("shopCategoryList", shopCategoryList);
            modelMap.put("areaList", areaList);
            modelMap.put("success", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }


    // 店铺管理页面的 controller 方法
    @RequestMapping(value = "/getShopList", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopList(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();

        PersonInfo user = new PersonInfo();
        user.setUserId(1L);
        user.setName("test");
        request.getSession().setAttribute("user", user);

        user = (PersonInfo) request.getSession().getAttribute("user");
        try {
            Shop shopCondition = new Shop();
            shopCondition.setOwner(user);
            // 因为个人创建店铺优先，就写死了，从0-100；
            ShopExecution se = shopService.getShopList(shopCondition, 0, 100);
            result.put("shopList", se.getShopList());
            result.put("user", user);
            result.put("success", true);
        } catch (Exception e) {
            result.put("success", false);
            result.put("errMsg", e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/getShopManagementInfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopManagementInfo(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();

        long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        if (shopId < 0) {
            Object currentShopObj = request.getSession().getAttribute("currentShop");
            if (currentShopObj == null) {
                result.put("redirect", true);
                result.put("url", "/o2o/shopAdmin/shopList");
            } else {
                Shop currentShop = (Shop) currentShopObj;
                result.put("redirect", false);
                result.put("shopId", currentShop.getShopId());
            }
        } else {
            Shop currentShop = new Shop();
            currentShop.setShopId(shopId);
            request.getSession().setAttribute("currentShop", currentShop);
            result.put("redirect", false);
        }
        return result;
    }
}
