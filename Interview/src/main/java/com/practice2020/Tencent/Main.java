package com.practice2020.Tencent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


/**
 * @author GJXAIOU
 * @create 2020/04/26 20:32
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int i = 0;
        LinkedList<Integer> res = new LinkedList<>();
        ArrayList<Integer> out = new ArrayList<>();
        while (i < num) {
            int count = scanner.nextInt();
            scanner.nextLine();
            while (count > 0) {
                String temp = scanner.nextLine();
                String[] value = new String[2];
                if (temp.contains(" ")) {
                    value = temp.split(" ");
                }else {
                    value[0] = String.valueOf(temp);
                }
                switch (value[0]) {
                    case "PUSH":
                        res.add(Integer.valueOf(value[1]));
                        break;
                    case "SIZE":
                        out.add(res.size());
                        break;
                    case "TOP":
                        if (res.size() <= 0) {
                            out.add(-1);
                        } else {
                            out.add(res.peek());
                        }
                        break;
                    case "POP":
                        if (res.size() <= 0) {
                            out.add(-1);
                        } else {
                            res.pop();
                        }
                        break;
                    case "CLEAR":
                        res.clear();
                        break;
                }
                count--;
            }
            res.clear();
            i++;
        }

        for (int j = 0; j < out.size(); j++) {
            System.out.println(out.get(j));
        }
    }
}
