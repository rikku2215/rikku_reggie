package com.rikku;

public class mobanfangfa {
    public static void main(String[] args) {
        moban m1 = new student();
        moban m2 = new teacher();
        m1.zuoshi();
        System.out.println("==========================");
        m2.zuoshi();
    }
}

abstract class moban{
    final void zuoshi(){
        zs();
        zw();
        ws();
    }
    abstract protected void zs();
    abstract protected void zw();
    abstract protected void ws();
}
class student extends moban{

    @Override
    protected void zs() {
        System.out.println("起床吃早餐");
    }

    @Override
    protected void zw() {
        System.out.println("听课");
    }

    @Override
    protected void ws() {
        System.out.println("写作业");
    }
}
class teacher extends moban{

    @Override
    protected void zs() {
        System.out.println("早早到学校");
    }

    @Override
    protected void zw() {
        System.out.println("讲课");
    }

    @Override
    protected void ws() {
        System.out.println("准备教案");
    }
}