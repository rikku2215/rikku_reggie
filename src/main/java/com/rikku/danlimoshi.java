package com.rikku;

public class danlimoshi {

}

class Singmode{
    private static Singmode singmode;
    private Singmode(){};
    public static Singmode getSingmode(){
        if (singmode==null){
            synchronized (Singmode.class){
                if (singmode==null){
                    singmode = new Singmode();
                }
            }
        }

        return singmode;
    }
}