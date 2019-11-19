import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThreadSafeDemo {

	public static int addByThreads(final List list, String type) {

		ThreadGroup group = new ThreadGroup(type);
		Runnable listAddTool = new Runnable() {
			public void run() {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				list.add("0");
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		};

		for (int i = 0; i < 10000; i++) {
			new Thread(group, listAddTool).start();
		}

		// 多个线程组之间sleep10毫秒，以免相互干扰
		while (group.activeCount() > 0) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return list.size();
	}

	public static void main(String[] args)  {

		List unsafeList = new ArrayList();
		List safeList = Collections.synchronizedList(new ArrayList());

		for (int i = 0; i < 3; i++) {
			unsafeList.clear();
			safeList.clear();
			int unsafeSize = addByThreads(unsafeList, "unsafe");
			int safeSize = addByThreads(safeList, "safe");
			System.out.println("unsafe/safe: " + unsafeSize + "/" + safeSize);
		}
	}
}