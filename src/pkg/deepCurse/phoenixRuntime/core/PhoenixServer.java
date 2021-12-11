package pkg.deepCurse.phoenixRuntime.core;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PhoenixServer {

	public PhoenixServer(PhoenixSettings settings, ServerSocket serverSocket) throws IOException {

		boolean done = false;

		while (!done && settings.isEnabled) {

			Socket socket = serverSocket.accept();
			DataInputStream dIn = new DataInputStream(socket.getInputStream());
			String message = dIn.readUTF();

//			if (message.startsWith(settings.auth)) {
//			if (settings.actions.containsKey(message.substring(settings.auth.length()))) {

			settings.commandManager.run(message, settings);

//			}
//			} else {
//				System.out.println("(##WARN##) INSECURE CONNECTION: " + socket.getRemoteSocketAddress());
//			}

			dIn.close();
			socket.close();

		}
	}
}
