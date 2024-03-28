import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Builder
public class Rental {
    private final LocalDate date;
    private final String label;
    private final double amount;
}