package com.gjxaiou.class10;

class MyCat{
    public MyCat(){
        System.out.println("MyCat is loaded..."+this.getClass().getClassLoader());
    }
}