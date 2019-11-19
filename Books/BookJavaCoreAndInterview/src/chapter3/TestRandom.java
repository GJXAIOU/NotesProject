package chapter3;

/**
 * @author GJXAIOU
 * @create 2019-08-18-9:12
 */
import java.util.ArrayList;
import java.util.Random;

public class TestRandom {

    public static void main(String[] args) {

        // 案例2 :对于种子相同的Random对象，生成的随机数序列是一样的。
        // 使用种子为10的Random对象生成[0,20)内随机整数序列
        Random ran1 = new Random(10);
        System.out.println("序列1: \n");
        for (int i = 0; i < 8; i++) {
            System.out.print(ran1.nextInt(20) + " ");
        }

        Random ran2 = new Random(10);
        System.out.println("\n 序列2:\n");
        for (int i = 0; i < 8; i++) {
            System.out.print(ran2.nextInt(20) + " ");
        }


        // 案例3
        // 在没带参数构造函数生成的Random对象的种子缺省是当前系统时间的毫秒数。
        Random r3 = new Random();
        System.out.println("\n 序列3: \n");
        for (int i = 0; i < 10; i++) {
            System.out.print(r3.nextInt(10)+" ");
        }


        // 另外，直接使用Random无法避免生成重复的数字，如果需要生成不重复的随机数序列，需要借助数组和集合类
        ArrayList list=new TestRandom().getDiffNO(10);
        System.out.println("\n 产生的n个不同的随机数："+list);
    }

    /**
     * 生成n个不同的随机数，且随机数区间为[0,10)
     * @param n
     * @return
     */
    public ArrayList getDiffNO(int n){
        // 生成 [0-n) 个不重复的随机数
        // list 用来保存这些随机数
        ArrayList list = new ArrayList();
        Random rand = new Random();
        boolean[] bool = new boolean[n];
        int num = 0;
        for (int i = 0; i < n; i++) {
            do {
                // 如果产生的数相同继续循环
                num = rand.nextInt(n);
            } while (bool[num]);
            bool[num] = true;
            list.add(num);
        }
        return list;
    }


}