package org.zerock.testproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Sql("/insert-members.sql")
    @Test
    void getAllMembers(){
        List<Member> members = memberRepository.findAll();
        assertThat(members.size()).isEqualTo(3);
    }
    @Sql("/insert-members.sql")
    @Test
    void getMemberById(){
        // findById(PK값) : PrimaryKey를 이용한 SELECT
        // SELECT * FROM member WHERE id=2
        Member member = memberRepository.findById(2L).get();
        // 콘솔창에 데이터 출력하기
        System.out.println(member);
        assertThat(member.getName()).isEqualTo("B");
    }
    @Sql("/insert-members.sql")
    @Test
    void getMemberByName(){
        // SELECT * FROM member WHERE name='C'
        Member member = memberRepository.findByName("C").get();
        // 콘솔창에 데이터 출력하기
        System.out.println(member);
        assertThat(member.getId()).isEqualTo(3);
    }
    @Sql("/insert-members.sql")
    @Test
    void getMemberByNameQuery(){
        // SELECT * FROM member WHERE name='C'
        Member member = memberRepository.findByNameQuery("C").get();
        // 콘솔창에 데이터 출력하기
        System.out.println(member);
        assertThat(member.getId()).isEqualTo(3);
    }
    @Test
    void saveMember(){
        // ID가 설정되지 않은 Member객체를 생성
        Member member = Member.builder()
                .name("A")
                .build();
        // 저장 전 데이터 확인 = ID가 null인 것을 확인
        System.out.println(member);
        // save를 실행하면 자동 생성된 ID가 member객체에 저장됨
        memberRepository.save(member);
        // 저장 후 데이터 확인 = 저장된 ID값을 확인
        System.out.println(member);
        assertThat(memberRepository.findById(1L).get().getName())
                .isEqualTo(member.getName());
    }
    @Test
    void saveMembers(){
        // List에 여러개의 entity를 담는 코드
        List<Member> members = new ArrayList<>();
        members.add(Member.builder().name("B").build());
        members.add(Member.builder().name("C").build());
        System.out.println(members);
        // saveAll(List객체) : List안에 있는 모든 Member를 저장
        memberRepository.saveAll(members);
        System.out.println(members);
        assertThat(memberRepository.findAll().size()).isEqualTo(2);
    }
    @Sql("/insert-members.sql")
    @Test
    void deleteMemberById(){
        // PrimaryKey를 검색 조건으로 사용하는 DELETE문 실행
        memberRepository.deleteById(2L);
        // 2L으로 검색하여 데이터가 없으면 테스트 통과
        assertThat(memberRepository.findById(2L).isEmpty()).isTrue();
        // 모든 데이터를 출력하여 확인
        System.out.println(memberRepository.findAll());

        // 1L 데이터를 검색하여 저장
        Member member = memberRepository.findById(1L).get();
        // 저장된 Member 객체를 이용하여 delete실행
        memberRepository.delete(member);
        assertThat(memberRepository.findById(1L).isEmpty()).isTrue();
        System.out.println(memberRepository.findAll());
    }
    @Sql("/insert-members.sql")
    @Test
    void deleteAll(){
        // 모든 데이터 삭제
        memberRepository.deleteAll();
        System.out.println(memberRepository.findAll());
        assertThat(memberRepository.findAll().size()).isZero();
    }
    @Sql("/insert-members.sql")
    @Test
    void update(){
        Member member = memberRepository.findById(2L).get();
        System.out.println(member);
        // update 경우 @Transactional 어노테이션이 있으면
        // save하지 않아도 자동으로 변경됨
        // @DateJpaTest에 @Transactional 포함되어 있음
        member.changeName("BC");
        System.out.println(member);
        assertThat(memberRepository.findById(2L).get().getName()).isEqualTo("BC");
    }
}