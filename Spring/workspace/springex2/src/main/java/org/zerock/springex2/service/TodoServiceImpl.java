package org.zerock.springex2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zerock.springex2.dto.PageRequestDTO;
import org.zerock.springex2.dto.PageResponseDTO;
import org.zerock.springex2.vo.TodoVO;
import org.zerock.springex2.dto.TodoDTO;
import org.zerock.springex2.mapper.TodoMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// 비즈니스 로직의 코드를 작성하는 Service 클래스
// DTO를 VO로 변환하거나 VO를 DTO로 변환하는 코드와
// 계산이나 데이터를 변경 및 추가하는 등의 처리를 작성하는 클래스
@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{
    // 의존성 주입의 종류 : 생성자 주입 방식 (최근에 권장되는 방식)
    // @RequiredArgsConstructor, final두개를 함께 사용하면
    // @Autowired를 사용하지 않고 의존성 주입이 가능
    private final TodoMapper todoMapper;

    @Override
    public void register(TodoDTO todoDTO) {
        // 등록의 경우 화면에서 가지고온 데이터인 DTO를 VO로 변경하여
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
        // VO로 되어있는 리스트를 dto 리스트로 변경
        List<TodoDTO> dtoList = new ArrayList<>();
        for(TodoVO todoVO : voList){
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
                .map(vo -> TodoDTO.builder()
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
    public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {
        List<TodoVO> voList = todoMapper.selectSearch(pageRequestDTO);
        List<TodoDTO> dtoList = voList.stream()
                .map(vo -> TodoDTO.builder()
                        .tno(vo.getTno())
                        .title(vo.getTitle())
                        .dueDate(vo.getDueDate())
                        .finished(vo.isFinished())
                        .writer(vo.getWriter())
                        .build())
                .collect(Collectors.toList());
        int total = todoMapper.getCount(pageRequestDTO);
        PageResponseDTO<TodoDTO> pageResponseDTO =
                PageResponseDTO.<TodoDTO>withAll()
                        .pageRequestDTO(pageRequestDTO)
                        .dtoList(dtoList)
                        .total(total)
                        .build();
        return pageResponseDTO;
    }

    @Override
    public TodoDTO getOne(Long tno) {
        // DB에서 tno와 일치하는 데이터를 저장
        TodoVO todoVO = todoMapper.selectOne(tno);
        //VO를 DTO로 변경
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

    @Override
    public void removeTodo(Long tno) {
        todoMapper.deleteTodo(tno);
    }

    @Override
    public String editTodo(TodoDTO todoDTO){
        // 데이터가 존재하는지 확인
        TodoVO vo = todoMapper.selectOne(todoDTO.getTno());
        if(vo != null){
            // 데이터가 있으면 변경 가능한 데이터를 변경
            vo.changeTodo(todoDTO.getTitle(),
                    todoDTO.getDueDate(),
                    todoDTO.isFinished());
            // update문 실행
            todoMapper.updateTodo(vo);
            return "수정했습니다.";
        }else{
            return "수정 처리중 예외가 발생했습니다.";
        }
    }
}
