package minor.server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import minor.network.SendData;



     public class Player extends Thread {
	       public ArrayList player;
	        public Player opponent;
			 public Socket socket;
			 public Stack circle,deck;
			public String ipAddress;
			 
	        public Player(ArrayList player,Stack circle,Stack deck, String ipAddress) {
	            this.player = player;
	            this.circle = circle;
	            this.deck = deck;
	            this.ipAddress = ipAddress;
	       
	        }
		    
	        public String getIpAddress() {
				return ipAddress;
			}
	        
	        public ArrayList getPlayer() {
				return player;
			}
	        
	        public Stack getDeck() {
				return deck;
			}
	      
	        
	        public Stack getCircle() {
				return circle;
			}

	        public void pushCircle(int m)
	        {
	        	circle.push(m);
	        }
	       public void removeCard(Integer m)
	       {
	    	   player.remove(m);
	       }
	       public void pushCard(Integer m)
	       {
	    	   player.add(m);
	       }
			public void setIpAddress(String ipAddress) {
				this.ipAddress = ipAddress;
			}

	    public void setOpponent(Player opponent) {
            this.opponent = opponent;
        }

     }