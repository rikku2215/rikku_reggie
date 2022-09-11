package com.rikku;

public class waiguan {
    public static void main(String[] args) {
        Computer1 c = new Computer1();
        c.start();
        System.out.println("过一段时间后-------------------");
        c.stop();

    }
}
class Computer1{
    Cpu1 c = new Cpu1();
    Disk d = new Disk();
    void start(){
        System.out.println("计算机开始启动=====");
        c.start();
        d.start();
        System.out.println("计算机启动完成=====");
    }
    void stop(){
        System.out.println("计算机开始关闭=====");
        c.stop();
        d.stop();
        System.out.println("计算机关闭完成=====");
    }
}

class Cpu1{
    void start(){
        System.out.println("cpu启动");
    }
    void stop(){
        System.out.println("cpu关闭");
    }
}

class Disk{
    void start(){
        System.out.println("disk启动");
    }
    void stop(){
        System.out.println("disk关闭");
    }
}
