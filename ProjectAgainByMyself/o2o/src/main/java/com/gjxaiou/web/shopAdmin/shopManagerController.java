package com.gjxaiou.web.shopAdmin;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author GJXAIOU
 * @create 2019-11-03-15:43
 */
@Controller
@RequestMapping("/shopAdmin")
public class shopManagerController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private ShopCategoryService shopCategoryService;

    /**
     * 实现店铺注册功能
     *
     * @param request 这是前端传入的 HttpServletRequest 类型参数 request，表示客户端的请求
     *                当客户端通过 HTTP 协议访问服务器的时候，其请求头中的信息都封装在该对象中，可以通过该对象提供的方法获取客户端请求的所有信息
     *                本方法中，当用户在注册店铺的页面中填写完店铺信息后，完整的店铺信息就会封装保存在 request 参数中；
     * @return 因为是注册店铺，提交的信息较多，使用 POST 方法
     */
    @RequestMapping(value = "/registerShop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();

        // 1.首先判断用户的验证码是否正确
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入的验证码错误");
            return modelMap;
        }

        // 2.读取请求信息（包括店铺信息和图片信息）并转换为实体类对象，即接收并转换响应的参数
        // 这里的 shopStr 是与前端约定好的，以此为 key，然后得到其 value 值
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        // 这里使用的是 Jackson 的方法 ，具体见：https://github.com/FasterXML/jackson-databind
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            // 将转换后的 shopStr，转换为 shop 实体类；
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (JsonParseException e) {
            // 转换失败之后，将错误和错误信息返回前端
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        } catch (JsonMappingException e) {
            // 转换失败之后，将错误和错误信息返回前端
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        } catch (IOException e) {
            // 转换失败之后，将错误和错误信息返回前端
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }

        // TODO: 2019/11/3 下面不知道在说啥
        // 3.处理店铺图片（使用 spring 自带的 CommonsMultipartFile）
        CommonsMultipartFile shopImg = null;
        // 文件解析器，解析 request 中的文件对象，即从 request 的本次回话的上下文获取文件内容
        CommonsMultipartResolver multipartResolver =
                new CommonsMultipartResolver(request.getSession().getServletContext());
        // 判断 request 是不是有上传的文件流，如果有则将 request 转换为 MultipartHttpServletRequest 对象
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            // 从该对象中提取文件流，同时强制转换为 spring 能够处理的文件流，shopImg 同前端一致
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "上传图片不能为空");
            return modelMap;
        }

        // 4.正式注册店铺,尽量不要依靠前端信息
        if (shop != null && shopImg != null) {
            // 通过从 session 中获取登录信息，得到店主信息，因为注册店铺之前必须先登录，登录时将登录信息以键值对的形式存储，这里 user 即为 key
            PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
            owner.setUserId(1L);
            shop.setOwner(owner);

            ShopExecution shopExecution = null;
            try {
                shopService.addShop(shop, new ImageHolder(shopImg.getInputStream(),
                        shopImg.getOriginalFilename()));
                if (shopExecution.getState() == ShopStateEnum.CHECK.getState()) {
                    modelMap.put("success", true);
                    // 如果店铺创建成功，则加入 session 作为权限使用，保存的店铺列表就是该用户可以操作的店铺，这样从 session
                    // 中就可以去除该用户可以操作的店铺列表
                    List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
                    if (shopList == null || shopList.size() == 0) {
                        shopList = new ArrayList<Shop>();
                    }
                    shopList.add(shopExecution.getShop());
                    request.getSession().setAttribute("shopList", shopList);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", shopExecution.getStateInfo());
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

    /** 根据店铺 Id 获取店铺
     * @param request 从 request 中可以获取店铺 Id
     * @return
     */
    @RequestMapping(value = "/getShopById", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopById(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();

        long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        // 判断前端中的 shopId 是否合规
        if (shopId >= 0) {
            try {
                Shop shop = shopService.getByShopId(shopId);
                // 因为 shopName/ ShopCategoryId 是不可以修改的，只能修改 所属区域、店铺描述、联系电话、缩略图等；
                    // 同样，所属区域列表需要从前端获取
                List<Area> areaList = areaService.getAreaList();
                modelMap.put("shop", shop);
                modelMap.put("areaList", areaList);
                modelMap.put("success", true);
            } catch (Exception e) {
                modelMap.put("success", "false");
                modelMap.put("errMsg", e.toString());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺 Id");
        }
        return modelMap;
    }


    /**
     * 修改更新店铺信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/modifyShop", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> modifyShop(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();

        // 1.首先判断用户的验证码是否正确
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入的验证码错误");
            return modelMap;
        }

        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (JsonParseException e) {
            modelMap.put("sucess", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        } catch (JsonMappingException e) {
            modelMap.put("sucess", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        } catch (IOException e) {
            modelMap.put("sucess", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }

        // 3.处理店铺图片（使用 spring 自带的 CommonsMultipartFile）：注：这里的图片可以上传可以不上传
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver multipartResolver =
                new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        }

        // 4.修改店铺信息
        if(shop != null && shop.getShopId() != null){
            ShopExecution shopExecution = null;
            try{
                // 判断是否需要处理图片
                if (shopImg == null){
                    shopExecution = shopService.modifyShop(shop,new ImageHolder(null, null));
                }else {
                    shopExecution = shopService.modifyShop(shop,
                            new ImageHolder(shopImg.getInputStream(),
                                    shopImg.getOriginalFilename()));
                }
// 这里应该不需要，但是 github 部分版本上有，先放置
//     shopExecution = shopService.modifyShop(shop,new ImageHolder(shopImg.getInputStream(),shopImg.getOriginalFilename()));
               // 如果 shop 创建成功，则加入 session 中，作为权限使用
                if (shopExecution.getState() == ShopStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg", shopExecution.getStateInfo());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return modelMap;
        }else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺Id");
            return modelMap;
        }
    }


    /**
     *  获取店铺信息
     * @return
     */
    @RequestMapping(value = "/getShopInitInfo", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getShopInitInfo(){
        Map<String, Object> modelMap = new HashMap<>();
        // 需要两个 List 分别接收 shopCategory 和 area 的信息
        List<ShopCategory> shopCategoryList =  new ArrayList<>();
        List<Area> areaList = new ArrayList<>();

        try{
            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList = areaService.getAreaList();
            modelMap.put("shopCategoryList", shopCategoryList);
            modelMap.put("areaList", areaList);
            modelMap.put("success", true);
        }catch (Exception e){
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }


    /**
     *  获取店铺列表
     * @param request
     * @return
     */
    // TODO: 2019/11/3 这里的参数可以设置的，不一定要写死
    @RequestMapping(value = "/getShopList", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getShopList(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();

        PersonInfo user = new PersonInfo();
        user.setUserId(1L);
        user.setName("test");
        request.getSession().setAttribute("user", user);
        user = (PersonInfo)request.getSession().getAttribute("user");

        try{
            Shop shopCondition = new Shop();
            shopCondition.setOwner(user);
            // 因为个人创建的店铺数量有限，最多设置为 100；
            ShopExecution shopExecution = shopService.getShopList(shopCondition,0, 100);
            modelMap.put("shopList",shopExecution.getShopList());
            modelMap.put("user",user);
            modelMap.put("success", true);
        }catch (Exception e){
            modelMap.put("success", false);
            modelMap.put("errMsg",e.getMessage());
        }
        return modelMap;
    }

    /**
     * 获取店铺管理信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/getShopManagementInfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopManagementInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();

        long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        if (shopId < 0) {
            Object currentShopObj = request.getSession().getAttribute("currentShop");
            if (currentShopObj == null) {
                modelMap.put("redirect", true);
                modelMap.put("url", "/o2o/shopAdmin/shopList");
            } else {
                Shop currentShop = (Shop) currentShopObj;
                modelMap.put("redirect", false);
                modelMap.put("shopId", currentShop.getShopId());
            }
        } else {
            Shop currentShop = new Shop();
            currentShop.setShopId(shopId);
            request.getSession().setAttribute("currentShop", currentShop);
            modelMap.put("redirect", false);
        }
        return modelMap;
    }

}