package com.gjxaiou.util;

/**
 * @author GJXAIOU
 * @create 2019-10-24-16:37
 */
public class PageCalculator {
    public static int calculateRowIndex(int pageIndex, int pageSize) {
        return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
    }
}
