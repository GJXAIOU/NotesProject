package nowcoder.advanced.day04;

import java.util.HashMap;

public class Code_02_LRU {

    public static class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> last;
        public Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static class NodeDoubleLinkedList<K, V> {
        private Node<K, V> head;
        private Node<K, V> tail;

        public NodeDoubleLinkedList() {
            this.head = null;
            this.tail = null;
        }

        // 自定义双向链表添加结点操作
        public void addNode(Node<K, V> newNode) {
            if (newNode == null) {
                return;
            }
            if (this.head == null) {
                this.head = newNode;
                this.tail = newNode;
            } else {
                this.tail.next = newNode;
                newNode.last = this.tail;
                this.tail = newNode;
            }
        }

        // 将给定的节点移动到链表的尾部
        public void moveNodeToTail(Node<K, V> node) {
            if (this.tail == node) {
                return;
            }
            // 头结点处理
            if (this.head == node) {
                this.head = node.next;
                this.head.last = null;
                // 普通结点处理
            } else {
                node.last.next = node.next;
                node.next.last = node.last;
            }
            node.last = this.tail;
            node.next = null;
            this.tail.next = node;
            this.tail = node;
        }

        // 移除头部结点
        public Node<K, V> removeHead() {
            if (this.head == null) {
                return null;
            }
            Node<K, V> res = this.head;
            // 只有一个结点情况下
            if (this.head == this.tail) {
                this.head = null;
                this.tail = null;
                // 不止一个结点的情况下
            } else {
                this.head = res.next;
                res.next = null;
                this.head.last = null;
            }
            return res;
        }
    }

    /**
     * 注意：注意：注意：当 map<key, value> 中 key 和 value 是基本数据类型或者 string 类型的时候，map
     * 中存放的是值，如果是自定义数据类型，存放的是内存地址（一个引用）。
     *
     * @param <K>
     * @param <V>
     */
    public static class MyCache<K, V> {
        private HashMap<K, Node<K, V>> keyNodeMap;
        private NodeDoubleLinkedList<K, V> nodeList;
        private int capacity;

        public MyCache(int capacity) {
            if (capacity < 1) {
                throw new RuntimeException("should be more than 0.");
            }
            this.keyNodeMap = new HashMap<K, Node<K, V>>();
            this.nodeList = new NodeDoubleLinkedList<K, V>();
            this.capacity = capacity;
        }

        public V get(K key) {
            if (this.keyNodeMap.containsKey(key)) {
                Node<K, V> res = this.keyNodeMap.get(key);
                // 在上面得到 Node 之后，然后下面在双向链表中将 Node 移动到尾部
                this.nodeList.moveNodeToTail(res);
                return res.value;
            }
            return null;
        }

        public void set(K key, V value) {
            if (this.keyNodeMap.containsKey(key)) {
                Node<K, V> node = this.keyNodeMap.get(key);
                // 将该结点值赋值为新值
                node.value = value;
                // 进行 set，将优先级提到最后
                this.nodeList.moveNodeToTail(node);
            } else {
                // 如果没有该结点，新建 Node，然后装入 Key - value
                Node<K, V> newNode = new Node<K, V>(key, value);
                // 在 Map 和双向链表中加入该结点
                this.keyNodeMap.put(key, newNode);
                this.nodeList.addNode(newNode);
                // 如果发现 map 大小超了，移除尾部的节点
                if (this.keyNodeMap.size() == this.capacity + 1) {
                    this.removeMostUnusedCache();
                }
            }
        }

        // 超过长度删除尾部，增加新节点
        private void removeMostUnusedCache() {
            // 删除 Node 在双向链表中位置
            Node<K, V> removeNode = this.nodeList.removeHead();
            // 删除 Map 中节点
            K removeKey = removeNode.key;
            this.keyNodeMap.remove(removeKey);
        }

    }

    public static void main(String[] args) {
        MyCache<String, Integer> testCache = new MyCache<String, Integer>(3);
        testCache.set("A", 1);
        testCache.set("B", 2);
        testCache.set("C", 3);
        System.out.println(testCache.get("B"));
        System.out.println(testCache.get("A"));
        testCache.set("D", 4);
        System.out.println(testCache.get("D"));
        System.out.println(testCache.get("C"));

    }

}
