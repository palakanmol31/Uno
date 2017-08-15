package minor.network;
import java.io.IOException;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Stack;

import minor.uno.MainActivity;
import android.content.Context;
import android.content.Intent;


public class ClsSocket implements Runnable{

	ServerSocket serverSocket;
	Socket listeningSocket;
	int port = 5025;
	Context _context;
	Thread t;
	 public static ArrayList player=null;
	    public static Stack circle=null;
	    public static Stack deck=null; 
	    public static Integer opp=null;
	
	public ClsSocket(Context context) {
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
				/*BufferedReader teacherStream = new BufferedReader(new InputStreamReader(listeningSocket.getInputStream()));
				String strData = teacherStream.readLine().trim();
				Log.d("Your data", strData);
				Intent intent = new Intent(_context, MainActivity.class);
				intent.putExtra("data", strData);
				intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				_context.startActivity(intent);
				*/
				((MainActivity)_context).j=0;
				ObjectInputStream ois= new ObjectInputStream(listeningSocket.getInputStream());
				    player = (ArrayList)ois.readObject();  
			        deck = (Stack)ois.readObject(); 
			        circle = (Stack)ois.readObject();
			        opp=(Integer)ois.readObject();
			        ((MainActivity)_context).p1=player;
			        ((MainActivity)_context).c1=circle;
			        ((MainActivity)_context).d1=deck;
			        ((MainActivity)_context).o1=opp;
			        Intent intent = new Intent(_context, MainActivity.class);
					
				intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
					_context.startActivity(intent);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
