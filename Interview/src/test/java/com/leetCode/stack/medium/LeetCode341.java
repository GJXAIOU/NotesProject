package stack.medium;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author GJXAIOU
 * @Date 2020/2/1 13:51
 */
public class LeetCode341 {
    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    public interface NestedInteger{
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();
        // @return the single integer that this NestedInteger holds, if it holds a single integer
        //Return null if this NestedInteger holds a nested list
        public Integer getInteger();
       // @return the nested list that this NestedInteger holds, if it holds a nested list
       // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    public class NestedIterator implements Iterator<Integer> {

        private LinkedList<Iterator<NestedInteger>> stack;
        private Integer num;
        private boolean flag;

        public NestedIterator(List<NestedInteger> nestedList) {
            // 构造一个栈 栈顶元素存放的是当前活跃的列表
            stack = new LinkedList<>();
            stack.add(nestedList.iterator());
        }

        @Override
        public Integer next() {
            flag = false;
            return num;
        }

        @Override
        public boolean hasNext() {
            if (stack.isEmpty()) {
                return false;
            }
            // 取出栈顶活跃的迭代器
            while (!stack.isEmpty() && !flag) {
                // 取出栈顶元素
                Iterator<NestedInteger> iterator = stack.peekFirst();
                if (!iterator.hasNext()) {
                    // 如果栈顶的迭代器已经是空了就出栈
                    stack.pollFirst();
                } else {
                    NestedInteger next = iterator.next();
                    if (next == null) {
                        continue;
                    }
                    if (next.isInteger()) {
                        num = next.getInteger();
                        flag = true;
                        iterator.remove();
                    } else {
                        stack.offerFirst(next.getList().iterator());
                        iterator.remove();
                    }
                }
            }

            return flag;
        }

    }

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
}
