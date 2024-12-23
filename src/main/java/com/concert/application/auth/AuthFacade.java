package com.concert.application.auth;

import com.concert.application.auth.dto.LoginCommand;
import com.concert.domain.member.MemberService;
import com.concert.domain.member.dto.MemberInfoDto;
import com.concert.domain.security.TokenErrorCode;
import com.concert.domain.security.TokenException;
import com.concert.domain.security.TokenService;
import com.concert.domain.security.dto.NewTokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthFacade {

    private final MemberService memberService;
    private final TokenService tokenService;

    public String login(LoginCommand loginCommand) {
        MemberInfoDto member = memberService.getMember(loginCommand.name());
        return tokenService.createToken(NewTokenDto.ofMemberId(member.id()));
    }

    public boolean validateMemberToken(String token) {
        try {
            if (Objects.nonNull(token) && tokenService.validateToken(token)) {
                Long memberId = tokenService.getMemberId(token)
                        .orElseThrow(() -> new TokenException(TokenErrorCode.NOT_EXIST_MEMBER));
                log.info("memberId={}", memberId);
                log.info("memberService.existsMember(memberId)={}", memberService.existsMember(memberId));
                return memberService.existsMember(memberId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
