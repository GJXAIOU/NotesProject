package com.gjxaiou.jdk8.stream;


import java.util.IntSummaryStatistics;
import java.util.UUID;
import java.util.stream.Stream;

public class StreamTest6 {
    public static void main(String[] args) {
        // generate() 返回
        Stream<String> stream = Stream.generate(UUID.randomUUID()::toString);
        stream.findFirst().ifPresent(System.out::println);

        // 没有 limit() 就是无限流
        Stream<Integer> stream2 = Stream.iterate(1, item -> item + 2).limit(6);

        // 找出该流中大于 2 的元素，然后将每个元素乘以 2，然后忽略掉流中的前两个元素，然后再取流中的前两个元素，最后求出流中元素的总和。
        System.out.println(stream2.filter(item -> item > 2).mapToInt(item -> item * 2).skip(2).limit(2).sum());

        // 因为像 min() 、max() 返回 OptionalInt，因为防止如果如空则可以规避 NPE 问题，而上面的 sum() 不存在 NPE 问题。
        stream2.filter(item -> item > 2).mapToInt(item -> item * 2).skip(2).limit(2).max().ifPresent(System.out::println);

        // 先得到结果
        IntSummaryStatistics summaryStatistics = stream2.filter(item -> item > 2).
                mapToInt(item -> item * 2).skip(2).limit(2).summaryStatistics();

        System.out.println(summaryStatistics.getMin());
        System.out.println(summaryStatistics.getCount());
        System.out.println(summaryStatistics.getMax());

        /**
         * 知识点：Stream 流不能重复使用
         */
        // 注意 stream 中每个操作都会生成一个全新的 stream 流
        System.out.println(stream2);
        // stream2.filter() 会返回一个新的流对象。
        System.out.println(stream2.filter(item -> item > 2));
        // 这行会报错：因为流不能在使用过或者关闭之后再次使用。因为这里操作的 stream2 不是上一行的返回值，而是原始的 stream2。
        System.out.println(stream2.distinct());

        // 下面是正确方式
        System.out.println(stream2);
        Stream<Integer> stream3 = stream2.filter(item -> item > 2);
        System.out.println(stream3);
        Stream<Integer> stream4 = stream3.distinct();
        System.out.println(stream4);
    }
}
