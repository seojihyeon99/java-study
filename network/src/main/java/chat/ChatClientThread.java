package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ChatClientThread extends Thread {
	private Socket socket;
	
	public ChatClientThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			// reader를 통해 읽은 데이터 콘솔에 출력하기 (message 처리)
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
 			
            while (true) {
            	String message = br.readLine();
            	
            	if(message == null) break;
            	
            	if(message.equals("quit:OK")) {
            		return;
            	}
            	
                System.out.println(message);
            }
		} catch(IOException e) {
			ChatServer.log("IOException:" + e);
		} catch(Exception e) {
			ChatServer.log("Error:" + e);
		}	
	}
	
}
