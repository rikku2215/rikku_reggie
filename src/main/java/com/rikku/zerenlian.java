package com.rikku;

public class zerenlian {
    public static void main(String[] args) {
        lingdao l1 = new fudaoyuan("辅导张");
        lingdao l2 = new yuanzhang("院长陈");
        lingdao l3 = new xiaozhang("校长李");
        l1.setXiayige(l2);
        l2.setXiayige(l3);

        //开始工作
        l1.gongzuo(8);
    }
}


abstract class lingdao{
    public String name;
    lingdao xiayige;
    lingdao(String name){
        this.name = name;
    }
    void setXiayige(lingdao l){
        this.xiayige=l;
    }

    abstract void gongzuo(int i);
}

class fudaoyuan extends lingdao{

    fudaoyuan(String name) {
        super(name);
    }

    @Override
    void gongzuo(int i) {
        if (i<=3){
            System.out.println("辅导员"+name+"审批三天内假期");
        }
        else if (xiayige!=null){
            xiayige.gongzuo(i);
        }
    }
}
class yuanzhang extends lingdao{

    yuanzhang(String name) {
        super(name);
    }

    @Override
    void gongzuo(int i) {
        if (i<=5){
            System.out.println("院长"+name+"审批五天内假期");
        }
        else if (xiayige!=null){
            xiayige.gongzuo(i);
        }
    }
}
class xiaozhang extends lingdao{

    xiaozhang(String name) {
        super(name);
    }

    @Override
    void gongzuo(int i) {
        if (i<=7){
            System.out.println("校长"+name+"审批七天内假期");
        }
        else{
            System.out.println("假期超过七天，不给批准");
        }
    }
}