/**
 * @Author GJXAIOU
 * @Date 2020/3/30 21:30
 */
public class Offer10II {
    // 版本一：暴力递归：超时
    public int numWays(int n) {
        // 最基础情况,应该为 if(n ==1),只是在这里 n < 1 返回结果和 n == 1 返回结果一致，所以两者合并。
        if (n <= 1) {
            return 1;
        }
        // 写完递归之后还要回来考虑基础情况是否完善
        if (n == 2) {
            return 2;
        }
        return numWays(n - 1) + numWays(n - 2);
    }

    // 版本二：记忆化递归
    public int numWays2(int n) {
        // 存放从第0~n阶爬到第n阶的爬法数，例如nemo[0]表示从第0阶爬到第n阶的爬法数
        long memo[] = new long[n + 1];
        // 得到从第0阶爬到第n阶的爬法数
        return (int) climbStairs(0, n, memo);
    }

    public long climbStairs(int i, int n, long memo[]) {
        if (i > n) { // 基本情况1--超出第n阶的走法不能算一种
            return 0;
        }
        if (i == n) { // 基本情况2--能到达第n阶的走法算一种
            return 1;
        }
        if (memo[i] > 0) { // 基本情况3--利用记忆化的到的昂贵结果直接得到几种走法
            return memo[i];
        }
        // 到达第n阶的前一次状态可能有两种，可能是爬两阶上来的，也可以是爬一阶上来的，要得到的结果是两种爬法数之和
        // 将这次的memo[i]记忆化，memo[i]表示从第i阶爬到第n阶的爬法数
        memo[i] = climbStairs(i + 1, n, memo) + climbStairs(i + 2, n, memo);
        // 返回记忆化结果中第i阶爬到第n阶的爬法数
        return memo[i] % 1000000007;
    }

    public static void main(String[] args) {
        Offer10II offer10II = new Offer10II();
        int i = offer10II.numWays(7);
        System.out.println(i);
    }
}
