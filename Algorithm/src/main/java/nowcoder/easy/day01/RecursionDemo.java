package nowcoder.easy.day01;

public class RecursionDemo{
	public static int getMax(int[] arr, int L, int R){
		if (L == R) {
			return arr[L];
		}

		int mid = L + (R - L) >> 1;
		// 获取左半部分最大值
		int maxLeft = getMax(arr, L, mid);
		// 获取右半部分最大值
		int maxRight = getMax(arr, mid + 1, R);
		return Math.max(maxLeft, maxRight);
	}

    public static void main(String[] args) {
		int[] arr = {2,3,5,1};
		System.out.println(getMax(arr, 0, arr.length - 1));
	}
}