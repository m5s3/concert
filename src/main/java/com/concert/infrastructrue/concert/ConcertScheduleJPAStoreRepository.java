package com.concert.infrastructrue.concert;

import com.concert.domain.concert.ConcertScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertScheduleJPAStoreRepository extends JpaRepository<ConcertScheduleEntity, Long> {
}
