package pkg.deepCurse.phoenixRuntime.core;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class PhoenixRuntime {

	private volatile PhoenixSettings settings = null;
	private PhoenixInterface runnable = null;
	private PhoenixServer server = null;

	public PhoenixRuntime(PhoenixSettings settings, PhoenixInterface runnable) {
		this.settings = settings;
		this.runnable = runnable;
	}

	public PhoenixServer getServer() {
		return server;
	}

	public void launch() {
		Thread launchThread = new Thread(() -> {

			Thread serverThread = new Thread(() -> {

				try {
					ServerSocket serverSocket = null;
					try {
						serverSocket = new ServerSocket(settings.commonPort);

					} catch (IOException e) {
						// is locked, update instead
						settings.onLockedRunnable().run();
					}
					if (serverSocket!=null) {
					this.server = new PhoenixServer(settings, serverSocket);
					
					} else {
						settings.isEnabled = false;
					}
				} catch (IOException ee) {

				}

			}, "phoenix-server-thread#" + this.hashCode());
			if (settings.isEnabled) {
			serverThread.start();

			runnable.boot();
			}
//			Thread loopingThread = new Thread(() -> {
////				while (settings.isEnabled && settings.shouldLoop) {
//				runnable.loop();
////				}
//			}, settings.loopingThreadName);
//			loopingThread.start();
		}, "launch-thread#" + this.hashCode());
		launchThread.start();
	}

	public void reboot() throws UnknownHostException, IOException {
		Socket cSocket = new Socket(this.settings.address, this.settings.commonPort);
		DataOutputStream dOut = new DataOutputStream(cSocket.getOutputStream());
		dOut.writeUTF("phoenix-update-start");

		dOut.flush();

		dOut.close();

		cSocket.close();
	}

}
