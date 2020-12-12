package com.gjxaiou.memory;

/**
 * @Author GJXAIOU
 * @Date 2019/12/11 19:00
 */

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * 元空间内存溢出测试(使用 cglib,需要导入对应 jar 包和 asm.jar)
 * 设置元空间最大大小（不让其扩容）：-XX:MaxMetaspaceSize=200m
 * 关于元空间参考：https://www.infoq.cn/article/java-permgen-Removed
 */
public class MyTest4 {
    public static void main(String[] args) {
        //使用动态代理动态生成类（不是实例）
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(MyTest4.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor) (obj, method, ags, proxy) -> proxy.invokeSuper(obj, ags));
            System.out.println("Hello World");
            // 在运行期不断创建 MyTest 类的子类
            enhancer.create();
        }
    }
}
/** output:
 * Caused by: java.lang.OutOfMemoryError: Metaspace
 */
