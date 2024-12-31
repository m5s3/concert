package com.concert.domain.concert.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@Builder
public record SearchCriteria(
        @Nullable String title,
        @Nullable String description,
        @Nullable LocalDateTime startDate,
        @Nullable LocalDateTime endDate,
        @Nullable LocalDateTime reservationStartDate,
        long page,
        long size
) {
}
