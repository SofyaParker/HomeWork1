package ru.SofyaParker.console;

import ru.SofyaParker.command.CommandParser;
import ru.SofyaParker.command.UserCommand;
import ru.SofyaParker.dataHandler.Rate;
import ru.SofyaParker.dataHandler.RatesCSVReader;
import ru.SofyaParker.forecasting.ForecastingMean;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleHandler {
    private final int TOMORROW_PERIOD = 1;
    private final int WEEK_PERIOD = 7;
    private static final Logger log = Logger.getLogger(RatesCSVReader.class.getName());

    public void runApp() {
        System.out.println("Введите команду (rate TRY/USD/EUR tomorrow/week); exit - выход");
        UserCommand userCommand = inputHandler();
        while (!userCommand.isEndFlag()) {
            if (userCommand.isSuccessFlag()) {
                ForecastingMean forecast = new ForecastingMean(
                        new RatesCSVReader(userCommand.getCurrency()).readCSV());
                if (userCommand.getForecastPeriod().equals("tomorrow")) {
                    forecast.predictPeriod(forecast.getLastDate().plusDays(TOMORROW_PERIOD));
                    outputHandler(forecast.getPredictionResult());
                } else if (userCommand.getForecastPeriod().equals("week")) {
                    forecast.predictPeriod(forecast.getLastDate().plusDays(WEEK_PERIOD));
                    outputHandler(forecast.getPredictionResult());
                }
                log.log(Level.FINE, "Команда выполнена.");
            }
            userCommand = inputHandler();
        }
    }

    private UserCommand inputHandler() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        return new CommandParser().parseCommand(input);
    }

    private void outputHandler(List<Rate> rateList) {
        for (Rate rate : rateList) {
            System.out.println(rate.toString());
        }
    }
}
