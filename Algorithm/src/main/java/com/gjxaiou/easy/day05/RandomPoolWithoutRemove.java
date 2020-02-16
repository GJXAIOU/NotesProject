package com.gjxaiou.easy.day05;

/**
 * 不考虑 Remove 方法的 RandomPool 程序
 *
 * @author GJXIAOU
 * @create 2020/1/14 0014 上午 11:13
 */

import java.util.HashMap;

public class RandomPoolWithoutRemove {
    public HashMap<String, Integer> map1;
    public HashMap<Integer, String> map2;
    public int size;

    public RandomPoolWithoutRemove() {
        map1 = new HashMap<>();
        map2 = new HashMap<>();
        size = 0;
    }

    public void add(String str) {
        map1.put(str, size);
        map2.put(size, str);
        size++;
    }

    public String getRandom() {
        if (size == 0) {
            return null;
        }
        int index = (int) (Math.random() * size);
        return map2.get(index);
    }
}
