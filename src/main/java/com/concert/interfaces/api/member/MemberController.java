package com.concert.interfaces.api.member;

import com.concert.application.member.MemberFacade;
import com.concert.application.member.dto.MemberDto;
import com.concert.interfaces.api.common.ApiResponse;
import com.concert.interfaces.api.member.dto.RequestCreateMember;
import com.concert.interfaces.api.member.dto.MemberRequest;
import com.concert.interfaces.api.member.dto.ResponseMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberFacade memberFacade;

    @PostMapping
    public ResponseEntity<ApiResponse<ResponseMember>> createMember(@RequestBody RequestCreateMember request) {
        MemberDto member = memberFacade.createMember(request.toCommand());
        return ResponseEntity.ok(ApiResponse.success(ResponseMember.from(member)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ResponseMember>> getMembers(@PathVariable("id") Long id) {
        MemberDto member = memberFacade.getMember(MemberRequest.builder().id(id).build().toQuery());
        return ResponseEntity.ok(ApiResponse.success(ResponseMember.from(member)));
    }
}
