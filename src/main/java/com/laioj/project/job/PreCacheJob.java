package com.laioj.project.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laioj.project.model.entity.User;
import com.laioj.project.service.UserService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PreCacheJob {
    private static final Logger log = LoggerFactory.getLogger(PreCacheJob.class);
    @Resource
    private UserService userService;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private RedissonClient redissonClient;
    //重点用户
    private final List<Long> mainUserList = Collections.singletonList(1L);

    //    每天执行，预热推荐用户
//这是cron表达式表示是否执行频率
    @Scheduled(cron = "0 59 23 * * ? *")
//    synchronized 关键字只对单个jvm有效
    public void doCacheRecommendUser() {
//        获取到锁的对象
        RLock lock = redissonClient.getLock("laipao:precachejob:docache:lock");
        try {
//            配置锁，失效时间等
            if (lock.tryLock(0, 30000, TimeUnit.MILLISECONDS)) {

                System.out.println("getLock"+Thread.currentThread().getId());
//                以下是查询数据的过程
                for (Long userId : mainUserList) {
                    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                    Page<User> userPage = userService.page(new Page<>(1, 20), queryWrapper);
                    String redisKey = String.format("laipao:User:recommend:%s", userId);
                    ValueOperations<String, Object> stringObjectValueOperations = redisTemplate.opsForValue();

                    //写缓存
                    try {
                        stringObjectValueOperations.set(redisKey, userPage, 30000, TimeUnit.MILLISECONDS);
                    } catch (Exception e) {
                        log.error("redis set key error", e);
                    }
                }

            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            //                只能释放自己的锁
//                isHeldByCurrentThread：判断当前线程是不是自己加的那个锁，底层通过线程id进行判断
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }

    }
}
