package com.gjxaiou.easy.day06;

public class Edge {
    // 边的权重
    public int weight;
    // 边的 from 结点
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }

}
