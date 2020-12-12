package com.gjxaiou.classloader;

public class MyTest22 {
    public static void main(String[] args) {
        MyTest22.StaticInnerCls.getInstance();
    }
    static class StaticInnerCls {
        private final static StaticInnerCls instance = new StaticInnerCls();
        public static StaticInnerCls getInstance() {
            return instance;
        }
    }
}
