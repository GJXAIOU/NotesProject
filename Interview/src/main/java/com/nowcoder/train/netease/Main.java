package com.nowcoder.train.netease;


import java.util.ArrayList;

interface Alpha {
    void f();
}
class Beta implements Alpha {
    public void f() {
        System.out.println("Beta.f()");
    }
    public void g() {
        System.out.println("Beta.g()");
    }
}
public class Main {
//    public static void main(String[] args) {
//        Alpha a = new Beta();
//        a.f();
//        System.out.println(a.getClass().getName());
//        try {
//            Beta b = (Beta) a;
//            b.g();
//        } catch (Exception e) {
//            System.out.println("Error");
//        }
//
//
//        Nowcoder nowcoder = new Nowcoder();
//        int i = 0;
//        nowcoder.inc(i);
//        i = i++;
//        System.out.println(i);
//    }

//    public static void main(String[] args) {
//        ArrayList<String> l1 = new ArrayList<String>();
//        ArrayList<Integer> l2 = new ArrayList<Integer>();
//        l1.add("1");
//        l2.add(1);
//        System.out.println(l1.get(0).getClass());
//        System.out.println(l2.get(0).getClass());
//        System.out.println(l1.getClass() == l2.getClass());
//    }
//

    public static void main(String[] args) {
        int i = 0;
        int res = 1;
        while (true) {
            try {
                res += res / (i++);
                System.out.println("No exception");
            } catch (Exception e) {
                System.out.println("Zero exception");
            } finally {
                System.out.print("In finally clause");
                System.out.println(i);
                if (i == 2) {
                    break;
                }
            }
        }
    }

}

    class Nowcoder {
        void inc(int i) {
            i++;
        }
    }
