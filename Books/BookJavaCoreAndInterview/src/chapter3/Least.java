package chapter3;
import java.util.*;

/**
 * @author GJXAIOU
 * @create 2019-08-17-20:13
 */

// 定义区间格式
class Section implements Comparable {
        Integer left;
        Integer right;

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }

    @Override
    public int compareTo(Object o) {
        int result = this.getLeft() - ((Section)o).getLeft();
        if ( result  > 0){
            return 1;
        }else if (result == 0 ){
            if (this.getRight() - ((Section)o).getRight() < 0){
                return 1;
            }
        }
       return 0;

    }
}

public class Least {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
         int N  = in.nextInt();
         int  M = in.nextInt();
        ArrayList<Section> sectionsList = new ArrayList<>();


        for (int i = 0; i < N; i++){
             Section section2 = new Section();
             section2.left = in.nextInt();
             section2.right = in.nextInt();
             sectionsList.add(section2);
         }
        Collections.sort(sectionsList);


        int e = 0;
        int rb = 0;
        int cnt = 0;
        int i = 0;
        int k = 0;
        int ok = 1;
        // 右边距小于长度M
        while (rb < M){
            // 如果下一个区间的左边界大于新的起点，没解
            Section section = new Section();
            if (sectionsList.get(k).getLeft() > rb){
                break;
            }

            for (i = k; i < N ; i++) {
                // 如果区间的左边界小于起点，可覆盖
                if (sectionsList.get(k).getLeft() <= rb){
                    // 选择最长区间
                    if (sectionsList.get(k).getRight() > e){
                        e = sectionsList.get(k).getRight();
                    }
                }else {
                    cnt++;
                    rb = e;
                    k = i;
                    break;
                }

            }
            // 最后一个选择区间，从cnt+1
            if (i == N){
                cnt++;
                break;
            }
        }

        if (ok == 1){
            System.out.println(cnt);
        }else {
            System.out.println(-1);
        }

    }



    public int panduan(Section section1, Section section2){
        if (section1.left < section2.left){
            return 1;
        }else if ((section1.left .equals(section2.left) ) && section1.right < section2.right){
            return 1;
        }else {
            return 0;
        }
    }
}
