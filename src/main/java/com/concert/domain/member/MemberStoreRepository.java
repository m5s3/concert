package com.concert.domain.member;

import com.concert.domain.member.dto.MemberInfoDto;
import com.concert.domain.member.dto.NewMemberDto;

public interface MemberStoreRepository {

    MemberInfoDto save(NewMemberDto member);
}
