package org.zerock.springex2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zerock.springex2.vo.TodoVO;
import org.zerock.springex2.dto.TodoDTO;
import org.zerock.springex2.mapper.TodoMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// 비즈니스 로직의 코드를 작서하는 Service 클래스
// DTO를 VO로 변환하거나 VO를 DTO로 변환하는 코드
// 계산이나 데이터를 변경 및 추가하는 등의 처리를 작성하는 클래스
@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    // 의존서 주입의 종류 : 생성자 주입 방식 (최근에 권장되는 방식)
    // @RequiredArgsConstructor, final 두개를 함께 사용하면
    // @Autowired를 사용하지 않고 의존성 주입이 가능
    private final TodoMapper todoMapper;

    @Override
    public void register(TodoDTO todoDTO) {
        //등록의 경우 화면에서 가지고 온 데이터인 DTO를 VO로 변경하여
        TodoVO todoVO = TodoVO.builder()
                .title(todoDTO.getTitle())
                .dueDate(todoDTO.getDueDate())
                .writer(todoDTO.getWriter())
                .build();
        // Mapper를 이용해 insert문을 실행
        todoMapper.insertTodo(todoVO);
    }

    @Override
    public List<TodoDTO> getAll() {
        // DB에서 전체 데이터를 받아와 voList에 저장
        List<TodoVO> voList = todoMapper.selectAll();
        // vo로 되어 있는 리스트를 dto 리스트로 변경
        List<TodoDTO> dtoList = new ArrayList<>();
        for (TodoVO todoVO : voList) {
            TodoDTO dto = new TodoDTO();
            dto.setTno(todoVO.getTno());
            dto.setTitle(todoVO.getTitle());
            dto.setDueDate(todoVO.getDueDate());
            dto.setFinished(todoVO.isFinished());
            dto.setWriter(todoVO.getWriter());
            dtoList.add(dto);
        }
        // stream과 Builder를 사용한 vo를 dto로 변경하는 방식
        List<TodoDTO> dtoList2 = todoMapper.selectAll().stream()
                .map(vo-> TodoDTO.builder()
                        .tno(vo.getTno())
                        .title(vo.getTitle())
                        .dueDate(vo.getDueDate())
                        .finished(vo.isFinished())
                        .writer(vo.getWriter())
                        .build()
                ).collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public TodoDTO getOne(Long tno) {
        // DB에서 tno와 일치하는 데이터를 저장
        TodoVO todoVO = todoMapper.selectOne(tno);
        // VO를 DTO로 변경
        TodoDTO dto = TodoDTO.builder()
                .tno(todoVO.getTno())
                .title(todoVO.getTitle())
                .dueDate(todoVO.getDueDate())
                .finished(todoVO.isFinished())
                .writer(todoVO.getWriter())
                .build();
        // 완성된 DTO를 Controller로 반환
        return dto;
    }
}
