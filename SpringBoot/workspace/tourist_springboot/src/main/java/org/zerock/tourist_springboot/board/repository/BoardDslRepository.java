package org.zerock.tourist_springboot.board.repository;

import org.zerock.tourist_springboot.board.dto.BoardDTO;
import org.zerock.tourist_springboot.common.dto.PageRequestDTO;
import org.zerock.tourist_springboot.common.dto.PageResponseDTO;

public interface BoardDslRepository {
    PageResponseDTO<BoardDTO> searchDsl(PageRequestDTO pageRequestDTO);
}
