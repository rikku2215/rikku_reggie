package com.rikku;

public class diali {
    public static void main(String[] args) {
        shixian s = new shixian("小明",21);
        s.talk("实现类");
        daili d = new daili(s);
        d.talk("代理类");
    }
}

abstract class fu{
    abstract void talk(String msg);
}

class shixian extends fu{
    String name;
    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public shixian(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    void talk(String msg) {
        System.out.println(name+"在"+age+"岁说了"+msg);
    }
}

class daili extends fu{
    private fu f;

    public daili(fu f) {
        this.f = f;
    }

    void sing(){
        System.out.println("大声唱歌");
    }
    @Override
    void talk(String msg) {
        sing();
        f.talk(msg);
    }
}