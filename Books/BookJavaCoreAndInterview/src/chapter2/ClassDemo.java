class Car 
{
    //价钱属性
	int price;
    // 启动方法
	void move() {System.out.println("is moving");}
    //刹车方法
	void stop() {System.out.println("is Stopped");} 
//	public String toString()
//	{
//		return "This is my car.";
//	}
}



public class ClassDemo {
	public static void main(String[] args) {
		Car car = new Car();
		car.move();
		car.stop();
	    System.out.println(car);
	}

}


