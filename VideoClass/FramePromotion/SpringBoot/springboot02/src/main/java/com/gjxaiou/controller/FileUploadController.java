package com.gjxaiou.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author GJXAIOU
 * @Date 2020/1/7 22:23
 */
// 表示对该类下面所有方法的返回值做 JSON 格式转换，约等于 @Controller + @ResponseBody
@RestController
public class FileUploadController {
    /**
     * 处理文件上传
     */
    @RequestMapping("/fileUploadController")
    public Map<String, Object> fileUpload(MultipartFile filename) throws IOException {
        System.out.println("上传文件名为：" + filename.getOriginalFilename());
        // 将文件保存到 XXX
        filename.transferTo(new File("d:/" + filename.getOriginalFilename()));
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "ok");
        return map;
    }
}
