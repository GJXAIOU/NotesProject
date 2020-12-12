package chapter3;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author GJXAIOU
 * @create 2019-08-17-16:25
 */
public class IteratorDemo {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            strings.add(Integer.valueOf(i).toString());
        }
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}// output: 0 1 2