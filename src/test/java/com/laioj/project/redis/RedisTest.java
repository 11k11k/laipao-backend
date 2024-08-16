package com.laioj.project.redis;

import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTest {
    @Autowired
    private RedissonClient redissonClient;

    @Test
    void testWatchDog(){
        RLock lock = redissonClient.getLock("laipao:precachejob:docache:lock");
        try {
            if(lock.tryLock(0,-1, TimeUnit.MILLISECONDS)){
                Thread.sleep(300000);
                System.out.println("getLock"+Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        finally{
            if (lock.isHeldByCurrentThread()) {
                System.out.println("unLock"+Thread.currentThread().getName());
                lock.unlock();
            }
        }
    }
}
