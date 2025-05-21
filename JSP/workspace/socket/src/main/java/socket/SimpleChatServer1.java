package socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleChatServer1 {

    // 클래스 멤버 필드 선언 (서버에서 사용할 객체들)
    ServerSocket server;          // 클라이언트의 연결을 기다리는 서버 소켓
    Socket client;                // 연결된 클라이언트 소켓
    BufferedReader br;           // 클라이언트에서 오는 메시지를 읽는 스트림
    BufferedReader keyReader;    // 서버 측 키보드 입력을 읽는 스트림
    BufferedWriter bw;           // 클라이언트에게 메시지를 보내는 스트림

    // 서버 초기화 및 실행
    public void initServer() {
        try {
            System.out.println("실행 되었음.");  // 서버 시작 알림
            server = new ServerSocket(8090);    // 포트 8090번으로 서버소켓 생성
            client = server.accept();           // 클라이언트 접속 기다림 (여기서 멈춤)
            System.out.println("소켓 연결 되었음.");  // 클라이언트가 접속하면 출력됨

            // 클라이언트에서 보내는 데이터를 읽을 준비
            br = new BufferedReader(new InputStreamReader(client.getInputStream()));

            // 서버에서 클라이언트에게 데이터를 보낼 준비
            bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            // 클라이언트로부터 메시지를 읽어서 출력
            try {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println("수신 메시지: " + line);
                }
                System.out.println("클라이언트가 연결을 종료했습니다.");
            } catch (IOException e) {
                System.out.println("클라이언트와의 연결이 끊어졌습니다.");
            }


            // 클라이언트 메시지 수신 후 서버 키보드 입력 대기
            System.out.println("keyReader: Start!");
            keyReader = new BufferedReader(new InputStreamReader(System.in));  // 키보드 입력 준비
            System.out.println("keyReader: Start!!");

            String serverMsg = "server: " + keyReader.readLine();  // 키보드로 한 줄 입력
            System.out.println("keyReader: Read Line");

            bw.write(serverMsg);     // 클라이언트로 메시지 전송
            bw.newLine();            // 줄 바꿈 문자 전송
            bw.flush();              // 출력 버퍼 비우기 (즉시 전송)
            System.out.println("Flush!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // main 메소드에서 서버 실행
    public static void main(String[] args) {
        new SimpleChatServer1();  // 생성자 호출 → initServer 실행됨
    }

    // 생성자에서 서버 초기화 메소드 호출
    public SimpleChatServer1() {
        initServer();
    }

}
