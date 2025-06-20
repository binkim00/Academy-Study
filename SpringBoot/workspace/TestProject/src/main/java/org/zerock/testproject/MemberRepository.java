package org.zerock.testproject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// JpaRepository<테이블클래스(Entity), 기본키 @ID로 설정한 데이터의 자료형>
public interface MemberRepository extends JpaRepository<Member, Long> {
}
