package com.concert.domain.point;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PointValidator {

    public void validateCharge(BigDecimal amount) {
        if (amount.compareTo(PointConst.MIN) < 0) {
            throw new PointException(PointErrorCode.E20002);
        }
        if (amount.compareTo(PointConst.MAX) > 0) {
            throw new PointException(PointErrorCode.E20001);
        }
    }

    public void validateUse(BigDecimal amount) {
        if (amount.compareTo(PointConst.MIN) < 0) {
            throw new PointException(PointErrorCode.E20003);
        }
    }
}
