package chapter3;
import	java.util.ArrayList;
import java.util.Iterator;
import	java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-08-18-16:46
 */
// 定义类时直接加上泛型
public class GenericClass<T> {
    private List<T> productList;

    public List<T> getProductList() {
        return productList;
    }

    public void setProductList(List<T> productList) {
        this.productList = productList;
    }

    // 构造函数
    public GenericClass() {
        productList = new ArrayList<T>();
    }

    // 添加元素的方法，参数类型为T
    void addItem(T item){
        productList.add(item);
    }

    // 打印所有对象
    public void printAllItems(){
        Iterator<T> iterator = productList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
    }

    public static void main(String[] args) {
        GenericClass genericClass = new GenericClass<String>();
        genericClass.addItem("hello");
        genericClass.addItem("world");

        GenericClass<Integer> integerGenericClass = new GenericClass<>();
        integerGenericClass.addItem(1);
        integerGenericClass.addItem(2);

        genericClass.printAllItems();
        integerGenericClass.printAllItems();
    }
}
