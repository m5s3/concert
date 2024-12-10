package com.concert.interfaces.api.member.dto;

import com.concert.application.member.dto.MemberQuery;
import lombok.Builder;

@Builder
public record MemberRequest(
        Long id,
        String name
) {

    public MemberQuery toQuery() {
        return MemberQuery.builder()
                .id(id)
                .name(name)
                .build();
    }
}
