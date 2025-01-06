package com.concert.infrastructrue.seat;

import com.concert.domain.seat.SeatEntity;
import com.concert.domain.seat.SeatStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SeatStoreRepositoryImpl implements SeatStoreRepository {

    private final SeatJPAStoreRepository seatJPAStoreRepository;

    @Override
    public SeatEntity save(SeatEntity seatEntity) {
        return seatJPAStoreRepository.save(seatEntity);
    }
}
