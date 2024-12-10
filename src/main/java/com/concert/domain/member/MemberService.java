package com.concert.domain.member;

import com.concert.domain.member.dto.NewMemberDto;
import com.concert.domain.member.dto.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberStoreRepository memberStoreRepository;
    private final MemberReaderRepository memberReaderRepository;
    private final MemberValidator memberValidator;

    public MemberInfoDto createMember(NewMemberDto memberInfo) {
        memberValidator.validate(memberInfo);
        if (memberReaderRepository.existsMember(memberInfo.name())) {
            throw new MemberException(MemberErrorCode.E10002);
        }
        return memberStoreRepository.save(memberInfo);
    }
}
