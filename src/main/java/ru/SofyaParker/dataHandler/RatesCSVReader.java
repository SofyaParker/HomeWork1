package ru.SofyaParker.dataHandler;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class RatesCSVReader {
    private final char SPLITTER = ';';
    private final char QUOTE_CHAR = '"';
    private final String filename;
    private static final Logger log = Logger.getLogger(RatesCSVReader.class.getName());

    public RatesCSVReader(String currency) {
        this.filename = "/csv/" + currency + ".csv";
    }

    public List<Rate> readCSV() {
        List<Rate> beans;
        try (InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream(filename))) {
            beans = new CsvToBeanBuilder<Rate>(reader)
                    .withType(Rate.class)
                    .withSeparator(SPLITTER)
                    .withQuoteChar(QUOTE_CHAR)
                    .withIgnoreEmptyLine(true)
                    .build()
                    .parse();
            beans = beans.stream()
                    .sorted(Comparator.comparing(Rate::getDate))
                    .collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            log.log(Level.SEVERE, "Файл <" + filename + "> не найден: ", e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.log(Level.SEVERE, "Файл <" + filename + "> недоступен: ", e);
            throw new RuntimeException(e);
        }
        return beans;
    }
}
