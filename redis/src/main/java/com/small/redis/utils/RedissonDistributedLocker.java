package com.small.redis.utils;

import com.small.common.constant.ResultCode;
import com.small.common.enums.GenericResultCodeEnum;
import com.small.common.exception.ResultException;
import lombok.extern.log4j.Log4j2;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Log4j2
@Component
public class RedissonDistributedLocker implements DistributedLocker {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public RLock lock(String lockKey) {
        try {
            RLock lock = redissonClient.getLock(lockKey);
            // 防止死锁
            lock.lock(10, TimeUnit.SECONDS);
            return lock;
        } catch (Exception e) {
            log.error("{}系统中加锁失败,key:{}", ResultCode.SysType, lockKey);
            throw new ResultException(GenericResultCodeEnum.DistributedLocksLockFail);
        }
    }

    @Override
    public RLock lock(String lockKey, int leaseTime) {
        try {
            RLock lock = redissonClient.getLock(lockKey);
            lock.lock(leaseTime, TimeUnit.SECONDS);
            return lock;
        } catch (Exception e) {
            log.error("{}系统中加锁失败,key:{}", ResultCode.SysType, lockKey);
            throw new ResultException(GenericResultCodeEnum.DistributedLocksLockFail);
        }
    }

    @Override
    public RLock lock(String lockKey, TimeUnit unit , int timeout) {
        try {
            RLock lock = redissonClient.getLock(lockKey);
            lock.lock(timeout, unit);
            return lock;
        } catch (Exception e) {
            log.error("{}系统中加锁失败,key:{}", ResultCode.SysType, lockKey);
            throw new ResultException(GenericResultCodeEnum.DistributedLocksLockFail);
        }
    }

    @Override
    public boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            return lock.tryLock(waitTime, leaseTime, unit);
        } catch (InterruptedException e) {
            return false;
        }
    }

    @Override
    public void unlock(String lockKey) {
        try {
            RLock lock = redissonClient.getLock(lockKey);
            lock.unlock();
        } catch (Exception e) {
            log.error("{}系统中解锁失败,key:{}", ResultCode.SysType, lockKey);
            throw new ResultException(GenericResultCodeEnum.DistributedLocksUnlockFail);
        }
    }

    @Override
    public void unlock(RLock lock) {
        try {
            lock.unlock();
        } catch (Exception e) {
            log.error("{}系统中解锁失败", ResultCode.SysType);
            throw new ResultException(GenericResultCodeEnum.DistributedLocksUnlockFail);
        }
    }
}
