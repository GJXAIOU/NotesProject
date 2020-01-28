# README
##  前言
- 项目环境
    - IntelliJ IDEA 2019.3.1 (Ultimate Edition)
    - java version "1.8.0_221"
    - Java(TM) SE Runtime Environment (build 1.8.0_221-b11)
    - Java HotSpot(TM) 64-Bit Server VM (build 25.221-b11, mixed mode)
    - Windows 10 10.0
    - GC: ParNew, ConcurrentMarkSweep
    - Memory: 1962M
    - Cores: 12
    - Plugins: Google Code Search, HexView, VisualVMLauncher,com.alibabacloud，jclasslib,

## 项目说明
- 该项目为在学习张龙老师的 《深入理解 JVM》 课程和周志明老师的 《深入理解 Java 虚拟机》（JVM
 高级特性和最佳实践 第 2 版）中所作的课程和书本代码，部分代码不完整，会在温习的过程逐步补充，完整代码在笔记中都有所呈现。
- 代码分为两部分
    - com.gjxaiou 包中为课程代码
    - book_understandingJVM 包为书本中代码
- 因为 《深入理解 Java 虚拟机》（第 2 版）以 JDK1.7 为基础，所以在 JVM 内存结构和垃圾收集器部分和项目基于的 JDK 1.8 有所不同，所有运行结果可能不同。    



- 项目文件目录（参考自 [sunspeedzy](https://github.com/sunspeedzy)）
    - classloader.MyTest1 用来演示Java类的主动使用和被动使用的区别

    - classloader.MyTest2 用来演示静态常量对Java类主动/被动使用的影响

    - classloader.MyTest3 用来演示运行期间才能确定值的类静态常量会导致主动使用这个静态常量所在的类

    - classloader.MyTest4 用来演示 引用类型和基本类型的数组的创建

    - classloader.MyTest5 用来演示 接口的初始化规则

    - classloader.MyTest6 用来演示 类加载器准备阶段的初始化过程 以及 初始化阶段的执行顺序

    - classloader.MyTest5_2 用来演示 接口的初始化规则

    - classloader.MyTest7 用来验证 类加载的双亲机制

    - classloader.MyTest8 用来验证 static final的成员在编译时确定则被引用时不会引起所属类的加载，在运行时确定则被引用时会引起所属类的加载，与 MyTest3 类似，不再编写

    - classloader.MyTest9 用来验证 类继承关系 与 初始化顺序和类加载顺序的 联系

    - classloader.MyTest10 与 MyTest9 类似，只是对Parent类的使用方式不同，使Parent类的初始化不会由Child类的初始化引起

    - classloader.MyTest11 通过子类去访问父类里定义的内容（方法、变量、常量），是对父类的主动使用，而不是对子类的主动使用，是否是主动使用要看定义内容的类，而不是看调用方是谁

    - classloader.MyTest12 用来验证 调用ClassLoader类的loadClass方法加载一个类，并不是对类的主动使用，不会导致类的初始化，反射会造成主动使用类

    - classloader.MyTest13 用来查看各个类加载器的层级关系

    - classloader.MyTest14 用来验证 对于数组实例来说，其类型是由JVM在运行期动态生成的

    - classloader.MyTest15 用来验证类加载器的JavaDoc中关于ClassLoader的描述

    - classloader.MyTest16 自定义一个类加载器，对类加载器的双亲委托机制进行深度剖析

    - classloader.MyCat  第19课创建，用来演示在一个类中创建另一个类的实例时类加载的情况

    - classloader.MySample

    - classloader.MyTest17

    - classloader.MyTest18 用来查看 启动类加载器、扩展类加载器、系统类加载器 所加载的类的路径，在第24课用来演示如何 将自定义的ClassLoader作为系统类加载器

    - classloader.MyTest18_1 用来验证 自定义类如果放在 启动类加载器所加载类的路径中，那么这个类将会被启动类加载器加载

    - classloader.MyTest19 用来验证 扩展类加载器 加载类的情况

    - classloader.MyTest20 用来验证 类加载器所加载的类 在反射时的使用情况

    - classloader.MyPerson

    - classloader.MyTest21 验证 同名类在不同的命名空间之间互不可见

    - classloader.MyTest24 用来讲解线程类上下文类加载器

    - classloader.MyTest25 验证线程的上下文类加载器是 AppClassLoader

    - classloader.MyTest26 ServiceLoader在SPI中的作用，第29课视频

    - classloader.MyTest27 通过JDBC驱动加载深刻理解线程上下文类加载器机制，第30课视频

    - classloader.MyTest22 查看 静态内部类的加载 情况，静态内部类在被调用之前不会被加载到内存中

    - bytecode.MyTest1 用来演示查看java class文件用

    - bytecode.MyTest2 含有静态方法和静态变量的java类

    - bytecode.MyTest3 含有异常处理的方法

    - bytecode.MyTest4~7 展示重载与重写的字节码

    - bytecode.MyTest8 展示基于栈的指令集

    - bytecode.dynamicproxy.* 用于演示动态代理在字节码中是如何表示的，即透过字节码生成审视Java动态代理运作机制

         