//: polymorphism/Transmogrify.java
// Dynamically changing the behavior of an object
// via composition (the "State" design pattern).

import static net.mindview.util.Print.*;

class Actor {
    public void act() {
    }
}

class HappyActor extends Actor {
    // 1.2.2 输出 HappyActor
    @Override
    public void act() {
        print("HappyActor");
    }
}

class SadActor extends Actor {
    // 1.4.1 输出：SadActor
    @Override
    public void act() {
        print("SadActor");
    }
}

class Stage {
    // 1.1 执行成员初始化
    private Actor actor = new HappyActor();

    public void change() {
        actor = new SadActor();
    }

    public void performPlay() {
        // 1.2.1 多态，调用的是HappyActor()的act()
        actor.act();
    }
}

public class Transmogrify {
    public static void main(String[] args) {
        // 1.新建stage对象
        Stage stage = new Stage();
        // 1.2调 actor.act()
        stage.performPlay();
        // 1.3 相当于执行：Actor actor = new SadActor()
        stage.change();
        // 1.4 调用子类的act()方法
        stage.performPlay();
    }
} /* Output:
HappyActor
SadActor
*///:~
