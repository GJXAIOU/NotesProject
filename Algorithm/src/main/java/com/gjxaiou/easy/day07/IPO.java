package com.gjxaiou.easy.day07;

import java.util.Comparator;
import java.util.PriorityQueue;

public class IPO {
    // 构造每个任务
    public static class Node {
        public int profit;
        public int cost;

        public Node(int profit, int cost) {
            this.profit = profit;
            this.cost = cost;
        }
    }

    // 下面两个用于实现小根堆和大根堆
    public static class MinCostComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.cost - o2.cost;
        }
    }

    public static class MaxProfitComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.profit - o1.profit;
        }
    }


    public static int findMaximizedCapital(int k, int m, int[] profits, int[] cost) {
        // 首先组装每个任务
        Node[] nodes = new Node[profits.length];
        for (int i = 0; i < profits.length; i++) {
            nodes[i] = new Node(profits[i], cost[i]);
        }

        PriorityQueue<Node> minCostQ = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Node> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
        // 首先将任务放入小根堆中
        for (int i = 0; i < nodes.length; i++) {
            minCostQ.add(nodes[i]);
        }
        // 开始做任务，一共最多做 K 次
        for (int i = 0; i < k; i++) {
            // 一直看小根堆堆顶，只要成本小于 m，则弹出该值并加入大根堆
            while (!minCostQ.isEmpty() && minCostQ.peek().cost <= m) {
                maxProfitQ.add(minCostQ.poll());
            }
            // 做不到 K 项目，即大根堆空了（资金不能做其他项目）也得停
            if (maxProfitQ.isEmpty()) {
                return m;
            }
            m += maxProfitQ.poll().profit;
        }
        return m;
    }
}
