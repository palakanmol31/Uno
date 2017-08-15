package minor.network;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Stack;

public class SendData implements Runnable{

	String host = "";
	//String msg;
	ArrayList player;
	Stack deck;
	Stack circle;
	int card;
	int clientPort;
	
	public SendData(String ipaddress, ArrayList player, Stack deck ,Stack circle,int card, int clientPort) {
		this.host = ipaddress;
		this.player=player;
		this.deck=deck;
		this.circle=circle;
		this.card=card;
		//this.msg = msg;
		this.clientPort = clientPort;
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		try {
			Socket soc = new Socket(host, clientPort);
			soc.setSoTimeout(30000);
			sendWhat(soc, player,deck,circle,card);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendWhat(Socket soc, ArrayList p , Stack d , Stack c ,int card) throws IOException {
		/*String strData = msg;		
		if(strData.length() > 0){
			BufferedOutputStream out = new BufferedOutputStream(soc.getOutputStream());
			out.write(strData.getBytes());
			out.flush();
			out.close();*/
		System.out.println("IN SEND WHAT" +card);
		ObjectOutputStream oos=new ObjectOutputStream (soc.getOutputStream());
		oos.writeObject(p);
		oos.writeObject(d);
		oos.writeObject(c);
		oos.writeObject(card);
		oos.flush();
		oos.close();
		}
	}

