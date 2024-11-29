package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import echo.EchoServer;

public class ChatServerThread extends Thread {
	private String nickname;
	private Socket socket;
	private List<Writer> listWriters;

	public ChatServerThread(Socket socket, List<Writer> listWriters) {
		this.socket = socket;
		this.listWriters = listWriters;
	}

	@Override
	public void run() {
		try {
			//1. Remote Host Information
			InetSocketAddress remoteSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
			String remoteHostAddress = remoteSocketAddress.getAddress().getHostAddress();
			int remotePort = remoteSocketAddress.getPort();
			ChatServer.log("클라이언트 연결 성공 [" + remoteHostAddress + ":" + remotePort + "]");
			
			//2. 스트림 얻기
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true); // Auto Flush
	
			//3. 요청 처리 			
			while(true) {
			   String request = br.readLine();
			   
			   if(request == null) {
				  ChatServer.log(nickname + " 클라이언트로부터 연결 끊김");
				  doQuit(pw);
			      break;
			   }
			   
			   ChatServer.log(request);
	
			   // 4. 프로토콜 분석
			   // join:홍길동, message:안녕하세요, quit:
			   String[] tokens = request.split(":");
				
			   if("join".equals(tokens[0])) {
				   doJoin(tokens[1], pw);
			   } else if("message".equals(tokens[0])) {
				   doMessage(tokens[1]);
			   } else if("quit".equals(tokens[0])) {		   
				   doQuit(pw);
				   break;
			   } else {
				   ChatServer.log("에러: 알수 없는 요청(" + tokens[0] + ")" );
			   }		
			}
		} catch(SocketException e) {
			ChatServer.log("Socket Exception:" + e);
		} catch(IOException e) {
			ChatServer.log("IOException:" + e);
		} catch(Exception e) {
			ChatServer.log("Error:" + e);
		} finally {
			try {
				if(socket != null && !socket.isClosed()) {
					socket.close();											
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	

	private void doJoin(String nickName, Writer writer) {
	   this.nickname = nickName;
	   
	   // 자신을 제외한 다른 사용자들에게 참여 메세지 브로드캐스팅
	   String data = nickname + "님이 참여하셨습니다.";
	   broadcast(data);
	   
	   // Writer Pool에 현재 스레드의 PrintWriter를 저장
	   addWriter(writer);
	   
	   // ack를 통해 자신에게 "방 참여 성공" 메세지를 보냄
	   PrintWriter printWriter = (PrintWriter)writer;
	   printWriter.println("join:OK");
	   printWriter.flush();
	}
	
	private void broadcast(String data) {
		synchronized(listWriters) {
			for(Writer writer : listWriters) {
				PrintWriter printWriter	 = (PrintWriter)writer;
				printWriter.println(data);
				printWriter.flush();
			}
		}		
	}

	private void addWriter(Writer writer) {
		synchronized (listWriters) { // 여러 스레드가 하나의 공유 객체에 접근시, 동기화 보장
			listWriters.add(writer);				
		}
	}

	private void doMessage(String str) {
		String data = str;
		broadcast(data);
	}
	
	private void doQuit(Writer writer) {
	   // 퇴장 로직 
	   removeWriter(writer);
		
	   String data = nickname + "님이 퇴장하셨습니다."; 
	   broadcast(data);
	   
	   // ack를 통해 자신에게 "퇴장 성공" 메세지를 보냄
	   PrintWriter printWriter = (PrintWriter)writer;
	   printWriter.println("quit:OK");
	   printWriter.flush();
	}

	private void removeWriter(Writer writer) {
		synchronized(listWriters) {
			listWriters.remove(writer);
		}	
	}	
	
	
}
