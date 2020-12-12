//: interfaces/Adventure.java
// Multiple interfaces.

interface CanFight {
  void fight();
}

interface CanSwim {
  void swim();
}

interface CanFly {
  void fly();
}

class ActionCharacter {
  public void fight() {}
}	

class Hero extends ActionCharacter
    implements CanFight, CanSwim, CanFly {
  // 因为继承了ActionCharacter里面的fight()方法，因此这里不需要具体实现接口CanFight中的接口
  @Override
  public void swim() {}
  @Override
  public void fly() {}
}

public class Adventure {
  public static void t(CanFight x) {
    x.fight();
  }

  public static void u(CanSwim x) {
    x.swim();
  }

  public static void v(CanFly x) {
    x.fly();
  }

  public static void w(ActionCharacter x) {
    x.fight();
  }

  public static void main(String[] args) {
    Hero h = new Hero();
    // 下面是依次向上转型为每一个接口
    t(h); // Treat it as a CanFight
    u(h); // Treat it as a CanSwim
    v(h); // Treat it as a CanFly
    w(h); // Treat it as an ActionCharacter
  }
} ///:~
