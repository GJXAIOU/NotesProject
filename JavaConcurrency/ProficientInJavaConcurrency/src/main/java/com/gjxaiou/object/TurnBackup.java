package com.gjxaiou.object;

public class TurnBackup {

	public static void main(String[] args) {
		BackupService backupService = new BackupService();
		for (int i = 0; i < 4; i++) {
			new BackupBThread(backupService).start();
			new BackupAThread(backupService).start();
		}
	}
}

class BackupService {
	volatile private boolean prevIsA = false;

	synchronized public void backupA() {
		try {
			while (prevIsA == true) {
				wait();
			}
			for (int i = 0; i < 2; i++) {
				System.out.println("backupA");
			}
			prevIsA = true;
			notifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	synchronized public void backupB() {
		try {
			while (prevIsA == false) {
				wait();
			}
			for (int i = 0; i < 2; i++) {
				System.out.println("backupB");
			}
			prevIsA = false;
			notifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

// 线程工具类
class BackupAThread extends Thread {
	private BackupService backupService;

	BackupAThread(BackupService backupService) {
		this.backupService = backupService;

	}

	@Override
	public void run() {
		backupService.backupA();
	}
}

class BackupBThread extends Thread {
	private BackupService backupService;

	BackupBThread(BackupService backupService) {
		this.backupService = backupService;

	}

	@Override
	public void run() {
		backupService.backupB();
	}
}
