import java.util.ArrayList;
import java.util.List;

public class InnerFinalDemo {
	public static int addByThreads(final List list) {
		// 创建一个线程组
		ThreadGroup group = new ThreadGroup("Group");
		// 通过内部类的方法来创建多线程
		Runnable listAddTool = new Runnable() {
			public void run() {// 在其中定义线程的主体代码			
				list.add("0"); // 在集合里添加元素				
			}
		};
		// 启动10个线程，同时向集合里添加元素
		for (int i = 0; i < 10; i++) {
			new Thread(group, listAddTool).start();
		}

		while (group.activeCount() > 0) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return list.size(); // 返回插入后的集合长度
	}

	public static void main(String[] args) {
		List list = new ArrayList();	
		//很大可能返回10
		System.out.println(addByThreads(list));

	}

}
