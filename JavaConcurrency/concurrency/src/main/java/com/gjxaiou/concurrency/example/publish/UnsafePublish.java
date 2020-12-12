package com.gjxaiou.concurrency.example.publish;

import com.gjxaiou.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
@NotThreadSafe
public class UnsafePublish {

    private String[] states = {"a", "b", "c"};

    /**
     * 通过public发布级别发布了类的域，在类的外部，任何线程都可以访问这个域.
     * 这样是不安全的，因为我们无法检查其他线程是否会修改这个域导致了错误
     */
    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStates()));

        // 修改该成员变量中私有属性的值
        unsafePublish.getStates()[0] = "d";
        log.info("{}", Arrays.toString(unsafePublish.getStates()));
    }
}
