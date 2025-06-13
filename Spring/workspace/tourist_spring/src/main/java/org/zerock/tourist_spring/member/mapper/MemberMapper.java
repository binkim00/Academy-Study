package org.zerock.tourist_spring.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.zerock.tourist_spring.member.dto.MemberDTO;
import org.zerock.tourist_spring.member.vo.MemberVO;

@Mapper
public interface MemberMapper {
    int insertMember(MemberVO member);

    MemberDTO login(@Param("id") String id, @Param("password") String password);
}


