package minor.network;

public interface CheckSocketListener {
	void onCompleted(boolean result, String connectionIp);
}
