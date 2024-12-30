package com.concert.infrastructrue.concert;

import com.concert.domain.concert.ConcertScheduleEntity;
import com.concert.domain.concert.ConcertScheduleReaderRepository;
import com.concert.domain.concert.dto.SearchCriteria;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.concert.domain.concert.QConcertScheduleEntity.concertScheduleEntity;

@Repository
@RequiredArgsConstructor
public class ConcertScheduleReaderRepositoryImpl implements ConcertScheduleReaderRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public ConcertScheduleEntity getSchedule(Long concertId) {
        return queryFactory.selectFrom(concertScheduleEntity)
                .where(concertScheduleEntity.concertId.eq(concertId))
                .fetchOne();
    }

    @Override
    public List<ConcertScheduleEntity> getSchedules(List<Long> concertIds) {
        return queryFactory.selectFrom(concertScheduleEntity)
                .where(concertScheduleEntity.concertId.in(concertIds))
                .fetch();
    }
}
