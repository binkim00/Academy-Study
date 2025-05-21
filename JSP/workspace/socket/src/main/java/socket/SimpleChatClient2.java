package socket;

import java.io.*;
import java.net.*;

public class SimpleChatClient2 {

    public static void main(String[] args) {
        try {
            // 1. 서버에 연결 요청
            Socket socket = new Socket("localhost", 8095);
            System.out.println("서버에 연결됨!");

            // 2. 입출력 스트림 준비
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 서버 → 클라이언트
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // 클라이언트 → 서버
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in)); // 클라이언트 키보드 입력

            // 3. 서버가 보낸 메시지를 계속 받는 쓰레드
            new Thread(() -> {
                try {
                    String line;
                    while ((line = in.readLine()) != null) {
                        System.out.println("서버: " + line);  // 받은 메시지 출력
                    }
                } catch (IOException e) {
                    System.out.println("서버 연결 종료");
                }
            }).start();  // 쓰레드 시작

            // 4. 클라이언트가 입력한 메시지를 서버로 보내는 쓰레드
            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = keyboard.readLine()) != null) {  // 한 줄 입력
                        out.write("클라이언트: " + msg);  // 서버로 보냄
                        out.newLine();                   // 줄바꿈
                        out.flush();                     // 즉시 전송
                    }
                } catch (IOException e) {
                    System.out.println("메시지 전송 오류");
                }
            }).start();  // 쓰레드 시작

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
