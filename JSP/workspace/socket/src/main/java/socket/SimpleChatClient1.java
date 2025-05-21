package socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleChatClient1 {
	
	// 멤버 필드: 클라이언트가 사용할 입출력 객체들
	Socket socket;                  // 서버에 접속할 소켓
	BufferedReader keyReader;       // 키보드 입력을 받는 객체
	BufferedWriter bw;              // 서버로 메시지를 보내는 객체
	BufferedReader socReader;       // 서버에서 보내는 메시지를 받는 객체
	
	public void initClient() {
		try {
			// 서버의 localhost:8090으로 연결 시도
			socket = new Socket("localhost", 8090);
			System.out.println("연결되었음.");  // 연결 성공 시 출력

			// ---------------------- 서버로 보내는 준비 ----------------------
			// 키보드 입력을 읽기 위한 BufferedReader
			keyReader = new BufferedReader(new InputStreamReader(System.in));
			
			// 서버로 데이터를 보내기 위한 BufferedWriter
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			// 사용자가 키보드에 입력할 때까지 여기서 대기함
			String input;
			while (true) {
			    System.out.print("메시지 입력 (exit 입력 시 종료): ");
			    input = keyReader.readLine();  // 키보드에서 입력 받기

			    if (input == null || input.equalsIgnoreCase("exit")) {
			        System.out.println("클라이언트 종료");
			        break;
			    }

			    bw.write(input);   // 입력한 메시지 서버로 전송
			    bw.newLine();      
			    bw.flush();        // 전송 확정
			}
			
			// ---------------------- 서버에서 받는 준비 ----------------------
			// 서버가 보내는 데이터를 읽기 위한 BufferedReader
			socReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String line;
			System.out.println("서버에서 오는 수신 메세지 대기");
			// 서버가 보내는 메시지를 계속 읽어서 출력
			while ((line = socReader.readLine()) != null) {
				System.out.println("수신 메시지: " + line);
			}
			
			socket.close();
			bw.close();

		} catch (UnknownHostException e) {
			System.out.println("서버를 찾을 수 없습니다.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("입출력 오류가 발생했습니다.");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// main에서 클라이언트 실행
		new SimpleChatClient1().initClient();
	}
}
