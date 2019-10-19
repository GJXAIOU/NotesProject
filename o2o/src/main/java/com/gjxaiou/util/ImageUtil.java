package com.gjxaiou.util;

import net.coobird.thumbnailator.Thumbnails;

import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author GJXAIOU
 * @create 2019-10-17-16:28
 */
public class ImageUtil {
    // 获取 classPath 绝对值路径
    // 这里水印位置可以使用线程返回来推出，这里得到的是 classPath 的绝对路径
    public static String basePath =
            Thread.currentThread().getContextClassLoader().getResource("").getPath();

    // 定义生成的随机文件名格式：当前年月日时分秒 + 五位随机数
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmSS");
    private static final Random r = new Random();


    // 店铺大图
        // 参数一：用户上传的文件流，参数二：目标输出地址,同时使用系统生成的随机名命名文件，防止用户上传重复名称文件
    public static String generateThumbnail(InputStream thumbnailInputStream,
                                           String fileName, String targetAddress){
        String realFileName = getRandomFileName();
        String extension = getFileExtension(fileName);
        // 存储路径可能不存在，新建存储路径
        makeDirPath(targetAddress);
        // 相对路径 = 目标文件夹位置 + 文件名 + 文件后缀；
        String relativeAddress = targetAddress + realFileName + extension;
        // 最终文件地址 = 项目根路径 + 文件项目相对路径
        File dest = new File(PathUtil.getImgBasePath() + relativeAddress);

        // 图片修饰
        try{
            Thumbnails.of(thumbnailInputStream).size(200,200).watermark(Positions.BOTTOM_RIGHT,
                    ImageIO.read(new File(basePath + "/watermark.jpg")), 0.5f).outputQuality(0.8f).toFile(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return relativeAddress;
    }

    // 创建目标路径涉及到的文件目录
        // 若路径为：/a/b/c.jpg ，则 a  b   c 三个文件夹都得自动创建
    private static void makeDirPath(String targetAddress) {
        // 获取文件的绝对路径
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddress;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()){
            dirPath.mkdirs();
        }
    }

    // 获取输入文件流的拓展流
    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public static String getRandomFileName() {
        // 获取随机的五位数
        int randomNum = r.nextInt(89999) + 10000;
        String nowTimeString = sDateFormat.format(new Date());
        return nowTimeString + randomNum;
    }


    // 将一张图片打上水印，改变图片大小同时将其压缩，然后输出到同级目录低下
    public static void main(String[] args) throws IOException {
        // 这里水印位置可以使用线程返回来推出，这里得到的是 classPath 的绝对路径
        String bathPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        // 参数传入文件或者文件流，这里以文件为例，URL为需要修改的文件目录
        // 指定文件大小，水印（水印位置，水印图片路径，透明度）,压缩图片，输出位置（可以改为新的名字）
        Thumbnails.of(new File("E:/Program/Java/Project/o2o/xiaohuangren.jpg")).size(200,200).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(bathPath + "watermark.jpg")), 0.5f).outputQuality(0.8f).toFile("E:/Program/Java/Project/o2o/xiaohuangrenNew.jpg");
    }
}
