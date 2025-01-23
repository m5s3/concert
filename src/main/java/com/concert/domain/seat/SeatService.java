package com.concert.domain.seat;

import com.concert.domain.seat.dto.NewSeatDto;
import com.concert.domain.seat.dto.SeatSearchCriteria;
import com.concert.domain.seat.dto.SeatInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SeatService {

    private final SeatValidator seatValidator;
    private final SeatReaderRepository seatReaderRepository;
    private final SeatStoreRepository seatStoreRepository;

    public void reserve(Long seatId) {
        SeatEntity seat = seatReaderRepository.getSeat(seatId);
        seatValidator.validateSeatAvailable(seat);
        seat.reserve();
    }

    public SeatInfoDto create(NewSeatDto seatDto) {
        SeatEntity newSeat = seatStoreRepository.save(seatDto.toEntity());
        return SeatInfoDto.from(newSeat);
    }

    @Transactional(readOnly = true)
    public SeatInfoDto findSeat(Long seatId) {
        SeatEntity seat = seatReaderRepository.getSeat(seatId);
        return SeatInfoDto.from(seat);
    }

    @Transactional(readOnly = true)
    public List<SeatInfoDto> getSeats(SeatSearchCriteria searchCriteria) {
        return seatReaderRepository.getSeats(searchCriteria)
                .stream()
                .map(SeatInfoDto::from)
                .toList();
    }
} 