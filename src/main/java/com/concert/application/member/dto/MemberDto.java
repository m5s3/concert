package com.concert.application.member.dto;

import com.concert.domain.member.dto.MemberInfoDto;
import lombok.Builder;

@Builder
public record MemberDto(
        Long id,
        String name
) {

    public static MemberDto from(MemberInfoDto member) {
        return MemberDto.builder()
                .id(member.id())
                .name(member.name())
                .build();
    }
}
