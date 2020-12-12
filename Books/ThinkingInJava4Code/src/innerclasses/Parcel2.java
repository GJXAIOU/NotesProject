//: innerclasses/Parcel2.java
// Returning a reference to an inner class.

public class Parcel2 {
    class Contents {
        private int i = 11;

        public int value() {
            return i;
        }
    }

    class Destination {
        private String label;

        Destination(String whereTo) {
            // 4.label = "Tasmania"
            label = whereTo;
        }

        // 5.0 返回 label = "Tasmania"
        String readLabel() {
            return label;
        }
    }

    public Destination to(String s) {
        return new Destination(s);
    }

    public Contents contents() {
        return new Contents();
    }

    public void ship(String dest) {
        // 2.等价于：Contents c = new Contents();
        Contents c = contents();
        // 3.等价于：Destination d = new Destination("Tasmania");
        Destination d = to(dest);
        // 5.调用readLabel()，输出：Tasmania
        System.out.println(d.readLabel());
    }

    public static void main(String[] args) {
        Parcel2 p = new Parcel2();
        // 1.调用ship()
        p.ship("Tasmania");
        Parcel2 q = new Parcel2();
        // Defining references to inner classes:
        // ☆☆ 这里相当于从外部类的非静态方法Content里面创建内部类对象；
        Parcel2.Contents c = q.contents();
        Parcel2.Destination d = q.to("Borneo");
    }
} /* Output:
Tasmania
*///:~
