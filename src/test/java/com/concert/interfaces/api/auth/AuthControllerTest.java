package com.concert.interfaces.api.auth;

import com.concert.application.Point.dto.PointDto;
import com.concert.application.auth.AuthFacade;
import com.concert.interfaces.api.RestDocsTest;
import com.concert.interfaces.api.auth.dto.LoginRequest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.restdocs.payload.JsonFieldType;

import java.math.BigDecimal;

import static com.concert.interfaces.api.RestDocsUtils.requestPreprocessor;
import static com.concert.interfaces.api.RestDocsUtils.responsePreprocessor;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

class AuthControllerTest extends RestDocsTest {

    private AuthController controller;
    private AuthFacade authFacade;

    @BeforeEach
    void setUp() {
        authFacade = mock(AuthFacade.class);
        controller = new AuthController(authFacade);
        mockMvc = mockController(controller);
    }

    @Test
    void login() {
        // Given

        // When
        when(authFacade.login(any()))
                .thenReturn("bearer xxxxxx");

        // Then
        given().contentType(ContentType.JSON)
                .body(new LoginRequest("test"))
                .post("/api/auth/login")
                .then()
                .status(HttpStatus.OK)
                .apply(document("login", requestPreprocessor(), responsePreprocessor(),
                        requestFields(
                                fieldWithPath("name").type(JsonFieldType.STRING)
                                        .description("유저 이름")
                        ),
                        responseFields(
                                fieldWithPath("success").type(JsonFieldType.BOOLEAN)
                                                .description("성공 여부"),
                                fieldWithPath("data.isSuccess").type(JsonFieldType.BOOLEAN)
                                                .description("로그인 성공 여부"),
                                fieldWithPath("message").type(JsonFieldType.NULL)
                                                .description("에러 정보")
                        )
                        ));

    }
}