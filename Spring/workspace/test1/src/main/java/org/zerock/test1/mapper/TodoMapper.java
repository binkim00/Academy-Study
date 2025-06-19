package org.zerock.test1.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.zerock.test1.dto.PageRequestDTO;
import org.zerock.test1.vo.TodoVO;

import java.util.List;

@Mapper
public interface TodoMapper {
    List<TodoVO> selectList(PageRequestDTO pageRequestDTO);
    int getCount(PageRequestDTO pageRequestDTO);
    void insert(TodoVO vo);
    TodoVO selectOne(Long tno);
    void update(TodoVO vo);
    void delete(@Param("tno") Long tno, @Param("writer") String writer);
}
