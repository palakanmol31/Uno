package minor.network;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.content.Context;

public class ChatSendData implements Runnable{

	String host = "";
	Context context;
	int serverAdd = 8125;
	String msg;
	
	public ChatSendData(String msg, Context _context) {
		this.context = _context;
		this.msg = msg;
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		try {
			Socket soc = new Socket("192.168.5.52", serverAdd);
			sendWhat(soc, msg);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendWhat(Socket soc, String msg) throws IOException {
		String strData = msg;
		
		if(strData.length() > 0){
			BufferedOutputStream out = new BufferedOutputStream(soc.getOutputStream());
			out.write(strData.getBytes());
			out.flush();
			out.close();
		}
	}
}
