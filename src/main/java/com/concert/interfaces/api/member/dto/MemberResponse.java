package com.concert.interfaces.api.member.dto;

import com.concert.application.member.dto.MemberDto;
import lombok.Builder;

@Builder
public record MemberResponse(
        Long id,
        String name
) {

    public static MemberResponse from(MemberDto memberDto) {
        return MemberResponse.builder()
                .id(memberDto.id())
                .name(memberDto.name())
                .build();
    }
}
