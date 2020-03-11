package nowcoder.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

/**
 * 思路：
 * 首先将值放入 HashMap，然后遍历排序
 *
 * 注意：
 * 输入与输出格式
 *
 * @Author GJXAIOU
 * @Date 2020/3/11 21:23
 */
public class Huawei56 {
    public static void main(String[] args) throws IOException {


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String inputString;
        while ((inputString = bufferedReader.readLine()) != null) {
            int count = Integer.parseInt(inputString);
            Map<Integer, Integer> resTreeMap = new TreeMap<>();
            for (int i = 0; i < count; i++) {
                inputString = bufferedReader.readLine();
                String[] values = inputString.split(" ");

                Integer key = Integer.parseInt(values[0]);
                Integer value = Integer.parseInt(values[1]);
                if (!resTreeMap.containsKey(key)) {
                    resTreeMap.put(key, value);
                } else {
                    resTreeMap.replace(key, resTreeMap.get(key), resTreeMap.get(key) + value);
                }

            }

            StringBuilder result = new StringBuilder();
            for (Map.Entry<Integer, Integer> entry : resTreeMap.entrySet()) {
                result.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
            }

            System.out.print(result);
        }
    }
}
