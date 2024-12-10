package com.concert.domain.member;

import com.concert.domain.member.dto.MemberInfoDto;
import com.concert.domain.member.dto.NewMemberDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("멤버 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberStoreRepository memberStoreRepository;

    @Mock
    private MemberReaderRepository memberReaderRepository;

    @Mock
    private MemberValidator memberValidator;

    @InjectMocks
    private MemberService memberService;

    @DisplayName("멤버를 생성한다")
    @Test
    void createMember_Success() {
        // Given
        NewMemberDto newMemberDto = new NewMemberDto("myoungsoo");
        MemberInfoDto expectedMemberInfoDto = new MemberInfoDto(1L, "myoungsoo");

        // Mock behaviors
        doNothing().when(memberValidator).validate(newMemberDto);
        when(memberReaderRepository.existsMember("myoungsoo")).thenReturn(false);
        when(memberStoreRepository.save(newMemberDto)).thenReturn(expectedMemberInfoDto);

        // When
        MemberInfoDto result = memberService.createMember(newMemberDto);

        // Then
        assertNotNull(result);
        assertEquals(expectedMemberInfoDto, result);
        verify(memberValidator).validate(newMemberDto); // Verify validation was called
        verify(memberReaderRepository).existsMember("myoungsoo"); // Verify existence check
        verify(memberStoreRepository).save(newMemberDto); // Verify save
    }

    @DisplayName("멤버를 생성할때 동일한 이름이 있으면 예외가 발생한다")
    @Test
    void createMember_ThrowsException_WhenMemberAlreadyExists() {
        // Given
        NewMemberDto newMemberDto = new NewMemberDto("myoungsoo");

        // Mock behaviors
        doNothing().when(memberValidator).validate(newMemberDto);
        when(memberReaderRepository.existsMember("myoungsoo")).thenReturn(true);

        // When & Then
        MemberException exception = assertThrows(MemberException.class,
                () -> memberService.createMember(newMemberDto)
        );

        assertEquals(MemberErrorCode.E10002, exception.getErrorCode());
        verify(memberValidator).validate(newMemberDto);
        verify(memberReaderRepository).existsMember("myoungsoo");
        verify(memberStoreRepository, never()).save(any());
    }
}