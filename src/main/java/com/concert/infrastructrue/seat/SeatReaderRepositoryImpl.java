package com.concert.infrastructrue.seat;

import com.concert.domain.seat.SeatEntity;
import com.concert.domain.seat.SeatReaderRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.concert.domain.seat.QSeatEntity.seatEntity;

@Repository
@RequiredArgsConstructor
public class SeatReaderRepositoryImpl implements SeatReaderRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public SeatEntity getSeat(long seatId) {
        return queryFactory.selectFrom(seatEntity)
                .where(
                        seatEntity.id.eq(seatId),
                        seatEntity.isDeleted.eq(false)
                ).fetchFirst();
    }
}
