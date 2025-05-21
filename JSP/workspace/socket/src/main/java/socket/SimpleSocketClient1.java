package socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleSocketClient1 {

    public static void main(String[] args) {
        Socket soc;  // 서버와 통신을 위한 소켓 객체 선언

        try {
            // localhost(내 컴퓨터)에서 포트 8888번으로 서버에 연결 시도
            soc = new Socket("localhost", 8888);
            System.out.println("연결되었음.");  // 연결이 성공되었을 때 출력

            // 서버로 데이터를 보내기 위한 출력 스트림 생성
            // true: 자동으로 flush(버퍼 비우기)해줌
            PrintWriter out = new PrintWriter(soc.getOutputStream(), true);

            // 서버로 문자열 전송
            out.println("Hello Server!!!");

            // 사용이 끝난 스트림과 소켓을 닫아줌 (자원 정리)
            soc.close();
            out.close();

        } catch (UnknownHostException e) {
            // 잘못된 호스트(서버 주소)를 입력했을 때 발생하는 예외 처리
            e.printStackTrace();
        } catch (IOException e) {
            // 입출력 관련 예외 발생 시 처리
            e.printStackTrace();
        }
    }

}
