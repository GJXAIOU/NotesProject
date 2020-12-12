package book_understandingJVM.chapter6;

/**
 * @Author GJXAIOU
 * @Date 2019/12/22 10:59
 */
public class Code6_5 {
    public int inc() {
        int x;
        try {
            x = 1;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
        }
    }

    public static void main(String[] args) {
        Code6_5 code6_5 = new Code6_5();
        System.out.println(code6_5.inc());
    }
}
