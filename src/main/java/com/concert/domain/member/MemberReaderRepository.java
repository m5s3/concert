package com.concert.domain.member;

import com.concert.domain.member.dto.MemberInfoDto;

public interface MemberReaderRepository {
    MemberInfoDto getMember(Long id);
    MemberEntity getMember(String name);
    boolean existsMember(String name);
    boolean existsMember(Long id);
}
