package entity;
/**
 * @author GJXAIOU
 * @create 2019-08-10-13:52
 */
public class Person {
    private int id;
    private String name;

    public int test;
    public static int testStatic = 10;

    private Person() {}

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void eat() {
        System.out.println("黄焖鸡米饭~~~");
    }

    public void sleep(int num) {
        System.out.println(name + "每天睡" + num + "个小时");
    }

    public void game() {
        System.out.println("大吉大利，今晚吃鸡~~~");
    }

    private void testPrivate() {
        System.out.println("这是一个Private修饰的私有化方法");
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + "]";
    }
}
