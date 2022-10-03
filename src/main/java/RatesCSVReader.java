import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.CsvToBean;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RatesCSVReader {
    private final String DATE_PATTERN = "M/d/y";
    private final String SPLITTER = ";";

    public Map<LocalDate, Double> ReadCSV(String filename) {
        Map<LocalDate, Double> ratesMap = new HashMap<>();
        try {
            BufferedReader csvReader = new BufferedReader(new InputStreamReader(
                    getClass().getResourceAsStream(filename)));
            String row = csvReader.readLine();
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(SPLITTER);
                ratesMap.put(LocalDate.parse(data[1], DateTimeFormatter.ofPattern(DATE_PATTERN)),
                        Double.parseDouble(data[2]) / Integer.parseInt(data[0]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Файл пуст");
            throw new RuntimeException(e);
        }
        return ratesMap;
    }
}
