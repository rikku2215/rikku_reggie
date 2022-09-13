package com.rikku;

import java.util.*;

public class test {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("请输入要输入数字的数量：");
//        int count = scanner.nextInt();
//        int[] sz = new int[count];
//        System.out.println("请输入要输入的数字");
//        for (int i=0;i<count;i++){
//            sz[i]=scanner.nextInt();
//        }
//        Arrays.sort(sz);
//        int l = 0;
//        int r = sz.length-1;
//        quickchecktest q = new quickchecktest();
//        int a = 42;
//        int result = q.chazhao(sz,42,0,sz.length-1);
//        if (result==-1){
//            System.out.println("不存在要查找的数字");
//        }
//        else{
//            System.out.println(a+"这个数字在数组的"+result+"位置上");
//        }

//        ArrayList<Integer> a1 = new ArrayList<>();
//        ArrayList<Integer> a2 = new ArrayList<>();
//        ArrayList<Integer> a3 = new ArrayList<>();
//        Collections.addAll(a1,1,2,3,4,5,6);
//        Collections.addAll(a2,1,2,4,5,6);
//        Collections.addAll(a3,4,5,6,7,8,9);
//
//        List<Integer> temp = (List<Integer>) a1.clone();
//        a1.removeAll(a3);
//        a3.removeAll(temp);
//        a1.addAll(a3);
//        System.out.println(a1);


//        ArrayList<Integer> a1 = new ArrayList<>();
//        Collections.addAll(a1,1,2,3,4,5,6,8,9,10);
//        ArrayList<Integer> a2 = new ArrayList<>();
//        for (int i =1;i<11;i++){
//            a2.add(i);
//        }
//        a2.removeAll(a1);
//        System.out.println("a1数组中缺少的数字是"+a2.get(0));

        Integer[] arr = new Integer[]{0,1,3,4};
        int n = arr.length+1;
        fun(arr,n);

    }

    static void fun(Integer[] arr,int n){
        ArrayList<Integer> ints = new ArrayList<>(Arrays.asList(arr));
        List<Integer> list = new ArrayList<>();
        for (int i=0;i<=n;i++){
            list.add(i);
        }
        list.removeAll(ints);
        System.out.println(list);
    }
}
