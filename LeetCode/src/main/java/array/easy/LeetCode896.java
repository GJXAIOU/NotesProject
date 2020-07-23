package array.easy;

/**
 * @author GJXAIOU
 * @create 2020/05/19 22:10
 */
public class LeetCode896 {
    // 方法一：两次遍历
    public boolean isMonotonic(int[] A) {
        return increasing(A) || decreasing(A);
    }

    public boolean increasing(int[] A) {
        for (int i = 0; i < A.length - 1; ++i) {
            if (A[i] > A[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public boolean decreasing(int[] A) {
        for (int i = 0; i < A.length - 1; ++i) {
            if (A[i] < A[i + 1]) {
                return false;
            }
        }
        return true;
    }

    // 方法二：一次遍历
    public boolean isMonotonic2(int[] A) {
        int store = 0;
        for (int i = 0; i < A.length - 1; ++i) {
            int c = Integer.compare(A[i], A[i + 1]);
            if (c != 0) {
                if (c != store && store != 0) {
                    return false;
                }
                store = c;
            }
        }
        return true;
    }


    // 方法三：计算递增或者递减次数
    public boolean isMonotonic3(int[] A) {
        //输入合法性判断
        if (A == null) {
            return true;
        }

        int ans1 = 0;
        int ans2 = 0;
        for (int i = 0; i < A.length - 1; i++) {
            //等于0的时候两个都加，不能归于上面两个判断中，都在判断递增数列会出问题；
            if (A[i + 1] - A[i] < 0) {
                ans1++;
            } else if (A[i + 1] - A[i] > 0) {
                ans2++;
            } else if (A[i + 1] - A[i] == 0) {
                ans1++;
                ans2++;
            }
        }
        if (ans1 == A.length - 1 || ans2 == A.length - 1) {
            return true;
        }

        return false;
    }
}
