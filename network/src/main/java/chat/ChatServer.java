package chat;

import java.io.IOException;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
	public static final int PORT = 8080;
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		List<Writer> listWriters = new ArrayList<Writer>(); // PrintWriter Pool
		
		try {
			// 1. 서버 소겟 생성
			serverSocket = new ServerSocket();

			// 2. 바인딩
			String hostAddress = InetAddress.getLocalHost().getHostAddress();
			serverSocket.bind(new InetSocketAddress(hostAddress, PORT));			
			log("연결 기다림 " + hostAddress + ":" + PORT);
			
			// 3. 요청 대기 
			while( true ) {
				Socket socket = serverSocket.accept();
				new ChatServerThread(socket, listWriters).start();
			}
			
		} catch (IOException e) {
			log("IOException:" + e);
		} catch(Exception e) {
			ChatServer.log("Error:" + e);
		} finally {
			try {
				if(serverSocket != null && !serverSocket.isClosed()) {
					serverSocket.close();					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
					
	}
	
	public static void log(String message) {
		System.out.println("[Chat Server] " + message);
	}	
}
