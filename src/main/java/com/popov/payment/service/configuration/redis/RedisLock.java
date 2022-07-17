package com.popov.payment.service.configuration.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Component
public class RedisLock {

    private static final String KEY_TEMPLATE = "lock:%s";

    private final RedisTemplate<String, Long> redisTemplate;

    /*
    Метод запроса блокировки.
    @param expireMillis - время, на которое данная зачада будет заблокирована в милисикундах
    @param taskKey - ключ, по которому будет заблокировна задача
    @return статус захвата (захвачено/не захвачена
    */

    public boolean acquireLock(long expireMillis, String taskKey) {
        String lockKey = getLockKey(taskKey);

        Long expireAt = redisTemplate.opsForValue().get(lockKey);

        long currentTimeMillis = System.currentTimeMillis();

        if (Objects.nonNull(expireAt)) {

            if (expireAt <= currentTimeMillis) {
                redisTemplate.delete(lockKey);
            } else {
                return false;
            }
        }

        Long expire = currentTimeMillis + expireMillis;

        return Optional
                .ofNullable(redisTemplate.opsForValue().setIfAbsent(lockKey, expire))
                .orElse(false);
    }

    public void releaseLock(String taskKey) {
        String lockKey = getLockKey(taskKey);
        redisTemplate.delete(lockKey);
    }

    private String getLockKey(String key) {
        return String.format(KEY_TEMPLATE, key);
    }
}
