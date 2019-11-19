package com.gjxaiou;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Fly {
    private static double[] min_distance = new double[10];
    private static int[] min_n = new int[10];
    private static int min_dis_count = 0;
    private static String[] min_dis_str = new String[10];
    private static String[] min_n_str = new String[10];
    //	private static int wrcount=0;
//	private static int wrcount1=0;
    private static int c=0;
    private static int c1=0;

    public static void main(String[] args) throws Exception {
        Point start=null,end=null;
        FileReader fr = new FileReader(new File("E:\\Program\\Java\\Project\\VideoClass" +
                "\\FramePromotion\\MathModel\\src\\resources\\data.txt"));
        BufferedReader br = new BufferedReader(fr);
        String str;
        int cnt=0;
        Point[] nodes = new Point[611];
        while((str = br.readLine()) != null){
            String[] items = str.split("\t");
            double x = Double.parseDouble(items[0]);
            double y = Double.parseDouble(items[1]);
            double z = Double.parseDouble(items[2]);
            if(cnt==0){
                start = new Point(x,y,z);
            }
            else if(cnt==612){
                end = new Point(x,y,z);
            }
            else{
                int type = Integer.parseInt(items[3]);
                nodes[cnt-1] = new Point(x,y,z,type);
            }
            cnt++;
        }
        br.close();
        Fly f = new Fly();
        Point vectorAB = new Point(end.x-start.x,end.y-start.y,end.z-start.z);
        f.routePlanning(start,nodes,end,0,0,new ArrayList<Result>(),0,0,vectorAB,new HashSet<Integer>());
    }

    static class Result{
        private int num;
        private double hError;
        private double vError;

        public Result(int num, double hError, double vError) {
            this.num = num;
            this.hError = hError;
            this.vError = vError;
        }
        public int getNum() {
            return num;
        }
        public void setNum(int num) {
            this.num = num;
        }
        public double gethError() {
            return hError;
        }
        public void sethError(double hError) {
            this.hError = hError;
        }
        public double getvError() {
            return vError;
        }
        public void setvError(double vError) {
            this.vError = vError;
        }

    }

    static class Point{
        private double x;
        private double y;
        private double z;
        private int type;

        public Point(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public Point(double x, double y, double z,int type) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public double getX() {
            return x;
        }
        public void setX(double x) {
            this.x = x;
        }
        public double getY() {
            return y;
        }
        public void setY(double y) {
            this.y = y;
        }
        public double getZ() {
            return z;
        }
        public void setZ(double z) {
            this.z = z;
        }
    }

    //params:start,Set<Calibration_point> nodes,end
    public void routePlanning(Point start,Point[] nodes,Point end,int count,double dis,List<Result> res,double hError,double vError,Point vectorAB,Set<Integer> indexs) throws Exception{
        double distance = calculateDistance(start,end);
        double next_hError = hError + distance*Paramaters.delta;
        double next_vError = vError + distance*Paramaters.delta;
        if(next_hError<Paramaters.theta && next_vError<Paramaters.theta){
            Result r = new Result(612, next_hError, next_vError);
            res.add(r);
            if(min_dis_count<10){
                StringBuilder sb = new StringBuilder();
                sb.append(dis+distance+",");
                sb.append(count+",{");
                for(Result item:res){
                    sb.append("("+item.num+",");
                    sb.append(item.hError+",");
                    sb.append(item.vError+"),");
                }
                sb.append("}");
                if(min_dis_count>0){
                    int j;
                    for(j=0;j<min_dis_count;j++){
                        if(dis+distance<=min_distance[j]){
                            for(int k=min_dis_count;k>j;k--){
                                min_distance[k]=min_distance[k-1];
                                min_dis_str[k]=min_dis_str[k-1];
                            }
                            min_distance[j]=dis+distance;
                            min_dis_str[j]=sb.toString();
                            break;
                        }

                    }
                    if(j==min_dis_count){
                        min_distance[j]=dis+distance;
                        min_dis_str[j]=sb.toString();
                    }
                    for(j=0;j<min_dis_count;j++){
                        if(count<=min_n[j]){
                            for(int k=min_dis_count;k>j;k--){
                                min_n[k]=min_n[k-1];
                                min_n_str[k]=min_n_str[k-1];
                            }
                            min_n[j]=count;
                            min_n_str[j]=sb.toString();
                            break;
                        }
                    }
                    if(j==min_dis_count){
                        min_n[j]=count;
                        min_n_str[j]=sb.toString();
                    }
                }
                else{
                    min_distance[0]=dis+distance;
                    min_dis_str[0]=sb.toString();
                    min_n[0]=count;
                    min_n_str[0]=sb.toString();
                }
                System.out.println(min_dis_count);
                //可以直接到达终点，输出本次飞行距离，经过的校正点个数，经过的校正点集合
                if(min_dis_count==9){
                    System.out.println("=======================");
                    String str = "";
                    for(int m=0;m<min_dis_str.length;m++){
                        str+=min_dis_str[m]+"\r\n";
                    }
                    System.out.println(str);
                    str="";
                    for(int m=0;m<min_n_str.length;m++){
                        str+=min_n_str[m]+"\r\n";
                    }
                }
                min_dis_count++;
            }
            else{///////////
                for(int j=0;j<10;j++){
                    if(dis+distance<=min_distance[j]){
                        StringBuilder sb = new StringBuilder();
                        sb.append(dis+distance+",");
                        sb.append(count+",{");
                        for(Result item:res){
                            sb.append("("+item.num+",");
                            sb.append(item.hError+",");
                            sb.append(item.vError+"),");
                        }
                        sb.append("}");
                        for(int k=9;k>j;k--){
                            min_distance[k]=min_distance[k-1];
                            min_dis_str[k]=min_dis_str[k-1];
                        }
                        min_distance[j]=dis+distance;
                        min_dis_str[j]=sb.toString();
                        String str="===========================\r\n";
                        for(int m=0;m<min_dis_str.length;m++){
                            str+=min_dis_str[m]+"\r\n";
                        }
                        System.out.println("第"+c+"次dis更新："+"\r\n"+str);
                        c++;
                        break;
                    }

                }
                for(int j=0;j<10;j++){
                    if(count<=min_n[j]){
                        StringBuilder sb = new StringBuilder();
                        sb.append(dis+distance+",");
                        sb.append(count+",{");
                        for(Result item:res){
                            sb.append("("+item.num+",");
                            sb.append(item.hError+",");
                            sb.append(item.vError+"),");
                        }
                        sb.append("}");
                        for(int k=9;k>j;k--){
                            min_n[k]=min_n[k-1];
                            min_n_str[k]=min_n_str[k-1];
                        }
                        min_n[j]=count;
                        min_n_str[j]=sb.toString();
                        String str="============================\r\n";
                        for(int m=0;m<min_n_str.length;m++)
                        {
                            str+=min_n_str[m]+"\r\n";
                        }
                        System.out.println("第"+c1+"次count更新："+"\r\n"+str);
                        c1++;
                        break;
                    }

                }
            }/////////////////
            res.remove(r);
            return;
        }
        int len = nodes.length;
        for(int i=0;i<len;i++){
            if(min_dis_count==10){
                if(count>min_n[4]||dis>min_distance[4])
                    continue;
            }
            if(indexs.contains(i))
                continue;
            if(count==0){//从起点出发
                //根据约束条件，选择满足下一跳的点
                distance = calculateDistance(start,nodes[i]);
                next_hError = hError + distance*Paramaters.delta;
                next_vError = vError + distance*Paramaters.delta;
                Point vector = new Point(nodes[i].x-start.x,nodes[i].y-start.y,nodes[i].z-start.z);
                //计算向量夹角
                double cos = vector.x*vectorAB.x+vector.y*vectorAB.y+vector.z*vectorAB.z;
                if(cos>=0 && next_hError<Paramaters.theta && next_vError<Paramaters.theta){
                    //计算校正后的垂直和水平误差
                    double next_AhError,next_AvError;
                    if(next_vError<=Paramaters.beta1 && next_hError<=Paramaters.beta2 && nodes[i].type==0){
                        //水平校正
                        next_AhError = 0;
                    }
                    else{
                        next_AhError = next_hError;
                    }
                    if(next_vError<=Paramaters.alpha1 && next_hError<=Paramaters.alpha2 && nodes[i].type==1){
                        //垂直校正
                        next_AvError = 0;
                    }
                    else{
                        next_AvError = next_vError;
                    }
                    //添加校正点，更新经过的校正点数目
                    Result r = new Result(i+1, next_hError, next_vError);
                    res.add(r);
                    count++;
//					System.out.println(count+":"+i);
                    indexs.add(i);
                    routePlanning(nodes[i],nodes,end,count,dis+distance,res,next_AhError,next_AvError,vectorAB,indexs);
                    indexs.remove(i);
                    //返回删除校正点，更新经过的校正点数目
                    res.remove(r);
                    count--;
                }
            }
            else{//从校正点出发,记得判断终止（到达终点）,约束，不能返回!!!
                //计算当前校正点与终点之间的距离
                //无法直接到达终点
                distance = calculateDistance(start,nodes[i]);
                next_hError = hError + distance*Paramaters.delta;
                next_vError = vError + distance*Paramaters.delta;
                Point vector = new Point(nodes[i].x-start.x,nodes[i].y-start.y,nodes[i].z-start.z);
                //计算向量夹角
                double cos = vector.x*vectorAB.x+vector.y*vectorAB.y+vector.z*vectorAB.z;
                if(cos>=0 && next_hError<Paramaters.theta && next_vError<Paramaters.theta){
                    //计算校正后的垂直和水平误差
                    double next_AhError,next_AvError;
                    if(next_vError<=Paramaters.beta1 && next_hError<=Paramaters.beta2 && nodes[i].type==0){
                        //水平校正
                        next_AhError = 0;
                    }
                    else{
                        next_AhError = next_hError;
                    }
                    if(next_vError<=Paramaters.alpha1 && next_hError<=Paramaters.alpha2 && nodes[i].type==1){
                        //垂直校正
                        next_AvError = 0;
                    }
                    else{
                        next_AvError = next_vError;
                    }
                    //添加校正点，更新经过的校正点数目
                    Result r = new Result(i+1, next_hError, next_vError);
                    res.add(r);
                    count++;
                    indexs.add(i);
                    routePlanning(nodes[i],nodes,end,count,dis+distance,res,next_AhError,next_AvError,vectorAB,indexs);
                    indexs.remove(i);
                    //返回删除校正点，更新经过的校正点数目
                    res.remove(r);
                    count--;
                }
            }
        }
    }

    public double calculateDistance(Point a,Point b){
        double xa = a.getX();
        double xb = b.getX();
        double ya = a.getY();
        double yb = b.getY();
        double za = a.getZ();
        double zb = b.getZ();
        double res = Math.sqrt((xa-xb)*(xa-xb)+(ya-yb)*(ya-yb)+(za-zb)*(za-zb));
        return res;
    }
}
