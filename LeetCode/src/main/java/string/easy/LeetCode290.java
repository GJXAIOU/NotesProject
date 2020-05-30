package string.easy;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author GJXAIOU
 * @create 2020/04/17 17:13
 */
public class LeetCode290 {
    // 思路：使用 HashMap
    public boolean wordPattern(String pattern, String str) {
        // 因为 pattern 中间没有空格，所以不再转换为数组
        String[] strArray = str.split(" ");
        if (pattern.length() != strArray.length) {
            return false;
        }
        HashMap<Character, String> resMap = new HashMap<>();
        HashSet<String> resSet = new HashSet<>();
        for (int i = 0; i < pattern.length(); i++) {
            if (!resMap.containsKey(pattern.charAt(i))) {
                //判断 value 中是否存在
                if (resSet.contains(strArray[i])) {
                    return false;
                }
                resMap.put(pattern.charAt(i), strArray[i]);
                resSet.add(strArray[i]);
            } else {
                if (!(resMap.get(pattern.charAt(i)).equals(strArray[i]))) {
                    return false;
                }
            }
        }
        return true;
    }

    // 方法二：
    public boolean wordPattern2(String pattern, String str) {
        String[] array1 = str.split(" ");
        if (array1.length != pattern.length()) {
            return false;
        }
        String[] array2 = pattern.split("");
        //两个方向的映射
        return wordPatternHelper(array1, array2) && wordPatternHelper(array2, array1);
    }

    //array1 到 array2 的映射
    private boolean wordPatternHelper(String[] array1, String[] array2) {
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < array1.length; i++) {
            String key = array1[i];
            if (map.containsKey(key)) {
                if (!map.get(key).equals(array2[i])) {
                    return false;
                }
            } else {
                map.put(key, array2[i]);
            }
        }
        return true;
    }

    // 方法三：
    public boolean wordPattern3(String pattern, String str) {
        String[] array = str.split(" ");
        if (array.length != pattern.length()) {
            return false;
        }
        //判断映射后的结果是否相等
        return wordPatternHelper(pattern.split("")).equals(wordPatternHelper(array));
    }

    private String wordPatternHelper(String[] array) {
        HashMap<String, Integer> map = new HashMap<>();
        // 保存映射后的结果
        StringBuilder resString = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            //是否已经映射过
            if (map.containsKey(array[i])) {
                resString.append(map.get(array[i]));
            } else {
                resString.append(i);
                map.put(array[i], i);
            }
        }
        return resString.toString();
    }


}
