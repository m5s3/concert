package com.concert.infrastructrue.seat;

import com.concert.domain.seat.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatJPAStoreRepository extends JpaRepository<SeatEntity, Long> {
}
