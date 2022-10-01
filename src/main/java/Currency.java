import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Currency {
    private final String currency;
    private Map<LocalDate, Double> currencyMap;

    /**
     * Constructor that copies .csv file's content into object
     * @param currency Currency label
     * @param filename Name of .csv file with rates
     */
    public Currency(String currency, String filename) {
        this.currency = currency;
        Map<LocalDate, Double> currencyMap = new HashMap<>();
        try {
            BufferedReader csvReader = new BufferedReader(new InputStreamReader(
                    getClass().getResourceAsStream("/csv/"+filename)));
            String row = csvReader.readLine();
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(";");
                currencyMap.put(LocalDate.parse(data[1], DateTimeFormatter.ofPattern("M/d/y")),
                        Double.parseDouble(data[2]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Файл пуст");
            throw new RuntimeException(e);
        }
        this.currencyMap = currencyMap;
    }

    public Map<LocalDate, Double> getCurrencyMap() {
        return currencyMap;
    }

    public void updateCurrencyMap(LocalDate key, Double value) {
        if (this.getCurrencyMap().containsKey(key)) {
            this.currencyMap.replace(key, value);
        } else {
            this.currencyMap.put(key, value);
        }

    }

    /**
     * The method predict currency value for specific date
     * @param curDate Date for prediction
     * @return predicted currency value
     */
    private Double predict (LocalDate curDate) {
        Map<LocalDate, Double> shortMapRates = getLastSevenRates(curDate);
        double prediction = 0;
        for (Map.Entry<LocalDate, Double> map :
                shortMapRates.entrySet()) {
            if (map.getValue().equals(-1)) {
                predict(map.getKey());
            } else {
                prediction += map.getValue();
            }
        }
        prediction /= 7.0;
        return prediction;
    }
    public void predictTomorrow(LocalDate curDate) {
        Double prediction = this.predict(curDate.plusDays(1));
        updateCurrencyMap(curDate.plusDays(1), prediction);
        consoleOut(curDate.plusDays(1), prediction);
    }

    public void predictWeek(LocalDate curDate) {
        for (int i = 1; i < 8; i++) {
            Double prediction = this.predict(curDate.plusDays(i));
            updateCurrencyMap(curDate.plusDays(i), prediction);
            consoleOut(curDate.plusDays(i), prediction);
        }
    }

    /**
     * Get last seven rates
     *
     * @param date date before which finding seven rates
     * @return The Map with LocalDate as keys and Double as values
     */
    private Map<LocalDate, Double> getLastSevenRates(LocalDate date) {
        Map<LocalDate, Double> shortCurrencyMap = new HashMap<>();
        Map<LocalDate, Double> map = getCurrencyMap();
        int counter = 1;
        while (counter <= 7) {
            if (map.containsKey(date)) {
                shortCurrencyMap.put(date, map.get(date));
                counter++;
            }
            date = date.minusDays(1);
        }
        return shortCurrencyMap;
    }

    private static void consoleOut(LocalDate date, double rate) {
        String day = date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault());
        System.out.println(day.substring(0, 1).toUpperCase() + day.substring(1) +
                " " + date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
                " - " + String.format("%.2f", rate));
    }
}
