package com.gjxaiou.hand;

/**
 * @Author GJXAIOU
 * @Date 2020/2/16 16:18
 */
public class EasyDay04PaperFolding {
    public void printAllFolds(int foldTotalTime) {
        printProcess(1, foldTotalTime, true);
    }

    public void printProcess(int currentTime, int foldTotalTime, boolean down) {
        if (currentTime > foldTotalTime) {
            return;
        }
        printProcess(currentTime + 1, foldTotalTime, true);
        System.out.println(down ? "down" : "up");
        printProcess(currentTime + 1, foldTotalTime, false);
    }
}
