package org.zerock.tourist_spring.board.service;

import org.zerock.tourist_spring.board.dto.BoardDTO;
import org.zerock.tourist_spring.common.dto.PageRequestDTO;
import org.zerock.tourist_spring.common.dto.PageResponseDTO;

import java.util.List;
import java.util.Map;

public interface BoardService {
    List<BoardDTO> getPagedList(Map<String, Object> paramMap);
    BoardDTO getView(int num);
    void write(BoardDTO dto);
    void edit(BoardDTO dto);
    void remove(int num);
    void increaseVisitCount(int num);

    PageResponseDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO);
}
