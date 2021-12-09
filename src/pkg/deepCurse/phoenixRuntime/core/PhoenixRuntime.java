package pkg.deepCurse.phoenixRuntime.core;

public class PhoenixRuntime {

	public void launch(PhoenixSettings settings, PhoenixRunnable runnable) {
		
		Thread phoenixServerThread = new Thread(runnable, "phoenix-server");
		
	}
	
	abstract class PhoenixRunnable implements Runnable {
		
		
		
	}

}
