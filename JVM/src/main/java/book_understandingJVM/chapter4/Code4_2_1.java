package book_understandingJVM.chapter4;

/**
 * @Author GJXAIOU
 * @Date 2019/12/21 17:32
 */
public class Code4_2_1 {
    public static void main(String[] args) throws InterruptedException {
        int size = 1048576;
        byte[] myAlloc1 = new byte[4 * size];
        System.out.println("----111111111----");
        byte[] myAlloc2 = new byte[4 * size];
        System.out.println("----222222222----");
        Thread.sleep(1000000);
        byte[] myAlloc3 = new byte[4 * size];
        System.out.println("----333333333----");
        byte[] myAlloc4 = new byte[2 * size];
        System.out.println("----444444444----");
    }
}
