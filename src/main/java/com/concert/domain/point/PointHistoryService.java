package com.concert.domain.point;

import com.concert.domain.point.dto.NewPointHistoryInfoDto;
import com.concert.domain.point.dto.PointHistoryInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PointHistoryService {

    private final PointHistoryReaderRepository pointHistoryReaderRepository;
    private final PointHistoryStoreRepository pointHistoryStoreRepository;

    public PointHistoryInfoDto create(NewPointHistoryInfoDto dto) {
        return pointHistoryStoreRepository.save(dto.toEntity());
    }
}
