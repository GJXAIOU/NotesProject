package com.gjxaiou.gc;

/**
 * 此代码演示两点：
 * 1.对象可以在 GC 时自我救赎。
 * 2.这种自我救赎的机会只有一次，因为finalize()方法最多只会被调用一次。
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC saveMe = null;

    public void isLive() {
        System.out.println("我还活着！");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("执行finalize()方法中……");
        // 完成自我救赎
        saveMe = this;
    }

    public static void main(String[] args) throws InterruptedException {
        saveMe = new FinalizeEscapeGC();

        // 对象第一次拯救自己
        saveMe = null;
        System.gc();

        // 因为finalize方法优先级比较低，所以暂停进行等待
        Thread.sleep(5000);

        if (saveMe == null) {
            System.out.println("我已经死亡！");
        } else {
            saveMe.isLive();
        }

        // 对象第二次自我救赎，失败
        saveMe = null;
        System.gc();
        Thread.sleep(5000);

        if (saveMe == null) {
            System.out.println("我已经死亡！");
        } else {
            saveMe.isLive();
        }
    }
}

