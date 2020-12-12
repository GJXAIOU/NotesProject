//: polymorphism/PolyConstructors.java
// Constructors and polymorphism
// don't produce what you might expect.
// 构造器和多态

import static net.mindview.util.Print.*;

// 1.1因为Glyph为RoundGlyph父类，先执行它的构造器
class Glyph {
  void draw() {
    print("Glyph.draw()");
  }
  Glyph() {
    // 1.1.1 输出 Glyph() before draw()
    print("Glyph() before draw()");
    // 1.1.2 这里因为子类进行了draw()重写，因此调用子类的draw()函数
    draw();
    // 1.1.4 执行输出 Glyph() after draw() ，至此父类的构造方法执行完毕
    print("Glyph() after draw()");
  }
}	

// 1.2开始执行本身的构造方法
class RoundGlyph extends Glyph {
  // 1.2.1 赋值并且输出：RoundGlyph.RoundGlyph(), radius = 5  运行结束
  private int radius = 1;
  RoundGlyph(int r) {
    radius = r;
    print("RoundGlyph.RoundGlyph(), radius = " + radius);
  }

  // 1.1.3 执行输出：RoundGlyph.draw(), radius = 0 ，因为radius尚未初始化，因此默认值为0
  @Override
  void draw() {
    print("RoundGlyph.draw(), radius = " + radius);
  }
}	

public class PolyConstructors {
  public static void main(String[] args) {
    // 1.开始，调用构造器
    new RoundGlyph(5);
  }
} /* Output:
Glyph() before draw()
RoundGlyph.draw(), radius = 0
Glyph() after draw()
RoundGlyph.RoundGlyph(), radius = 5
*///:~
