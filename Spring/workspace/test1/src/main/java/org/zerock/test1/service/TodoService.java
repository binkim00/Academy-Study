package org.zerock.test1.service;

import org.zerock.test1.dto.PageRequestDTO;
import org.zerock.test1.dto.PageResponseDTO;
import org.zerock.test1.dto.TodoDTO;

public interface TodoService {
    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);
    void register(TodoDTO dto);
    TodoDTO getOne(Long tno);
    String modify(TodoDTO dto);
    void remove(Long tno, String writer);
}
