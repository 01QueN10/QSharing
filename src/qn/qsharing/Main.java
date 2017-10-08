package qn.qsharing;

import java.io.File;
import java.io.IOException;

import qn.qsharing.gui.IndexGUI;

public class Main {
	
	public static final String NAME = "QSharing 1.0 UNSTABLE";
	
	public static void main(String[] args) {
		boolean isConsole = false;
		for (int i = 0; i < args.length; i++) {
			if (args[i].equalsIgnoreCase("/c") || args[i].equalsIgnoreCase("-c")) {
				isConsole = true;
			}
		}
		if (!isConsole) {
			IndexGUI gui = new IndexGUI();
			gui.launchGUI(args);
		} else {
			byte[] input = new byte[100];
			try {
				System.in.read(input);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String rawCommand = new String(input).trim();
			String[] command = rawCommand.split(" ");
			switch (command[0]) {
			case "send":
				try {
					Sender sender = new Sender();
					sender.file = new File(command[1]);
					sender.run();
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("==== Incorrect command ====");
					System.out.println("Correct usage: send <file-directory>");
					Main.main(null);
				}
				break;
			case "get":
				try {
					Getter getter = new Getter();
					getter.ip = command[1];
					getter.run();
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("==== Incorrect command ====");
					System.out.println("Correct usage: get <ip>");
					Main.main(null);
				}
				break;
			case "exit":
				System.exit(0);
				break;
			default:
				System.out.println("==== Unknown command ====");
				System.out.println("send <file-directory>: Send data");
				System.out.println("get <ip>: Get data");
				System.out.println("exit: Exit QP2P");
				Main.main(null);
			}
		}
	}
}
