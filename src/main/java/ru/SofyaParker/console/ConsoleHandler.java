package ru.SofyaParker.console;

import ru.SofyaParker.command.CommandParser;
import ru.SofyaParker.command.UserCommand;
import ru.SofyaParker.dataHandler.RatesCSVReader;
import ru.SofyaParker.forecasting.ForecastingMean;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleHandler {
    private static Logger log = Logger.getLogger(RatesCSVReader.class.getName());

    public void RunApp() {
        System.out.println("Введите команду (rate TRY/USD/EUR tomorrow/week)");
        InputHandler();
    }

    private void InputHandler() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        UserCommand userCommand = new CommandParser().parseCommand(input);
        try {
            ForecastingMean forecast = new ForecastingMean(
                    new RatesCSVReader().ReadCSV("/csv/" + userCommand.getCurrency() + ".csv"));
            if (userCommand.getForecastPeriod().equals("tomorrow")) {
                forecast.predictTomorrow(LocalDate.now());
            } else if (userCommand.getForecastPeriod().equals("week")) {
                forecast.predictWeek(LocalDate.now());
            }
        } catch (NullPointerException e) {
            log.log(Level.SEVERE, "Команда не найдена: ", e);
            throw new RuntimeException(e);
        }
    }
}
