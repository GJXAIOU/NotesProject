package array.easy;


/**
 * @author GJXAIOU
 * @create 2020/05/05 14:38
 */
public class Demo {
    public static void main(String[] args) {
        int x = 10;
        if ((x & 1) == 1) {
            System.out.println(x + "为奇数\n");
        } else {
            System.out.println(x + "为偶数\n");
        }

        // 因为 1 正好处于二进制的第一位，所以如果需要将 1 移动到第 n + 1位，只需要移动 n 次，因此这里是 << n 操作。
        int n = 4;
        if ((x & (1 << n)) != 0) {
            System.out.println("x 的第 n + 1 位为：1");
        } else {
            System.out.println("x 的第 n + 1 位为：0");
        }

        // 交换两个数的值
        int a = 10;
        int b = 20;
        System.out.println("交换前: a = " + a + " b = " + b);

        System.out.println("执行 a = a ^ b: 即 a ^ b = ab");
        a = a ^ b;

        System.out.println("执行 a = a ^ b：即 ab ^ b = a");
        b = a ^ b;

        System.out.println("执行 a = a ^ b：即 ab ^ a = b");
        a = a ^ b;

        System.out.println("交换后: a = " + a + " b = " + b);


        int arr[] = {1, 1, 2, 2, 3, 4, 4};
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            ans ^= arr[i];
        }
        System.out.println("ans = " + ans);
    }
}
