package pkg.deepCurse.phoenixRuntime.core;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pkg.deepCurse.phoenixRuntime.core.PhoenixSettings.PhoenixRunnable;

public class PhoenixCommandManager {

	private final Map<String, PhoenixRunnable> commandMap = new HashMap<>();
	// private final Map<String, DirectCommandInterface> directCommandMap = new
	// HashMap<>();
//	private static Executor executor = null;
	PhoenixSettings settings = null;

	public PhoenixCommandManager addCommand(String name, PhoenixRunnable runnable) {
		this.commandMap.put(name, runnable);
		return this;
	}

	public PhoenixRunnable getCommand(String commandName) {
		return commandMap.get(commandName);
	}

	public Collection<PhoenixRunnable> getCommands() {
		return commandMap.values();
	}

	public void run(String message, PhoenixSettings settings) {

		String[] messageArray = message.split(settings.commandSplitRegex);

		String command = messageArray[0];

		List<String> args = Arrays.asList(messageArray).subList(1, messageArray.length);
		
		
		
	}

}
