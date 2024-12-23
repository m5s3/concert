package com.concert.domain.security;

import com.concert.domain.security.dto.NewTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenProvider tokenProvider;

    public String createToken(NewTokenDto newTokenDto) {
        return TokenConst.TOKEN_PREFIX + tokenProvider.createAccessToken(newTokenDto);
    }

    public Optional<Long> getMemberId(String token) {
        String removedPrefixToken = removePrefixToken(token);
        if (validateToken(removedPrefixToken)) {
            return Optional.ofNullable(tokenProvider.getMemberId(removedPrefixToken));
        }
        return Optional.empty();
    }

    public boolean validateToken(String token) {
        return tokenProvider.validateToken(removePrefixToken(token));
    }

    private String removePrefixToken(String token) {
        return token.replace(TokenConst.TOKEN_PREFIX, "");
    }
}
