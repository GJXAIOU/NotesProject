package com.gjxaiou.class10;

class MySample{
    public MySample(){
        System.out.println("MySample is loaded..."+this.getClass().getClassLoader());
        new MyCat();
    }
}
