package chapter3;
import	java.io.File;

/** 实现在指定路径下寻找是否有文件拓展名为.csv的文件
 * @author GJXAIOU
 * @create 2019-08-18-20:02
 */
public class VisitFolder {
    static void getCSVInFloder(String filePath){
        File folderName = new File(filePath);
        File[] files = folderName.listFiles();

        if (files == null || files.length == 0) {
            return ;
        }
        String fileName = null;
        // 遍历整个文件夹
        for (File file : files) {
            // 如果是文件夹，则递归调用
            if (file.isDirectory()) {
                getCSVInFloder(file.getAbsolutePath());
            }else {
                // 如果是文件，判断文件后缀名是否为.csv
                fileName = file.getName();
                if (fileName.substring(fileName.lastIndexOf(".") + 1).equals("csv")){
                    System.out.println(file.getAbsolutePath());
                }
            }
        }
    }

    public static void main(String[] args) {
        getCSVInFloder("C:/User/");
    }
}
