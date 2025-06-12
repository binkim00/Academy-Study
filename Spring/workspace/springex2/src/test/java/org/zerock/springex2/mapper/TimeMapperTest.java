package org.zerock.springex2.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/root-context.xml")
public class TimeMapperTest {
    // 의존성 주입을 이용하여 timeMapper를 선언 및 초기화 실행
    // @Autowired 의존성 주입을 실행하는 어노테이션
    @Autowired(required = false)
    private TimeMapper timeMapper;

    // xml로 만든 TimeMapper2 테스트
    @Autowired(required = false)
    private TimeMapper2 timeMapper2;

    // 테스트 어노테이션으로 서버를 실행하지 않고 코드를 확인할 수 있음
    @Test
    public void testGetTime(){
        // log4j2를 이용한 로그를 출력
        // log4j2.xml에 설정한 패턴 대로 콘솔창에 출력됨
        log.info(timeMapper.getTime());
        System.out.println(timeMapper.getTime());
    }

    @Test
    public void testGetTime2(){
        log.info(timeMapper2.getTime()+ " timeMapper2를 실행했습니다.");
    }
}









