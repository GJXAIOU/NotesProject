package math.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/2/4 14:40
 */
public class LeetCode190 {

    public int reverseBits(int n) {
        int res = 0;
        int count = 0;
        while (count < 32) {
            // res 左移一位空出位置
            res <<= 1;
            // 得到的最低位加过来，因为 1 对应二进制为：00000...0001，所以 & 之后获得最后一位
            res |= (n & 1);
            // 原数字右移一位去掉已经处理过的最低位
            n >>= 1;
            count++;
        }
        return res;
    }
}
