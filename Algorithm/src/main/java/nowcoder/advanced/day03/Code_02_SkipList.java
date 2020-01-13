package nowcoder.advanced.day03;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 跳表
 */
public class Code_02_SkipList {

    public static class SkipListNode {
        public Integer value;
        // 该调表 ArrayList 的长度表示有多少层，nextNodes[0] 表示第一层上 SkipListNode 节点的下一层是什么
        public ArrayList<SkipListNode> nextNodes;

        public SkipListNode(Integer value) {
            this.value = value;
            nextNodes = new ArrayList<SkipListNode>();
        }
    }

    public static class SkipListIterator implements Iterator<Integer> {
        SkipList list;
        SkipListNode current;

        public SkipListIterator(SkipList list) {
            this.list = list;
            this.current = list.getHead();
        }

        @Override
        public boolean hasNext() {
            return current.nextNodes.get(0) != null;
        }

        @Override
        public Integer next() {
            current = current.nextNodes.get(0);
            return current.value;
        }
    }

    public static class SkipList {
        // head 就是巨小，根据最大的层数决定
        private SkipListNode head;
        private int maxLevel;
        private int size;
        // 以什么概率出 0，则 1- 概率出 1
        private static final double PROBABILITY = 0.5;

        public SkipList() {
            size = 0;
            maxLevel = 0;
            head = new SkipListNode(null);
            head.nextNodes.add(null);
        }

        public SkipListNode getHead() {
            return head;
        }

        public void add(Integer newValue) {
            if (!contains(newValue)) {
                size++;
                int level = 0;
                while (Math.random() < PROBABILITY) {
                    level++;
                }
                // 如果当前 level 大于之前最大的 maxLevel，则头部一定要增加
                while (level > maxLevel) {
                    head.nextNodes.add(null);
                    maxLevel++;
                }
                // 当新加入元素总是从头部 head 的最高层往下移动的
                SkipListNode newNode = new SkipListNode(newValue);
                SkipListNode current = head;
                // 同一层，如果下一个位置数比当前位置数小，则往右移动；如果大了就往下移动
                // 每次都是从最高层开始往下找
                int levelAll = maxLevel;
                do {
                    current = findNext(newValue, current, levelAll);
                    if (level >= levelAll) {
                        newNode.nextNodes.add(0, current.nextNodes.get(level));
                        current.nextNodes.set(level, newNode);
                        level--;
                    }
                } while (levelAll-- > 0);
            }
        }

        public void delete(Integer deleteValue) {
            if (contains(deleteValue)) {
                SkipListNode deleteNode = find(deleteValue);
                size--;
                int level = maxLevel;
                SkipListNode current = head;
                do {
                    current = findNext(deleteNode.value, current, level);
                    if (deleteNode.nextNodes.size() > level) {
                        current.nextNodes.set(level, deleteNode.nextNodes.get(level));
                    }
                } while (level-- > 0);
            }
        }

        // Returns the skiplist node with greatest value <= e
        private SkipListNode find(Integer e) {
            return find(e, head, maxLevel);
        }

        // Returns the skiplist node with greatest value <= e
        // Starts at node start and level
        private SkipListNode find(Integer e, SkipListNode current, int level) {
            do {
                current = findNext(e, current, level);
            } while (level-- > 0);
            return current;
        }

        // Returns the node at a given level with highest value less than e
        private SkipListNode findNext(Integer e, SkipListNode current, int level) {
            SkipListNode next = current.nextNodes.get(level);
            while (next != null) {
                // 拿出 next 值
                Integer value = next.value;
                // 如果当前值小于拿出来的值，找到了
                if (lessThan(e, value)) {
                    break;
                }
                // 如果不小于，则往右走，所以 current 就是在该 level 层中最后一个小于当前数的
                current = next;
                next = current.nextNodes.get(level);
            }
            return current;
        }

        public int size() {
            return size;
        }

        public boolean contains(Integer value) {
            SkipListNode node = find(value);
            return node != null && node.value != null && equalTo(node.value, value);
        }

        public Iterator<Integer> iterator() {
            return new SkipListIterator(this);
        }

        /******************************************************************************
         * Utility Functions *
         ******************************************************************************/

        private boolean lessThan(Integer a, Integer b) {
            return a.compareTo(b) < 0;
        }

        private boolean equalTo(Integer a, Integer b) {
            return a.compareTo(b) == 0;
        }

    }

    public static void main(String[] args) {

    }

}
