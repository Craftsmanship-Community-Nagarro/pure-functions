import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class rental_calculator_should {
    private final List<Rental> rentals = List.of(
            Rental.builder()
                    .amount(1089.90)
                    .date(LocalDate.of(2020, 10, 9))
                    .label("Le Refuge des Loups (LA BRESSE)")
                    .build(),
            Rental.builder()
                    .amount(1276.45)
                    .date(LocalDate.of(2020, 10, 12))
                    .label("Au pied de la Tour (NOUILLORC)")
                    .build(),
            Rental.builder()
                    .amount(670.89)
                    .date(LocalDate.of(2020, 10, 24))
                    .label("Le moulin du bonheur (GLANDAGE)")
                    .build());

    @Test
    public void throws_an_illegal_state_exception_when_no_rentals() {
        var calculator = new RentalCalculator(Collections.emptyList());
        assertThrows(IllegalStateException.class, calculator::calculateRental);
    }

    @Test
    public void calculate_rentals_and_format_statement() {
        var calculator = new RentalCalculator(rentals);
        var statement = calculator.calculateRental();

        assertTrue(calculator.isCalculated());
        assertEquals(3037.24, calculator.getAmount(), 0.01);
        assertEquals("2020-10-09 : Le Refuge des Loups (LA BRESSE) | 1089.900000 \n" +
                "2020-10-12 : Au pied de la Tour (NOUILLORC) | 1276.450000 \n" +
                "2020-10-24 : Le moulin du bonheur (GLANDAGE) | 670.890000 \n" +
                "Total amount | 3037.240000", statement);
    }
}