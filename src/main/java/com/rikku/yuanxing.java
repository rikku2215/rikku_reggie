package com.rikku;

public class yuanxing {
    public static void main(String[] args) throws CloneNotSupportedException {
       Syx syx1 = new Syx("陈翔",3303,97);
       Syx syx2= (Syx) syx1.clone();

        System.out.println(syx1);
        System.out.println(syx2);
        System.out.println("------------");
        System.out.println(syx1.name== syx2.name);
    }
}

class Syx implements Cloneable{
    public String name;
    public int id;
    public int cj;

//    @Override
//    public String toString() {
//        return "syx{" +
//                "name='" + name + '\'' +
//                ", id=" + id +
//                ", cj=" + cj +
//                '}';
//    }

    public Syx(String name, int id, int cj) {
        this.name = name;
        this.id = id;
        this.cj = cj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCj() {
        return cj;
    }

    public void setCj(int cj) {
        this.cj = cj;
    }

    //浅拷贝
//    @Override
//    public Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }

    //深拷贝，基本类型不用动
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Syx syx = (Syx) super.clone();
        //syx.setName(new String(name));
        syx.name = new String(name);
        return syx;
    }
}
