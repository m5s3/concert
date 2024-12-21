package com.concert.domain.security;

import com.concert.domain.security.dto.NewTokenDto;

public interface TokenProvider {

    String createAccessToken(NewTokenDto token);
    Long getUserId(String token);
    boolean validateToken(String token);
}
