package com.concert.domain.member;

import com.concert.domain.member.dto.MemberInfoDto;

public interface MemberReaderRepository {
    MemberInfoDto getMember(Long id);
    boolean existsMember(String name);
}
