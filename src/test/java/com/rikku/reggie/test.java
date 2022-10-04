package com.rikku.reggie;

import com.rikku.reggie.service.DishFlavorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class test {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test1(){
        redisTemplate.opsForValue().set("test1",1111111111,20, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set("test2",22222222,20, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set("test3",333333333,20, TimeUnit.SECONDS);
    }

    @Test
    public void test2(){
        redisTemplate.delete("test1");
        redisTemplate.delete("test2");
    }

    @Test
    public void test3(){
        System.out.println(redisTemplate.opsForValue().get("test1"));
        System.out.println(redisTemplate.opsForValue().get("test2"));
        System.out.println(redisTemplate.opsForValue().get("test3"));
    }
}
