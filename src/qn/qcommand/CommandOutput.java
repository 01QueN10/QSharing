package qn.qcommand;

public final class CommandOutput {
	public String[] args;
	public String command;
	
	public CommandOutput(String[] rawCommands) {
		command = rawCommands[0];
		for (int i = 0; i < args.length; i++) {
			args[i] = rawCommands[i + 1];
		}
	}
	
	public String[] toStringArray() {
		String[] array = new String[args.length + 1];
		array[0] = command;
		for (int i = 0; i < args.length; i++) {
			array[i + 1] = args[i];
		}
		return array;
	}

	public String[] getArgs() {
		return args;
	}

	public String getCommand() {
		return command;
	}
}
