package com.gjxaiou;

import java.math.BigDecimal;
import java.util.HashSet;

public class Demo3 {
	public static void main(String[] args) throws InterruptedException {
		HashSet<Num> nums = new HashSet<>();
		nums.add(new Num(1,2));
		nums.add(new Num(2,3));

	}

}

class Num {
	private  int i;
	private  int j;

	public Num(int i, int j) {
		this.i = i;
		this.j = j;
	}
}
