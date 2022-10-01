import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Currency cTry = new Currency("TRY", "TRY.csv");
        Currency cUsd = new Currency("USD", "USD.csv");
        Currency cEur = new Currency("EUR", "EUR.csv");
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Введите команду (rate TRY/USD/EUR tomorrow/week); exit - выход");
            String input = in.nextLine();
            if (input.matches("rate (TRY|USD|EUR) (tomorrow|week)")) {
                String[] arr = input.split(" ");
                String currency = arr[1];
                if (arr[2].equals("tomorrow")) {
                    switch (currency) {
                        case "TRY":
                            cTry.predictTomorrow(LocalDate.now());
                            break;
                        case "USD":
                            cUsd.predictTomorrow(LocalDate.now());
                            break;
                        case "EUR":
                            cEur.predictTomorrow(LocalDate.now());
                    }
                } else {
                    switch (currency) {
                        case "TRY":
                            cTry.predictWeek(LocalDate.now());
                            break;
                        case "USD":
                            cUsd.predictWeek(LocalDate.now());
                            break;
                        case "EUR":
                            cEur.predictWeek(LocalDate.of(2022,9,28));
                    }
                }
            }
            else {
                if (input.equals("exit")) {
                    break;
                }
                else {
                    System.out.println("Команда не найдена");
                }
            }
        }
    }
}
