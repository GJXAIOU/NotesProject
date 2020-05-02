package com.practice2020.meituan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author GJXAIOU
 * @create 2020/04/23 20:00
 */
public class Main22 {
   static List<String> resList = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

     //   while (scanner.hasNextLine()){
            float value = scanner.nextFloat();
            String inputString = String.valueOf(value);
            fun(inputString);
       // }

        
        for (String s : resList) {
            System.out.println(s);
        }
    }

    public static void fun(String inputString){
        // 有没有 .
        int dotIndex = inputString.length() - 1;
        for (int i = 0; i < inputString.length(); i++){
            if (inputString.charAt(i) == '.'){
                dotIndex = i;
            }
        }

        int temp = dotIndex;
        StringBuilder res  = new StringBuilder();
        for (int i = dotIndex - 1; i >= 0; i--){
            if (i >= 0){
                res.append(inputString.charAt(i));
            }else {
                break;
            }
           if (i -1 >= 0){
               res.append(inputString.charAt(i -1));
           }else {
               break;
           }
           if (i -2 >= 0){
               res.append(inputString.charAt(i -2));
               res.append(',');
           }else {
               break;
           }
        }
        StringBuilder trueRes = res.reverse();
        // 没有 .
        if (temp == inputString.length() - 1){
            trueRes.append(".00");
        }else  if (temp == inputString.length() - 2){
            trueRes.append('.');
            trueRes.append(inputString.length() - 1);
            trueRes.append('0');
        }else if (temp <= inputString.length() - 3){
            trueRes.append('.');
            trueRes.append(inputString.length() - 2);
            trueRes.append(inputString.length() - 1);
        }

        resList.add(trueRes.toString());
    }

}
