public class CommandParser {
    private final String COMMAND_PATTERN = "rate (TRY|USD|EUR) (tomorrow|week)";

    public UserCommand parseCommand(String commandLine) {
        if (commandLine.matches(COMMAND_PATTERN)) {
            UserCommand userCommand = new UserCommand();
            String[] subcommandLine = commandLine.split(" ");
            userCommand.setCommand(subcommandLine[0]);
            userCommand.setCurrency(subcommandLine[1]);
            userCommand.setForecastPeriod(subcommandLine[2]);
            return userCommand;
        }
        return null;
    }
}
