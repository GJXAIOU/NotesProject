package com.gjxaiou.easy.day07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianQuick {

    public static class MedianHolder {
        // 首先创建两个堆，一个大根堆、一个小根堆
        // 大根堆中放的是较小的一半数，所以堆顶是较小的一半数的最大一个
        private PriorityQueue<Integer> maxHeap =
                new PriorityQueue<Integer>(new MaxHeapComparator());
        // 小根堆中放的是较大的一半数，所以堆顶是较大的一半数中最小的一个
        private PriorityQueue<Integer> minHeap =
                new PriorityQueue<Integer>(new MinHeapComparator());

        // 如果大根堆和小根堆的 size 差距 >= 2，则调整堆
        private void modifyTwoHeapsSize() {
            if (maxHeap.size() == minHeap.size() + 2) {
                minHeap.add(this.maxHeap.poll());
            }
            if (minHeap.size() == maxHeap.size() + 2) {
                maxHeap.add(minHeap.poll());
            }
        }

        // 添加新数的时候，如果大根堆为空则加入大根堆
        public void addNumber(int num) {
            if (maxHeap.isEmpty()) {
                maxHeap.add(num);
                return;
            }
            // 如果该数小于等于大根堆顶，放入大根堆
            if (maxHeap.peek() >= num) {
                maxHeap.add(num);
            } else {
                // 如果该数大于大根堆的堆顶
                if (minHeap.isEmpty()) {
                    minHeap.add(num);
                    return;
                }
                // 当然如果该数小于小根堆的顶部，还是应该放入大根堆，否则放入小根堆
                if (minHeap.peek() > num) {
                    maxHeap.add(num);
                } else {
                    minHeap.add(num);
                }
            }
            // 放入之后判断是否需要调整堆大小
            modifyTwoHeapsSize();
        }

        /**
         * 获取中位数，入口
         *
         * @return
         */
        public Integer getMedian() {
            int maxHeapSize = maxHeap.size();
            int minHeapSize = minHeap.size();
            if (maxHeapSize + minHeapSize == 0) {
                return null;
            }
            Integer maxHeapHead = maxHeap.peek();
            Integer minHeapHead = minHeap.peek();
            // 如果两个堆中元素数目和为偶数，则结果为两个堆顶和的一半
            if (((maxHeapSize + minHeapSize) & 1) == 0) {
                return (maxHeapHead + minHeapHead) / 2;
            }
            // 如果为奇数，则结果为较大的堆的堆顶
            return maxHeapSize > minHeapSize ? maxHeapHead : minHeapHead;
        }

    }

    public static class MaxHeapComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o2 > o1) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    public static class MinHeapComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o2 < o1) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    ///////////// 测试 //////////////////
    public static int[] getRandomArray(int maxLen, int maxValue) {
        int[] res = new int[(int) (Math.random() * maxLen) + 1];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue);
        }
        return res;
    }

    // for test, this method is ineffective but absolutely right
    public static int getMedianOfArray(int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(newArr);
        int mid = (newArr.length - 1) / 2;
        if ((newArr.length & 1) == 0) {
            return (newArr[mid] + newArr[mid + 1]) / 2;
        } else {
            return newArr[mid];
        }
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        boolean err = false;
        int testTimes = 200000;
        for (int i = 0; i != testTimes; i++) {
            int len = 30;
            int maxValue = 1000;
            int[] arr = getRandomArray(len, maxValue);
            MedianHolder medianHold = new MedianHolder();
            for (int j = 0; j != arr.length; j++) {
                medianHold.addNumber(arr[j]);
            }
            if (medianHold.getMedian() != getMedianOfArray(arr)) {
                err = true;
                printArray(arr);
                break;
            }
        }
        System.out.println(err ? "Bad" : "OK");
    }
}
