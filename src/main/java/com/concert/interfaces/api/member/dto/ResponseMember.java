package com.concert.interfaces.api.member.dto;

import com.concert.application.member.dto.MemberDto;
import lombok.Builder;

@Builder
public record ResponseMember(
        Long id,
        String name
) {

    public static ResponseMember from(MemberDto memberDto) {
        return ResponseMember.builder()
                .id(memberDto.id())
                .name(memberDto.name())
                .build();
    }
}
