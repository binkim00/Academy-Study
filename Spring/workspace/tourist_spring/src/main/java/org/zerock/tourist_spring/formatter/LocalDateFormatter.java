package org.zerock.tourist_spring.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

//문자열 데이터를 날짜 데이터로 변경하는 클래스
public class LocalDateFormatter implements Formatter<LocalDate> {
    // alt + insert 로 코드 자동생성창 열기
    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        // 문자 데이터가 yyyy-MM-dd형식이면 날짜로 변경하여 반환
        return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        // 날짜 데이터를 문자열 yyyy-MM-dd 형식으로 변경하여 출력
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(object);
    }
}
