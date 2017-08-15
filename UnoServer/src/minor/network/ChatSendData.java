package minor.network;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatSendData implements Runnable{

	String host = "";
	String msg;
	int clientPort;
	
	public ChatSendData(String ipaddress, String msg, int clientPort) {
		this.host = ipaddress;
		this.msg = msg;
		this.clientPort = clientPort;
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		try {
			Socket soc = new Socket(host, clientPort);
			soc.setSoTimeout(30000);
			sendWhat(soc, msg);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendWhat(Socket soc, String msg) throws IOException {
		String strData = msg;		
		System.out.println("ab mai agai chat ke send what mei with string ... "+strData);
		if(strData.length() > 0){
			BufferedOutputStream out = new BufferedOutputStream(soc.getOutputStream());
			out.write(strData.getBytes());
			out.flush();
			out.close();
		}
	}
}
