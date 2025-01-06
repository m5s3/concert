package com.concert.infrastructrue.reservation;

import com.concert.domain.reservation.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationJPAStoreRepository extends JpaRepository<ReservationEntity, Long> {
}
