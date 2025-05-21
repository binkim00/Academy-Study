package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleSocketServer1 {

    public static void main(String[] args) {
        ServerSocket serversocket;  // 서버 소켓 선언 (클라이언트의 접속을 받는 역할)

        try {
            // 포트 번호 8888번으로 서버 소켓을 생성 (클라이언트가 이 포트로 접속함)
            serversocket = new ServerSocket(8888);
            System.out.println("접속했음.");  // 서버가 준비되었음을 알리는 메시지

            // 클라이언트의 접속을 기다림 (여기서 멈춰 있음. 클라이언트가 접속하면 진행됨)
            Socket clientsocket = serversocket.accept();
            System.out.println("accept!!!.");  // 클라이언트가 접속되었음을 출력

            // 클라이언트가 보낸 데이터를 읽기 위해 입력 스트림을 BufferedReader로 감쌈
            BufferedReader in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));

            String line;
            // 클라이언트로부터 한 줄씩 데이터를 읽음 (더 이상 읽을 데이터가 없으면 null 반환)
            while ((line = in.readLine()) != null) {
                // 수신한 메시지를 출력
                System.out.println("수신 메시지: " + line);
            }

        } catch (IOException e) {
            // 입출력 예외 발생 시 에러 메시지 출력
            e.printStackTrace();
        }
    }
}
