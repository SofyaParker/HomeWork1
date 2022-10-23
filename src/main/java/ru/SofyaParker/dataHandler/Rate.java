package ru.SofyaParker.dataHandler;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Getter
public class Rate {
    private final String OUTPUT_DATE_PATTERN = "E dd.MM.yyyy";
    private final String INPUT_DATE_PATTERN = "M/d/y";
    private final Integer ONE = 1;
    @CsvBindByName(column = "nominal")
    private Integer nominal;
    @CsvDate(value = INPUT_DATE_PATTERN)
    @CsvBindByName(column = "data")
    private LocalDate date;
    @CsvBindByName(column = "curs")
    private Double rate;

    public Rate(LocalDate date, Double rate, Integer nominal) {
        this.date = date;
        this.rate = rate / nominal;
        this.nominal = ONE;
    }

    @Override
    public String toString(){
        return date.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_PATTERN)) +
                " - " + String.format("%.2f", rate);
    }
}
