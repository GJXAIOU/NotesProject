package com.gjxaiou.easy.day05;

import java.util.HashMap;
import java.util.List;

/**
 * 查找两个节点是否在同一个集合中
 * @author GJXAIOU
 */
public class UnionFind {

	public static class Node {
		// 可以是 String，Int，等等类型
	}

	// 初始化并查集中数据
	public static class UnionFindSet {
		// key:child,value:father节点
		public HashMap<Node, Node> fatherMap;
		// 某结点（使用代表结点即可）所在集合中有多少个结点
		public HashMap<Node, Integer> sizeMap;

		// 初始化的时候创建两个表，用户必须给定数据
		public UnionFindSet(List<Node> nodes) {
			makeSets(nodes);
		}

		private void makeSets(List<Node> nodes) {
			fatherMap = new HashMap<Node, Node>();
			sizeMap = new HashMap<Node, Integer>();
			for (Node node : nodes) {
				// 每个结点构成一个集合
				fatherMap.put(node, node);
				// 每个集合中都加上 size 为 1；
				sizeMap.put(node, 1);
			}
		}

		// 找头，并将其扁平化
		private Node findHead(Node node) {
			Node father = fatherMap.get(node);
			if (father != node) {
				father = findHead(father);
			}
			// 然后将 father 为头结点，将其挂在所有结点上。
			fatherMap.put(node, father);
			return father;
		}
		// 上面方法的非递归版本
//		private Node findHead(Node node) {
//			Stack<Node> stack = new Stack<Node>();
//			Node cur = node;
//			Node parent = fatherMap.get(cur);
//			// 一直向上找，找到头结点，其余结点放在栈中
//			while (cur != parent){
//				stack.push(cur);
//				cur = parent;
//				parent = fatherMap.get(cur);
//			}
//			// 将栈中所有元素挨个弹出，并将它们的父结点设置为头结点。
//			while (! stack.isEmpty()){
//				fatherMap.put(stack.pop(), parent);
//			}
//			return  parent;
//		}

		public boolean isSameSet(Node a, Node b) {
			return findHead(a) == findHead(b);
		}

		public void union(Node a, Node b) {
			if (a == null || b == null) {
				return;
			}
			Node aHead = findHead(a);
			Node bHead = findHead(b);
			if (aHead != bHead) {
				int aSetSize= sizeMap.get(aHead);
				int bSetSize = sizeMap.get(bHead);
				if (aSetSize <= bSetSize) {
					fatherMap.put(aHead, bHead);
					sizeMap.put(bHead, aSetSize + bSetSize);
				} else {
					fatherMap.put(bHead, aHead);
					sizeMap.put(aHead, aSetSize + bSetSize);
				}
			}
		}
	}

	public static void main(String[] args) {

	}
}
