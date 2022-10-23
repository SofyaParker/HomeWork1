package ru.SofyaParker.forecasting;

import lombok.Getter;
import ru.SofyaParker.dataHandler.Rate;
import java.time.LocalDate;
import java.util.*;

public class ForecastingMean implements Forecasting {
    private final int ONE = 1;
    private final int ZERO = 0;
    private List<Rate> ratesList;
    @Getter
    private List<Rate> predictionResult = new ArrayList<>();
    private final int PREDICTION_DEPTH = 7;
    private final int DAYS_STEP = 1;
    @Getter
    private final LocalDate lastDate;
    public ForecastingMean(List<Rate> ratesList) {
        this.ratesList = ratesList;
        this.lastDate = ratesList.get(ratesList.size()-1).getDate();
    }

    public void updateRatesList(LocalDate date, Double rate) {
        this.ratesList.add(new Rate(date, rate, ONE));
    }

    @Override
    public Double predict(LocalDate date) {
        List<Rate> shortRatesList = getLastSevenRates();
        Double prediction = Double.valueOf(ZERO);
        for (Rate rate : shortRatesList) {
            if (rate.getRate() == null) {
                this.predict(rate.getDate());
            }
            prediction += rate.getRate() / rate.getNominal();
        }
        prediction /= PREDICTION_DEPTH;
        return prediction;
    }
    @Override
    public void predictPeriod(LocalDate toDate) {
        List<Rate> resultList = new ArrayList<>();
        LocalDate date = this.getLastDate().plusDays(DAYS_STEP);
        while (!date.isAfter(toDate)) {
            Double prediction = this.predict(date);
            updateRatesList(date, prediction);
            resultList.add(new Rate(date, prediction, ONE));
            date = date.plusDays(DAYS_STEP);
        }
        predictionResult = resultList;
    }

    /**
     * Search for the last seven currency rates based on date
     * @return The List of rates
     */
    private List<Rate> getLastSevenRates() {
        List<Rate> shortRateList = new ArrayList<>();
        for (int i = 0; i < PREDICTION_DEPTH; i++) {
            shortRateList.add(ratesList.get(ratesList.size() - i-1));
        }
        return shortRateList;
    }


}
