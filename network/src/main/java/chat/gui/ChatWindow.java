package chat.gui;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

import chat.ChatClientThread;
import chat.ChatServer;

public class ChatWindow {
	private static final int PORT = 8080;
	
	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;
	private ChatClientThread clientThread;
	
	private String name;
	private BufferedReader br;
	private PrintWriter pw;
	private Socket socket;

	public ChatWindow(String name) {
		frame = new Frame(name);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
		
		this.name = name;
	}

	public void show() {	
		// Button
		buttonSend.setBackground(Color.GRAY);
		buttonSend.setForeground(Color.WHITE);
		// 익명 클래스 (프로그램에서 일시적으로 한번만 사용되고 버려지는 객체)
		buttonSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				sendMessage();
			}
		});
//		buttonSend.addActionListener(actionEvent -> sendMessage());
		
		
		// Textfield
		textField.setColumns(80);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if(keyChar == KeyEvent.VK_ENTER) {
					sendMessage();
				}
			}
		});

		// Pannel
		pannel.setBackground(Color.LIGHT_GRAY);
		pannel.add(textField);
		pannel.add(buttonSend);
		frame.add(BorderLayout.SOUTH, pannel);

		// TextArea
		textArea.setEditable(false);
		frame.add(BorderLayout.CENTER, textArea);

		// Frame
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});
		frame.setVisible(true);
		frame.pack();
		
		try {
			// 1. 소켓 생성 및 서버 연결 작업
			socket = new Socket();
			
			String hostAddress = InetAddress.getLocalHost().getHostAddress();
			socket.connect(new InetSocketAddress(hostAddress, PORT));
						
			// 2. IO Stream Set
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true); // Auto Flush
			
			// 3. JOIN Protocol
			String encodedName = Base64.getEncoder().encodeToString(name.getBytes());
			pw.println("join:" + encodedName);
			pw.flush();
			
			// 4. ChatClientThread 생성
			clientThread = new ChatClientThread();
			clientThread.start();
			
		} catch(IOException ex) {
		    System.out.println("[Client] IOException:" + ex);
		}
	}
	
	private void sendMessage() {
		String message = textField.getText();
		
		textField.setText("");
		textField.requestFocus();

		// 빈 문자열인 경우 서버에 통신 보내지 않음
		if(message.trim().isEmpty()) {
			return;
		}
		
		String encodedMessage = Base64.getEncoder().encodeToString(message.getBytes());
		pw.println("message:" + encodedMessage);
		pw.flush();
		
		// ChatClientThread에서 서버로부터 받은 메세지가 있다고 치고~
//		updateTextArea("아무개:" + message); // 나중에 지워야 하는 코드. Thread에 있어야함
	}
	
	private void updateTextArea(String message) {
		textArea.append(message);
		textArea.append("\n");
	}
	
	private void finish() {
		// quit protocol 구현
		pw.println("quit:");
		pw.flush();

		// 자원정리
		try {
			if(socket != null && !socket.isClosed()) {
				socket.close();					
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// exit java application
		System.exit(0);
	}
	
	// Inner Class => 다른 클래스(ChatWindow)의 private에 접근 가능 
	private class ChatClientThread extends Thread {
		
		@Override
		public void run() {
			try {
				// reader를 통해 읽은 데이터 콘솔에 출력하기 (message 처리)
//				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
	 			
	            while (true) {
	            	String message = br.readLine();
	            	
	            	if(message == null) break;
	            	
	            	if(message.equals("quit:OK")) {
	            		return;
	            	} else if(message.equals("join:OK")) {
	            		updateTextArea("채팅을 시작합니다.");
	            	} else {
	            		updateTextArea(message);	            		
	            	}
	            	
	            }
			} catch(IOException e) {
				ChatServer.log("IOException:" + e);
			}
		}
	}
	
	public static void log(String message) {
		System.out.println("[Client] " + message);
	}
}










