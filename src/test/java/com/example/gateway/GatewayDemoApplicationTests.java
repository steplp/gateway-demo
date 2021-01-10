package com.example.gateway;

import com.example.gateway.model.Hello;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

@SpringBootTest
class GatewayDemoApplicationTests {
    @Resource
    RedisTemplate redisTemplate;


    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Test
    void test01() {


        redisTemplate.opsForValue().set("key02", "value01");
    }


    @Test
    void test02() {


        stringRedisTemplate.opsForValue().set("key01", "value02");


    }


    @Test
    void test03() {


        String key02 = (String) redisTemplate.opsForValue().get("key02");
        System.err.println(key02);
    }


    @Test
    void test04() {


        String key01 =  stringRedisTemplate.opsForValue().get("key01");
        System.err.println(key01);
    }

    @Test
    void test05() {
        List<Hello> list = new ArrayList<>();
        Hello hello1 = new Hello(1000L, "哈哈");
        list.add(hello1);

        Hello hello2 = new Hello(2000L, "呵呵呵");
        list.add(hello2);


        Long aLong = redisTemplate.opsForList().rightPushAll("list-key", list);
        System.out.println(aLong);
    }

    @Test
    void test06() {
//        Hello hello = (Hello) redisTemplate.opsForList().rightPop("list-key");
//        System.out.println(hello);

        List list1 = redisTemplate.opsForList().range("list-key", 0, -1);
//        List<Hello> list1 = redisTemplate.opsForList().range("list-key", 0, -1);
        System.out.println(list1);

        redisTemplate.opsForList().trim("list-key", 0, 2);

        List<Hello> list2 = redisTemplate.opsForList().range("list-key", 0, -1);
        System.out.println(list2);
    }


    @Test
    void test07() {
        Boolean list = redisTemplate.delete("list-key");

        System.out.println(list);
    }

}
