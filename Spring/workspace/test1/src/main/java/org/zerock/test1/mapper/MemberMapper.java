package org.zerock.test1.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.zerock.test1.vo.MemberVO;

@Mapper
public interface MemberMapper {
    void insert(MemberVO vo);

    MemberVO login(@Param("id") String id, @Param("pw") String pw);

    void delete(@Param("id") String id);

}
