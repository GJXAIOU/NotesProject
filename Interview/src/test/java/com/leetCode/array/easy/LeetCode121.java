package array.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/1/20 22:00
 */
public class LeetCode121 {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            // 不断找小的值
            if (prices[i] < minPrice) {
                minPrice = prices[i];
                // 如果有值比当前最小值大就计算最大值
            } else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }

    // ----- 对数器-----
    // 1.绝对正确的暴力方法
    public int absoluteMaxProfit(int[] prices) {
        int length = prices.length;

        int max = 0;

        for (int i = 0; i < length - 1; ++i) {
            for (int j = i + 1; j < length; ++j) {
                int temp = prices[j] - prices[i];
                max = temp > max ? temp : max;
            }
        }
        return max;
    }

    // 随机数产生器
    public static int[] generateRandom(int length, int maxValue) {
        int[] randomValue = new int[length];
        for (int i = 0; i < randomValue.length; i++) {
            randomValue[i] = (int) (Math.random() * maxValue);
        }
        return randomValue;
    }

    // 比较方法省略

    public static void main(String[] args) {
        LeetCode121 leetCode121 = new LeetCode121();
        int[] randomValue = generateRandom(40, 100);
        System.out.println("产生的随机值为：");
        for (int i : randomValue) {
            System.out.print(i + " ");
        }

        int i = leetCode121.maxProfit(randomValue);
        int j = leetCode121.absoluteMaxProfit(randomValue);
        System.out.println("\n 两方法得到的值为：");
        System.out.println("方法一：" + i);
        System.out.println("方法二：" + j);
        System.out.println("两者是否相等: " + (i == j));
    }
}
