package socket;

import java.io.*;
import java.net.*;

public class SimpleChatServer3 {
    public static void main(String[] args) {
        try {
            // 1. 서버 소켓 생성 (8090번 포트에서 클라이언트 연결 기다림)
            ServerSocket serverSocket = new ServerSocket(8090);
            System.out.println("서버 실행 중...");

            // 2. 클라이언트 접속 수락 (접속이 올 때까지 대기)
            Socket socket = serverSocket.accept();
            System.out.println("클라이언트 연결됨!");

            // 3. 데이터 송수신을 위한 스트림 설정
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 클라이언트가 보내는 메시지 받기
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // 서버가 클라이언트로 보내기
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in)); // 서버 키보드 입력 받기

            // 4. 채팅 반복 (서버-클라이언트 번갈아 가며 메시지 주고받기)
            while (true) {
                // 1) 클라이언트가 보낸 메시지 받기
                String clientMsg = in.readLine(); // 한 줄 읽기
                if (clientMsg == null || clientMsg.equalsIgnoreCase("exit")) break; // "exit"이면 종료
                System.out.println("클라이언트: " + clientMsg); // 받은 메시지 출력

                // 2) 서버가 한 줄 입력해서 클라이언트에게 보내기
                System.out.print("서버 입력 > "); // 입력 안내 출력
                String serverMsg = keyboard.readLine(); // 키보드로 한 줄 입력
                out.write(serverMsg);         // 클라이언트에게 전송
                out.newLine();                // 줄바꿈 문자
                out.flush();                  // 전송 확정
                if (serverMsg.equalsIgnoreCase("exit")) break; // "exit"이면 종료
            }

            // 5. 종료 처리
            System.out.println("서버 종료");
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
