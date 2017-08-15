package minor.network;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.content.Context;

public class SendData implements Runnable{

	String host = "";
	Context context;
	int serverAdd = 5125;
	int msg;
	
	public SendData(int m, Context _context) {
		this.context = _context;
		this.msg = m;
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
	
	public void sendWhat(Socket soc, int msg) throws IOException {
		int strData = msg;
		
				
			BufferedOutputStream out = new BufferedOutputStream(soc.getOutputStream());
			out.write(strData);
			out.flush();
			out.close();
		
	}
}
