package com.rikku;

public class celue {
    public static void main(String[] args) {
        Context context = new Context();
        context.setCat(new Smallcat());
        context.setDog(new Bigdog());
        context.catcry();
        context.dogrun();
    }
}
//控制类
class Context{
    private Cat cat;
    private Dog dog;
    public void setCat(Cat cat){
        this.cat = cat;
    }
    public void setDog(Dog dog){
        this.dog = dog;
    }
    public void catcry(){
        cat.cry();
    }
    public void dogrun(){
        dog.run();
    }

}

//策略类接口1
interface Cat{
    void cry();
}
//策略类1实现
class Bigcat implements Cat{

    @Override
    public void cry() {
        System.out.println("大猫的叫声是--->唔唔喵");
    }
}
class Smallcat implements Cat{

    @Override
    public void cry() {
        System.out.println("小猫的叫声是--->nyanya");
    }
}


//策略类接口2
interface Dog{
    void run();
}
//策略类2实现
class Bigdog implements Dog{

    @Override
    public void run() {
        System.out.println("大狗飞快地跑了出去");
    }
}
class Smalldog implements Dog{

    @Override
    public void run() {
        System.out.println("小狗慢慢地走");
    }
}