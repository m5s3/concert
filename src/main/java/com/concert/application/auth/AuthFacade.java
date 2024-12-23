package com.concert.application.auth;

import com.concert.application.auth.dto.LoginCommand;
import com.concert.domain.member.MemberService;
import com.concert.domain.member.dto.MemberInfoDto;
import com.concert.domain.security.TokenConst;
import com.concert.domain.security.TokenService;
import com.concert.domain.security.dto.NewTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthFacade {

    private final MemberService memberService;
    private final TokenService tokenService;

    public String login(LoginCommand loginCommand) {
        MemberInfoDto member = memberService.getMember(loginCommand.name());
        return TokenConst.TOKEN_PREFIX + tokenService.createToken(NewTokenDto.ofMemberId(member.id()));
    }
}
