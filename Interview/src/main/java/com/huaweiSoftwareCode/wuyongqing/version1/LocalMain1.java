package com.huaweiSoftwareCode.wuyongqing.version1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * @Author GJXAIOU
 * @Date 2020/4/6 12:14
 */
public class LocalMain1 {
    private List<List<Integer>> list = new CopyOnWriteArrayList<List<Integer>>();
    // 处理数据，去掉重复的转账记录：重复的定义就是发款方与收款方的id均相同。
    // 采用处理后的数据建立邻接矩阵。
    // 循环所有的发款方的id，寻找循环转账链，即从发款方id出发最后再回到发款方id
    // 循环转账链的长度>=3 <=7

    /**
     * @param goal   //循环链的起点，也是唯一终点
     * @param arr    //邻接矩阵
     * @param flag   //标记矩阵
     * @param start  //本次调用的起点
     * @param length //链的长度，默认为 1
     * @param threadSet    //容器
     */
    public void fun(int goal, int[][] arr, boolean[][] flag, int start, int length,
                    Set<Integer> threadSet, List<Integer> threadList) {
        // length > 7不要继续递归下去了，返回
        if (length > 7) {
            return;
        }
        for (int j = 1; j < arr[start].length; j++) {
            if (!flag[start][j] && arr[start][j] == 1) {
                if (threadSet.contains(j) && j == goal && length > 2) {
                    List<Integer> temp = new ArrayList<Integer>(threadList);
                    list.add(temp);
                    //如果可以允许这种情况1->2->3->2->4->1那么将if后面的内容注掉
                } else if (!threadSet.contains(j)) {
                    List<Integer> temp = new ArrayList<Integer>(threadList);
                    temp.add(j);
                    threadSet.add(j);
                    flag[start][j] = true;
                    fun(goal, arr, flag, j, length + 1, threadSet, temp);
                    threadSet.remove(j);
                    flag[start][j] = false;
                }
            }
        }
    }

    public void run(int[][] arr) {
        for (int i = 1; i < arr.length; i++) {
            // 开启多个线程，每个线程负责由某个id开始形成的循环转账链。
            boolean[][] flag = new boolean[arr.length][arr[0].length];
            Set<Integer> threadSet = new HashSet<Integer>();
            List<Integer> ansList = new ArrayList<Integer>();
            ansList.add(i);
            threadSet.add(i);
            fun(i, arr, flag, i, 1, threadSet, ansList);
        }
    }


    /**
     * @param inputFilePath ：输入文件的路径
     * @throws IOException
     */
    public void start(String inputFilePath) throws IOException {
        try {
            FileInputStream fis = new FileInputStream(new File(inputFilePath));
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            List<List<Integer>> lineList = new ArrayList<List<Integer>>();
            String s = null;
            Set<String> sendSet = new HashSet<String>();
            Set<String> receiveSet = new HashSet<String>();
            Set<String> sendAndReceiveSumSet = new HashSet<String>();

            int maxSendValue = 0, maxReceiveValue = 0;
            while ((s = br.readLine()) != null) {
                // data 存放输入文件中的每一行数组
                String[] data = s.split(",");
                
                // 发送方去重集合
                if (sendSet.add(data[0])) {
                    int t = Integer.parseInt(data[0]);
                    if (maxSendValue < t) {
                        maxSendValue = t;
                    }
                }
                if (receiveSet.add(data[1])) {
                    int t = Integer.parseInt(data[1]);
                    if (maxReceiveValue < t) {
                        maxReceiveValue = t;
                    }
                }

                if (sendAndReceiveSumSet.add(data[0] + data[1])) {
                    List<Integer> temp = new ArrayList<Integer>();
                    temp.add(Integer.parseInt(data[0]));
                    temp.add(Integer.parseInt(data[1]));
                    lineList.add(temp);
                }
            }

            // 建立邻接矩阵
            int[][] arr = new int[maxSendValue + 1][maxReceiveValue + 1];
            for (int i = 0; i < lineList.size(); i++) {
                int m = lineList.get(i).get(0);
                int n = lineList.get(i).get(1);
                arr[m][n] = 1;
            }

            run(arr);
        } catch (FileNotFoundException e) {
            System.out.println("没有发现文件");
        }
    }

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        LocalMain1 localMain = new LocalMain1();
        localMain.start("E:\\Program\\Java\\Project\\Interview\\src\\main\\java\\com" +
                "\\huaweiSoftwareCode\\wuyongqing" +
                "\\test_data.txt");


        Set<String> set = new HashSet<String>();
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        for (int i = 0, len = localMain.list.size(); i < len; i++) {
            List<Integer> lt = new ArrayList<Integer>(localMain.list.get(i));
            localMain.list.get(i).sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });
            if (set.add(localMain.list.get(i).toString())) {
                list.add(lt);
            }
        }
        //  System.out.println(list.size());
        list.sort(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.size() - o2.size();
            }
        });

        // 输出结果
        FileOutputStream fos = new FileOutputStream(new File("E:\\Program\\Java\\Project" +
                "\\Interview" +
                "\\src\\main\\java\\com\\huaweiSoftwareCode\\wuyongqing\\test_result.txt"));
        fos.write((list.size() + "\r\n").getBytes());
        for (int i = 0, len = list.size(); i < len; i++) {
            String ss = list.get(i).toString();
            ss = ss.substring(1, ss.length() - 1);
            ss = ss + "\r\n";
            fos.write(ss.getBytes());
        }
        fos.close();
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) / 1000);
    }
}
