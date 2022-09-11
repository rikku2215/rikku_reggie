package com.rikku;

import java.util.Arrays;
import java.util.Scanner;

public class quicksorttest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要输入数字的数量：");
        int count = scanner.nextInt();
        int[] sz = new int[count];
        System.out.println("请输入要输入的数字");
        for (int i=0;i<count;i++){
            sz[i]=scanner.nextInt();
        }


        //Arrays.sort(sz);
        quicksorttest t = new quicksorttest();
        t.quickSort(sz,0,sz.length-1);

        System.out.println("输入的数组为："+ Arrays.toString(sz));
    }



    void quickSort(int[] arrs,int low,int high){
        int z = arrs[low];
        int l = low;
        int r = high;

        while(l<r){
            while(l<r && arrs[r]>=z){
                r--;
            }
            while(l<r && arrs[l]<=z){
                l++;
            }
            if (l<r){
                int temp;
                temp = arrs[l];
                arrs[l]=arrs[r];
                arrs[r]=temp;
            }
        }
        arrs[low] = arrs[l];
        arrs[l]=z;


        if (low<l){
            quickSort(arrs,0,l-1);
        }
        if (high>r){
            quickSort(arrs,l+1,high);
        }
    }
}
