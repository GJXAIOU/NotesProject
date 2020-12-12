package com.gjxaiou.easy.day05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * @author GJXAIOU
 * HashMap 基本使用操作
 */
public class HashMapOperation {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("zhangsan", "11");

        System.out.println(map.containsKey("zhangsan"));
        System.out.println(map.containsKey("lisi"));
        System.out.println("=========================");

        System.out.println(map.get("zhangsan"));
        System.out.println(map.get("lisi"));
        System.out.println("=========================");

        System.out.println(map.isEmpty());
        System.out.println(map.size());
        System.out.println("=========================");
        // remove 方法返回删除 key 对应的 value 值
        System.out.println(map.remove("zhangsan"));
        System.out.println(map.containsKey("zhangsan"));
        System.out.println(map.get("zhangsan"));
        System.out.println(map.isEmpty());
        System.out.println(map.size());
        System.out.println("=========================");

        map.put("zhangsan", "11");
        System.out.println(map.get("zhangsan"));
        map.put("zhangsan", "22");
        System.out.println(map.get("zhangsan"));
        System.out.println("=========================");

        map.put("zhangsan", "11");
        map.put("lisi", "22");
        map.put("wangwu", "33");

        for (String key : map.keySet()) {
            System.out.println(key);
        }
        System.out.println("=========以上为 key 列表================");

        for (String values : map.values()) {
            System.out.println(values);
        }
        System.out.println("===========以上为 value 列表==============");

        map.clear();
        map.put("A", "1");
        map.put("B", "2");
        map.put("C", "3");
        map.put("D", "1");
        map.put("E", "2");
        map.put("F", "3");
        map.put("G", "1");
        map.put("H", "2");
        map.put("I", "3");
        for (Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + "," + value);
        }
        System.out.println("=========================");

        // 移除所有 value 值为 1 的键值对
        List<String> removeKeys = new ArrayList<String>();
        for (Entry<String, String> entry : map.entrySet()) {
            if (!entry.getValue().equals("1")) {
                removeKeys.add(entry.getKey());
            }
        }
        for (String removeKey : removeKeys) {
            map.remove(removeKey);
        }
        for (Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + "," + value);
        }
        System.out.println("=========================");
    }
}
