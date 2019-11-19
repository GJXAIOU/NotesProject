package chapter3;

import java.util.ArrayList;

class Car 
{
	private int i;
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public Car(int i)
	{
		this.i = i;
	}
}

public class CopyDemo {

	public static void main(String[] args) {

       Car c = new Car(1);
       
       ArrayList<Car> a1 = new ArrayList<Car>();
       ArrayList<Car> a2 = new ArrayList<Car>();
       a1.add(c);
       a2.add(c);
       
       a1.get(0).setI(2);
       System.out.println(a2.get(0).getI());
	}
}
