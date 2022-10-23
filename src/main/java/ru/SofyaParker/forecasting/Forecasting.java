package ru.SofyaParker.forecasting;

import java.time.LocalDate;

public interface Forecasting {
    /**
     * The method calculate currency rate in a specific day using mean of last seven rates.
     * @param date The day for which the forecast is required
     * @return Currency forecast for the specified day
     */
    Double predict(LocalDate date);

    /**
     * The method forecast currency rate up to toDate
     * @param toDate The day until which forecast is required
     */
    void predictPeriod(LocalDate toDate);

}
