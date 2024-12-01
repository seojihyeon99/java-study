package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

public class ChatClient {
//	private static final String Server_IP = "127.0.0.1";
	private static final int PORT = 8080;
	
	public static void main(String[] args) {
		Scanner scanner = null;
		Socket socket = null;
		try {
			//1. 키보드 연결
			scanner = new Scanner(System.in);
			
			//2. socket 생성
			socket = new Socket();			
			
			//3. 연결
			String hostAddress = InetAddress.getLocalHost().getHostAddress();
			socket.connect(new InetSocketAddress(hostAddress, PORT));
			
			//4. reader/writer 생성
//			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true); // Auto Flush
				
			//5. join 프로토콜
			String nickname = null;
			while(true) {
				System.out.print("닉네임>>");
				nickname = scanner.nextLine();
				
				if(nickname.trim().isEmpty()) {
					System.out.println("닉네임을 입력해주세요.");
				} else {
					break;
				}
			}
			// Base64 인코딩 (join:`My:Nickname`과 같은 경우의 parsing 오류 방지)
			String encodedNickname = Base64.getEncoder().encodeToString(nickname.getBytes());
			pw.println("join:" + encodedNickname);
			pw.flush();

			//6. ChatClientReceiveThread 시작
			ChatClientThread clientThread = new ChatClientThread(socket);
			clientThread.start();
			
			//7. 키보드 입력 처리
			while(true) {
				String input = scanner.nextLine();
				
				// 빈 문자열인 경우 서버에 통신 보내지 않음
				if(input.trim().isEmpty()) {
					continue;
				}
						
				if("quit".equals(input)) {
					// 8. quit 프로토콜 처리
					pw.println("quit:");
					pw.flush();
					
					// ChatServerThread 종료 대기(blocking)
					try {
						clientThread.join();						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					break;
				} else {
					// 9. 메시지 처리
					// Base64 인코딩 (message:`제목:임시제목`과 같은 경우의 parsing 오류 방지)
					String encodedMessage = Base64.getEncoder().encodeToString(input.getBytes());
					pw.println("message:" + encodedMessage);
					pw.flush();
				}
			}
		} catch(IOException ex) {
		    log("IOException:" + ex);
		} catch(Exception e) {
			log("Error:" + e);
		} finally {
			//10. 자원정리
			try {
				if(scanner != null) {
					scanner.close();					
				}
				if(socket != null && !socket.isClosed()) {
					socket.close();					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
	
	public static void log(String message) {
		System.out.println("[Client] " + message);
	}
	
}
