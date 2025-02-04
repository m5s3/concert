package com.concert.interfaces.api.point;


import com.concert.application.Point.PointFacade;
import com.concert.application.Point.dto.PointDto;
import com.concert.application.auth.AuthFacade;
import com.concert.domain.security.TokenProvider;
import com.concert.domain.security.TokenService;
import com.concert.domain.security.dto.NewTokenDto;
import com.concert.interfaces.api.RestDocsTest;
import com.concert.interfaces.api.point.dto.UpdatePointRequest;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.restdocs.payload.JsonFieldType;

import java.math.BigDecimal;

import static com.concert.interfaces.api.RestDocsUtils.requestPreprocessor;
import static com.concert.interfaces.api.RestDocsUtils.responsePreprocessor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

class PointControllerTest extends RestDocsTest {

    private PointController controller;
    private PointFacade pointFacade;

    @BeforeEach
    void setUp() {
        pointFacade = mock(PointFacade.class);
        controller = new PointController(pointFacade);
        mockMvc = mockController(controller);
    }

    @Test
    void charge() {
        // Given
        Long pointId = 1L;
        Long memberId = 1L;
        long amount = 1000L;

        // When
        when(pointFacade.charge(any()))
                .thenReturn(PointDto.builder().id(pointId).memberId(memberId).amount(BigDecimal.valueOf(amount)).build());

        // Then
        given().contentType(ContentType.JSON)
                .body(UpdatePointRequest.builder().memberId(memberId).amount(amount).build())
                .post("/api/points/charge")
                .then()
                .status(HttpStatus.OK)
                .apply(document("charge", requestPreprocessor(), responsePreprocessor(),
                        requestFields(
                                fieldWithPath("member_id").type(JsonFieldType.NUMBER)
                                        .description("멤버 ID"),
                                fieldWithPath("amount").type(JsonFieldType.NUMBER)
                                        .description("충전 금액")
                        ),
                        responseFields(
                                fieldWithPath("result").type(JsonFieldType.STRING)
                                        .description("API 응답 성공 여부"),
                                fieldWithPath("data.id").type(JsonFieldType.NUMBER)
                                                .description("포인트 ID"),
                                fieldWithPath("data.memberId").type(JsonFieldType.NUMBER)
                                                .description("포인트 멤버 ID"),
                                fieldWithPath("data.amount").type(JsonFieldType.NUMBER)
                                                .description("충전 금액"),
                                fieldWithPath("error").type(JsonFieldType.NULL).ignored()
                        )));
    }

    @Test
    void use() {
        // Given
        Long pointId = 1L;
        Long memberId = 1L;
        long amount = 1000L;

        // When
        when(pointFacade.use(any()))
                .thenReturn(PointDto.builder().id(pointId).memberId(memberId).amount(BigDecimal.valueOf(amount)).build());

        // Then
        given().contentType(ContentType.JSON)
                .body(UpdatePointRequest.builder().memberId(memberId).amount(amount).build())
                .post("/api/points/use")
                .then()
                .status(HttpStatus.OK)
                .apply(document("use", requestPreprocessor(), responsePreprocessor(),
                        requestFields(
                                fieldWithPath("member_id").type(JsonFieldType.NUMBER)
                                        .description("멤버 ID"),
                                fieldWithPath("amount").type(JsonFieldType.NUMBER)
                                        .description("사용 금액")
                        ),
                        responseFields(
                                fieldWithPath("result").type(JsonFieldType.STRING)
                                        .description("API 응답 성공 여부"),
                                fieldWithPath("data.id").type(JsonFieldType.NUMBER)
                                        .description("포인트 ID"),
                                fieldWithPath("data.memberId").type(JsonFieldType.NUMBER)
                                        .description("포인트 멤버 ID"),
                                fieldWithPath("data.amount").type(JsonFieldType.NUMBER)
                                        .description("사용 금액"),
                                fieldWithPath("error").type(JsonFieldType.NULL).ignored()
                        )));
    }
}