package com.practice2020.jd;

/**
 * @author GJXAIOU
 * @create 2020/04/18 19:48
 */

import java.util.*;

public class Main5 {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        while (num != 0) {
            ArrayList<List<Integer>> resValue = new ArrayList<>();
            ArrayList<Integer> lineList = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                int longSide = in.nextInt();
                int shortSide = in.nextInt();
                lineList.add(longSide > shortSide ? longSide : shortSide);
                lineList.add(longSide > shortSide ? shortSide : longSide);
                resValue.add(new ArrayList<>(lineList));
                lineList.clear();

            }
            Collections.sort(resValue, new MyComparator());

            System.out.println("测试");
            for (int i = 0; i < resValue.size(); i++) {
                for (int j = 0; j < 2; j++) {
                    System.out.print(resValue.get(i).get(j) + " ");
                }
            }


            boolean a =
                    resValue.get(0).get(0) == resValue.get(1).get(0);
            boolean b = resValue.get(1).get(0) == resValue.get(2).get(0);
            boolean c = resValue.get(2).get(0) == resValue.get(3).get(0);

            boolean e = resValue.get(0).get(1) == resValue.get(1).get(1);
            boolean f = resValue.get(1).get(1) == resValue.get(4).get(0);
            boolean g = resValue.get(4).get(0) == resValue.get(5).get(0);

            boolean h = resValue.get(2).get(1) == resValue.get(3).get(1);
            boolean k = resValue.get(3).get(1) == resValue.get(4).get(1);
            boolean m = resValue.get(4).get(1) == resValue.get(5).get(1);

            if (a && b && c && e && f && g && h && k && m) {
                System.out.println("POSSIBLE");
            } else {
                System.out.println("IMPOSSIBLE");
            }
            resValue.clear();
            num--;
        }
    }

    static class MyComparator implements Comparator<List<Integer>> {

        @Override
        public int compare(List<Integer> o1, List<Integer> o2) {
            if (o1.get(0) > o2.get(0)) {
                return -1;
            } else if ((o1.get(0) == o2.get(0)) && (o1.get(1) > o2.get(1))) {
                return -1;
            } else {
                return 1;
            }
        }
    }

}