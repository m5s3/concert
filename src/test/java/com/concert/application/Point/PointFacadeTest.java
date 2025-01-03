package com.concert.application.Point;

import com.concert.application.Point.dto.PointDto;
import com.concert.application.Point.dto.UpdatePointCommand;
import com.concert.application.member.MemberFacade;
import com.concert.application.member.dto.CreateMemberCommand;
import com.concert.application.member.dto.MemberDto;
import com.concert.config.TestContainerConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(TestContainerConfig.class)
@DisplayName("포인트 통합 테스트")
class PointFacadeTest {

    @Autowired
    PointFacade pointFacade;

    @Autowired
    MemberFacade memberFacade;

    @Test
    @DisplayName("포인트를 여러번 충전하고 사용하면 적절한 포인트가 남아야 한다")
    public void test_concurrency() throws InterruptedException {
        // Given
        String uniqueName = "test_" + System.currentTimeMillis();  // 고유한 이름 생성
        MemberDto member = memberFacade.createMember(CreateMemberCommand.builder().name(uniqueName).build());

        // When
        UpdatePointCommand chargeCommand = UpdatePointCommand.builder()
                .memberId(member.id())
                .amount(BigDecimal.valueOf(2000))
                .build();

        UpdatePointCommand useCommand = UpdatePointCommand.builder()
                .memberId(member.id())
                .amount(BigDecimal.valueOf(500))
                .build();

        int threadCount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        pointFacade.charge(chargeCommand); // 2000 충전
        for (int i = 0; i < threadCount / 2; i++) {
            executorService.submit(() -> {
                pointFacade.charge(chargeCommand); // 2000 충전
            });

            executorService.submit(() -> {
                pointFacade.use(useCommand); // 500 사용
            });
        }

        executorService.shutdown();
        boolean finished = executorService.awaitTermination(10, TimeUnit.SECONDS);

        // Then
        if (!finished) {
            throw new RuntimeException("쓰레드가 종료되지가 않았습니다.");
        }
        PointDto point = pointFacade.getPoint(member.id());
        System.out.println("point.amount() = " + point.amount());
        assertThat(point.amount().compareTo(BigDecimal.valueOf(9500))).isZero();
    }
}