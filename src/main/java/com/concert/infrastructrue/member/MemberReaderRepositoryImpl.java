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

import static com.concert.domain.member.QMemberEntity.*;

@Repository
@RequiredArgsConstructor
public class MemberReaderRepositoryImpl implements MemberReaderRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public MemberInfoDto getMember(Long id) {
        return queryFactory
                .select(Projections.constructor(MemberInfoDto.class, memberEntity.id, memberEntity.name))
                .from(memberEntity)
                .where(memberEntity.id.eq(id)).fetchFirst();
    }

    @Override
    public boolean existsMember(String name) {
        return queryFactory.selectOne()
                .from(memberEntity)
                .where(memberEntity.name.eq(name))
                .fetchFirst() != null;
    }
}
