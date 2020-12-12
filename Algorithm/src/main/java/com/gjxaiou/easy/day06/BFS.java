package com.gjxaiou.easy.day06;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

	public static void bfs(Node node) {
		if (node == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<>();
		// set 用来判断该 node 点进没进过这个队列
		HashSet<Node> map = new HashSet<>();
		// 先把源节点加入队列和 set 中
		queue.add(node);
		map.add(node);

		while (!queue.isEmpty()) {
			// 首先弹出队列中元素，然后打印
			Node cur = queue.poll();
			System.out.println(cur.value);
			// 然后遍历弹出结点的所有 Next 结点，全部放入队列和 set 中
			for (Node next : cur.nexts) {
				if (!map.contains(next)) {
					map.add(next);
					queue.add(next);
				}
			}
		}
	}

}
