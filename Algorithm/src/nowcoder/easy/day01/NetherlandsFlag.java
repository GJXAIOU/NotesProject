package nowcoder.easy.day01;

public class NetherlandsFlag {
	/**
	 * @param sourceArray：要分割的数组
	 * @param left：小于 tagNum 区域最右边数
	 * @param right：大于 tagNum 区域最左边数
	 * @param tagNum：用于分割的参考数字
	 * @return
	 */
	public static int[] partition(int[] sourceArray, int left, int right, int tagNum) {
		// less 表示小于 tagNum 区域最右边数，more 是大于 tagNum 区域最左边数
		int less = left - 1;
		int more = right + 1;
		while (left < more) {
			if (sourceArray[left] < tagNum) {
				swap(sourceArray, ++less, left++);
			} else if (sourceArray[left] > tagNum) {
				swap(sourceArray, --more, left);
			} else {
				left++;
			}
		}
		return new int[] { less + 1, more - 1 };
	}

	public static void swap(int[] sourceArray, int left, int right) {
		int tmp = sourceArray[left];
		sourceArray[left] = sourceArray[right];
		sourceArray[right] = tmp;
	}

	// for test
	public static int[] generateArray() {
		int[] arr = new int[10];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 3);
		}
		return arr;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] test = generateArray();

		printArray(test);
		int[] res = partition(test, 0, test.length - 1, 1);
		printArray(test);
		System.out.println(res[0]);
		System.out.println(res[1]);

	}
}
