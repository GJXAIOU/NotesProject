package chapter3;

import java.util.ArrayList;

/**
 * @author GJXAIOU
 * @create 2019-08-17-15:52
 */

// 1.自定义类实现Cloneable接口
class CarDeepCopy implements Cloneable {
    private int id;

    public CarDeepCopy(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // 调用父类的clone完成对象的复制
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

public class DeepCopy  {
    public static void main(String[] args) {
        CarDeepCopy carDeepCopy1 = new CarDeepCopy(1);
        // 通过clone方法将carDeepCopy1做备份
        CarDeepCopy carDeepCopy2 = null;
        try {
            carDeepCopy2 = (CarDeepCopy) carDeepCopy1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        ArrayList<CarDeepCopy> carDeepCopies1 = new ArrayList<>();
        ArrayList<CarDeepCopy> carDeepCopies2 = new ArrayList<>();
        carDeepCopies1.add(carDeepCopy1);
        carDeepCopies2.add(carDeepCopy2);
        carDeepCopies1.get(0).setId(2);
        System.out.println(carDeepCopies2.get(0).getId());
    }

}// output : 1
