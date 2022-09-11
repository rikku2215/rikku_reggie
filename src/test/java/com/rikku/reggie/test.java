package com.rikku.reggie;

import com.rikku.reggie.service.DishFlavorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class test {
    @Autowired
    DishFlavorService dishFlavorService;

    @Test
    public void test1(){

        dishFlavorService.selectBatchByDishId(1397849739276890114L);
    }

    @Test
    public void test2(){
        Long[] ids={23132L,21321321L,2141L,436534L,7865L};
        System.out.println("开始测试------------>");
        System.out.println(Arrays.toString(ids));
        System.out.println("结束测试------------>");
    }
}
