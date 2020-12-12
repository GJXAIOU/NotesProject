//: innerclasses/Parcel1.java
// Creating inner classes.

public class Parcel1 {
    class Contents {
        private int i = 11;

        public int value() {
            return i;
        }
    }

    class Destination {
        private String label;

        Destination(String whereTo) {
            // 2.label = "Tasmania"
            label = whereTo;
        }

        // 4.执行返回：label = "Tasmania"
        String readLabel() {
            return label;
        }
    }

    // Using inner classes looks just like
    // using any other class, within Parcel1:
    public void ship(String dest) {
        Contents c = new Contents();
        // 1.调用Destination(String whereTo)构造方法
        Destination d = new Destination(dest);
        // 3.调用readLabel()方法
        // 4.输出：Tasmania
        System.out.println(d.readLabel());
    }

    public static void main(String[] args) {
        Parcel1 p = new Parcel1();
        p.ship("Tasmania");
    }
} /* Output:
Tasmania
*///:~
