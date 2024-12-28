package com.concert.infrastructrue.concert;

import com.concert.domain.concert.ConcertEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertJPAStoreRepository extends JpaRepository<ConcertEntity, Long> {
}
