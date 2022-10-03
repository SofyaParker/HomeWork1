import java.time.LocalDate;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class ConsoleHandler {
    private final String DATE_PATTERN = "dd.MM.yyyy";

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
            System.out.println("Команда не найдена");
            throw new RuntimeException(e);
        }
    }
}
