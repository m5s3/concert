package com.concert.interfaces.api.member;

import com.concert.application.member.MemberFacade;
import com.concert.application.member.dto.MemberDto;
import com.concert.interfaces.api.member.dto.CreateMemberRequest;
import com.concert.interfaces.api.member.dto.MemberRequest;
import com.concert.interfaces.api.member.dto.MemberResponse;
import com.concert.support.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberFacade memberFacade;

    @PostMapping
    public ApiResponse<MemberResponse> createMember(@RequestBody CreateMemberRequest request) {
        MemberDto member = memberFacade.createMember(request.toCommand());
        return ApiResponse.success(MemberResponse.from(member));
    }

    @GetMapping("/{id}")
    public ApiResponse<MemberResponse> getMember(@PathVariable("id") Long id) {
        MemberDto member = memberFacade.getMember(MemberRequest.builder().id(id).build().toQuery());
        return ApiResponse.success(MemberResponse.from(member));
    }
}
