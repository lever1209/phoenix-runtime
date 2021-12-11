package pkg.deepCurse.phoenixRuntime.tests;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import pkg.deepCurse.phoenixRuntime.core.PhoenixRuntime;
import pkg.deepCurse.phoenixRuntime.core.PhoenixSettings;

public class Main {

	public static void main(String[] args) {

		PhoenixSettings settings = new PhoenixSettings().setCommonPort(1209);

		PhoenixRuntime runtime = new PhoenixRuntime(settings, new Boot());

		runtime.onLockedRunnable = () -> {
			System.out.println("Is Locked, sending <shutdown>");
			try {
				Socket cSocket = new Socket("127.0.0.1", settings.commonPort);
				DataOutputStream dOut = new DataOutputStream(cSocket.getOutputStream());
				dOut.writeUTF("poweroff");
				dOut.flush();
				dOut.close();
				cSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		};

		runtime.launch();

	}

}
