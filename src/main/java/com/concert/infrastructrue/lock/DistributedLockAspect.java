package com.concert.infrastructrue.lock;

import com.concert.domain.core.lock.DistributedLock;
import com.concert.domain.core.lock.LockAcquisitionException;
import com.concert.domain.core.lock.LockErrorCode;
import com.concert.domain.core.lock.annotation.DistributedLockOperation;
import com.concert.infrastructrue.aop.support.AopForTransaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class DistributedLockAspect {

    private final DistributedLock distributedLock;
    private final LockKeyGenerator lockKeyGenerator;
    private final AopForTransaction aopForTransaction;

    @Around("@annotation(lockOperation)")
    public Object executeWithLock(ProceedingJoinPoint joinPoint,
                                  DistributedLockOperation lockOperation) throws Throwable {
        String lockKey = lockKeyGenerator.generate(joinPoint, lockOperation);

        try {
            if (!distributedLock.tryLock(lockKey, lockOperation.waitTime(), lockOperation.leaseTime())) {
                throw new LockAcquisitionException(
                        "%s: %s".formatted(LockErrorCode.E60000.getMessage(), lockKey)
                );
            }
            log.debug("락 획득: {}", lockKey);
            return aopForTransaction.proceed(joinPoint);
        } catch (InterruptedException e) {
            throw new LockAcquisitionException(
                "%s: %s".formatted(LockErrorCode.E60001.getMessage(), lockKey), e
            );
        } finally {
            distributedLock.unlock(lockKey);
            log.debug("락 해제: {}", lockKey);
        }
    }


//    @Around("@annotation(com.concert.application.common.annotation.DistributedLockOperation) && " +
//            "@annotation(lockOperation)")
//    public Object executeWithLock(ProceedingJoinPoint joinPoint,
//                                  DistributedLockOperation lockOperation) throws Throwable {
//        String lockKey = lockKeyGenerator.generate(joinPoint, lockOperation);
//
//        try {
//            if (!distributedLock.tryLock(lockKey, lockOperation.waitTime(), lockOperation.leaseTime())) {
//                throw new LockAcquisitionException("Failed to acquire lock: " + lockKey);
//            }
//            log.debug("Lock acquired: {}", lockKey);
//            return joinPoint.proceed();
//        } finally {
//            distributedLock.unlock(lockKey);
//            log.debug("Lock released: {}", lockKey);
//        }
//    }
}
