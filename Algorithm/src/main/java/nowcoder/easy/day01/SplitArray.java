package nowcoder.easy.day01;

/**
 * @author GJXAIOU
 * @create 2019-10-07-15:03
 */
public class SplitArray {
    public static void sort(int[] sourceArray, int left, int right, int tagMum){
        int less = left - 1;
        while(left < right){
            if(sourceArray[left] <= tagMum){
                swap(sourceArray, left++, ++less);
            }
            if(sourceArray [left] > tagMum){
                left++;
            }
        }
        return ;
    }

    public static void swap(int[] sourceArray, int left, int right) {
        if(left == right){
            return;
        }
        sourceArray[left] = sourceArray[left] ^ sourceArray[right];
        sourceArray[right] = sourceArray[left] ^ sourceArray[right];
        sourceArray[left] = sourceArray[left] ^ sourceArray[right];
    }

    // 测试方法
    public static void main(String[] args) {
        int[] sourceArray ={1,2,8,4,9,3,1,4,3,2,7};
        int left = 0;
        int right = sourceArray.length - 1;
        int tagMum = 6;

        System.out.println("原数组为：");
        for (int i : sourceArray) {
            System.out.print(i + " ");
        }

        sort(sourceArray, left, right, tagMum);

        System.out.println("\n分割之后数组为：");
        for (int i : sourceArray) {
            System.out.print(i + " ");
        }
    }
}
