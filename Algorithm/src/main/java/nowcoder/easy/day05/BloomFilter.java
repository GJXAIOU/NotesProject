package nowcoder.easy.day05;

/**
 * 使用基本数据结构实现布隆过滤器，如果想减少数组空间，可以使用 Long 类型或者使用矩阵
 * @author GJXIAOU
 * @create 2020/1/14 0014 下午 1:43
 */

public class BloomFilter {
    public static void main(String[] args) {
        // 使用 int 类型，因为一个 int 占 4 位即 32 bit，因此 1000 个 int 数据可以表示 32000bit；
        int[] arr = new int[1000];
        // 想要查询的 30000 位置对应哪一个下标；即将第 30000 位置描黑。
        int index = 30000;
        // 结果对应于上面数组中的 0 ~ 999 中间一个位置（桶）； intIndex = 937
        int intIndex = index / 32;
        // 对应于桶中的具体哪一个 bit 应该被描黑；bitIndex = 16
        int bitIndex = index % 32;
        // 1 << 16,即只有第 16 位为 1，其他均为0，同时 num | (1 << 16) 使得 num 的第 16 号位置变为 1，
        arr[intIndex] = (arr[intIndex] | (1 << bitIndex));
    }
}