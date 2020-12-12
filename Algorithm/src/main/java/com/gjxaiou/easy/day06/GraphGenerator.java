package com.gjxaiou.easy.day06;

public class GraphGenerator {
    // matrix 样式见笔记图
    public static Graph createGraph(Integer[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            Integer weight = matrix[i][0];
            Integer from = matrix[i][1];
            Integer to = matrix[i][2];
            // 如果在 graph 中没有 from 这个点则建上这个点
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            // 如果在 graph 中没有 to 这个点则建上这个点
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }

            // 拿出 from 点和 to 点，建立一个新的边
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(weight, fromNode, toNode);

            // fromNode 邻居指向加上 toNode 结点
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            // fromNode 的边集（从 fromNode 出发的边的集合）加上该边
            fromNode.edges.add(newEdge);
            // 加到整个图的边集中
            graph.edges.add(newEdge);
        }
        return graph;
    }

}
