package org.zerock.springex2.mapper;

import org.zerock.springex2.vo.TodoVO;

import java.util.List;

public interface TodoMapper {
    String getTime();
    // VO : 데이터베이스에서 사용하는 테이블을 클래스로 작성한 객체
    // DTO : 화면에서 쓰이는 데이터를 클래스화하여 작성한 객체
    // VO는 테이블당 하나, DTO 필요한 만큼 작성해도 무방
    void insertTodo(TodoVO todoVO);
    List<TodoVO> selectAll();
    TodoVO selectOne(Long tno);
    void deleteTodo(Long tno);
    void updateTodo(TodoVO todoVO);
}
