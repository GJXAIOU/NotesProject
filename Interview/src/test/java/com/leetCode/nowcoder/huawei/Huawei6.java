package nowcoder.huawei;

import java.util.Scanner;

/**
 * 思路：
 * 根据 . 划分两部分，然后判断后面字符值是否大于等于 5
 *
 * 注意：
 * 1、如果用“.”作为分隔的话,必须是如下写法,String.split("\\."),这样才能正确的分隔开,不能用String.split(".");
 *
 * 2、如果用“|”作为分隔的话,必须是如下写法,String.split("\\|"),这样才能正确的分隔开,不能用String.split("|");
 *
 * “.”和“|”都是转义字符,必须得加"\\";
 * @Author GJXAIOU
 * @Date 2020/3/11 20:44
 */
public class Huawei6 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        float inputValue = scanner.nextFloat();
        String inputString = String.valueOf(inputValue);
        String[] splitValue = inputString.split("\\.");
        if (Integer.valueOf(splitValue[1]) >= 5) {
            System.out.println(Integer.valueOf(splitValue[0]) + 1);
        } else {
            System.out.println(Integer.valueOf(splitValue[0]));
        }
    }
}
