package com.concert.infrastructrue.seat;

import com.concert.domain.seat.SeatEntity;
import com.concert.domain.seat.SeatReaderRepository;
import com.concert.domain.seat.dto.SeatSearchCriteria;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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
                        isDelete()
                ).fetchFirst();
    }

    @Override
    public List<SeatEntity> getSeats(SeatSearchCriteria criteria) {
        long offset = criteria.page() * criteria.size();
        return queryFactory.selectFrom(seatEntity)
                .where(
                        seatEntity.concertScheduleId.eq(criteria.scheduleId()),
                        isDelete()
                )
                .offset(offset)
                .limit(criteria.size())
                .fetch();
    }

    private BooleanExpression isDelete() {
        return seatEntity.isDeleted.eq(false);
    }
}
