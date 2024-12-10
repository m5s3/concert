package com.concert.domain.member;

import com.concert.domain.member.dto.NewMemberDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("맴버 검증 테스트")
class MemberEntityValidatorTest {

    MemberValidator memberValidator = new MemberValidator();

    @Test
    @DisplayName("멤버 생성 시 이름은 필수값이다")
    void test_name_nullable() {
        Assertions.assertThatThrownBy(() -> memberValidator.validate(NewMemberDto.builder().build()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("멤버 생성 시 이름은 2자 이상이다")
    void test_name_min() {
        Assertions.assertThatThrownBy(() -> memberValidator.validate(NewMemberDto.builder().name("A").build()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("멤버 생성 시 이름은 20자 이하이다")
    void test_name_max() {
        Assertions.assertThatThrownBy(() -> memberValidator.validate(
                        NewMemberDto.builder()
                                .name("AAAA_AAAA_AAAA_AAAA__")
                                .build()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}