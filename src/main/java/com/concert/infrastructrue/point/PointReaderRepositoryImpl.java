package com.concert.infrastructrue.point;

import com.concert.domain.point.PointEntity;
import com.concert.domain.point.PointReaderRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Objects;

import static com.concert.domain.point.QPointEntity.pointEntity;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PointReaderRepositoryImpl implements PointReaderRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public PointEntity getPoint(Long memberId) {
        PointEntity point = queryFactory
                .selectFrom(pointEntity)
                .where(pointEntity.memberId.eq(memberId))
                .fetchFirst();

        if (Objects.isNull(point)) {
            throw new IllegalArgumentException("해당 유저는 포인트가 없습니다.");
        }
        return point;
    }

    @Override
    public boolean existsPoint(Long memberId) {
        return queryFactory.selectOne()
                .from(pointEntity)
                .where(pointEntity.memberId.eq(memberId))
                .fetchOne() != null;
    }
}
