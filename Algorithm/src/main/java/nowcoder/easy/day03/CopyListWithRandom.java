package nowcoder.easy.day03;

import java.util.HashMap;

public class CopyListWithRandom {

	public static class Node {
		public int value;
		public Node next;
		public Node rand;

		public Node(int data) {
			this.value = data;
		}
	}

	// 首先准备一个 hash 表
	public static Node copyListWithRand1(Node head) {
		HashMap<Node, Node> map = new HashMap<Node, Node>();
		Node cur = head;
		while (cur != null) {
			// cur：在原链表中遍历到的节点，后面的 new Node() 就是生成对应的拷贝节点；最后放入 map
			map.put(cur, new Node(cur.value));
			cur = cur.next;
		}
		cur = head;
		while (cur != null) {
			// 拷贝节点的 next 应该指向原来 cur 节点的 next节点对应的 value值；
			map.get(cur).next = map.get(cur.next);
			map.get(cur).rand = map.get(cur.rand);
			cur = cur.next;
		}
		return map.get(head);
	}

	public static Node copyListWithRand2(Node head) {
		if (head == null) {
			return null;
		}

		// 复制新的结点链接到对应结点后面
		Node cur = head;
		Node nextNode = null;
		while (cur != null) {
			nextNode = cur.next;
			cur.next = new Node(cur.value);
			cur.next.next = nextNode;
			cur = nextNode;
		}

		// 两个结点一起，根据源节点对其对应的复制结点赋值
		cur = head;
		Node curCopy = null;
		while (cur != null) {
			nextNode = cur.next.next;
			curCopy = cur.next;
			// 复制结点的 random = 原来结点的 random 的下一节点（该结点为原来结点的 random 结点的复制结点）
			curCopy.rand = cur.rand != null ? cur.rand.next : null;
			cur = nextNode;
		}
		Node res = head.next;
		cur = head;
		// split
		while (cur != null) {
			nextNode = cur.next.next;
			curCopy = cur.next;
			cur.next = nextNode;
			curCopy.next = nextNode != null ? nextNode.next : null;
			cur = nextNode;
		}
		return res;
	}

	public static void printRandLinkedList(Node head) {
		Node cur = head;
		System.out.print("order: ");
		while (cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.next;
		}
		System.out.println();
		cur = head;
		System.out.print("rand:  ");
		while (cur != null) {
			System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
			cur = cur.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head = null;
		Node res1 = null;
		Node res2 = null;
		printRandLinkedList(head);
		res1 = copyListWithRand1(head);
		printRandLinkedList(res1);
		res2 = copyListWithRand2(head);
		printRandLinkedList(res2);
		printRandLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);

		head.rand = head.next.next.next.next.next; // 1 -> 6
		head.next.rand = head.next.next.next.next.next; // 2 -> 6
		head.next.next.rand = head.next.next.next.next; // 3 -> 5
		head.next.next.next.rand = head.next.next; // 4 -> 3
		head.next.next.next.next.rand = null; // 5 -> null
		head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

		printRandLinkedList(head);
		res1 = copyListWithRand1(head);
		printRandLinkedList(res1);
		res2 = copyListWithRand2(head);
		printRandLinkedList(res2);
		printRandLinkedList(head);
		System.out.println("=========================");
	}
}
