package com.gjxaiou.web.shopadmin;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import	java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.gjxaiou.util.ImageUtil;
import com.gjxaiou.util.PathUtil;
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
@RequestMapping("/shopadmin")
public class ShopManagerController {
    @Autowired
    private ShopService shopService;

    /**
     *  实现店铺注册
     * @param request ：前端传进来的 Request 参数 httpServletRequest，表示客户端的请求,当客户端通过
     *                           Http协议访问服务器，其请求头中信息都封装在该对象中，通过该对象提供的方法可以获取客户端请求的所有信息
     *                本例中，当用户注册用户，需要在前端页面中填店铺信息，店铺信息都会保存在 request 中
     * @return ：返回一下必须的键值对结果
     */
    // 该方法的访问路径，因为是表单，所以使用 POST
    @RequestMapping(value="/registerShop", method = RequestMethod.POST)
    // 自动将返回结果转换为 JSON 字符串
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<>();

        if(!CodeUtil.checkVerifyCode(request)){
            modelMap.put("success",false);
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
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
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
                // 店主的信息从 session 获取；
                PersonInfo owner = new PersonInfo();
                owner.setUserId(1L);
                shop.setOwner(owner);

//                // 随机传入一个空文件占位置，为的是 下面的文件流与 File 转换
//                File shopImgFile =
//                        new File(PathUtil.getImgBasePath() + ImageUtil.getRandomFileName());
//                try {
//                    shopImgFile.createNewFile();
//                } catch (IOException e) {
//                    modelMap.put("success", false);
//                    modelMap.put("errMsg", e.getMessage());
//                    return modelMap;
//                }
//                try {
//                    inputStringToFile(shopImg.getInputStream(), shopImgFile);
//                } catch (IOException e) {
//                    modelMap.put("success", false);
//                    modelMap.put("errMsg", e.getMessage());
//                    return modelMap;
//                }


            ShopExecution se = null;
            try {
                se = shopService.addShop(shop, shopImg.getInputStream(),
                        shopImg.getOriginalFilename());
                if (se.getState() == ShopStateEnum.CHECK.getState()) {
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
            } else{
                modelMap.put("success", false);
                modelMap.put("errMsg", "请输入店铺信息");
                return modelMap;
            }
        }

//    // 因为上面的 shopImg 属于 CommonsMultipartFile shopImg，但是shopService.addShop(shop, shopImg);中
//    // shopImg需要是File类型，因此将 其转换
//    private static void inputStringToFile(InputStream ins, File file){
//        // 将输出流转换为 File
//        FileOutputStream os = null;
//        try{
//            os = new FileOutputStream(file);
//            int byteRead = 0;
//            // 使用一个数组读取 inputStream 中内容，读满 1024 个字节就往输出流中写入一次
//            byte[] buffer = new byte[1024];
//            while ((byteRead = ins.read(buffer)) != -1) {
//                os.write(buffer,0,byteRead);
//            }
//        }catch (Exception e){
//            throw new RuntimeException("调用 inputStreamToFile 产生异常:" + e.getMessage());
//        }finally {
//                try {
//                    if (os != null) {
//                        os.close();
//                    }
//                    if (ins != null) {
//                        ins.close();
//                    }
//                } catch (IOException e) {
//                    throw new RuntimeException("inputStreamToFile 关闭 IO 产生异常:" + e.getMessage());
//                }
//            }
//        }


    /**
     *
     */
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/getshopinitinfo",method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopInitInfo(){
        // 定义返回值,同时需要两个 List 分别接收 shopCategory 和 area 信息
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<ShopCategory> shopCategoryList = new ArrayList<>();
        List<Area> areaList = new ArrayList<>();
        try {
            shopCategoryList  = shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList = areaService.getAreaList();
            modelMap.put("shopCategoryList", shopCategoryList);
            modelMap.put("areaList", areaList);
            modelMap.put("success", true);
        }catch (Exception e){
            modelMap.put("success", false);
            modelMap.put("errMsg",e.getMessage());
        }
       return modelMap;
    }
}
