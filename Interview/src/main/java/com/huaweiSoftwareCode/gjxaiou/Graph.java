
package com.huaweiSoftwareCode.gjxaiou;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
	// 点的编号，实际的 Node
	public HashMap<Integer,Node> nodes;
	public HashSet<Edge> edges;

	public Graph() {
		nodes = new HashMap<>();
		edges = new HashSet<>();
	}
}