package com.concert.infrastructrue.security;

import com.concert.domain.security.TokenErrorCode;
import com.concert.domain.security.TokenProvider;
import com.concert.domain.security.dto.NewTokenDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDateTime;

@Slf4j
@Component
public class JwtTokenProvider implements TokenProvider {

    private final Key key;
    @Value("${jwt.expiration-time}")
    private Long accessTokenExpiredTime;

    public JwtTokenProvider(
            @Value("${jwt.secret}") String secretKey
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String createAccessToken(NewTokenDto token) {
        return createToken(token);
    }

    private String createToken(NewTokenDto token) {
        Claims claims = Jwts.claims();
        claims.put("memberId", token.memberId());

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tokenExpirationTime = now.plusSeconds(accessTokenExpiredTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(java.sql.Timestamp.valueOf(now))
                .setExpiration(java.sql.Timestamp.valueOf(tokenExpirationTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public Long getMemberId(String token) {
        return parseClaims(token).get("memberId", Long.class);
    }

    private Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info(TokenErrorCode.INVALID_TOKEN.getMessage(), e);
        } catch (ExpiredJwtException e) {
            log.info(TokenErrorCode.EXPIRED_TOKEN.getMessage(), e);
        } catch (UnsupportedJwtException e) {
            log.info(TokenErrorCode.UNSUPPORTED_TOKEN.getMessage(), e);
        } catch (IllegalArgumentException e) {
            log.info(TokenErrorCode.EMPTY_TOKEN.getMessage(), e);
        }
        return false;
    }
}
