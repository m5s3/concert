package com.concert.domain.core.lock;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LockErrorCode {

    E60000("락 획득 실패"),
    E60001("락 타임아웃");

    private final String message;
}
