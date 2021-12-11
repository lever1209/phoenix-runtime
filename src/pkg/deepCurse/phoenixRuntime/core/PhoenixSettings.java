package pkg.deepCurse.phoenixRuntime.core;

import java.util.List;

public class PhoenixSettings {

	public volatile boolean isEnabled = true;
	public volatile int commonPort = 43259;
	public volatile String address = "127.0.0.1";
	public volatile String loopingThreadName = "phoenix-looping-thread#" + this.hashCode();
	public boolean shouldLoop = false;
	public String auth = "";
	private PhoenixRuntime runtime = null;
	public PhoenixCommandManager commandManager = null;
	public String commandSplitRegex = ":split:";

	public PhoenixSettings setCommonPort(int port) {
		this.commonPort = port;
		return this;
	}
	
	public PhoenixSettings setCommandManager(PhoenixCommandManager commandManager) {
		this.commandManager = commandManager;
		return this;
	}
	
	public PhoenixSettings setCommandSplitRegex(String regex) {
		this.commandSplitRegex = regex;
		return this;
	}
	
	public PhoenixSettings setAuthentication(String auth) {
		this.auth = auth;
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
	
	public interface PhoenixRunnable {
		public void run(PhoenixRuntime runtime, List<String> args);
	}

	public PhoenixRuntime getRuntime() {
		return this.runtime;
	}
	
	public void setRuntime(PhoenixRuntime runtime) {
		this.runtime  = runtime;
	}
	
}
