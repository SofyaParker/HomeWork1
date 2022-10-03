import java.time.LocalDate;

public class Rate {
    private LocalDate date;
    private String currency;
    private double rate;

    public Rate(int nominal, LocalDate date, String currency, double rate) {
        this.date = date;
        this.currency = currency;
        this.rate = rate / nominal;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCurrency() {
        return currency;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
