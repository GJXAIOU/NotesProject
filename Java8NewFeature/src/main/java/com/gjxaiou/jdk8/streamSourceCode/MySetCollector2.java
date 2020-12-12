package com.gjxaiou.jdk8.streamSourceCode;


import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 输入：Set<String>
 * 输出：Map<String,String>
 *
 * @param <T>
 */
public class MySetCollector2<T> implements Collector<T, Set<T>, Map<T, T>> {

    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("supplier invoked!");

        //  return HashSet<T>::new;
        return () -> {
            System.out.println("-----------");
            return new HashSet<T>();
        };
        /**
         *     A a1 = supplier.get();
         *     accumulator.accept(a1, t1);
         *     accumulator.accept(a1, t2);
         *     R r1 = finisher.apply(a1);  // result without splitting
         *
         *     A a2 = supplier.get();
         *     accumulator.accept(a2, t1);
         *     A a3 = supplier.get();
         *     accumulator.accept(a3, t2);
         *     R r2 = finisher.apply(combiner.apply(a2, a3));  // result with splitting
         *
         */
    }

    // 接收两个参数（结果容器类型，流中元素类型），并且不返回值
    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("accumulator invoked!");

        return (set, item) -> {
            // 并发异常根源：多个线程使用 accumulator()
            // 遍历同一个结合进行添加元素的时候，但是这里又进行了遍历所以爆出异常，具体异常分析见：ConcurrentModificationException 源码。
            System.out.println("accumulator: " + set + ", " + Thread.currentThread().getName());
            set.add(item);
        };
    }

    // 并发流时候才会调用
    @Override
    public BinaryOperator<Set<T>> combiner() {
        System.out.println("combiner invoked!");

        return (set1, set2) -> {
            System.out.println("set1: " + set1);
            System.out.println("set2: " + set2);
            set1.addAll(set2);
            return set1;
        };
    }

    // 中间类型和最终类型不一样，需要使用 finisher() 转换
    @Override
    public Function<Set<T>, Map<T, T>> finisher() {
        System.out.println("finisher invoked!");

        return set -> {
            Map<T, T> map = new TreeMap<>();
            set.stream().forEach(item -> map.put(item, item));
            return map;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("characteristics invoked!");

        return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED));
        // 测试情况一：这样会在运行时候报错，因为如果加上这个标识表示中间结果和最终结果类型一致，会直接使用强制类型转换，不会再使用 finisher
        //  return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
        // 测试情况二：会爆出并发异常，因为下面使用了 parallelStream()，如果加上这个是相当于多个线程操作同一个结果容器（combiner
        // () 也就不会被调用了），不加表示多个线程操作多个结果容器（和线程数相同）。
        // return Collections.unmodifiableSet(EnumSet.of(Characteristics.CONCURRENT));
    }

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());

        for (int i = 0; i < 1; ++i) {
            List<String> list = Arrays.asList("hello", "world", "welcome", "hello", "a", "b", "c"
                    , "d", "e", "f", "g");
            Set<String> set = new HashSet<>();
            set.addAll(list);

            System.out.println("set: " + set);
            Map<String, String> map = set.parallelStream().collect(new MySetCollector2<>());
            System.out.println(map);
        }
    }
}