package com.gjxaiou.principle.inversion.improve;

public class DependencyPass {
	public static void main(String[] args) {
		ChangHong changHong = new ChangHong();
//		OpenAndClose openAndClose = new OpenAndClose();
//		openAndClose.open(changHong);
		
//		//通过构造器进行依赖传递
//		OpenAndClose openAndClose = new OpenAndClose(changHong);
//		openAndClose.open();

		//通过setter方法进行依赖传递
		OpenAndClose openAndClose = new OpenAndClose();
		// 如果不使用 setTV就没有对象，则会报空指针异常
		openAndClose.setTv(changHong);
		openAndClose.open();
	}
}


///**
// * 方式1： 通过接口传递实现依赖
// */
//interface ITV {
//	public void play();
//}
//
//class ChangHong implements ITV {
//	@Override
//	public void play() {
//		System.out.println("长虹电视机，打开");
//	}
//}
//
//interface IOpenAndClose {
//	//抽象方法,接收接口
//	public void open(ITV tv);
//}
//
//// 实现接口
//class OpenAndClose implements IOpenAndClose {
//	@Override
//	public void open(ITV tv) {
//		tv.play();
//	}
//}


///**
// * 方式 2：通过构造方法依赖传递
// */
//interface ITV {
//	public void play();
//}
//
//class ChangHong implements ITV {
//	@Override
//	public void play() {
//		System.out.println("长虹电视机，打开");
//	}
//}
//
//interface IOpenAndClose {
//	public void open();
//}
//
//class OpenAndClose implements IOpenAndClose {
//	// 成员变量
//	public ITV tv;
//
//	// 构造器
//	public OpenAndClose(ITV tv) {
//		this.tv = tv;
//	}
//
//	@Override
//	public void open() {
//		this.tv.play();
//	}
//}



// 方式3 , 通过setter方法传递
interface IOpenAndClose {
	public void open(); // 抽象方法
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

interface ITV { // ITV接口
	public void play();
}

class ChangHong implements ITV {
	@Override
	public void play() {
		System.out.println("长虹电视机，打开");
	}

}