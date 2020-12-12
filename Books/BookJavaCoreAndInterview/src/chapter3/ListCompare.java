package chapter3;
import java.util.ArrayList;
import java.util.LinkedList;
import	java.util.List;
import java.util.Random;


/**
 * @author GJXAIOU
 * @create 2019-08-17-14:11
 */
public class ListCompare {
    public static void main(String [] args) {
        ListCompare listCompare = new ListCompare();
        List arrayList = new ArrayList();
        List linkedList = new LinkedList();

        // 比较两者在批量向末尾添加数据时候时间差
        listCompare.testAddAtTail(arrayList, "arraylist");
        listCompare.testAddAtTail(linkedList, "linkList");

        // 比较两者在查找某个元素的时间差
        listCompare.testRandomSearch(arrayList, "arrayList");
        listCompare.testRandomSearch(linkedList, "linkList");

        listCompare.testAddAtRandom(arrayList, "arraylist");
        listCompare.testAddAtRandom(linkedList, "linkList");



    }

    // 在尾部添加数据
    public void testAddAtTail(List list , String string){
        int size = 10000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(string);
    }

    // 随机查找数据
    public void testRandomSearch(List list, String string){
        Random random = new Random();
        long start = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++) {
            list.indexOf(random.nextInt(1000));
        }
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(string);
    }

    // 随机添加数据
    public void testAddAtRandom(List list, String string){
        Random random = new Random();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            list.add(random.nextInt(1000), "0");
        }
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(string);
    }
}
