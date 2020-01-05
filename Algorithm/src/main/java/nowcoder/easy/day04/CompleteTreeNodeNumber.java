package nowcoder.easy.day04;

public class CompleteTreeNodeNumber {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static int nodeNum(Node head) {
		if (head == null) {
			return 0;
		}
		return bs(head, 1, mostLeftLevel(head, 1));
	}

	// node 表示当前节点， level：当前节点在第几层，h:整棵树的高度，为定值；返回值是以这个节点为头的子树一共有多少个节点；
	public static int bs(Node node, int level, int h) {
		if (level == h) {
			return 1;
		}
		// node 的右子树上的左边界到 h 层
		if (mostLeftLevel(node.right, level + 1) == h) {
			// 1 << (h - level)表示当前节点的左子树和当前节点的节点个数和，2^(h - level)个
			// 因为右孩子也是一个完全二叉树，使用递归求其总节点，就是后面部分；
			return (1 << (h - level)) + bs(node.right, level + 1, h);
		} else {
			// 没有到 h 层，则右树的高度比左树少一个，1 << (h - level - 1))就是右树所有节点加上当前节点个数，然后后面是左树也是完全二叉树，递归求解；
			return (1 << (h - level - 1)) + bs(node.left, level + 1, h);
		}
	}

	public static int mostLeftLevel(Node node, int level) {
		while (node != null) {
			level++;
			node = node.left;
		}
		return level - 1;
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		System.out.println(nodeNum(head));

	}

}
