package paChong;

import java.io.*;


/**
 * @author GJXAIOU
 * 功能：讲爬虫文件中 content: 内容为空的该行删除
 * 思路：
 * 1.每行格式：{"title": ["13日晚間7:30 自強號撞小轎車"], "url": "https://news.ebc.net.tw/News/society/313", "pubtime": "2015/11/13 21:44 ", "content": ["13日晚間", "再發生闖平交道憾事，一列437次新左營開往樹林的DMU自強號，晚間7時20分於富源與光復間大全村平交道撞及卡在平交道上的小轎車，所幸無人傷亡，目前列車有延誤。", "\r\n", "\r\n以下為事發現場照片", "\r\n", "\r\n", "\r\n", "\r\n", "\r\n"]},
 * 2.注意：每行的 ":" 数目不一致，因为标题中或者内容中很可能也包含不同数目的 “：”
 * 3.按照 “：” 进行切割每行字符串，判别最后一个字符串是否以 “[]},” 结尾，因为如果有内容，内容将存在 [] 中。
 * 4.如果不以该字符串结尾则表示有内容，写入新的文件中。
 */
public class QuKong {
    public static void quKong(String filePath) throws IOException {
        File inputFile = new File(filePath);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
        String str = null;
        String[] arr = null;
        StringBuffer bf = new StringBuffer();
        while ((str = bufferedReader.readLine()) != null){
            arr = str.split(":");
            if (!arr[arr.length - 1].endsWith("[]},")) {
                bf.append(str).append("\r\n");
                System.out.println(arr[arr.length - 1]);
            }
        }
        // 输出文件目录
        File outputFile = new File("C:\\Users\\Administrator\\Desktop\\数据\\社情数据去空之后\\societyEBC10250.json");
        BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));
        out.write(bf.toString());
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        // 需要出的的输入文件路径
        quKong("C:\\Users\\Administrator\\Desktop\\数据\\社情数据\\societyEBC00001.json");
    }
}
