package com.concert.infrastructrue.member;

import com.concert.domain.member.MemberReaderRepository;
import com.concert.domain.member.QMemberEntity;
import com.concert.domain.member.dto.MemberInfoDto;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Objects;

import static com.concert.domain.member.QMemberEntity.*;

@Repository
@RequiredArgsConstructor
public class MemberReaderRepositoryImpl implements MemberReaderRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public MemberInfoDto getMember(Long id) {
        MemberInfoDto member = queryFactory
                .select(Projections.constructor(MemberInfoDto.class, memberEntity.id, memberEntity.name))
                .from(memberEntity)
                .where(memberEntity.id.eq(id)).fetchFirst();
        if (Objects.isNull(member)) {
            throw new IllegalArgumentException("유저를 찾을 수 없습니다. id=%d".formatted(id));
        }
        return member;
    }

    @Override
    public boolean existsMember(String name) {
        return queryFactory.selectOne()
                .from(memberEntity)
                .where(memberEntity.name.eq(name))
                .fetchFirst() != null;
    }
}
