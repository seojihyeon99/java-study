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
import java.util.Scanner;

public class ChatClient {
//	private static final String Server_IP = "127.0.0.1";
	public static final int PORT = 8080;
	
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
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true); // Auto Flush
				
			//5. join 프로토콜
			System.out.print("닉네임>>");
			String nickname = scanner.nextLine();
			pw.println("join:" + nickname);
			pw.flush();

			//6. ChatClientReceiveThread 시작
			new ChatClientThread(socket).start();
			
			//7. 키보드 입력 처리
			while(true) {
				String input = scanner.nextLine();
						
				if("quit".equals(input) == true) {
					// 8. quit 프로토콜 처리
					pw.println("quit:");
					pw.flush();
					
					// 여기서 Server 스레드 응답 완료될때까지 blocking하는 처리해주어야!!
					
					break;
				} else {
					// 9. 메시지 처리
					pw.println("message:" + "[" + nickname + "] " + input);
					pw.flush();
				}
			}
		} catch(IOException ex) {
		    log( "error:" + ex );
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
