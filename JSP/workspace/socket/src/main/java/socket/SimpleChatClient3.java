package socket;

import java.io.*;
import java.net.*;

public class SimpleChatClient3 {
    public static void main(String[] args) {
        try {
            // 1. 서버에 연결 시도 (localhost의 8090 포트)
            Socket socket = new Socket("localhost", 8090);
            System.out.println("서버에 연결됨!");

            // 2. 데이터 송수신을 위한 스트림 설정
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 서버가 보내는 메시지 받기
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // 클라이언트가 서버로 보내기
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in)); // 클라이언트 키보드 입력 받기

            // 3. 채팅 반복 (클라이언트가 먼저 보내고 → 서버 응답 받기)
            while (true) {
                // 1) 클라이언트가 먼저 한 줄 입력
                System.out.print("클라이언트 입력 > "); // 입력 안내 출력
                String clientMsg = keyboard.readLine(); // 키보드 입력
                out.write(clientMsg);           // 서버로 전송
                out.newLine();                  // 줄바꿈 문자
                out.flush();                    // 전송 확정
                if (clientMsg.equalsIgnoreCase("exit")) break; // "exit"이면 종료

                // 2) 서버의 응답 받기
                String serverMsg = in.readLine(); // 서버가 보낸 한 줄 읽기
                if (serverMsg == null || serverMsg.equalsIgnoreCase("exit")) break; // "exit"이면 종료
                System.out.println("서버: " + serverMsg); // 받은 메시지 출력
            }

            // 4. 종료 처리
            System.out.println("클라이언트 종료");
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
