package chapter24;  // 이 클래스가 속한 패키지 이름

import java.io.FileOutputStream;  // 파일에 바이트 단위로 출력할 수 있는 클래스
import java.io.IOException;       // 입출력 예외 처리를 위한 클래스
import java.io.OutputStream;      // 바이트 출력 스트림의 최상위 클래스

public class Ex01_FileWrite1
{
    public static void main(String[] args) throws IOException  // 예외처리를 하지 않음
    {
        // OutputStream 타입의 변수 out을 선언하고, FileOutputStream 객체를 할당
        // "data.txt"라는 파일을 새로 만들거나 기존 파일을 덮어씁니다.
        OutputStream out = new FileOutputStream("data.txt");
        
        // 정수 65를 파일에 씁니다.
        // 65는 ASCII 코드로 'A' 문자에 해당합니다.
        out.write(65);  // 파일에 'A' 문자 하나가 저장됨
        
        // 스트림을 닫아줍니다. (자원 해제)
        out.close(); 
    }
}
