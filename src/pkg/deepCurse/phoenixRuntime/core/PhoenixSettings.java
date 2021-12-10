package pkg.deepCurse.phoenixRuntime.core;

import java.util.HashMap;

public class PhoenixSettings {

	public volatile boolean isEnabled = true;
	public volatile int commonPort = 1209;
	public volatile String address = "127.0.0.1";
	public volatile String loopingThreadName = "phoenix-looping-thread#" + this.hashCode();
	public boolean shouldLoop = false;
	public HashMap<String, Runnable> actions = new HashMap<String, Runnable>();
	public Runnable onLockedRunnable = ()->{
		System.out.println("System is locked. . .");
	};

	public PhoenixSettings() {
		this.actions.put("restart", () -> {
			this.isEnabled = false;
		});
	}

	public PhoenixSettings setCommonPort(int port) {
		this.commonPort = port;
		return this;
	}

	public PhoenixSettings setEnabled(boolean bool) {
		this.isEnabled = bool;
		return this;
	}

	public PhoenixSettings shouldLoop() {
		this.shouldLoop = true;
		return this;
	}

	public Runnable onLockedRunnable() {
		return this.onLockedRunnable ;
	}

}
