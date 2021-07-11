import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo1 {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList(Arrays.asList(1,2,3,4,4));
		list.stream().filter(item -> item > 3).forEach(item1 ->System.out.println(item1));
	}
}
