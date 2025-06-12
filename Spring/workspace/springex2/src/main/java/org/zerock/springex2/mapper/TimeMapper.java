package org.zerock.springex2.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
// mybatis에서 사용하는 SQL을 설정하는 인터페이스
public interface TimeMapper {
    @Select("SELECT now()")
    String getTime();
//    @Insert()
//    @Delete()
//    @Update()
}
