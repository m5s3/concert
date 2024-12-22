package com.concert.domain.security;

import com.concert.domain.security.dto.NewTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenProvider tokenProvider;

    public String createToken(NewTokenDto newTokenDto) {
        return tokenProvider.createAccessToken(newTokenDto);
    }

    public Long getMemberId(String token) {
        if(tokenProvider.validateToken(token)) {
            return tokenProvider.getMemberId(token);
        }
        return null;
    }
}
