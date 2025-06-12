package org.zerock.springex2.controller.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

// 컨트롤러에서 발생하는 예외를 처리하는 어노테이션
@ControllerAdvice
@Log4j2
public class CommonExceptionAdvice {
    // 문자열을 JSON형식으로 돌려주도록 설정하는 어노테이션
    @ResponseBody
    // 예외처리를 할 예외를 설정
    @ExceptionHandler(NumberFormatException.class)
    public String exceptNumber(NumberFormatException numberFormatException){
        log.error("-------------------------------");
        log.error(numberFormatException.getMessage());
        return "NUMBER FORMAT EXCEPTION";
    }

    /*@ResponseBody
    // 위에서 설정한 NumberFormatException제외한 모든 예외
    @ExceptionHandler(Exception.class)
    public String exceptionCommon(Exception e){
        log.error("-----------------------------");
        log.error(e.getMessage());
        // HTML 형식으로 e.printStackTrace의 내용을 html화면에 출력하는 반복문
        StringBuffer buffer = new StringBuffer("<ul>");
        // 예외 메시지 저장
        buffer.append("<li>"+e.getMessage()+"</li>");
        // 예외가 일어나기 까지의 전체 메서드 실행 순서를 저장
        Arrays.stream(e.getStackTrace())
                .forEach(stackTraceElement->{
                    buffer.append("<li>"+stackTraceElement+"</li>");
                });
        buffer.append("</ul>");
        // StringBuffer를 String으로 변경하여 반환
        return buffer.toString();
    }*/
    @ExceptionHandler(NoHandlerFoundException.class)
    // 404 예외 발생시 실행되는 메서드
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(){
        // 404예외 발생시 custom404.jsp 파일을 실행하도록 설정
        return "custom404";
    }

}












