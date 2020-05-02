package com.huaweiSoftwareCode.up.version2;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * 思路：
 * 1.处理数据，去掉重复的转账记录：重复的定义就是发款方与收款方的 id 均相同。
 * 2.采用处理后的数据建立邻接矩阵。新方案改为 HashMap
 * 3.循环所有的发款方的 id，寻找循环转账链，即从发款方 id 出发最后再回到发款方 id
 * 4.循环转账链的长度 >=3 <=7
 */
public class Main {
    private List<List<Integer>> ansList = new CopyOnWriteArrayList<List<Integer>>();

    /**
     * @param inputFilePath 输入文件路径
     * @throws IOException
     */
    public void start(String inputFilePath) throws IOException {
        try {
            FileInputStream fis = new FileInputStream(new File(inputFilePath));
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            // 功能：输入数据去重
            List<List<Integer>> list = new ArrayList<List<Integer>>();
            String s = null;
            Set<String> strSet = new HashSet<String>();
            int maxSendValue = 0;
            while ((s = br.readLine()) != null) {
                // data 存放输入文件中的每一行数据
                String[] data = s.split(",");
                // 如果发送方和接收方均不重复
                if (strSet.add(data[0] + "," + data[1])) {
                    List<Integer> lineValue = new ArrayList<Integer>();
                    // 获取发送数据的最大值
                    int sendValue = Integer.parseInt(data[0]);
                    if (maxSendValue < sendValue) {
                        maxSendValue = sendValue;
                    }
                    maxSendValue = sendValue > maxSendValue ? sendValue : maxSendValue;
                    // 将发送方和接收方加入 list
                    lineValue.add(sendValue);
                    lineValue.add(Integer.parseInt(data[1]));
                    list.add(lineValue);
                }
            }

            // 使用 HashMap 代替邻接矩阵
            Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
            for (int i = 0, len = list.size(); i < len; i++) {
                int key = list.get(i).get(0);
                int value = list.get(i).get(1);
                if (map.get(key) == null) {
                    map.put(key, new ArrayList<Integer>());
                }
                map.get(key).add(value);
            }
            Set<Integer> keySet = map.keySet();
            for (Integer i : keySet) {
                map.get(i).sort(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1 - o2;
                    }
                });
            }
            run(map, maxSendValue);
        } catch (FileNotFoundException e) {
            System.out.println("没有发现文件");
        }
    }

    /**
     * @param goal      // 循环链的起点，也是唯一终点
     * @param map       // HashMap 代替邻接矩阵
     * @param idAnsList // 每个结果的所有节点的 List
     * @param start     // 本次调用的起点
     * @param length    // 链的长度
     * @param con       // 容器
     */
    public void fun(int goal, Map<Integer, List<Integer>> map, int start, int length,
                    Set<Integer> con, List<Integer> idAnsList) {
        // length > 7 停止递归调用，返回
        if (length > 7) {
            return;
        }
        // map里的list要排个序！
        List<Integer> idList = map.get(start);
        if (idList == null) {
            return;
        }
        for (int j = 0, len = idList.size(); j < len; j++) {
            // 排除掉自己给自己转账
            if (idList.get(j) != start) {
                int id = idList.get(j);
                if (con.contains(id) && id == goal && length > 2) {
                    List<Integer> temp = new ArrayList<Integer>(idAnsList);
                    ansList.add(temp);
                    //如果可以允许这种情况1->2->3->2->4->1那么将if后面的内容注掉
                } else if (!con.contains(id)) {
                    List<Integer> temp = new ArrayList<Integer>(idAnsList);
                    temp.add(id);
                    con.add(id);
                    fun(goal, map, id, length + 1, con, temp);
                    con.remove(id);
                }
            }
        }
    }

    public void run(Map<Integer, List<Integer>> map, int max) {

        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= max; i++) {
            if (!map.containsKey(i)) {
                continue;
            }
            final int j = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    Set<Integer> s = new HashSet<Integer>();
                    List<Integer> ansList = new ArrayList<Integer>();
                    ansList.add(j);
                    s.add(j);
                    fun(j, map, j, 1, s, ansList);
                }
            });
        }
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (Exception e) {

        }
    }


    public static void main(String[] args) throws IOException {
        Main t = new Main();
        t.start("/data/test_data.txt");
        Set<String> set = new HashSet<String>();
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        for (int i = 0, len = t.ansList.size(); i < len; i++) {
            List<Integer> lt = new ArrayList<Integer>(t.ansList.get(i));
            t.ansList.get(i).sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });
            ;
            if (set.add(t.ansList.get(i).toString())) {
                list.add(lt);
            }
        }
        list.sort(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.size() - o2.size();
            }
        });
        FileOutputStream fos = new FileOutputStream(new File("/projects/student/result.txt"));
        fos.write((list.size() + "\r\n").getBytes());
        for (int i = 0, len = list.size(); i < len; i++) {
            String ss = list.get(i).toString().replaceAll(" ", "");
            ss = ss.substring(1, ss.length() - 1);
            ss = ss + "\r\n";
            fos.write(ss.getBytes());
        }
        fos.close();
    }
}

