package com.concert.infrastructrue.concert;

import com.concert.domain.concert.ConcertEntity;
import com.concert.domain.concert.ConcertReaderRepository;
import com.concert.domain.concert.dto.SearchCriteria;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.concert.domain.concert.QConcertEntity.concertEntity;
import static com.concert.domain.concert.QConcertScheduleEntity.concertScheduleEntity;

@Repository
@RequiredArgsConstructor
public class ConcertReaderRepositoryImpl implements ConcertReaderRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public ConcertEntity getConcert(Long id) {
        return queryFactory.selectFrom(concertEntity)
                .where(concertEntity.id.eq(id))
                .fetchOne();
    }

    @Override
    public List<ConcertEntity> searchConcerts(SearchCriteria criteria) {
        return queryFactory.selectFrom(concertEntity)
                .join(concertScheduleEntity)
                .on(concertEntity.id.eq(concertScheduleEntity.concertId))
                .where(
                        titleContains(criteria.title()),
                        descriptionContains(criteria.description()),
                        startDateAfter(criteria.startDate()),
                        endDateBefore(criteria.endDate()),
                        reservationStartDateAfter(criteria.reservationStartDate())
                )
                .fetch();
    }

    private BooleanExpression descriptionContains(String description) {
        return description != null ? concertEntity.description.containsIgnoreCase(description) : null;
    }

    private BooleanExpression titleContains(String title) {
        return title != null ? concertEntity.title.containsIgnoreCase(title) : null;
    }

    private BooleanExpression startDateAfter(LocalDateTime startDate) {
        return startDate != null ? concertScheduleEntity.startDate.goe(startDate) : null;
    }

    private BooleanExpression endDateBefore(LocalDateTime endDate) {
        return endDate != null ? concertScheduleEntity.endDate.loe(endDate) : null;
    }

    private BooleanExpression reservationStartDateAfter(LocalDateTime reservationStartDate) {
        return reservationStartDate != null ? concertScheduleEntity.reservationStartDate.goe(reservationStartDate) : null;
    }
}
