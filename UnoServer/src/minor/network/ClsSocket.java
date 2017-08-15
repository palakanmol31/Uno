package minor.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ClsSocket implements Runnable{

	ServerSocket serverSocket;
	Socket ListeningSocket;
	int port = 5125;
	Thread t;
	
	public ClsSocket() {
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
				int recievedData = studentStream.read();
				System.out.println("Student Send - >"+ recievedData);
				StartServer.doOperation(recievedData,ListeningSocket.getInetAddress());
				
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
