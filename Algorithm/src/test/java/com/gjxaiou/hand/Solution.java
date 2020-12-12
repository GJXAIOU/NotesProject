package com.gjxaiou.hand;

public class Solution {
    public int cutRope(int target) {
        int num = target / 3;
        int leave = target % 3;
        if (leave == 0) {
            return (int) Math.pow(3, num);
        } else if (leave == 1) {
            return (int) (Math.pow(3, num - 1) * 4);
        } else {
            return (int) (Math.pow(3, num) * 2);
        }
    }
}

interface hello{
    void h1();
}
interface world{
    void h1();
}

class demo implements hello,world{

    @Override
    public void h1() {
        System.out.println("demo");
    }
}
