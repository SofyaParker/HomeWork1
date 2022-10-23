import org.junit.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MainTest {

    @Test
    public void tomorrowPrediction() {
//        Map<LocalDate, Double> ratesEUR = new HashMap<>();
//        ratesEUR.put(LocalDate.of(2022, 9, 28), 55.9953);
//        ratesEUR.put(LocalDate.of(2022, 9, 27), 55.9278);
//        ratesEUR.put(LocalDate.of(2022, 9, 24), 56.4751);
//        ratesEUR.put(LocalDate.of(2022, 9, 23), 58.9388);
//        ratesEUR.put(LocalDate.of(2022, 9, 22), 60.2110);
//        ratesEUR.put(LocalDate.of(2022, 9, 21), 60.0507);
//        ratesEUR.put(LocalDate.of(2022, 9, 20), 60.0426);
//        double testPrediction = 0;
//        LocalDate testDate = LocalDate.of(2022,9,29);
//        for (Map.Entry<LocalDate,Double> rate :
//             ratesEUR.entrySet()) {
//            testPrediction += rate.getValue();
//        }
//        testPrediction /= 7.0;

//        Currency result = new Currency("EUR", "EUR.csv");
//        result.predictTomorrow(testDate.minusDays(1));

//        assertThat(testPrediction).isEqualTo(result.getCurrencyMap().get(testDate));
    }

    @Test
    public void weekPrediction() {
//        Map<LocalDate, Double> ratesEUR = new HashMap<>();
//        ratesEUR.put(LocalDate.of(2022, 9, 28), 55.9953);
//        ratesEUR.put(LocalDate.of(2022, 9, 27), 55.9278);
//        ratesEUR.put(LocalDate.of(2022, 9, 24), 56.4751);
//        ratesEUR.put(LocalDate.of(2022, 9, 23), 58.9388);
//        ratesEUR.put(LocalDate.of(2022, 9, 22), 60.2110);
//        ratesEUR.put(LocalDate.of(2022, 9, 21), 60.0507);
//        ratesEUR.put(LocalDate.of(2022, 9, 20), 60.0426);

//        Currency result = new Currency("EUR", "EUR.csv");
//        result.predictWeek(LocalDate.of(2022,9,28));

//        //1. 2022-09-29
//        double testPrediction = 0;
//        LocalDate testDate = LocalDate.of(2022,9,29);
//        for (Map.Entry<LocalDate,Double> rate :
//                ratesEUR.entrySet()) {
//            testPrediction += rate.getValue();
//        }
//        testPrediction /= 7.0;
//        ratesEUR.put(testDate, testPrediction);
//        ratesEUR.remove(LocalDate.of(2022, 9, 20));
//        assertThat(testPrediction).isEqualTo(result.getCurrencyMap().get(testDate));

//        //2. 2022-09-30
//        testPrediction = 0;
//        testDate = LocalDate.of(2022,9,30);
//        for (Map.Entry<LocalDate,Double> rate :
//                ratesEUR.entrySet()) {
//            testPrediction += rate.getValue();
//        }
//        testPrediction /= 7.0;
//        ratesEUR.put(testDate, testPrediction);
//        ratesEUR.remove(LocalDate.of(2022, 9, 21));
//        assertThat(testPrediction).isEqualTo(result.getCurrencyMap().get(testDate));

//        //3. 2022-10-01
//        testPrediction = 0;
//        testDate = LocalDate.of(2022,10,1);
//        for (Map.Entry<LocalDate,Double> rate :
//                ratesEUR.entrySet()) {
//            testPrediction += rate.getValue();
//        }
//        testPrediction /= 7.0;
//        ratesEUR.put(testDate, testPrediction);
//        ratesEUR.remove(LocalDate.of(2022, 9, 22));
//        assertThat(testPrediction).isEqualTo(result.getCurrencyMap().get(testDate));

//        //4. 2022-10-02
//        testPrediction = 0;
//        testDate = LocalDate.of(2022,10,2);
//        for (Map.Entry<LocalDate,Double> rate :
//                ratesEUR.entrySet()) {
//            testPrediction += rate.getValue();
//        }
//        testPrediction /= 7.0;
//        ratesEUR.put(testDate, testPrediction);
//        ratesEUR.remove(LocalDate.of(2022, 9, 23));
//        assertThat(testPrediction).isEqualTo(result.getCurrencyMap().get(testDate));

//        //5. 2022-10-03
//        testPrediction = 0;
//        testDate = LocalDate.of(2022,10,3);
//        for (Map.Entry<LocalDate,Double> rate :
//                ratesEUR.entrySet()) {
//            testPrediction += rate.getValue();
//        }
//        testPrediction /= 7.0;
//        ratesEUR.put(testDate, testPrediction);
//        ratesEUR.remove(LocalDate.of(2022, 9, 24));
//        assertThat(testPrediction).isEqualTo(result.getCurrencyMap().get(testDate));

//        //6. 2022-10-04
//        testPrediction = 0;
//        testDate = LocalDate.of(2022,10,4);
//        for (Map.Entry<LocalDate,Double> rate :
//                ratesEUR.entrySet()) {
//            testPrediction += rate.getValue();
//        }
//        testPrediction /= 7.0;
//        ratesEUR.put(testDate, testPrediction);
//        ratesEUR.remove(LocalDate.of(2022, 9, 27));
//        assertThat(testPrediction).isEqualTo(result.getCurrencyMap().get(testDate));

//        //7. 2022-10-05
//        testPrediction = 0;
//        testDate = LocalDate.of(2022,10,5);
//        for (Map.Entry<LocalDate,Double> rate :
//                ratesEUR.entrySet()) {
//            testPrediction += rate.getValue();
//        }
//        testPrediction /= 7.0;
//        ratesEUR.put(testDate, testPrediction);
//        ratesEUR.remove(LocalDate.of(2022, 9, 28));
//        assertThat(testPrediction).isEqualTo(result.getCurrencyMap().get(testDate));

    }
}
