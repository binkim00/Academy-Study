package socketEx;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import org.json.JSONObject;

public class ChatClient {
	//필드
		Socket socket;
		DataInputStream dis; //수신
		DataOutputStream dos; //송신
		String chatName;
		
		//메소드: 서버 연결
		public  void connect() throws IOException {
			socket = new Socket("10.100.105.3", 50001); //연결할 서버의 내용 제공, localhost에 서버 IP를 넣어야함, 50001은 서버의 포트번호
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			System.out.println("[클라이언트] 서버에 연결됨");		
		}	
		//메소드: JSON 받기
		public void receive() {
			Thread thread = new Thread(() -> { //메시지를 받는 스레드 생성
				try {
					while(true) {
						String json = dis.readUTF(); //JSON 수신
						JSONObject root = new JSONObject(json);
						String clientIp = root.getString("clientIp");
						String chatName = root.getString("chatName");
						String message = root.getString("message");
						System.out.println("<" + chatName + "@" + clientIp + "> " + message);
					}
				} catch(Exception e1) { //서어봐 연결이 끊겼을 시
					System.out.println("[클라이언트] 서버 연결 끊김");
					System.exit(0);
				}
			});
			thread.start();
		}	
		//메소드: JSON 보내기
		public void send(String json) throws IOException {
			dos.writeUTF(json);
			dos.flush();
		}	
		//메소드: 서버 연결 종료
		public void unconnect() throws IOException {
			socket.close();
		}	
		//메소드: 메인
		public static void main(String[] args) {		
			try {			
				ChatClient chatClient = new ChatClient();
				chatClient.connect(); //서버에 연결
				
				Scanner scanner = new Scanner(System.in);
				System.out.println("대화명 입력: ");
				chatClient.chatName = scanner.nextLine();
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("command", "incoming"); //command 에 incoming (첫 입장) 내용 전달
				jsonObject.put("data", chatClient.chatName); //설정한 채팅네임 전달
				String json = jsonObject.toString();
				chatClient.send(json); //전달
				
				chatClient.receive(); //메시지 수신을 기다림
				
				System.out.println("--------------------------------------------------");
				System.out.println("보낼 메시지를 입력하고 Enter");
				System.out.println("채팅를 종료하려면 q를 입력하고 Enter");
				System.out.println("--------------------------------------------------");
				while(true) {
					String message = scanner.nextLine();
					if(message.toLowerCase().equals("q")) {
						break;
					} else {
						jsonObject = new JSONObject();
						jsonObject.put("command", "message"); //command 종류 : message
						jsonObject.put("data", message); //data에 보낼 메시지 전달
						json = jsonObject.toString();
						chatClient.send(json); //전달
					}
				}
				scanner.close();
				chatClient.unconnect();
			} catch(IOException e) {
				System.out.println("[클라이언트] 서버 연결 안됨");
			}
		}
}
