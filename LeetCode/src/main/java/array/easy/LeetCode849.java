package array.easy;

/**
 * @author GJXAIOU
 * @create 2020/05/18 22:43
 */
public class LeetCode849 {
    public int maxDistToClosest(int[] seats) {

        int countLeft = 0;
        int countRight = 0;
        int countMid = 0;

        int i = 0;
        int j = seats.length - 1;
        int k = 0;
        //求开头开始连续0的个数
        while (i < seats.length) {
            if (seats[i] == 0) {
                countLeft++;
                i++;
            } else {
                break;
            }
        }

        //求结尾开始连续0的个数
        while (j > 0) {
            if (seats[j] == 0) {
                countRight++;
                j--;
            } else {
                break;
            }
        }

        //求中间连续0的个数
        //如果k = 0是值为0，上面已经求过了，如果值为1，因为计算0的连续个数，也不用考虑
        int count = 0;
        for (k = 1; k < seats.length; k++) {
            if (seats[k] == 0) {
                count++;
                countMid = countMid > count ? countMid : count;
            } else {
                count = 0;
            }
        }

        //中间连续零对应的最远距离
        if (countMid % 2 == 0) {
            countMid = countMid / 2;
        } else {
            countMid = countMid / 2 + 1;
        }

        int max = countLeft;
        if ((countMid) > max) {
            max = countMid;
        }
        if (countRight > max) {
            max = countRight;
        }

        return max;
    }
}
