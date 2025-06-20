package org.zerock.testproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    @Autowired
    MemberRepository memberRepository;

    public List<Member> getAllMembers(){
        // 1번 데이터 검색, SELECT
        Member member = memberRepository.findById(1L).orElseThrow();
        System.out.println(member);  // PK로 검색
        // 새로운 데이터 저장 ,INSERT문
//        memberRepository.save(new Member(4L, "test 4"));
        // 1번 데이터 삭제 , DELETE문
        memberRepository.delete(member);
        return memberRepository.findAll();
    }

}
