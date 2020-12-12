package com.gjxaiou.gc;

/**
 * @Author GJXAIOU
 * @Date 2019/12/15 13:16
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Test2 {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream(new File("C:\\Users\\gjx16\\Desktop\\law\\policy_change1.json"));
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        File file = new File("C:\\Users\\gjx16\\Desktop\\law\\policy_change.json");
        FileOutputStream fos = new FileOutputStream(file);
        HashSet<String> hs = new HashSet<>();
        String str=null;
        while((str=br.readLine())!=null){
            String[] words = str.split(",");
            String ss = words[0];
            String temp = ss.split(":")[1];
            if(hs.add(temp)){
                fos.write((str + "\n").getBytes());
            }
        }
        fis.close();
        br.close();
        fos.close();
    }
}
