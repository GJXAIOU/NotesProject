package com.gjxaiou.jdk8.lambdaAndFunctionInterface;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StringComparator {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("zhangsan", "lisi", "wangwu", "zhaoliu");

        /**
         * 倒序排序方式一：匿名内部类
         */
        // Comparator 也是函数式接口，
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println(names);


        /**
         * 方式二：Lambda 表达式
         */
        Collections.sort(names, (o1, o2) -> {
            return o2.compareTo(o1);
        });

        // 方式二的简化版本
        Collections.sort(names, (o1, o2) -> o2.compareTo(o1));

        // 方式二的简化版本
        Collections.sort(names, Comparator.reverseOrder());
        System.out.println(names);
    }
}
/** output:
 * [zhaoliu, zhangsan, wangwu, lisi]
 * [zhaoliu, zhangsan, wangwu, lisi]
 */