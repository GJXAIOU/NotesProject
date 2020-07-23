import java.util.HashSet;
import java.util.Scanner;

/**
 * @Author GJXAIOU
 * @Date 2020/6/22 21:55
 */
public class Main2 {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            String[] input = scanner.nextLine().split(",");
            int maxValue = input.length - 1;
            int minValue = 0;
            int temp = Integer.valueOf(input[0]);
            boolean flag = true;
            HashSet tempSet = new HashSet();
            for (int i = 0; i < input.length; i++) {
                int cur = temp + Integer.valueOf(input[i]);
                if (tempSet.contains(cur)) {
                    System.out.println(false);
                    flag = false;
                    break;
                } else {
                    if (temp > maxValue || temp < minValue) {
                        System.out.println(false);
                        flag = false;
                        break;
                    } else {
                        tempSet.add(cur);
                    }
                }
            }
            if (flag){
                System.out.println(true);
            }
        }


}
