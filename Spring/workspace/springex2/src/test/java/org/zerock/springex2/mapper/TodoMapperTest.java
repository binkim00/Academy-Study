package org.zerock.springex2.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.springex2.vo.TodoVO;

import java.time.LocalDate;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoMapperTest {
    @Autowired(required=false)
    private TodoMapper todoMapper;

    @Test
    public void testGetTime() {
        log.info(todoMapper.getTime());
    }

    @Test
    public void testInsertTodo() {
//        TodoVO todoVO = new TodoVO();
        TodoVO todoVO = TodoVO.builder()
                .title("스프링 테스트")
                .dueDate(LocalDate.of(2025,06,12))
                .writer("스프링 작성자")
                .build();
        todoMapper.insertTodo(todoVO);
    }
}
