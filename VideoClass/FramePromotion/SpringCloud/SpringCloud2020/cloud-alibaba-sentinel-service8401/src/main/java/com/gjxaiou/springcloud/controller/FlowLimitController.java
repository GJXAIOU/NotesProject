package com.gjxaiou.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author GJXAIOU
 * @Date 2020/12/5 9:39
 */
@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        return "---testA-----";
    }

    @GetMapping("/testB")
    public String testB() {
//        try {
//            Thread.sleep(3);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "-----testB----";
    }

    // 用于测试 RT：平均响应时间
    @GetMapping("/testD")
    public String testD() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "---testD---";
    }

    // 用于测试异常比例
    @GetMapping("/testE")
    public String testE() {
        log.info("进入 testE------");
        int age = 10 / 0;
        return "---testE---";
    }

    /**
     * 热点参数限流
     */
    @GetMapping("/testHotKey")
    // 名字可以随意起，但为唯一标识
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    // required 表示是否必须包含此参数
    public String testHostKey(@RequestParam(value = "p1",required = false) String p1,
                              @RequestParam(value="p2",required = false)String p2){
        System.out.println(p1);
        return "正常执行：testHostKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException exception){
        return "兜底方法：deal_testHotKey";
    }
}
