package com.concert.domain.member.dto;

import com.concert.domain.member.MemberEntity;
import lombok.Builder;

@Builder
public record NewMemberDto(
        String name
) {

    public MemberEntity toMember() {
        return MemberEntity.builder().name(name).build();
    }
}
