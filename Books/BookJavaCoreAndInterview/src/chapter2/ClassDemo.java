class Car 
{
    //��Ǯ����
	int price;
    // ��������
	void move() {System.out.println("is moving");}
    //ɲ������
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


