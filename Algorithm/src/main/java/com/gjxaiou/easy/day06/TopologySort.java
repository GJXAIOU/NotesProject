package com.gjxaiou.easy.day06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologySort {

    // 要求：该图必须是有向且无环的
    public static List<Node> sortedTopology(Graph graph) {
        // inMap 为当前所有节点的入度
        HashMap<Node, Integer> inMap = new HashMap<>();
        Queue<Node> zeroInQueue = new LinkedList<>();
        // 遍历所有的点
        for (Node node : graph.nodes.values()) {
            // 把所有点和入度放入 Map
            inMap.put(node, node.in);
            // 入度为 0 的点放入队列
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        List<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            // 从入度为 0 的队列中拿出一个结点
            Node cur = zeroInQueue.poll();
            // 放入拓扑排序的下一个
            result.add(cur);
            // 然后该节点的所有下一个结点的入度统一减一（相当于删除该结点操作）
            for (Node next : cur.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                // 如果出现入度为 0 的节点加入队列中
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }
}
