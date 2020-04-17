package string.easy;

import java.util.HashMap;

/**
 * @author GJXAIOU
 * @create 2020/04/17 18:27
 */
public class LeetCode299 {

    public String getHint(String secret, String guess) {
        int equalNum = 0;
        int allNum = 0;
        HashMap<Character, Integer> tempMap = new HashMap<>();
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                equalNum++;
            }
            if (tempMap.containsKey(secret.charAt(i))) {
                Integer oldValue = tempMap.get(secret.charAt(i));
                Integer newValue = oldValue + 1;
                tempMap.replace(secret.charAt(i), oldValue, newValue);
            } else {
                tempMap.put(secret.charAt(i), 1);
            }

        }


        for (int i = 0; i < guess.length(); i++) {
            if (tempMap.containsKey(guess.charAt(i)) && tempMap.get(guess.charAt(i)) > 0) {
                allNum++;
                Integer oldValue = tempMap.get(guess.charAt(i));
                Integer newValue = oldValue - 1;
                tempMap.replace(guess.charAt(i), oldValue, newValue);
            }
        }

        return equalNum + "A" + (allNum - equalNum) + "B";
    }

    // 方法二：桶
}
