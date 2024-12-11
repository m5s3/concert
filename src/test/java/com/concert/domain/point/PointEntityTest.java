package com.concert.domain.point;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

@DisplayName("포인트 도메인 테스트")
class PointEntityTest {

    @DisplayName("포인트 충전 시 최대 값을 넘어 가면 예외가 발생한다")
    @Test
    void test_max_point() {
        // Given
        PointEntity pointEntity = new PointEntity(1L, BigDecimal.valueOf(1));

        // When & Then
        Assertions.assertThatThrownBy(() -> pointEntity.charge(PointConst.MAX))
                .isInstanceOf(PointException.class)
                .hasMessage(PointErrorCode.E20001.getMessage());
    }

    @DisplayName("포인트는 가지고 있는 양보다 더 사용 할 수가 없다")
    @Test
    void test_min_point() {
        // Given
        PointEntity pointEntity = new PointEntity(1L, BigDecimal.valueOf(1000));

        // When & Then
        Assertions.assertThatThrownBy(() -> pointEntity.use(BigDecimal.valueOf(1001)))
                .isInstanceOf(PointException.class)
                .hasMessage(PointErrorCode.E20000.getMessage());
    }
}