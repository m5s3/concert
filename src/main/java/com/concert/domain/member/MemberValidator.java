package com.concert.domain.member;

import com.concert.domain.member.dto.NewMemberDto;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MemberValidator {

    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 20;

    public void validate(NewMemberDto memberInfo) {
        String name = memberInfo.name();

        if (Objects.isNull(name)) {
            throw new IllegalArgumentException(new MemberException(MemberErrorCode.E10000));
        }

        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(new MemberException(MemberErrorCode.E10001));
        }
    }
}
