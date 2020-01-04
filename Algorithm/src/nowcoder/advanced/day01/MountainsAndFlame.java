package nowcoder.advanced.day01;

import java.util.Scanner;
import java.util.Stack;

/**
 * @Author GJXAIOU
 * @Date 2020/1/3 17:44
 */
public class MountainsAndFlame {
    public static void main(String[] args) {
        // 输入两部分值：数组长度和数组具体的内容
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int size = in.nextInt();
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = in.nextInt();
            }
            System.out.println(communications(arr));
        }
        in.close();
    }

    // 在环形数组中 i 位置的下一位，没到底则 +1，到底就为 0
    public static int nextIndex(int size, int i) {
        return i < (size - 1) ? (i + 1) : 0;
    }

    public static long getInternalSum(int n) {
        return n == 1L ? 0L : (long) n * (long) (n - 1) / 2L;
    }

    public static class Pair {
        public int value;
        public int times;

        public Pair(int value) {
            this.value = value;
            this.times = 1;
        }
    }

    // arr 为环形数组
    public static long communications(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int size = arr.length;
        // 找到最大值位置（第一个）
        int maxIndex = 0;
        for (int i = 0; i < size; i++) {
            maxIndex = arr[maxIndex] < arr[i] ? i : maxIndex;
        }
        // value 为最大值
        int value = arr[maxIndex];
        int index = nextIndex(size, maxIndex);
        long res = 0L;
        // 首先将最大值扔入栈中
        Stack<Pair> stack = new Stack<Pair>();
        stack.push(new Pair(value));
        // 因为是从 maxIndex 的下一个位置开始遍历的，所有如果值再次相等说明遍历结束
        while (index != maxIndex) {
            // 数组中的当前值
            value = arr[index];
            while (!stack.isEmpty() && stack.peek().value < value) {
                int times = stack.pop().times;
                // res += getInernalSum(times) +times;
                // res += stack.isEmpty() ? 0 : times;
                // 下面结果为上面两句合并
                res += getInternalSum(times) + 2 * times;
            }
            // 如果当前值使得栈顶弹出，然后栈顶和弹出之后露出的栈顶值相同，则原来栈顶数目 +1
            if (!stack.isEmpty() && stack.peek().value == value) {
                stack.peek().times++;
                // 如果和弹出之后露出的栈顶值不相等，则将当前值放入栈中
            } else {
                stack.push(new Pair(value));
            }
            index = nextIndex(size, index);
        }
        // 遍历结束之后剩余栈中元素进行结算
        while (!stack.isEmpty()) {
            int times = stack.pop().times;
            res += getInternalSum(times);
            if (!stack.isEmpty()) {
                res += times;
                if (stack.size() > 1) {
                    res += times;
                } else {
                    res += stack.peek().times > 1 ? times : 0;
                }
            }
        }
        return res;
    }
}
