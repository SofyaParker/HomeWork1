package ru.SofyaParker.forecasting;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ForecastingMean implements Forecasting {

    private Map<LocalDate, Double> ratesMap;

    private final int PREDICTION_DEPTH = 7;
    private final double NAN = Double.NaN;
    private final int DAYS_STEP = 1;

    public ForecastingMean(Map<LocalDate, Double> ratesMap) {
        this.ratesMap = ratesMap;
    }

    public Map<LocalDate, Double> getRatesMap() {
        return ratesMap;
    }

    public void updateRatesMap(LocalDate key, Double value) {
        this.ratesMap.put(key, value);
    }

    /**
     * The method calculate currency rate in a specific day using mean of last seven rates.
     * @param date The day for which the forecast is required
     * @return Currency forecast for the specified day
     */
    @Override
    public Double predict(LocalDate date) {
        Map<LocalDate, Double> shortRatesMap = getLastSevenRates(date);
        double prediction = 0;
        for (Map.Entry<LocalDate, Double> rate :
                shortRatesMap.entrySet()) {
            if (rate.getValue().equals(NAN)) {
                predict(rate.getKey());
            } else {
                prediction += rate.getValue();
            }
        }
        prediction /= PREDICTION_DEPTH;
        return prediction;
    }

    @Override
    public void predictTomorrow(LocalDate currentDate) {
        Double prediction = this.predict(currentDate.plusDays(DAYS_STEP));
        updateRatesMap(currentDate.plusDays(DAYS_STEP), prediction);
        consoleOut(currentDate.plusDays(DAYS_STEP), prediction);
    }

    @Override
    public void predictWeek(LocalDate currentDate) {
        for (int i = 1; i <= PREDICTION_DEPTH; i++) {
            Double prediction = this.predict(currentDate.plusDays(i));
            updateRatesMap(currentDate.plusDays(i), prediction);
            consoleOut(currentDate.plusDays(i), prediction);
        }
    }

    /**
     * Search for the last seven currency rates relative to a specific date
     * @param date date before which finding seven rates
     * @return The Map with LocalDate as keys and Double as values
     */
    private Map<LocalDate, Double> getLastSevenRates(LocalDate date) {
        Map<LocalDate, Double> shortCurrencyMap = new HashMap<>();
        int lastSevenRatesCounter = 0;
        while (lastSevenRatesCounter < PREDICTION_DEPTH) {
            Double rate = this.ratesMap.get(date);
            if (rate != null) {
                shortCurrencyMap.put(date, this.ratesMap.get(date));
                lastSevenRatesCounter++;
            }
            date = date.minusDays(DAYS_STEP);
        }
        return shortCurrencyMap;
    }

    private static void consoleOut(LocalDate date, double rate) {
        System.out.println(date.format(DateTimeFormatter.ofPattern("E dd.MM.yyyy")) +
                " - " + String.format("%.2f", rate));
    }
}
