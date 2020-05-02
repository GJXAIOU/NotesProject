package com.huaweiSoftwareCode.up.version4;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Main {

    /**
     * 这里构建的应该是每个节点的入度
     *
     * @param send：发送结点
     * @param receive：接收结点
     */
    // degree 存在结点的度；key 为结点值，value 为度的大小
    private Map<Integer, Integer> intoDegree = new HashMap<Integer, Integer>();
    // 构成一张图，key 为发送结点，value 为连接的接收结点的 List 集合
    private Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();



    public void addEdge(int send, int receive) {
        // 如果 receive 是叶子结点，则度为 0
        if (intoDegree.get(receive) == null) {
            intoDegree.put(receive, 0);
        }
        // 如果不为叶子结点，每调用这个函数一次则度 + 1；
        intoDegree.put(receive, intoDegree.get(receive) + 1);
        // 如果图中不存在发发送结点，加入
        if (graph.get(send) == null) {
            graph.put(send, new ArrayList<Integer>());
        }
        // 将该接收节点放在发送结点的列表中
        graph.get(send).add(receive);
    }

    public void topologySort() {
        Queue<Integer> queue = new ArrayBlockingQueue<Integer>(280000);
        Set<Integer> degreeSet = intoDegree.keySet();
        // 遍历接收结点，如果度为 0（说明没有向他转账的），加入队列中。
        for (int i : degreeSet) {
            if (intoDegree.get(i) == 0)
                queue.add(i);
        }

        while (!queue.isEmpty()) {
            int temp = queue.poll();
            // 获取以该结点为出发结点的所有接收结点
            List<Integer> receiveList = graph.get(temp);
            for (int receiveNode : receiveList) {
                // 将所有以它为出发点的接收结点的入度 - 1
                intoDegree.put(receiveNode, intoDegree.get(receiveNode) - 1);
                // 如果出现入度为 0 则加入队列中
                if (intoDegree.get(receiveNode) == 0) {
                    queue.add(receiveNode);
                }
            }
        }
    }

    public void dfs(int goal, Map<Integer, List<Integer>> map, int start, int length,
                    Set<Integer> con, List<Integer> lt) {
        if (length > 7) {//什么时候停止调用？length>7不要继续递归下去了，返回
            return;
        }
        List<Integer> idList = map.get(start);
        if (idList == null) return;
        for (int j = 0, len = idList.size(); j < len; j++) {
            if (idList.get(j) != start) {//排除掉自己给自己转账
                int id = idList.get(j);
                if (con.contains(id) && id == goal && length > 2) {
                    List<Integer> temp = new ArrayList<Integer>(lt);
                    list.add(temp);
                } else if (!con.contains(id)) {
                    List<Integer> temp = new ArrayList<Integer>(lt);
                    temp.add(id);
                    con.add(id);
                    dfs(goal, map, id, length + 1, con, temp);
                    con.remove(id);
                }
            }
        }
    }

    private List<List<Integer>> list = new CopyOnWriteArrayList<List<Integer>>();

    public void run(Map<Integer, List<Integer>> map) {
        ExecutorService threadPool = Executors.newFixedThreadPool(4);
        Set<Integer> set = map.keySet();
        List<Integer> mapList = new ArrayList<Integer>(set);
        for (int i : mapList) {
            if (!map.containsKey(i)) continue;
            final int j = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    Set<Integer> s = new HashSet<Integer>();
                    List<Integer> lt = new ArrayList<Integer>();
                    lt.add(j);
                    s.add(j);
                    dfs(j, map, j, 1, s, lt);
                }
            });
        }
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (Exception e) {

        }
    }

    public void start(String inputFilePath) throws IOException {
        try {
            FileInputStream fis = new FileInputStream(new File(inputFilePath));
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            // 保存最大的接收方的数值
            int maxSend = 0;
            String lineString = null;
            // lineSet 保证去除重复的转账记录
            Set<String> lineSet = new HashSet<String>();
            // 所有行去重之后的结果，每行数据包括：发送方、接收方
            List<List<Integer>> allLineList = new ArrayList<List<Integer>>();

            // 将转账记录去重
            while ((lineString = br.readLine()) != null) {
                String[] lineData = lineString.split(",");
                if (lineSet.add(lineData[0] + "," + lineData[1])) {
                    List<Integer> lineList = new ArrayList<Integer>();
                    int send = Integer.parseInt(lineData[0]);
                    if (maxSend < send) {
                        maxSend = send;
                    }
                    lineList.add(send);
                    lineList.add(Integer.parseInt(lineData[1]));
                    allLineList.add(lineList);
                }
            }

            // 遍历上面去重之后的转账记录
            // 这个 nodeMap 也是 key 为 send，value 为接受结点的 List 集合
            Map<Integer, List<Integer>> nodeMap = new HashMap<Integer, List<Integer>>();
            for (int i = 0, len = allLineList.size(); i < len; i++) {
                int key = allLineList.get(i).get(0);
                int value = allLineList.get(i).get(1);
                // 将这两个点构成一条边放入图中
                addEdge(key, value);
                if (nodeMap.get(key) == null) {
                    nodeMap.put(key, new ArrayList<Integer>());
                }
                nodeMap.get(key).add(value);
            }

            // 拓扑排序
            topologySort();

            Set<Integer> nodeSet = new HashSet<Integer>();
            // 将所有不在环上的点保存起来
            for (int i : intoDegree.keySet()) {
                if (intoDegree.get(i) == 0) {
                    nodeSet.add(i);
                }
            }
            // 移除所有不在环上的点
            for (int i : nodeMap.keySet()) {
                if (nodeSet.contains(i)) {
                    nodeMap.remove(i);
                }
            }
            for (List<Integer> receive : nodeMap.values()) {
                for (int j : nodeSet) {
                    if (receive.contains(j))
                        receive.remove(j);
                }
            }
            //////// 至此，nodeMap 中去除了所有不在环上的点  /////

            // 首先将所有结点按照字典序排列
            Set<Integer> keySet = nodeMap.keySet();
            for (int i : keySet) {
                nodeMap.get(i).sort(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1 - o2;
                    }
                });
            }

            run(nodeMap);
        } catch (FileNotFoundException e) {
            System.out.println("没有发现文件");
        }
    }


    public static void main(String[] args) throws IOException {
        String inputFilePath = "/data/test_data.txt";
        String outputFilePath = "/projects/student/result.txt";
        Main t = new Main();
        t.start(inputFilePath);

        Set<String> set = new HashSet<String>();
        t.list.sort(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                int res = o1.size() - o2.size();
                if (res == 0) res = o1.get(0) - o2.get(0);
                return res;
            }

        });
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        for (int i = 0, len = t.list.size(); i < len; i++) {
            List<Integer> lt = new ArrayList<Integer>(t.list.get(i));
            //如果放进去了，再将所有的循环后的放进去，放不进去的抛弃
            if (set.add(lt.toString())) {
                List<Integer> otherCircle = new ArrayList<Integer>(lt);
                for (int k = 0, length = lt.size() - 1; k < length; k++) {
                    int head = otherCircle.get(0);
                    otherCircle.remove(0);
                    otherCircle.add(head);
                    set.add(otherCircle.toString());
                }
                list.add(lt);
            }
        }

        // 结果输出写入文件
        FileOutputStream fos = new FileOutputStream(new File(outputFilePath));
        fos.write((list.size() + "\n").getBytes());
        for (int i = 0, len = list.size(); i < len; i++) {
            String ss = list.get(i).toString().replaceAll(" ", "");
            ss = ss.substring(1, ss.length() - 1);
            ss = ss + "\n";
            fos.write(ss.getBytes());
        }
        fos.close();
    }
}
