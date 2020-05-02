
import java.io.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
	private List<List<Integer>> list=new CopyOnWriteArrayList<List<Integer>>();
	//处理数据，去掉重复的转账记录：重复的定义就是发款方与收款方的id均相同。
	//采用处理后的数据建立邻接矩阵。
	//循环所有的发款方的id，寻找循环转账链，即从发款方id出发最后再回到发款方id
	//循环转账链的长度>=3 <=7
	
	/**
	 * @param goal //循环链的起点，也是唯一终点
	 * @param arr //邻接矩阵
	 * @param flag //标记矩阵
	 * @param start //本次调用的起点
	 * @param length //链的长度
	 * @param con //容器
	 */
	public void fun(int goal,Map<Integer,List<Integer>> map,int start,int length,Set<Integer> con,List<Integer> lt){
		if(length>7){//什么时候停止调用？length>7不要继续递归下去了，返回
			return;
		}
		//map里的list要排个序！！！！
		List<Integer> idList=map.get(start);
		if(idList==null) {
			return;
		}
		for(int j=0,len=idList.size();j<len;j++){
			if(idList.get(j)!=start){//排除掉自己给自己转账
				int id=idList.get(j);
				if(con.contains(id)&&id==goal&&length>2){
					List<Integer> temp=new ArrayList<Integer>(lt);
					list.add(temp);
				}else if(!con.contains(id))//如果可以允许这种情况1->2->3->2->4->1那么将if后面的内容注掉
				{
					List<Integer> temp=new ArrayList<Integer>(lt);
					temp.add(id);
					con.add(id);
					fun(goal,map,id,length+1,con,temp);
					con.remove(id);
				}	
			}
		}
	}
	public void run(Map<Integer,List<Integer>> map,int max){
		ExecutorService threadPool=Executors.newFixedThreadPool(5);
		for(int i=0;i<=max;i++){
			if(!map.containsKey(i)) {
				continue;
			}
			final int j=i;
			threadPool.execute(new Runnable() {	
				@Override
				public void run() {
					Set<Integer> s=new HashSet<Integer>();
					List<Integer> lt=new ArrayList<Integer>();
					lt.add(j);
					s.add(j);
					fun(j,map,j,1,s,lt);
				}
			});
		}
		threadPool.shutdown();
		try{
		threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
		}catch(Exception e){
			
		}
	}
	public void start(String str) throws IOException{
		try {
			FileInputStream fis=new FileInputStream(new File(str));
			InputStreamReader isr=new InputStreamReader(fis);
			BufferedReader br=new BufferedReader(isr);
			List<List<Integer>> list=new ArrayList<List<Integer>>();
			String s=null;
			Set<String> setstr=new HashSet<String>();
			int max=0;
			while((s=br.readLine())!=null){
				String[] data=s.split(",");
				if(setstr.add(data[0]+","+data[1])){
					List<Integer> temp=new ArrayList<Integer>();
					int x=Integer.parseInt(data[0]);
					if(max<x) {
						max=x;
					}
					temp.add(x);
					temp.add(Integer.parseInt(data[1]));
					list.add(temp);
				}
			}
			Map<Integer,List<Integer>> map=new HashMap<Integer, List<Integer>>();
			for(int i=0,len=list.size();i<len;i++){
				int key=list.get(i).get(0);
				int value=list.get(i).get(1);
				if(map.get(key)==null){
					map.put(key, new ArrayList<Integer>());
				}
				map.get(key).add(value);
			}
			Set<Integer> keySet=map.keySet();
			for(Integer i:keySet){
				map.get(i).sort(new Comparator<Integer>() {
					@Override
					public int compare(Integer o1, Integer o2) {
						return o1-o2;
					}
				});
			}
			run(map,max);
		} catch (FileNotFoundException e) {
			System.out.println("没有发现文件");
		}
	}
	public static void main(String[] args) throws IOException {
		Main t=new Main();
		t.start("/data/test_data.txt");
		Set<String> set=new HashSet<String>();
		List<List<Integer>> list=new ArrayList<List<Integer>>();
		for(int i=0,len=t.list.size();i<len;i++){
			List<Integer> lt=new ArrayList<Integer>(t.list.get(i));
			//如果放进去了，再将所有的循环后的放进去，放不进去的抛弃
			if(set.add(lt.toString())){
				List<Integer> otherCircle=new ArrayList<Integer>(lt);
				for(int k=0,length=lt.size()-1;k<length;k++){
					int head=otherCircle.get(0);
					otherCircle.remove(0);
					otherCircle.add(head);
					set.add(otherCircle.toString());
				}
				list.add(lt);
			}
		}
		list.sort(new Comparator<List<Integer>>() {

			@Override
			public int compare(List<Integer> o1, List<Integer> o2) {
				return o1.size()-o2.size();
			}
		});
		FileOutputStream fos=new FileOutputStream(new File("/projects/student/result.txt"));
		fos.write((list.size()+"\r\n").getBytes());
		for(int i=0,len=list.size();i<len;i++){
			String ss=list.get(i).toString().replaceAll(" ", "");
			ss=ss.substring(1, ss.length()-1);
			ss=ss+"\r\n";
			fos.write(ss.getBytes());
		}
		fos.close();
	}
	
}

