import java.time.LocalDate;
import java.util.Map;

public interface Forecasting {

    Double predict(LocalDate date);

    void predictTomorrow(LocalDate currentDate);

    void predictWeek(LocalDate currentDate);

}
