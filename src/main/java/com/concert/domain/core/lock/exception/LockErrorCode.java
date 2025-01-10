package com.concert.domain.core.lock.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LockErrorCode {
    
    E60000("락 획득 실패"),
    E60001("락 해제 실패"),
    E60002("락 타임아웃"),
    E60003("락 키 생성 실패"),
    E60004("이미 락이 존재함");

    private final String message;
} 