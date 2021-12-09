package pkg.deepCurse.phoenixRuntime.core;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public interface PhoenixInterface {

	public void Boot();

	public default void Update(PhoenixSettings settings) throws UnknownHostException, IOException {
		Socket cSocket = new Socket("127.0.0.1", settings.commonPort);
		DataOutputStream dOut = new DataOutputStream(cSocket.getOutputStream());
		dOut.writeByte(2);
		// System.out.println("WRITE");
		dOut.flush();

		dOut.close();

		cSocket.close();
	}
}
