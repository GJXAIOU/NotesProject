package array.easy;

/**
 * @author GJXAIOU
 * @create 2020/05/18 22:26
 */
public class LeetCode836 {
    // 方法一：投影
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        boolean xOverlap = !(rec1[2] <= rec2[0] || rec2[2] <= rec1[0]);
        boolean yOverlap = !(rec1[3] <= rec2[1] || rec2[3] <= rec1[1]);
        return xOverlap && yOverlap;
    }

    // 方法二：
    //这里默认的是两个矩形均与坐标轴平行
    public boolean isRectangleOverlap2(int[] rec1, int[] rec2) {
        // 看哪个矩形在左边
        int length = rec1[0] < rec2[0] ? Math.abs(rec1[2] - rec1[0]) : Math.abs(rec2[2] - rec2[0]);
        int width = rec1[1] < rec2[1] ? Math.abs(rec1[3] - rec1[1]) : Math.abs(rec2[3] - rec2[1]);

        boolean res =
                (Math.abs(rec1[0] - rec2[0]) >= length) || (Math.abs(rec1[1] - rec2[1]) >= width);
        return !res;
    }
}
