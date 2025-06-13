package org.zerock.tourist_spring.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.tourist_spring.board.vo.BoardVO;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {

    List<BoardVO> selectPaged(Map<String, Object> paramMap);
    int countAll(String searchWord);
    BoardVO selectOne(int num);
    void insert(BoardVO board);
    void update(BoardVO board);
    void delete(int num);
    void updateVisitCount(int num);
}
