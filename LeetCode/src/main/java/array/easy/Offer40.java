package array.easy;

import java.util.Arrays;

/**
 * @Author GJXAIOU
 * @Date 2020/2/25 17:46
 */
public class Offer40 {
    /**
     * 方法一：排序
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr.length == 0 || k > arr.length) {
            return new int[0];
        }
        Arrays.sort(arr);
        int[] resArray = new int[k];
        for (int i = 0; i < k; i++) {
            resArray[i] = arr[i];
        }
        return resArray;
    }


    /**
     * 方法二：大根堆
     */
    public int[] getLeastNumbers2(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }
        int len = arr.length;
        if (k == len) {
            return arr;
        }
        // 步骤一：对 arr 数组的前 k 个元素建堆
        int[] heap = new int[k];
        for (int i = 0; i < k; i++) {
            heap[i] = arr[i];
        }
        buildHeap(heap);

        // 对后面的数进行遍历，如果比堆顶的数大则直接淘汰，反之则该元素加入堆，淘汰堆顶，然后对堆进行维护，即还是要保证堆顶的元素是这个堆中最大的数。
        for (int i = k; i < len; i++) {
            if (arr[i] < heap[0]) {
                // 替换堆顶
                heap[0] = arr[i];
                // 对堆进行重新调整
                heapify(heap, 0);
            }
        }
        return heap;
    }

    /**
     * 建堆过程
     * 其实是一颗完全二叉树，用数组实现。节点i的父节点和子节点可通过如下的计算得到。
     * parent = (i - 1) / 2; child1（左结点） = 2 * i + 1;, child2（右结点） = 2 * i + 2;
     */
    private void buildHeap(int[] nums) {
        // 最后一个节点
        int lastNode = nums.length - 1;
        // 最后一个节点的父节点
        int startHeapify = (lastNode - 1) / 2;
        // 开始 heapify 直到最后
        while (startHeapify >= 0) {
            heapify(nums, startHeapify--);
        }
    }


    /**
     * 维护大顶堆的函数，和当前节点的左右节点比较，如果节点中有更大的数，那么交换，并继续对交换后的节点进行维护
     *
     * @param nums
     * @param i    ：要维护的节点
     */
    private void heapify(int[] nums, int i) {
        int len = nums.length;
        if (i >= len) {
            return;
        }
        // 获取当前结点 i 的左右子节点
        int left = ((i << 1) + 1), right = ((i << 1) + 2);
        // 假定节点 i 是这三个点(i 节点，i 节点的左子节点，i 节点的右子节点)中最大的那个数
        int max = i;
        // 如果左子节点比较大，max更新为max = left;
        if (left < len && nums[left] > nums[max]) {
            max = left;
        }
        // 如果右子节点比较大，max更新为max = right;
        if (right < len && nums[right] > nums[max]) {
            max = right;
        }
        // 如果最大的数不是节点 i 的话，那么 heapify(nums, max)，即调整节点 i 的子树。
        if (max != i) {
            swap(nums, max, i);
            heapify(nums, max);
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}