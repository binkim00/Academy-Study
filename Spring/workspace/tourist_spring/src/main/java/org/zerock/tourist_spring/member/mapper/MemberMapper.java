package org.zerock.tourist_spring.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.tourist_spring.member.vo.MemberVO;

@Mapper
public interface MemberMapper {
    int insertMember(MemberVO member);
}
