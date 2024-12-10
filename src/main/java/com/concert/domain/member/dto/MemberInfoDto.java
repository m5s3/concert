package com.concert.domain.member.dto;

import com.concert.domain.member.MemberEntity;
import lombok.Builder;

@Builder
public record MemberInfoDto(
        Long id,
        String name
) {

    public static MemberInfoDto from(MemberEntity memberEntity) {
        return MemberInfoDto.builder()
                .id(memberEntity.getId())
                .name(memberEntity.getName())
                .build();
    }
}
