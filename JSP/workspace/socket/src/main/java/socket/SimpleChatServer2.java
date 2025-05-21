package socket;

import java.io.*;
import java.net.*;

public class SimpleChatServer2 {

    public static void main(String[] args) {
        try {
            // 1. 서버 시작 (포트 8090으로 클라이언트 접속 대기)
            ServerSocket serverSocket = new ServerSocket(8095);
            System.out.println("서버 실행 중...");
            
            // 2. 클라이언트가 접속하면 연결 수락
            Socket clientSocket = serverSocket.accept();
            System.out.println("클라이언트 연결됨!");

            // 3. 입출력 스트림 준비
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // 클라이언트 → 서버
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())); // 서버 → 클라이언트
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in)); // 서버 키보드 입력

            // 4. 클라이언트가 보낸 메시지를 계속 읽는 쓰레드
            new Thread(() -> {
                try {
                    String line;
                    while ((line = in.readLine()) != null) {
                        System.out.println("클라이언트: " + line);  // 클라이언트 메시지 출력
                    }
                } catch (IOException e) {
                    System.out.println("클라이언트 연결 종료");
                }
            }).start();  // 쓰레드 시작

            // 5. 서버가 입력한 메시지를 클라이언트에게 보내는 쓰레드
            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = keyboard.readLine()) != null) {  // 서버 키보드에서 한 줄 입력
                        out.write("서버: " + msg);     // 클라이언트에게 보냄
                        out.newLine();                // 줄바꿈
                        out.flush();                  // 버퍼 비우기 → 전송
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
