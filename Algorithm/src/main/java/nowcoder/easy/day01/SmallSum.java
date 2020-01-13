package nowcoder.easy.day01;

/**
 * 小和问题
 * 解决思路：
 * 1.借助于归并排序，首先将原数组分为两部分
 * 2.对划分的两个子序列进行 merge 操作，得到合并过程重产生的小和，再加上前面两个子序列的小和
 * 3.递归的执行 1，2 步骤。
 */
public class SmallSum {

	public static int smallSum(int[] sourceArray) {
		if (sourceArray == null || sourceArray.length < 2) {
			return 0;
		}
		return mergeSort(sourceArray, 0, sourceArray.length - 1);
	}

	public static int mergeSort(int[] sourceArray, int left, int right) {
		if (left == right) {
			return 0;
		}
		int mid = left + ((right - left) >> 1);
		// 最终的小和数目为分开数组分别求得的小和加上合并之后的小和
		return mergeSort(sourceArray, left, mid) + mergeSort(sourceArray, mid + 1, right) + merge(sourceArray, left, mid, right);
	}

	public static int merge(int[] arr, int left, int mid, int right) {
		int[] help = new int[right - left + 1];
		int i = 0;
		int startLeft = left;
		int startRight = mid + 1;
		int res = 0;
		while (startLeft <= mid && startRight <= right) {
			// 因为两个序列都是按照从小到大排序好的，所以一旦 startＬｅｆｔ　＜　ｓｔａｒｔＲｉｇｈｔ　则相当于　ａｒｒ［ｓｔａｒｔＬｅｆｔ］是　ｓｔａｒｔＲｉｇｈｔ　＋　１　～　right　之间所有元素的小和
			res += arr[startLeft] < arr[startRight] ? (right - startRight + 1) * arr[startLeft] : 0;
			help[i++] = arr[startLeft] < arr[startRight] ? arr[startLeft++] : arr[startRight++];
		}
		while (startLeft <= mid) {
			help[i++] = arr[startLeft++];
		}
		while (startRight <= right) {
			help[i++] = arr[startRight++];
		}
		for (i = 0; i < help.length; i++) {
			arr[left + i] = help[i];
		}
		return res;
	}

	// --------对数器-----------
	// 这里仅仅保留作为比较的绝对正确的方法，其它例如随机数产生器和比较器和上面一样
	public static int comparator(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int res = 0;
		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				res += arr[j] < arr[i] ? arr[j] : 0;
			}
		}
		return res;
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
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

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			if (smallSum(arr1) != comparator(arr2)) {
				succeed = false;
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}

}
