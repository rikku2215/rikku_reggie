package com.rikku;

import java.util.Arrays;

public class quickchecktest {
    public int chazhao(int[] sz,int a,int l,int r){
        int mid = (l+r)/2;
        while (l<=r){
            if (sz[mid]<a){
                l = mid;
                mid = (l+r)/2;

            }
            else if (sz[mid]>a){
                r =  mid;
                mid = (l+r)/2;
            }
            else if (sz[mid]==a){

                return mid;
            }
        }
        return -1;
    }
}
