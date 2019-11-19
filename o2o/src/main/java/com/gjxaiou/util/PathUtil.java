package com.gjxaiou.util;

/**
 * @author GJXAIOU
 * @create 2019-10-17-16:53
 */
public class PathUtil {
    /**
     *   获取系统文件路径的分隔符
     */
    private static String separator = System.getProperty("file.separator");

    // 返回项目图片的根路径
    public static String getImgBasePath(){
        // 获取操作系统名称
        String os = System.getProperty("os.name");
        String basePath = "";
        // 不能将图片放在项目之下，否则每次重启就会将后生成的图片删除
        if (os.toLowerCase().startsWith("win")){
            basePath="E:\\Program\\Java\\Project\\ProjectAgainByMyself\\";
        }else{
            basePath = "/home/GJXAIOU/Project/o2o/image";
        }
        basePath = basePath.replace("/", separator);
        return basePath;
    }

    // 返回项目图片子路径
    public static String  getShopImagePath(long shopId){
        String imagePath = "/item/shop/" + shopId + "/";
        return imagePath.replace("/", separator);
    }
    /**
     * 获取首页头图路径
     */
    public static String getHeadLineImagePath() {
        String imagePath = "/item/headLine/";
        return imagePath.replace("/", separator);
    }

    /**
     * 获取店铺类别路径
     */
    public static String getShopCategoryImagePath() {
        String imagePath = "/item/shopCategory/";
        return imagePath.replace("/", separator);
    }
}
