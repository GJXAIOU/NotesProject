package com.gjxaiou.easy.day04;

/**
 * 折纸问题
 *
 * @author GJXAIOU
 */
public class PaperFolding {

    public static void printAllFolds(int foldTotalTime) {
        printProcess(1, foldTotalTime, true);
    }

    public static void printProcess(int currentTime, int foldTotalTime, boolean down) {
        if (currentTime > foldTotalTime) {
            return;
        }
        // 按照中序遍历顺序
        printProcess(currentTime + 1, foldTotalTime, true);
        System.out.print(down ? "down " : "up ");
        printProcess(currentTime + 1, foldTotalTime, false);
    }

    public static void main(String[] args) {
        printAllFolds(1);
        System.out.println();
        printAllFolds(2);
        System.out.println();
        printAllFolds(3);
        System.out.println();
        printAllFolds(4);
    }
}
