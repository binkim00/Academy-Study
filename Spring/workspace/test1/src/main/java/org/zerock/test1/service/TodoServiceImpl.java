package org.zerock.test1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zerock.test1.dto.PageRequestDTO;
import org.zerock.test1.dto.PageResponseDTO;
import org.zerock.test1.dto.TodoDTO;
import org.zerock.test1.mapper.TodoMapper;
import org.zerock.test1.vo.TodoVO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoServiceImpl implements TodoService {

    private final TodoMapper todoMapper;

    @Override
    public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {
        int total = todoMapper.getCount(pageRequestDTO);
        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);

        List<TodoDTO> dtoList = voList.stream()
                .map(vo -> TodoDTO.builder()
                        .tno(vo.getTno())
                        .title(vo.getTitle())
                        .writer(vo.getWriter())
                        .dueDate(vo.getDueDate())
                        .finished(vo.isFinished())
                        .build())
                .collect(Collectors.toList());

        return PageResponseDTO.<TodoDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    @Override
    public void register(TodoDTO dto) {
        TodoVO vo = TodoVO.builder()
                .title(dto.getTitle())
                .dueDate(dto.getDueDate())
                .finished(Boolean.TRUE.equals(dto.getFinished())) // null 방지
                .writer(dto.getWriter())
                .build();

        todoMapper.insert(vo);
    }

    @Override
    public TodoDTO getOne(Long tno) {
        TodoVO vo = todoMapper.selectOne(tno);

        return TodoDTO.builder()
                .tno(vo.getTno())
                .title(vo.getTitle())
                .writer(vo.getWriter())
                .dueDate(vo.getDueDate())
                .finished(vo.isFinished())
                .build();
    }

    @Override
    public String modify(TodoDTO dto) {
        TodoVO vo = todoMapper.selectOne(dto.getTno());

        vo.setWriter(dto.getWriter());
        vo.changeTodo(dto.getTitle(), dto.getDueDate(), Boolean.TRUE.equals(dto.getFinished()));

        todoMapper.update(vo);
        return "수정되었습니다.";
    }


    @Override
    public void remove(Long tno, String writer) {
        log.info(">>> 삭제 시도: tno={}, writer={}", tno, writer);
        todoMapper.delete(tno, writer);
    }

}
