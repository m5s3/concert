package com.concert.application.member;

import com.concert.application.member.dto.CreateMemberCommand;
import com.concert.application.member.dto.MemberDto;
import com.concert.domain.member.MemberService;
import com.concert.domain.member.dto.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberFacade {
    private final MemberService memberService;

    public MemberDto createMember(CreateMemberCommand command) {
        MemberInfoDto member = memberService.createMember(command.toNewMemberDto());
        return MemberDto.from(member);
    }
}
