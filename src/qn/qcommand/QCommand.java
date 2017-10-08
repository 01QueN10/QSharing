package qn.qcommand;

import java.io.IOException;
import java.util.ArrayList;

/**
 * QCommand is light-weight library that handles commands.
 * @author QN
 * @version 1.0 UNSTABLE
 *
 */
public class QCommand {
	/**
	 * Get input and parse it to CommandOutput value.
	 * @param maxLength Max length of byte array. If the value is too small, input will be cut.
	 * @return It will return CommandOutput.
	 */
	public static final CommandOutput getCommand(int maxLength) {
		byte[] input = new byte[maxLength];
		
		try {
			System.in.read(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String rawCommand = new String(input).trim();
		String[] rawCommands = rawCommand.split(" ");
		
		boolean isQuoted = false;
		ArrayList<String> args = new ArrayList<String> ();
		for (int i = 0; i < rawCommands.length; i++) {
			if (rawCommands[i].startsWith("\"")) {
				if (isQuoted) {
					args.set(args.size() - 1, args.get(args.size() - 1) + rawCommands[i]);
					isQuoted = false;
				} else {
					args.add(rawCommands[i]);
					isQuoted = true;
				}
			} else {
				if (isQuoted) {
					args.set(args.size() - 1, args.get(args.size() - 1) + rawCommands[i]);
				} else {
					args.add(rawCommands[i]);
				}
			}
		}
		
		String[] array = (String[]) args.toArray();
		return new CommandOutput(array);
	}

	/**
	 * Get input and parse it to CommandOutput. It can handle up to 100 bytes. If limit is too low, use 'getCommand(maxLength)' method.
	 * @return It will return CommandOutput value.
	 */
	public static final CommandOutput getCommand() {
		return getCommand(100);
	}

	/**
	 * Get input and parse it to string array.
	 * @param maxLength Max length of byte array. If the value is too small, input will be cut.
	 * @return It will return string array.
	 */
	public static final String[] getCommandStringArray(int maxLength) {
		return getCommand(maxLength).toStringArray();
	}

	/**
	 * Get input and parse it to string array. It can handle up to 100 bytes. If limit is too low, use 'getCommand(maxLength)' method.
	 * @return It will return string array.
	 */
	public static final String[] getCommandStringArray() {
		return getCommandStringArray(100);
	}
}