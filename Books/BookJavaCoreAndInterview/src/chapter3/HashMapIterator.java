package chapter3;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author GJXAIOU
 * @create 2019-08-17-19:20
 */
public class HashMapIterator {
    public static void main(String[] args) {
        // 使用两个HashMap存放数据表和文件中的学生信息
        HashMap<Integer, String> stringStringHashMap1 = new HashMap<>();
        HashMap<Integer, String> stringStringHashMap2 = new HashMap<>();

        // 模拟从数据表和文件中读取学生信息并插入HashMap
        stringStringHashMap1.put(1, "A1");
        stringStringHashMap1.put(2, "A1");
        stringStringHashMap1.put(3, "A1");
        stringStringHashMap2.put(2, "A2");
        stringStringHashMap2.put(3, "A1");
        stringStringHashMap2.put(4, "A2");

        Integer idInDB = null;
        String classNameInDB = null;
        String idInFile = null;
        String classNameInFile = null;

        // 通过iterator遍历stringstringHashMap1
        Iterator<Map.Entry<Integer, String>> iterator = stringStringHashMap1.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = (Map.Entry<Integer, String>) iterator.next();
            // 得到键值对
            idInDB = entry.getKey();
            classNameInDB = entry.getValue();

            // 如果存在于数据表但是不存在文件中，则删除
            if (!stringStringHashMap2.containsKey(idInDB)) {
                System.out.println("删除id为：" + idInDB);
            }else {
                // 如果两边都存在，则比较文件中数据和数据表里面的班级名
                classNameInFile = stringStringHashMap2.get(idInDB);
                // 如果不一致，则用文件里的班级名更新数据表
                if (!classNameInFile.equals(classNameInDB)){
                    System.out.println("更新id为：" + idInDB);
                }
            }
        }
    }
}
//output:
// 删除id为：1
//更新id为：2
