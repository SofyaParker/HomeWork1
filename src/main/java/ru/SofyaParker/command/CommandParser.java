package ru.SofyaParker.command;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandParser {
    private final String COMMAND_PATTERN = "rate (TRY|USD|EUR) (tomorrow|week)";
    private final String COMMAND_SPLITTER = " +";
    private final int FUNCTION_INDEX = 0;
    private final int CURRENCY_INDEX = 1;
    private final int PERIOD_INDEX = 2;
    private static Logger log = Logger.getLogger(CommandParser.class.getName());

    public UserCommand parseCommand(String commandLine) {
        UserCommand userCommand = new UserCommand();
        if (commandLine.equals("exit")) {
            log.log(Level.INFO, "Работа программы завершена.");
            userCommand.setEndFlag(true);
            userCommand.setSuccessFlag(true);
            return userCommand;
        } else if (commandLine.matches(COMMAND_PATTERN)) {
            String[] subcommandLine = commandLine.split(COMMAND_SPLITTER);
            userCommand.setCommand(subcommandLine[FUNCTION_INDEX]);
            userCommand.setCurrency(subcommandLine[CURRENCY_INDEX]);
            userCommand.setForecastPeriod(subcommandLine[PERIOD_INDEX]);
            userCommand.setEndFlag(false);
            userCommand.setSuccessFlag(true);
            log.log(Level.FINE, "Команда <" + commandLine + "> обработана.");
            return userCommand;
        } else {
            log.log(Level.WARNING, "Команда <" + commandLine + "> не найдена.");
            userCommand.setEndFlag(false);
            userCommand.setSuccessFlag(false);
            return  userCommand;
        }
    }
}
