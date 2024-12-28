package com.concert.domain.concert;

import com.concert.domain.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "concert")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ConcertEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "concert_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    @Builder
    public ConcertEntity(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public void delete(ConcertScheduleEntity concertScheduleEntity) {
        this.isDeleted = true;
        concertScheduleEntity.delete();
    }
}
