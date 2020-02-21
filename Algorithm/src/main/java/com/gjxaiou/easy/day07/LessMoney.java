package com.gjxaiou.easy.day07;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LessMoney {

    public static int lessMoney(int[] arr) {
        // Java 中小根堆可以采用优先级队列实现，如果实现大根堆重写比较器即可
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        // 将数组中元素全部塞入优先级队列
        for (int i = 0; i < arr.length; i++) {
            priorityQueue.add(arr[i]);
        }

        int sum = 0;
        int cur = 0;
        while (priorityQueue.size() > 1) {
            cur = priorityQueue.poll() + priorityQueue.poll();
            sum += cur;
            priorityQueue.add(cur);
        }
        return sum;
    }

    public static class MinheapComparator implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    public static class MaxheapComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1; // <   o2 < o1
        }

    }

    public static void main(String[] args) {
        // solution
        int[] arr = {10,20,30};
        System.out.println("形成 10，20,30 最少需要成本为：" + lessMoney(arr));

        int[] arrForHeap = {1,2,6,4,3,7,1,8};

        System.out.println("{1,2,6,4,3,7,1,8}形成的最小堆为：");
        // min heap
        PriorityQueue<Integer> minQ1 = new PriorityQueue<>();
        for (int i = 0; i < arrForHeap.length; i++) {
            minQ1.add(arrForHeap[i]);
        }
        while (!minQ1.isEmpty()) {
            System.out.print(minQ1.poll() + " ");
        }
        System.out.println();

        // min heap use Comparator
        PriorityQueue<Integer> minQ2 = new PriorityQueue<>(new MinheapComparator());
        for (int i = 0; i < arrForHeap.length; i++) {
            minQ2.add(arrForHeap[i]);
        }
        while (!minQ2.isEmpty()) {
            System.out.print(minQ2.poll() + " ");
        }
        System.out.println();

        System.out.println("{1,2,6,4,3,7,1,8}形成的最大堆为：");
        // max heap use Comparator
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(new MaxheapComparator());
        for (int i = 0; i < arrForHeap.length; i++) {
            maxQ.add(arrForHeap[i]);
        }
        while (!maxQ.isEmpty()) {
            System.out.print(maxQ.poll() + " ");
        }
    }
}
