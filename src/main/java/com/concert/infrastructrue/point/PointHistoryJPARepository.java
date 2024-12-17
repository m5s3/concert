package com.concert.infrastructrue.point;

import com.concert.domain.point.PointHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointHistoryJPARepository extends JpaRepository<PointHistoryEntity, Long> {
}
