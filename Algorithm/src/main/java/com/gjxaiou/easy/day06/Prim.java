package com.gjxaiou.easy.day06;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

// undirected graph only
public class Prim {

    public static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }

    }

    // 不牵涉到两个集合的合并问题，只是从一个点出现，通过边的连接让一个点加入一个大的集合中，所以使用 HashSet 即可。
    public static Set<Edge> primMST(Graph graph) {
        // 边的优先级队列（小根堆）
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(
                new EdgeComparator());
        HashSet<Node> set = new HashSet<>();
        Set<Edge> result = new HashSet<>();
        // 如果只有一个联通图，则该 for 循环可以删除，这是考虑森林的情况，则多个联通图之间不连接的情况。
        for (Node node : graph.nodes.values()) {
            // node 不在集合中
            if (!set.contains(node)) {
                // 加入集合中
                set.add(node);
                // 该新加入的结点连接的所有边都加入队列中
                for (Edge edge : node.edges) {
                    priorityQueue.add(edge);
                }
                while (!priorityQueue.isEmpty()) {
                    // 从优先级队列中弹出一个最小的边
                    Edge edge = priorityQueue.poll();
                    // 看该边连接的 to 结点在不在集合中咋将 toNode 加入，同时该边也是有效的，所有加入结果
                    Node toNode = edge.to;
                    if (!set.contains(toNode)) {
                        set.add(toNode);
                        result.add(edge);
                        // 将该 toNode 的所有边全部加入优先级队列
                        for (Edge nextEdge : toNode.edges) {
                            priorityQueue.add(nextEdge);
                        }
                    }
                }
            }
        }
        return result;
    }

}
