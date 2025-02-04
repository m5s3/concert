package com.concert.interfaces.api.concert;

import com.concert.application.concert.ConcertFacade;
import com.concert.application.concert.dto.ConcertDto;
import com.concert.application.concert.dto.CreateConcertCommend;
import com.concert.application.seat.SeatFacade;
import com.concert.application.seat.dto.SeatDto;
import com.concert.interfaces.api.RestDocsTest;
import com.concert.interfaces.api.common.dto.Pagination;
import com.concert.interfaces.api.concert.dto.ConcertSearchQuery;
import com.concert.interfaces.api.concert.dto.CreateConcertRequest;
import com.concert.interfaces.api.concert.dto.SearchConcertRequest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.concert.interfaces.api.RestDocsUtils.requestPreprocessor;
import static com.concert.interfaces.api.RestDocsUtils.responsePreprocessor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;

class ConcertControllerTest extends RestDocsTest {

    private ConcertController controller;
    private ConcertFacade concertFacade;
    private SeatFacade seatFacade;

    // 테스트용 Concert
    private static final long concertId = 1L;
    private static final String title = "%d 테스트 콘서트";
    private static final String description = "%d 테스트 콘서트 설명";
    private static final long scheduleId = 1L;
    private static final LocalDateTime startDate = LocalDateTime.now().plusDays(1);
    private static final LocalDateTime endDate = LocalDateTime.now().plusDays(2);
    private static final LocalDateTime reservationStartDate = LocalDateTime.now();
    private static final int countOfSeat = 50;
    private static final int countOfRemainSeat = 50;

    @BeforeEach
    public void setUp() {
        concertFacade = mock(ConcertFacade.class);
        seatFacade = mock(SeatFacade.class);
        controller = new ConcertController(concertFacade, seatFacade);
        mockMvc = mockController(controller);
    }

    @Test
    void createConcert() {
        // Given
        when(concertFacade.createConcert(any())).thenReturn(createConcertDto(concertId));

        // When & Then
        given().contentType(ContentType.JSON)
            .body(new CreateConcertRequest(title.formatted(concertId), description.formatted(concertId), startDate, endDate,
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
                        fieldWithPath("result").type(JsonFieldType.STRING)
                                .description("API 응답 성공 여부"),
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
                        fieldWithPath("error").type(JsonFieldType.NULL).ignored()
                    )));
    }

    @Test
    void getConcert() {
        // Given
        when(concertFacade.getConcert(any())).thenReturn(createConcertDto(concertId));

        // When & Then
        given()
            .get("/api/concerts/{concertId}", concertId)
            .then()
            .status(HttpStatus.OK)
            .apply(document("getConcert", requestPreprocessor(), responsePreprocessor(),
                pathParameters(parameterWithName("concertId").description("콘서트 아이디")),
                responseFields(
                    fieldWithPath("result").type(JsonFieldType.STRING)
                            .description("API 응답 성공 여부"),
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
                    fieldWithPath("error").type(JsonFieldType.NULL).ignored()
                )));
    }

    @Test
    void searchConcerts() {
        // Given
        when(concertFacade.searchConcerts(any()))
                .thenReturn(List.of(createConcertDto(1L), createConcertDto(2L), createConcertDto(3L)));

        // When & Then
        Pagination pagination = new Pagination(1, 50);
        ConcertSearchQuery concertSearchQuery = new ConcertSearchQuery(1L, "테스트",
                "테스트 콘서트 설명", 1L, LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2), LocalDateTime.now());

        given().contentType(ContentType.JSON)
            .body(new SearchConcertRequest(pagination, concertSearchQuery))
            .post("/api/concerts/search")
            .then()
            .status(HttpStatus.OK)
            .apply(document("searchConcerts", requestPreprocessor(), responsePreprocessor(),
                requestFields(
                    fieldWithPath("pagination.page").type(JsonFieldType.NUMBER)
                            .description("페이지 번호"),
                    fieldWithPath("pagination.size").type(JsonFieldType.NUMBER)
                            .description("콘서트 요청 수"),
                    fieldWithPath("query.concertId").type(JsonFieldType.NUMBER)
                            .description("콘서트 아이디 (nullable: true)"),
                    fieldWithPath("query.title").type(JsonFieldType.STRING)
                            .description("콘서트 제목 (nullable: true)"),
                    fieldWithPath("query.description").type(JsonFieldType.STRING)
                            .description("콘서트 설명 (nullable: true)"),
                    fieldWithPath("query.scheduleId").type(JsonFieldType.NUMBER)
                            .description("콘서트 스케줄 아이디 (nullable: true)"),
                    fieldWithPath("query.startDate").type(JsonFieldType.STRING)
                            .description("콘서트 시작 일시 (nullable: true)"),
                    fieldWithPath("query.endDate").type(JsonFieldType.STRING)
                            .description("콘서트 종료 일시 (nullable: true)"),
                    fieldWithPath("query.reservationStartDate").type(JsonFieldType.STRING)
                            .description("콘서트 예약 시작 일시 (nullable: true)")
                ),
                responseFields(
                    fieldWithPath("result").type(JsonFieldType.STRING)
                                .description("API 응답 성공 여부"),
                    fieldWithPath("data.concertResponses[].id").type(JsonFieldType.NUMBER)
                            .description("콘서트 ID"),
                    fieldWithPath("data.concertResponses[].title").type(JsonFieldType.STRING)
                            .description("콘서트 제목"),
                    fieldWithPath("data.concertResponses[].description").type(JsonFieldType.STRING)
                            .description("콘서트 설명"),
                    fieldWithPath("data.concertResponses[].scheduleId").type(JsonFieldType.NUMBER)
                            .description("스케줄 ID"),
                    fieldWithPath("data.concertResponses[].startDate").type(JsonFieldType.STRING)
                            .description("콘서트 시작일시"),
                    fieldWithPath("data.concertResponses[].endDate").type(JsonFieldType.STRING)
                            .description("콘서트 종료일시"),
                    fieldWithPath("data.concertResponses[].reservationStartDate").type(JsonFieldType.STRING)
                            .description("예약 시작일시"),
                    fieldWithPath("data.concertResponses[].countOfSeat").type(JsonFieldType.NUMBER)
                            .description("총 좌석 수"),
                    fieldWithPath("data.concertResponses[].countOfRemainSeat").type(JsonFieldType.NUMBER)
                            .description("잔여 좌석 수"),
                    fieldWithPath("data.totalCount").type(JsonFieldType.NUMBER)
                            .description("콘서트 수"),
                    fieldWithPath("error").type(JsonFieldType.NULL).ignored()
                )
            ));
    }

    @Test
    void getSeats() {
        // Given
        when(seatFacade.getSeats(any())).thenReturn(List.of(
                SeatDto.builder().id(1L).seatNumber("A1").scheduleId(scheduleId).isReserved(false).build(),
                SeatDto.builder().id(2L).seatNumber("A2").scheduleId(scheduleId).isReserved(false).build(),
                SeatDto.builder().id(3L).seatNumber("A3").scheduleId(scheduleId).isReserved(true).build()
        ));

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("page", "1");
        queryParams.put("size", "10");
        queryParams.put("concertScheduleId", String.valueOf(scheduleId));

        // When & Then
        given()
            .queryParams(queryParams)
            .get("/api/concerts/{concertId}/seats", concertId)
            .then()
            .status(HttpStatus.OK)
            .apply(document("getSeats", requestPreprocessor(), responsePreprocessor(),
                pathParameters(
                    parameterWithName("concertId").description("콘서트 ID")
                ),
                queryParameters(
                    parameterWithName("concertScheduleId").description("콘서트 스케줄 ID"),
                    parameterWithName("size").description("좌석 수"),
                    parameterWithName("page").description("페이지 번호")
                ),
                responseFields(
                    fieldWithPath("result").type(JsonFieldType.STRING)
                            .description("API 응답 성공 여부"),
                    fieldWithPath("data.seats[].id").type(JsonFieldType.NUMBER)
                            .description("좌석 ID"),
                    fieldWithPath("data.seats[].scheduleId").type(JsonFieldType.NUMBER)
                            .description("스케줄 ID"),
                    fieldWithPath("data.seats[].seatNumber").type(JsonFieldType.STRING)
                            .description("좌석 번호"),
                    fieldWithPath("data.seats[].isReserved").type(JsonFieldType.BOOLEAN)
                            .description("좌석 예약 상태"),
                    fieldWithPath("data.totalCount").type(JsonFieldType.NUMBER)
                            .description("좌석 개수"),
                    fieldWithPath("error").type(JsonFieldType.NULL).ignored()
                )
                ));
    }

    private static ConcertDto createConcertDto(Long id) {
        return ConcertDto.builder()
                .id(id)
                .title(title.formatted(id))
                .description(description.formatted(id))
                .scheduleId(scheduleId)
                .startDate(startDate)
                .endDate(endDate)
                .reservationStartDate(reservationStartDate)
                .countOfSeat(countOfSeat)
                .countOfRemainSeat(countOfRemainSeat)
                .build();
    }
}