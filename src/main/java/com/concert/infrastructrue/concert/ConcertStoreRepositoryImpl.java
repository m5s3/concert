package com.concert.infrastructrue.concert;

import com.concert.domain.concert.ConcertEntity;
import com.concert.domain.concert.ConcertStoreRepository;
import com.concert.domain.concert.dto.NewConcertDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ConcertStoreRepositoryImpl implements ConcertStoreRepository {

    private final ConcertJPAStoreRepository repository;

    @Override
    public ConcertEntity save(NewConcertDto concertDto) {
        return repository.save(concertDto.toEntity());
    }
}
