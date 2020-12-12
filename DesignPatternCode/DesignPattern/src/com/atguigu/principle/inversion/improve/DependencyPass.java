package com.atguigu.principle.inversion.improve;

public class DependencyPass {
	public static void main(String[] args) {
		ChangHong changHong = new ChangHong();
//		OpenAndClose openAndClose = new OpenAndClose();
//		openAndClose.open(changHong);
		
//		//ͨ��������������������
//		OpenAndClose openAndClose = new OpenAndClose(changHong);
//		openAndClose.open();

		//ͨ��setter����������������
		OpenAndClose openAndClose = new OpenAndClose();
		// �����ʹ�� setTV��û�ж�����ᱨ��ָ���쳣
		openAndClose.setTv(changHong);
		openAndClose.open();
	}
}


///**
// * ��ʽ1�� ͨ���ӿڴ���ʵ������
// */
//interface ITV {
//	public void play();
//}
//
//class ChangHong implements ITV {
//	@Override
//	public void play() {
//		System.out.println("������ӻ�����");
//	}
//}
//
//interface IOpenAndClose {
//	//���󷽷�,���սӿ�
//	public void open(ITV tv);
//}
//
//// ʵ�ֽӿ�
//class OpenAndClose implements IOpenAndClose {
//	@Override
//	public void open(ITV tv) {
//		tv.play();
//	}
//}


///**
// * ��ʽ 2��ͨ�����췽����������
// */
//interface ITV {
//	public void play();
//}
//
//class ChangHong implements ITV {
//	@Override
//	public void play() {
//		System.out.println("������ӻ�����");
//	}
//}
//
//interface IOpenAndClose {
//	public void open();
//}
//
//class OpenAndClose implements IOpenAndClose {
//	// ��Ա����
//	public ITV tv;
//
//	// ������
//	public OpenAndClose(ITV tv) {
//		this.tv = tv;
//	}
//
//	@Override
//	public void open() {
//		this.tv.play();
//	}
//}



// ��ʽ3 , ͨ��setter��������
interface IOpenAndClose {
	public void open(); // ���󷽷�
	public void setTv(ITV tv);
}

class OpenAndClose implements IOpenAndClose {
	private ITV tv;

	@Override
	public void setTv(ITV tv) {
		this.tv = tv;
	}

	@Override
	public void open() {
		this.tv.play();
	}
}

interface ITV { // ITV�ӿ�
	public void play();
}

class ChangHong implements ITV {
	@Override
	public void play() {
		System.out.println("������ӻ�����");
	}

}