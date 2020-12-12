//: innerclasses/Parcel7.java
// Returning an instance of an anonymous inner class.

public class Parcel7 {
    public Contents contents() {
        // Insert a class definition
        return new Contents() {
            private int i = 11;

            @Override
            public int value() {
                return i;
            }
        }; // Semicolon required in this case
    }

    public static void main(String[] args) {
        Parcel7 p = new Parcel7();
        Contents c = p.contents();
    }
} ///:~
