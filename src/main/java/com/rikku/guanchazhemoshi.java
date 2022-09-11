package com.rikku;

import java.util.Observable;
import java.util.Observer;

public class guanchazhemoshi {
    public static void main(String[] args) {
        Computer computer = new Computer();
        Cpu cpu = new Cpu();
        Display display = new Display();
        Fan fan = new Fan();
        computer.addObserver(cpu);
        computer.addObserver(display);
        computer.addObserver(fan);
        computer.tongdian(220);

    }
}

class Computer extends Observable {
    private int dianya;

    public void tongdian(int dianya){
        this.dianya = dianya;
        this.setChanged();
        this.notifyObservers(this.dianya);
    }
}

class Cpu implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        int dianya = (int) arg;
        if (dianya>=110){
            System.out.println("电压到达110V,cpu启动");
        }
        else{
            System.out.println("电压不足110V,cpu无法工作");
        }
    }
}

class Display implements Observer{
    @Override
    public void update(Observable o, Object arg) {
        int dianya = (int) arg;
        if (dianya>=180){
            System.out.println("电压到达180V,显示器启动");
        }
        else{
            System.out.println("电压不足180V,显示器无法工作");
        }
    }
}

class Fan implements Observer{
    @Override
    public void update(Observable o, Object arg) {
        int dianya = (int) arg;
        if (dianya>=220){
            System.out.println("电压到达220V,风扇启动");
        }
        else{
            System.out.println("电压不足220V,风扇无法工作");
        }
    }
}