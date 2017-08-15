package minor.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatClsSocket implements Runnable{

	ServerSocket serverSocket;
	Socket ListeningSocket;
	int port = 8125;
	Thread t;
	
	public ChatClsSocket() {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void connectSocket() {
		this.t = new Thread(this);
		t.start();
	}
	
	public void disconnectSocket() {
		if(t != null){
			t.interrupt();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				ListeningSocket = serverSocket.accept();
				System.out.println("Student Send - >"+ ListeningSocket.getInetAddress().getHostName());
				BufferedReader studentStream = new BufferedReader(new InputStreamReader(ListeningSocket.getInputStream()));
				String recievedData = studentStream.readLine().trim();
				System.out.println("Student Send - >"+ recievedData);
				StartServer.doChatOperation(recievedData,ListeningSocket.getInetAddress());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
