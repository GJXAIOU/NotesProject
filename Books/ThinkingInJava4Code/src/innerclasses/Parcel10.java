//: innerclasses/Parcel10.java
// Using "instance initialization" to perform
// construction on an anonymous inner class.

public class Parcel10 {
    public Destination
    destination(final String dest, final float price) {
        return new Destination() {
            // private 默认即为：final
            private int cost;

            // Instance initialization for each object:
            {
                cost = Math.round(price);
                if (cost > 100) {
                    System.out.println("Over budget!");
                }
            }

            private String label = dest;

            @Override
            public String readLabel() {
                return label;
            }
        };
    }

    public static void main(String[] args) {
        Parcel10 p = new Parcel10();
        Destination d = p.destination("Tasmania", 101.395F);
    }
} /* Output:
Over budget!
*///:~
