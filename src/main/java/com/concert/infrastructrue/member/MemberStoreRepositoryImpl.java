package com.concert.infrastructrue.member;

import com.concert.domain.member.MemberStoreRepository;
import com.concert.domain.member.dto.NewMemberDto;
import com.concert.domain.member.dto.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberStoreRepositoryImpl implements MemberStoreRepository {
    private final MemberJPAStoreRepository memberJPAStoreRepository;

    @Override
    public MemberInfoDto save(NewMemberDto newMemberDto) {
        return MemberInfoDto.from(memberJPAStoreRepository.save(newMemberDto.toMember()));
    }
}
