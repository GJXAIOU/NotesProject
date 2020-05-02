package array.medium;

import java.util.*;

/**
 * @Author GJXAIOU
 * @Date 2020/2/9 12:51
 */
public class LeetCode347 {
    // 方法一：哈希表 + 堆
    public List<Integer> topKFrequent(int[] nums, int k) {
        if (nums.length == 0 || nums == null) {
            return new ArrayList<>();
        }

        // 使用哈希表，统计每个元素出现的次数，元素为键，元素出现的次数为值
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        // 遍历 map，用最小堆保存频率最大的k个元素，注：Java 中使用优先级队列实现堆
        // 因为优先级队列默认就是最小堆，但是是对 key 进行排序，这里重写是需要对 value 进行排序
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2);
            }
        });

        for (Integer key : map.keySet()) {
            // heap.size() 则当前 heap 中前几
            if (heap.size() < k) {
                heap.add(key);
                // 如果 heap 的大小到了 k，新加入元素和堆顶进行比较，大的留下
            } else if (map.get(key) > map.get(heap.peek())) {
                heap.remove();
                heap.add(key);
            }
        }

        // 取出堆中所有元素即可
        ArrayList<Integer> ansList = new ArrayList<>();
        while (!heap.isEmpty()) {
            ansList.add(heap.remove());
        }
        return ansList;
    }
}
