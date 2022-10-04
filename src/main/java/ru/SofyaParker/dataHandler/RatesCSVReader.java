package ru.SofyaParker.dataHandler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RatesCSVReader {
    private final String DATE_PATTERN = "M/d/y";
    private final String SPLITTER = ";";
    private int dateColumn;
    private int rateColumn;
    private int nominalColumn;
    private static Logger log = Logger.getLogger(RatesCSVReader.class.getName());

    public Map<LocalDate, Double> ReadCSV(String filename) {
        Map<LocalDate, Double> ratesMap = new HashMap<>();
        try {
            BufferedReader csvReader = new BufferedReader(new InputStreamReader(
                    getClass().getResourceAsStream(filename)));
            String row = csvReader.readLine().replaceAll("\"", "");
            String[] data = row.split(SPLITTER);
            this.nominalColumn = getIndexOf("nominal", data);
            this.dateColumn = getIndexOf("data", data);
            this.rateColumn = getIndexOf("curs", data);

            if (nominalColumn == -1 || dateColumn == -1 || rateColumn == -1) {
                log.log(Level.SEVERE, "Данные не найдены в файле " + filename);
                throw new RuntimeException();
            }

            while ((row = csvReader.readLine()) != null) {
                data = row.split(SPLITTER);
                ratesMap.put(LocalDate.parse(data[dateColumn], DateTimeFormatter.ofPattern(DATE_PATTERN)),
                        Double.parseDouble(data[rateColumn]) /
                                Integer.parseInt(data[nominalColumn].replaceAll(",", "")));
            }
        } catch (FileNotFoundException e) {
            log.log(Level.SEVERE, "Файл не найден: ", e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.log(Level.SEVERE, "Файл пуст: ", e);
            throw new RuntimeException(e);
        }
        return ratesMap;
    }

    private static int getIndexOf(String requiredColumn, String[] data) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(requiredColumn)) {
                return i;
            }
        }
        return -1;
    }
}
