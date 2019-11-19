interface CarFactoryImp {
	public void sellCar();
}

class CarFactory implements CarFactoryImp {
	public void sellCar() {
		System.out.println("Sell Car");
	}
}

class CarProxy implements CarFactoryImp {
	// 接收保存目标对象
	private CarFactoryImp target;

	public void sellCar() {
		if (target == null) {
			target = new CarFactory();
		}
		target.sellCar();
	}
}

public class StaticProxy {
	public static void main(String[] args) {
		CarFactoryImp imp = new CarProxy();
		imp.sellCar();
	}

}
