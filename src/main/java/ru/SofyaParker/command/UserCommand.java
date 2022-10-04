package ru.SofyaParker.command;

public class UserCommand {
    private String command;
    private String currency;
    private String forecastPeriod;

    public String getCommand() {
        return command;
    }

    public String getCurrency() {
        return currency;
    }

    public String getForecastPeriod() {
        return forecastPeriod;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setForecastPeriod(String forecastPeriod) {
        this.forecastPeriod = forecastPeriod;
    }
}
