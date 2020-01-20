package array.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/1/20 16:42
 */
public class LeetCode66 {
    // 注意：这里没有判断如果数组值为 MAX_VALUE 的时候溢出问题
    public int[] plusOne(int[] digits) {

        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] %= 10;
            // 如果不为 0，则说明加上 1 之后并没有产生进位问题。
            if (digits[i] != 0) {
                return digits;
            }
        }
        //针对[9,9],[9,9...9]等等直接将数组长度+1，然后将第一位置为1，其他均为0
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }


    //-------------对数器----------------------
    // 1.绝对正确的方法：将数组转换为字符串，然后转为 int 类型进行运算
    public int[] absolutePlusOne(int[] digits) {
        StringBuilder builder = new StringBuilder();
        int[] res = null;
        if (digits.length == 0 || digits == null) {
            return res;
        }
        for (int i = 0; i < digits.length; i++) {
            builder.append(digits[i]);
        }
        int ans = Integer.parseInt(builder.toString()) + 1;

        res = transfer(ans);
        return res;
    }

    public static int[] transfer(int a) {
        String str = null;
        str = Integer.toString(a);
        int[] arr = new int[str.length()];
        for (int i = 0; i < arr.length; i++) {
            char c = str.charAt(i);
            String s = String.valueOf(c);
            int num = Integer.parseInt(s);
            arr[i] = num;
        }
        return arr;
    }

    // 2.随机数产生器
    public int[] generateRandom(int length) {
        int[] randomValue = new int[length];
        for (int i = 0; i < randomValue.length; i++) {
            randomValue[i] = (int) (1 + Math.random() * (9 - 1 + 1));
        }
        return randomValue;
    }

    // 3.比较方法
    public boolean isEquals(int[] x, int[] y) {
        if (x.length != y.length) {
            return false;
        }
        for (int i = 0; i < x.length; i++) {
            if (x[i] != y[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode66 leetCode66 = new LeetCode66();
        int[] value = leetCode66.generateRandom(5);
        // 因为方法一是原地修改，所以复制数组作为方法二输入值
        int[] copyValue = new int[value.length];
        for (int i = 0; i < value.length; i++) {
            copyValue[i] = value[i];
        }

        System.out.println(" 原始输入值为：");
        for (int i = 0; i < value.length; i++) {
            System.out.print(value[i] + " ");
        }
        System.out.println("\n 方法一结果为：");
        int[] x = leetCode66.plusOne(value);
        for (int i = 0; i < x.length; i++) {
            System.out.print(x[i] + " ");
        }

        System.out.println("\n 方法二结果为：");
        int[] y = leetCode66.absolutePlusOne(copyValue);
        for (int i = 0; i < y.length; i++) {
            System.out.print(y[i] + " ");
        }

        System.out.println("\n 两者是否相等：");
        boolean equals = leetCode66.isEquals(x, y);
        System.out.println(equals);
    }
}
