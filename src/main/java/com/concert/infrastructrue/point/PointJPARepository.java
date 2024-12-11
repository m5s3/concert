package com.concert.infrastructrue.point;

import com.concert.domain.point.PointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointJPARepository extends JpaRepository<PointEntity, Long> {
}
