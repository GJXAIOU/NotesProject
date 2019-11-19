package chapter4;

import com.sun.org.apache.xpath.internal.WhitespaceStrippingElementMatcher;

import java.sql.SQLException;

/**
 * @author GJXAIOU
 * @create 2019-08-18-19:09
 */
public class tryCatch {
    public static void main(String[] args) {
        int[] a = {1,2,3,4};
        int i = 0;
       try {
           while (i < 5){
               System.out.println(a[i]);
               i++;
           }
           System.out.println("happy try");
       }catch (Exception e) {
           System.out.println("Happy Exception");
       }finally {
           System.out.println("happy Finally");
       }
    }
}


