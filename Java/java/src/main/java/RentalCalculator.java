import lombok.Getter;

import java.util.List;

@Getter
public class RentalCalculator {
    private double amount;
    private boolean calculated;

    private final List<Rental> rentals;

    public RentalCalculator(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public String calculateRental() {
        if (rentals.isEmpty()) {
            throw new IllegalStateException("No rentals !!!");
        }

        var result = new StringBuilder();

        for (var rental : rentals) {
            if (!calculated)
                this.amount += rental.getAmount();

            result.append(formatLine(rental, amount));
        }
        result.append(String.format("Total amount | %f", this.amount));
        calculated = true;

        return result.toString();
    }

    private String formatLine(Rental rental, double amount) {
        return String.format("%tF : %s | %f \n",
                rental.getDate(),
                rental.getLabel(),
                rental.getAmount());
    }
}