package nowcoder.huawei;

import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.XMLFormatter;

/**
 * @Author GJXAIOU
 * @Date 2020/3/11 22:30
 */
public class Huawei32 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();

        if (inputString == null || inputString.length() == 0) {
            return;
        }

        HashMap<Integer, Integer> referenceHashMap = new HashMap<>();
        int count = 0;
        for (int i = 0; i <= inputString.length() - 1; i++) {
            int value = Integer.valueOf(inputString.charAt(i));
            if ((value >= 0) && (value <= 127)) {
                if (!referenceHashMap.containsKey(value)){
                    count++;
                    referenceHashMap.put(value, 1);
                }
            }
        }
        System.out.println(count);

    }
}
