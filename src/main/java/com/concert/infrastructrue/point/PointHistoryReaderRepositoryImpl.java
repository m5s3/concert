package com.concert.infrastructrue.point;

import com.concert.domain.point.PointHistoryReaderRepository;
import com.concert.domain.point.dto.PointHistoryInfoDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.concert.domain.point.QPointHistoryEntity.pointHistoryEntity;

@Repository
@RequiredArgsConstructor
public class PointHistoryReaderRepositoryImpl implements PointHistoryReaderRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<PointHistoryInfoDto> getHistories(Long memberId, int offset, int limit) {
        return queryFactory
                .select(
                        Projections.constructor(
                                PointHistoryInfoDto.class,
                                pointHistoryEntity.id,
                                pointHistoryEntity.pointId,
                                pointHistoryEntity.memberId,
                                pointHistoryEntity.type,
                                pointHistoryEntity.amount,
                                pointHistoryEntity.createdAt
                        )
                )
                .where(pointHistoryEntity.memberId.eq(memberId))
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    @Override
    public long countHistories(Long memberId) {
        return queryFactory.select(Expressions.ONE)
                .from(pointHistoryEntity)
                .where(pointHistoryEntity.memberId.eq(memberId))
                .fetch()
                .size();
    }
}
