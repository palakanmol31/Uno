package minor.network;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Stack;

import minor.server.CardDistributer;
import minor.server.Player;


public class StartServer {

	public static ArrayList<Integer> player1=new ArrayList<Integer>(),player2=new ArrayList<Integer>(); ;
	public static Stack circle=new Stack() , deck=new Stack();
	static ArrayList<Player> clients = new ArrayList<Player>();
	static ArrayList<String> clientIpAddresses = new ArrayList<String>();
	public static String ia;
	static int count =  0;
	static int matchplayer;
	static int unmatchplayer;

	public static void main(String[] args) {
		ClsSocket socket = new ClsSocket();
		socket.connectSocket();
		ChatClsSocket socket1 = new ChatClsSocket();
		socket1.connectSocket();
		allocateCard();
		findConnectedStudents();
	}

	public static void findConnectedStudents(){
		for(int i = 0; i < 256 ; i++){
			new CheckSockets(i+"", "192.168.5."+i, new CheckSocketListener() {

				@Override
				public void onCompleted(boolean result, String connectionIp) {
					if(result){
						clientIpAddresses.add(connectionIp);
					}
					count ++;
					if(count >= 256 ){
						System.out.println("Process Terminated");
						System.out.println("connected : "+clientIpAddresses.get(0));
					    System.out.println("connected : "+clientIpAddresses.get(1));
						allocateValues();
					}
				}
			});
		}
	}
	public static void allocateValues(){
		if(clientIpAddresses.size() > 1){
			Player playerOne = new Player(player1, circle, deck, clientIpAddresses.get(0));
			Player playerTwo = new Player(player2, circle, deck, clientIpAddresses.get(1));
			clients.add(playerOne);
			clients.add(playerTwo);
			initialOperation();
		}
	}
	public static void initialOperation(){
		System.out.println("INITIAL OPERATION");
		for(Player client : clients){
			client.player.trimToSize();
			new SendData(client.getIpAddress(), client.getPlayer(),client.getDeck(),client.getCircle(),client.player.size(), 5025);
		}
	}

	public synchronized static void doOperation(int m, InetAddress ip){
		System.out.println("in do operation" +m);
		//System.out.println();
		int a=0; 
		
		String ipA= ip.toString();
		int len=ipA.length();
		
		if(ipA.contains("/"))
		{
			a=ipA.indexOf("/");
			ia=ipA.substring(a+1,len);
		}
		System.out.println(ia);
		
		if(m==200)
		{
			int rem=(int)deck.pop();
			for(Player client : clients){
				
				System.out.println("Client ka ipaddress..."+client.getIpAddress());
				if(ia.equalsIgnoreCase(client.getIpAddress()))
				{
					
					client.pushCard(rem);
					playerCard(ia);
					System.out.println("client player1"+client.getPlayer());
					System.out.println("client player1"+client.getCircle());
				new SendData(client.getIpAddress(),client.getPlayer() ,client.getDeck(),client.getCircle(),unmatchplayer, 5025);
				}
				else
				{
					playerCard(ia);
					new SendData(client.getIpAddress(),client.getPlayer() ,client.getDeck(),client.getCircle(),matchplayer, 5025);	
				}
				
			}		

			
		}
		else if((m==10) || (m==21) || (m==32)|| (m==43))
		{
			circle.push(m);
			for(Player client : clients){
				
				System.out.println("Client ka ipaddress..."+client.getIpAddress());
				if(ia.equalsIgnoreCase(client.getIpAddress()))
				{
					
					client.removeCard(m);
					playerCard(ia);
					System.out.println("client player1"+client.getPlayer());
					System.out.println("client player1"+client.getCircle());
				new SendData(client.getIpAddress(),client.getPlayer() ,client.getDeck(),client.getCircle(),unmatchplayer ,5025);
				}
				else
				{
					int a1=(int)deck.pop();
					int a2=(int)deck.pop();
					client.pushCard(a1);
					client.pushCard(a2);
					playerCard(ia);
					System.out.println("client player"+client.getPlayer());
					System.out.println("client player"+client.getCircle());
					new SendData(client.getIpAddress(), client.getPlayer(),client.getDeck(),client.getCircle(),matchplayer, 5025);
				}
			}		
			}

			
		else
		{
			circle.push(m);
		for(Player client : clients){
			
			System.out.println("Client ka ipaddress..."+client.getIpAddress());
			if(ia.equalsIgnoreCase(client.getIpAddress()))
			{
				
				client.removeCard(m);
				playerCard(ia);
				System.out.println("client player1"+client.getPlayer());
				System.out.println("client player1"+client.getCircle());
			new SendData(client.getIpAddress(),client.getPlayer() ,client.getDeck(),client.getCircle(),unmatchplayer ,5025);
			}
			else
			{
				playerCard(ia);
				System.out.println("client player"+client.getPlayer());
				System.out.println("client player"+client.getCircle());
				new SendData(client.getIpAddress(), client.getPlayer(),client.getDeck(),client.getCircle(),matchplayer, 5025);
			}
		}		
		}
	}


	static void allocateCard()
	{
		CardDistributer cd = new CardDistributer();
		Object[] cardNumbers = cd.getCardNumber().toArray();
		int j = 0;

		for (int i = 0; i < 7; i++) {
			player1.add(i, Integer.parseInt(cardNumbers[j++].toString()));

		}
		for (int i = 0; i < 7; i++) {
			player2.add(i, Integer.parseInt(cardNumbers[j++].toString()));
		}
		for (int i = 0; i < 1; i++) {
			circle.push(Integer.parseInt(cardNumbers[j++].toString()));

		}
		for (int i = 0; i < 28; i++) {
			deck.push(Integer.parseInt(cardNumbers[j++].toString()));

		}

		System.out.println("card for player1: " + player1);
		System.out.println("card for player2: " + player2);
		System.out.println("card for circle: " + circle);
		System.out.println("card for deck: " + deck);

	}
	public static void playerCard(String ia)
	{
		for(Player client : clients)
		{
			client.player.trimToSize();
			if(ia.equalsIgnoreCase(client.getIpAddress()))
			{
				
			matchplayer=client.player.size();
			}
			else
			{
			unmatchplayer=client.player.size();	
			}	
				
		}
	}

	public static void doChatOperation(String recievedData,InetAddress ip) {
		System.out.println("Chat");
		int a=0; 
		
		String ipA= ip.toString();
		int len=ipA.length();
		
		if(ipA.contains("/"))
		{
			a=ipA.indexOf("/");
			ia=ipA.substring(a+1,len);
		}
		System.out.println(ia);
		
		for(Player client : clients){
			
			System.out.println("Client ka ipaddress..."+client.getIpAddress());
			if(ia.equalsIgnoreCase(client.getIpAddress()))
			{
				System.out.println("Equal hai");
			}	
			else
			{
				System.out.println("Equal ni h ");
				new ChatSendData(client.getIpAddress(),recievedData, 8025);
			}
				
		}

	}
}
