import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedList;

interface Service {  
    public String sellCar(String carName);
}  

class ServiceImpl implements Service {  
    public String sellCar(String carName) {  
         return carName + " is ready!";  
    }  
}   

class MyInvocationHandler implements InvocationHandler {
	
	private Object target;
	
	MyInvocationHandler(Object target) {  
    	this.target = target;  
    } 
	
	public Object invoke(Object o, Method method, Object[] args) throws Throwable {  
        System.out.println("Call:" + method.getName());  
        Object result = method.invoke(target, args);  
        return result;
    }  
}

public class DynamicProxy {

	public static void main(String[] args) {
		//要被代理的真实对象
		Service service = new ServiceImpl();  
        //要代理哪个真实对象，就把这个对象传进去
        InvocationHandler invocationHandler = new MyInvocationHandler(service);  
        Service serviceProxy = (Service)Proxy.newProxyInstance(service.getClass().getClassLoader(),  
        		service.getClass().getInterfaces(), invocationHandler);  
        System.out.println(serviceProxy.sellCar("Aston Martin")); 

	}

}
