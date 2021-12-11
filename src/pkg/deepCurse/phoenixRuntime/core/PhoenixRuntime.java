package pkg.deepCurse.phoenixRuntime.core;

import java.io.IOException;
import java.net.ServerSocket;

public class PhoenixRuntime {

	private volatile PhoenixSettings settings = null;
	private PhoenixInterface runnable = null;
	private PhoenixServer server = null;
	public Runnable onLockedRunnable = () -> {
		System.out.println("System is locked. . .");
	};

	public PhoenixRuntime(PhoenixSettings settings, PhoenixInterface runnable) {
		this.settings = settings;
		this.runnable = runnable;
		settings.setRuntime(this);
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
						this.onLockedRunnable().run();
					}
					if (serverSocket != null) {
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

	public void shutdown(int status) {
		System.exit(status);
	}

	private Runnable onLockedRunnable() {
		return this.onLockedRunnable;
	}

	public PhoenixRuntime setLockedRunnable(Runnable onLockedRunnable) {
		this.onLockedRunnable = onLockedRunnable;
		return this;
	}

}
