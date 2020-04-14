package array.easy;

/**
 * @author GJXAIOU
 * @create 2020/04/14 17:15
 */
public class LeetCode733 {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        // 获取该位置原始颜色像素值
        int oldColor = image[sr][sc];
        // 从该点向周围遍历
        if (oldColor != newColor) {
            dfs(image, sr, sc, oldColor, newColor);
        }
        // 因为是可以直接在原数组上修改，所以直接返回 image 即可
        return image;
    }

    public void dfs(int[][] image, int sr, int sc, int oldColor, int newColor) {
        int[][] direct = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        // 别忘了先给自己染色
        image[sr][sc] = newColor;
        for (int i = 0; i < 4; i++) {
            int newSr = sr + direct[i][0];
            int newSc = sc + direct[i][1];
            boolean isIn =
                    (newSr >= 0 && newSr <= image.length - 1) && (newSc >= 0 && newSc <= image[0].length - 1);
            if (isIn && (image[newSr][newSc] == oldColor) && (image[newSr][newSc] != newColor)) {
                image[newSr][newSc] = newColor;
                dfs(image, newSr, newSc, oldColor, newColor);
            }
        }
    }
}
