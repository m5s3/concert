package com.concert.application.member.dto;

import com.concert.domain.member.dto.NewMemberDto;
import lombok.Builder;

@Builder
public record CreateMemberCommand(
        String name
) {

    public NewMemberDto toNewMemberDto() {
        return NewMemberDto.builder()
                .name(name)
                .build();
    }
}
