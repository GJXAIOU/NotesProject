package com.huaweiSoftwareCode;

import com.huaweiSoftwareCode.wuyongqing.version3.LocalMain3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author GJXAIOU
 * @Date 2020/4/10 10:50
 */
public class CompactFile {

    public List<String> compactFile(String trueAnswerFilePath, String codeAnswerFilePath) throws FileNotFoundException {
        ArrayList<String> ansList = new ArrayList<String>();
        FileInputStream fis1 = null;
        FileInputStream fis2 = null;
        try {
            fis1 = new FileInputStream(trueAnswerFilePath);
            fis2 = new FileInputStream(codeAnswerFilePath);

            //返回总的字节数
            int len1 = fis1.available();
            int len2 = fis2.available();

            //长度相同，则比较具体内容
            if (len1 == len2) {
                //建立两个字节缓冲区
                byte[] data1 = new byte[len1];
                byte[] data2 = new byte[len2];

                //分别将两个文件的内容读入缓冲区
                fis1.read(data1);
                fis2.read(data2);

                //依次比较文件中的每一个字节
                for (int i = 0; i < len1; i++) {
                    //只要有一个字节不同，两个文件就不一样
                    if (data1[i] != data2[i]) {
                        System.out.println("下面几行内容不同");
                        ansList.add("第 " + i + "行： " + data1[i]);
                    }
                }
            } else {
                //长度不一样，文件肯定不同
                System.out.println("文件长度不同");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            //关闭文件流，防止内存泄漏
        } finally {
            if (fis1 != null) {
                try {
                    fis1.close();
                } catch (IOException e) {
                    //忽略
                    e.printStackTrace();
                }
            }
            if (fis2 != null) {
                try {
                    fis2.close();
                } catch (IOException e) {
                    //忽略
                    e.printStackTrace();
                }
            }
        }
        return ansList;
    }

}
