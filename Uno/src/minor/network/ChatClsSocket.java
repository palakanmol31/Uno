package minor.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import minor.uno.MainActivity;



import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class ChatClsSocket implements Runnable{

	ServerSocket serverSocket;
	Socket listeningSocket;
	int port = 8025;
	Context _context;
	Thread t;

	
	public ChatClsSocket(Context context) {
		this._context = context;
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
				listeningSocket = serverSocket.accept();
				BufferedReader teacherStream = new BufferedReader(new InputStreamReader(listeningSocket.getInputStream()));
               String strData = teacherStream.readLine().trim();
				Log.d("Your data", strData);
				((MainActivity)_context).j=1;
				((MainActivity)_context).chatMsg=strData;
				//Toast.makeText(_context, strData, 3000).show();
				Message msg = new Message();
				msg.obj = strData;
				Intent intent = new Intent(_context, MainActivity.class);
				intent.putExtra("data", strData);
				intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				_context.startActivity(intent);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
