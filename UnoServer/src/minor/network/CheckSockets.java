package minor.network;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class CheckSockets implements Runnable{

	private StringBuilder host;
	private CheckSocketListener completedListener;
	private int student_port = 5025;
	
	public CheckSockets(String threadName, String hostAddress, CheckSocketListener listener) {
		
		this.host = new StringBuilder();
		this.host.append(hostAddress);
		this.completedListener = listener;
		
		Thread thread = new Thread(this, threadName);
		thread.start();
	}
	
	@Override
	public void run() {
		boolean available = true;
		try {
			new Socket(host.toString(), student_port);
		} catch (UnknownHostException e) {
			available = false;
		} catch (IOException e) {
			available = false;
		}
		System.out.println("Host Address:"+ host +" , "+ available);
		completedListener.onCompleted(available, host.toString());
	}

}
