package com.concert.interfaces.api.concert;

import com.concert.application.concert.ConcertFacade;
import com.concert.application.concert.dto.ConcertDto;
import com.concert.application.concert.dto.CreateConcertCommend;
import com.concert.interfaces.api.RestDocsTest;
import com.concert.interfaces.api.concert.dto.CreateConcertRequest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

import static com.concert.interfaces.api.RestDocsUtils.requestPreprocessor;
import static com.concert.interfaces.api.RestDocsUtils.responsePreprocessor;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.post;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ConcertControllerTest extends RestDocsTest {

    private ConcertController controller;
    private ConcertFacade concertFacade;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        concertFacade = mock(ConcertFacade.class);
        controller = new ConcertController(concertFacade);
        mockMvc = mockController(controller);
        objectMapper = new ObjectMapper();
    }


    @Test
    void createConcert() throws Exception {
        String title = "테스트 콘서트";
        String description = "테스트 콘서트 설명";
        long scheduleId = 1L;
        LocalDateTime startDate = LocalDateTime.now().plusDays(1);
        LocalDateTime endDate = LocalDateTime.now().plusDays(2);
        LocalDateTime reservationStartDate = LocalDateTime.now();
        int countOfSeat = 50;
        int countOfRemainSeat = 50;
        when(concertFacade.createConcert(any())).thenReturn(
                ConcertDto.builder()
                        .id(1L)
                        .title(title)
                        .description(description)
                        .scheduleId(scheduleId)
                        .startDate(startDate)
                        .endDate(endDate)
                        .reservationStartDate(reservationStartDate)
                        .countOfSeat(countOfSeat)
                        .countOfRemainSeat(countOfRemainSeat)
                        .build()
        );

        given().contentType(ContentType.JSON)
                        .body(new CreateConcertRequest(title, description, startDate, endDate,
                                reservationStartDate, countOfSeat)
                        )
                        .post("/api/concerts")
                        .then()
                        .status(HttpStatus.OK)
                                .apply(document("createConcert", requestPreprocessor(), responsePreprocessor(),
                                        requestFields(
                                                fieldWithPath("title").type(JsonFieldType.STRING)
                                                        .description("콘서트 제목"),
                                                fieldWithPath("description").type(JsonFieldType.STRING)
                                                        .description("콘서트 설명"),
                                                fieldWithPath("startDate").type(JsonFieldType.ARRAY)
                                                        .description("콘서트 시작일시"),
                                                fieldWithPath("endDate").type(JsonFieldType.ARRAY)
                                                        .description("콘서트 종료일시"),
                                                fieldWithPath("reservationStartDate").type(JsonFieldType.ARRAY)
                                                        .description("예약 시작일시"),
                                                fieldWithPath("countOfSeat").type(JsonFieldType.NUMBER)
                                                        .description("총 좌석 수")
                                        ),
                                        responseFields(
                                                fieldWithPath("success").type(JsonFieldType.BOOLEAN)
                                                        .description("성공 여부"),
                                                fieldWithPath("data.id").type(JsonFieldType.NUMBER)
                                                        .description("콘서트 ID"),
                                                fieldWithPath("data.title").type(JsonFieldType.STRING)
                                                        .description("콘서트 제목"),
                                                fieldWithPath("data.description").type(JsonFieldType.STRING)
                                                        .description("콘서트 설명"),
                                                fieldWithPath("data.scheduleId").type(JsonFieldType.NUMBER)
                                                        .description("스케줄 ID"),
                                                fieldWithPath("data.startDate").type(JsonFieldType.STRING)
                                                        .description("콘서트 시작일시"),
                                                fieldWithPath("data.endDate").type(JsonFieldType.STRING)
                                                        .description("콘서트 종료일시"),
                                                fieldWithPath("data.reservationStartDate").type(JsonFieldType.STRING)
                                                        .description("예약 시작일시"),
                                                fieldWithPath("data.countOfSeat").type(JsonFieldType.NUMBER)
                                                        .description("총 좌석 수"),
                                                fieldWithPath("data.countOfRemainSeat").type(JsonFieldType.NUMBER)
                                                        .description("잔여 좌석 수"),
                                                fieldWithPath("message").type(JsonFieldType.NULL)
                                                        .description("에러 정보")
                                        )));

    }
}