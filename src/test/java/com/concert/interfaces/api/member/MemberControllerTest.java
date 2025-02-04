package com.concert.interfaces.api.member;

import com.concert.application.member.MemberFacade;
import com.concert.application.member.dto.MemberDto;
import com.concert.interfaces.api.RestDocsTest;
import com.concert.interfaces.api.member.dto.CreateMemberRequest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.concert.interfaces.api.RestDocsUtils.requestPreprocessor;
import static com.concert.interfaces.api.RestDocsUtils.responsePreprocessor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

class MemberControllerTest extends RestDocsTest {

    private MemberController controller;
    private MemberFacade memberFacade;

    @BeforeEach
    public void setUp() {
        memberFacade = mock(MemberFacade.class);
        controller = new MemberController(memberFacade);
        mockMvc = mockController(controller);
    }

    @Test
    void createMember() {
        // Given
        Long memberId = 1L;

        // When
        when(memberFacade.createMember(any())).thenReturn(new MemberDto(memberId, "test"));
        // Then
        given().contentType(ContentType.JSON)
                .body(new CreateMemberRequest("test"))
                .post("/api/members")
                .then()
                .status(HttpStatus.OK)
                .apply(document("createMember", requestPreprocessor(), responsePreprocessor(),
                        requestFields(
                                fieldWithPath("name").type(JsonFieldType.STRING)
                                        .description("유저 이름")
                        ),
                        responseFields(
                                fieldWithPath("result").type(JsonFieldType.STRING)
                                        .description("API 응답 성공 여부"),
                                fieldWithPath("data.id").type(JsonFieldType.NUMBER)
                                        .description("멤버 ID"),
                                fieldWithPath("data.name").type(JsonFieldType.STRING)
                                        .description("멤버 이름"),
                                fieldWithPath("error").type(JsonFieldType.NULL).ignored()
                        )));
    }

    @Test
    void getMember() {
        // Given
        Long memberId = 1L;

        // When
        when(memberFacade.getMember(any())).thenReturn(new MemberDto(memberId, "test"));

        // Then
        given()
            .get("/api/members/{memberId}", memberId)
            .then()
            .status(HttpStatus.OK)
            .apply(document("getMember", requestPreprocessor(), responsePreprocessor(),
                pathParameters(parameterWithName("memberId").description("멤버 ID")),
                responseFields(
                    fieldWithPath("result").type(JsonFieldType.STRING)
                            .description("API 응답 성공 여부"),
                    fieldWithPath("data.id").type(JsonFieldType.NUMBER)
                            .description("멤버 ID"),
                    fieldWithPath("data.name").type(JsonFieldType.STRING)
                            .description("멤버 이름"),
                    fieldWithPath("error").type(JsonFieldType.NULL).ignored()
                )));
    }
}