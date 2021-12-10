package pkg.deepCurse.phoenixRuntime.core;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class PhoenixServer {

	public PhoenixServer(PhoenixSettings settings, ServerSocket serverSocket) throws IOException {

		boolean done = false;

		while (!done && settings.isEnabled) {

			Socket socket = serverSocket.accept();
			DataInputStream dIn = new DataInputStream(socket.getInputStream());
			String message = dIn.readUTF();

			settings.actions.get(message).run();

			dIn.close();
			socket.close();

		}
	}
}
