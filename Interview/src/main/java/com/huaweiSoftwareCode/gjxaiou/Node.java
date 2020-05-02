package com.huaweiSoftwareCode.gjxaiou;

import java.util.ArrayList;

public class Node {

	public int value;
	// in 表示入度：多少个结点指向我
	public int in;
	public int out;
	// 邻居节点：从我出发能到达的下一个结点
	public ArrayList<Node> nexts;
	// 从我出发发散出边的集合
	public ArrayList<Edge> edges;

	public Node(int value) {
		this.value = value;
		in = 0;
		out = 0;
		nexts = new ArrayList<>();
		edges = new ArrayList<>();
	}
}
