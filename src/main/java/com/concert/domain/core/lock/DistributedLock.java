package com.concert.domain.core.lock;

public interface DistributedLock {

    void lock(String key);
    boolean tryLock(String key, long waitTime, long leaseTime);
    void unlock(String key);
}
