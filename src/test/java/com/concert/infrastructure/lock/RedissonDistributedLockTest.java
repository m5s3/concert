package com.concert.infrastructure.lock;

import com.concert.config.TestContainerConfig;
import com.concert.domain.core.lock.DistributedLock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(TestContainerConfig.class)
class RedissonDistributedLockTest {

    @Autowired
    DistributedLock distributedLock;

    @Test
    @DisplayName("동시에 같은 키로 락 획득 시도시 하나만 성공해야 함")
    void concurrentLockAcquisition() throws InterruptedException {
        // given
        String lockKey = "test:lock:2";
        CountDownLatch startLatch = new CountDownLatch(1); // 스레드 동시 시작을 위한 래치
        CountDownLatch endLatch = new CountDownLatch(2);   // 스레드 완료 대기를 위한 래치
        AtomicBoolean firstThreadLocked = new AtomicBoolean(false);
        AtomicBoolean secondThreadLocked = new AtomicBoolean(false);

        // when
        Thread t1 = new Thread(() -> {
            try {
                startLatch.await(); // 동시 시작을 위해 대기
                firstThreadLocked.set(distributedLock.tryLock(lockKey, 3, 10));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                if (firstThreadLocked.get()) {
                    distributedLock.unlock(lockKey); // 락 해제 추가
                }
                endLatch.countDown();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                startLatch.await(); // 동시 시작을 위해 대기
                secondThreadLocked.set(distributedLock.tryLock(lockKey, 3, 10));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                if (secondThreadLocked.get()) {
                    distributedLock.unlock(lockKey); // 락 해제 추가
                }
                endLatch.countDown();
            }
        });

        // 스레드 시작
        t1.start();
        t2.start();
        
        // 두 스레드 동시 시작
        startLatch.countDown();
        
        // 두 스레드 완료 대기
        endLatch.await(10, TimeUnit.SECONDS);

        // then
        assertThat(firstThreadLocked.get() && secondThreadLocked.get())
            .as("두 스레드 모두 락을 획득하면 안됨")
            .isFalse();
        assertThat(firstThreadLocked.get() || secondThreadLocked.get())
            .as("적어도 하나의 스레드는 락을 획득해야 함")
            .isTrue();
    }
}