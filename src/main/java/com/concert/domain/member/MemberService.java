package com.concert.domain.member;

import com.concert.domain.member.dto.NewMemberDto;
import com.concert.domain.member.dto.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

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

    public MemberInfoDto getMember(Long id) {
        return memberReaderRepository.getMember(id);
    }

    public MemberInfoDto getMember(String name) {
        MemberEntity member = memberReaderRepository.getMember(name);
        if (Objects.isNull(member)) {
            throw new MemberException(MemberErrorCode.E10003);
        }
        return MemberInfoDto.from(member);
    }

    public boolean existsMember(Long id) {
        return memberReaderRepository.existsMember(id);
    }
}
