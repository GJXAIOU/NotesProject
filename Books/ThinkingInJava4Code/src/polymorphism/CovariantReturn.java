//: polymorphism/CovariantReturn.java

class Grain {
  @Override
  public String toString() { return "Grain"; }
}

class Wheat extends Grain {
  @Override
  public String toString() { return "Wheat"; }
}

class Mill {
  Grain process() { return new Grain(); }
}

class WheatMill extends Mill {
  @Override
  Wheat process() { return new Wheat(); }
}

public class CovariantReturn {
  public static void main(String[] args) {
    Mill m = new Mill();
    // 相当于： Grain g = new Grain();
    Grain g = m.process();
    System.out.println(g);
    m = new WheatMill();
    // 相当于：Grain g = new Wheat();
    g = m.process();
    System.out.println(g);
  }
} /* Output:
Grain
Wheat
*///:~
