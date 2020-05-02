package com.practice2020.huawei;

import java.util.*;

/**
 * @author GJXAIOU
 * @create 2020/04/15 19:20
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String[] inputValue = scanner.nextLine().split(",");
            if(inputValue.length == 0){
                System.out.println("error.0001");
            }
            Map<String, Integer> resMap = new TreeMap<>();
            for (int i = 0; i < inputValue.length; i++) {
                if (!resMap.containsKey(inputValue[i])) {
                    resMap.put(inputValue[i], 1);
                } else {
                    Integer oldValue = resMap.get(inputValue[i]);
                    int newValue = oldValue + 1;
                    resMap.put(inputValue[i], newValue);
                }
            }

            // 重写比较器
            Comparator<Map.Entry<String, Integer>> valueCompator =
                    new Comparator<Map.Entry<String, Integer>>() {
                        @Override
                        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String,
                                Integer> o2) {
                            if ((o1.getValue().equals("Tom"))&& o2.getValue().equals("Lucy")){
                                return -1;
                            } if ((o1.getValue().equals("Lucy"))&& o2.getValue().equals("Tom")){
                                return 1;
                            }
                            if (o1.getValue() == o2.getValue()) {
                                return o2.getKey().compareTo(o1.getKey());
                            } else {
                                return o2.getValue() - o1.getValue();
                            }

                        }
                    };

            // map 转换为 list
            List<Map.Entry<String, Integer>> list =
                    new ArrayList<Map.Entry<String, Integer>>(resMap.entrySet());
            Collections.sort(list, valueCompator);

            // 结果
            String[] res = new String[resMap.keySet().size()];

            int i = 0;
            for (Map.Entry<String, Integer> entry : list) {
                res[i++] = entry.getKey();
            }

            System.out.println(res[res.length - 1]);
        }

    }
}
