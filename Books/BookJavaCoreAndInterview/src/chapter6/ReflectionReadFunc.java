package chapter6;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

class MyFuncClass {
	public MyFuncClass(){}
	public MyFuncClass(int i){}
	private void f1(){}
	protected int f2(int i){return 0;}
	public String f2(String s) {return "Java";}
}

public class ReflectionReadFunc {
	public static void main(String[] args) {
		Class<MyFuncClass> clazz = MyFuncClass.class;
		// 返回所有的方法，但是不包括继承的方法和构造方法
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        System.out.println("***********");

        //得到所有的构造函数
        Constructor[] c1 = clazz.getDeclaredConstructors();
        //输出所有的构造函数
        for(Constructor ct : c1){
            System.out.println(ct);
        }
	}
}
/**
 * output:
 * protected int chapter6.MyFuncClass.f2(int)
 * public java.lang.String chapter6.MyFuncClass.f2(java.lang.String)
 * private void chapter6.MyFuncClass.f1()
 * ***********
 * public chapter6.MyFuncClass()
 * public chapter6.MyFuncClass(int)
 */
