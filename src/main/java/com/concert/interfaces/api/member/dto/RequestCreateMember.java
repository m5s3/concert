package com.concert.interfaces.api.member.dto;

import com.concert.application.member.dto.CreateMemberCommand;

public record RequestCreateMember(
        String name
) {

    public CreateMemberCommand toCommand() {
        return CreateMemberCommand.builder().name(name).build();
    }
}
