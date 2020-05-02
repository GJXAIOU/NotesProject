package com.huaweiSoftwareCode.gjxaiou;

import java.io.*;
import java.sql.ResultSet;
import java.util.*;

/**
 * @Author GJXAIOU
 * @Date 2020/4/6 15:30
 */
public class LocalMain {

    // 拓扑排序
    // 要求：该图必须是有向且无环的
    public static List<Node> sortedTopology(Graph graph) {
        // inMap 为当前所有节点的入度
        HashMap<Node, Integer> inMap = new HashMap<>();
        Queue<Node> zeroInQueue = new LinkedList<>();
        // 遍历所有的点
        for (Node node : graph.nodes.values()) {
            // 把所有点和入度放入 Map
            inMap.put(node, node.in);
            // 入度为 0 的点放入队列
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        List<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            // 从入度为 0 的队列中拿出一个结点
            Node cur = zeroInQueue.poll();
            // 放入拓扑排序的下一个
            result.add(cur);
            // 然后该节点的所有下一个结点的入度统一减一（相当于删除该结点操作）
            for (Node next : cur.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                // 如果出现入度为 0 的节点加入队列中
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        Graph graph = new Graph();
        // 输入文件去重和构建图
        String inputFilePath = "E:\\Program\\Java\\Project\\Interview\\src\\main\\java\\com" +
                "\\huaweiSoftwareCode\\gjxaiou\\test_data.txt";
        try {
            FileInputStream fis = new FileInputStream(new File(inputFilePath));
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String s;
            String[] data = new String[3];
            int lineNum = 0;
            while ((s = br.readLine()) != null) {
                lineNum++;
            }
            Integer[][] generateGraphMatrix = new Integer[lineNum][3];
            int i = 0;
            while ((s = br.readLine()) != null) {
                data = s.split(",");
                for (int i1 = 0; i1 < data.length; i1++) {
                    generateGraphMatrix[i][i1] = Integer.parseInt(data[i1]);
                }
                i++;
            }

            // 建立图
            graph = GraphGenerator.createGraph(generateGraphMatrix);

        } catch (FileNotFoundException e) {
            System.out.println("没有发现文件");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
