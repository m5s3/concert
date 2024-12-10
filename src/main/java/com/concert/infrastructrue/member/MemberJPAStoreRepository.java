package com.concert.infrastructrue.member;

import com.concert.domain.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJPAStoreRepository extends JpaRepository<MemberEntity, Long> {
}
