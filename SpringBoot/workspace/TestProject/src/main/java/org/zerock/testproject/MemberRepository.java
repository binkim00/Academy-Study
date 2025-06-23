package org.zerock.testproject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
// JpaRepository<테이블클래스(Entity), 기본키 @ID로 설정한 데이터의 자료형>
interface MemberRepository extends JpaRepository<Member, Long> {
    // SELECT * FROM member WHERE name=매개변수name
    // 쿼리 메서드 : 메서드의 이름으로 쿼리를 작성하는 방식
    Optional<Member> findByName(String name);
    // @Query : SQL을 직접 사용하고 싶은 경우 사용하는 어노테이션
    // 테이블이름은 반드시 entity이름으로 설정
    // 컬럼이름에 테이블이름을 붙여 .으로 연결하여 설정
    // ?숫자를 이용하여 매개변수를 SQL에 적용
    @Query("SELECT m FROM Member m WHERE m.name = ?1")
    Optional<Member> findByNameQuery(String name);
}
