package com.gjxaiou.advanced.day04;

import java.util.HashMap;

public class LFU {

    // 这是竖着挂着的小链表结构
    public static class Node {
        public Integer key;
        public Integer value;
        public Integer times;
        public Node up;
        public Node down;

        public Node(int key, int value, int times) {
            this.key = key;
            this.value = value;
            this.times = times;
        }
    }

    public static class LFUCache {
        // 包括横向对应的头结点的整个竖着的链表结构，包含上面的 Node 节点
        public static class NodeList {
            public Node head;
            public Node tail;
            public NodeList last;
            public NodeList next;

            public NodeList(Node node) {
                head = node;
                tail = node;
            }

            public void addNodeFromHead(Node newHead) {
                newHead.down = head;
                head.up = newHead;
                head = newHead;
            }

            public boolean isEmpty() {
                return head == null;
            }

            // 删掉 NodeList 这一个竖串中任意一个结点，例如中间某个结点使用 get 操作之后， times + 1，即从当前 NodeList 中删除然后加到下一个
            //NodeList 中。
            public void deleteNode(Node node) {
                // 仅剩唯一一个结点的时候
                if (head == tail) {
                    head = null;
                    tail = null;
                } else {
                    // 如果为头指针，换头
                    if (node == head) {
                        head = node.down;
                        head.up = null;
                        // 如果为尾指针，换尾
                    } else if (node == tail) {
                        tail = node.up;
                        tail.down = null;
                        // 如果为中间节点，则将该结点上下结点指针连接好即可
                    } else {
                        node.up.down = node.down;
                        node.down.up = node.up;
                    }
                }
                // 将结点与整个链表关联性删除
                node.up = null;
                node.down = null;
            }
        }


        private int capacity;
        private int size;
        // key（Integer）对应一个 Node
        private HashMap<Integer, Node> records;
        private HashMap<Node, NodeList> heads;
        // 记录整个大双向链表 NodeList 的头部，因为头部并不一定就是词频为 1 的 NodeList，因为如果词频为 1 的 Node 都没有，则整个词频为 1
        //的 NodeList 也就删除了。
        private NodeList headList;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.records = new HashMap<>();
            this.heads = new HashMap<>();
            headList = null;
        }

        public void set(int key, int value) {
            // 如果该 key 存在，通过该 key 将 Node 的内存地址拿出，就可以访问到 Node 结构
            if (records.containsKey(key)) {
                Node node = records.get(key);
                node.value = value;
                node.times++;
                // 该 Node 属于原来的哪个 NodeList
                NodeList curNodeList = heads.get(node);
                // 将该 Node 移动到原来 NodeList + 1 位置上；
                move(node, curNodeList);
            } else {
                // 如果达到容量，需要将整个 NodeList 的头部的大 List 下面挂载的尾结点删除
                if (size == capacity) {
                    Node node = headList.tail;
                    headList.deleteNode(node);
                    // 移除元素之后涉及到原来 NodeList 中可能没有元素换头，以及后加入元素也要换头问题（只原来最小是 2 次出现，然后新加一个
                    //Node，不仅需要创建词频为 1 的 NodeList，还要将原来指向 3  的 headList 指向 1）
                    modifyHeadList(headList);
                    // 消除该结点影响
                    records.remove(node.key);
                    heads.remove(node);
                    size--;
                }
                // 如果没有改元素，新建 Node 并且赋值
                Node node = new Node(key, value, 1);
                // 如果没有 headList，新建并将 Node 放入
                if (headList == null) {
                    headList = new NodeList(node);
                } else {
                    // 如果 NodeList 中有该词频的链表，直接挂在上面即可
                    if (headList.head.times.equals(node.times)) {
                        headList.addNodeFromHead(node);
                    } else {
                        // 新建大头 NodeList，然后挂入
                        NodeList newList = new NodeList(node);
                        newList.next = headList;
                        headList.last = newList;
                        headList = newList;
                    }
                }
                records.put(key, node);
                heads.put(node, headList);
                size++;
            }
        }

        private void move(Node node, NodeList oldNodeList) {
            // 从老链表中删除该 Node
            oldNodeList.deleteNode(node);
            // 原 NodeList 为 3 - 4 - 6 ，如果get NodeList 4 下面元素，如果 get 之后还有元素，则是在 4 和 6 直接新建 5，然后4 -
            //5 -6，如果 get 之后没有元素了，则新建 5，然后是 3 - 5 - 6
            // preList 是 oldNodeList 的前一个，如果 modifyHeadList（）为真，则新的链表的前面 NodeList 为老链表的前一个
            //NodeList，否则就是 oldNodeList 本身
            NodeList preList = modifyHeadList(oldNodeList) ? oldNodeList.last
                    : oldNodeList;
            NodeList nextList = oldNodeList.next;
            // 如果 nextList 为空，则 oldNodeList 就是整个大链表的尾部
            if (nextList == null) {
                // 新建 NodeList，然后挂上 Node
                NodeList newList = new NodeList(node);
                // 大链表重连
                if (preList != null) {
                    preList.next = newList;
                }
                newList.last = preList;
                if (headList == null) {
                    headList = newList;
                }
                // Node 放入新的 NodeList 中
                heads.put(node, newList);
            } else {
                // 原链表后面存在当前词频的 + 1
                if (nextList.head.times.equals(node.times)) {
                    nextList.addNodeFromHead(node);
                    heads.put(node, nextList);
                } else {
                    // 新建一个 +1 词频的 NodeList，然后重连
                    NodeList newList = new NodeList(node);
                    if (preList != null) {
                        preList.next = newList;
                    }
                    newList.last = preList;
                    newList.next = nextList;
                    nextList.last = newList;
                    if (headList == nextList) {
                        headList = newList;
                    }
                    heads.put(node, newList);
                }
            }
        }

        // Node 删除之后，判断这个 NodeList 是否也需要删除
        private boolean modifyHeadList(NodeList nodeList) {
            // 如果 NodeList 不为空，直接返回 false，如果为空则需要删除
            if (nodeList.isEmpty()) {
                // 如果该 NodeList 正好是整个大链表的头部，
                if (headList == nodeList) {
                    // 首先将头指向老头部的下一个
                    headList = nodeList.next;
                    // 如果不为空，这里的 headList 已经是新头部了，让其前指向空
                    if (headList != null) {
                        headList.last = null;
                    }
                } else {
                    // 如果是大链表中间元素，将整个 NodeList 前后直接连接即可
                    nodeList.last.next = nodeList.next;
                    if (nodeList.next != null) {
                        nodeList.next.last = nodeList.last;
                    }
                }
                return true;
            }
            return false;
        }

        public int get(int key) {
            // 不存在就返回空
            if (!records.containsKey(key)) {
                return -1;
            }
            Node node = records.get(key);
            node.times++;
            NodeList curNodeList = heads.get(node);
            move(node, curNodeList);
            return node.value;
        }
    }
}