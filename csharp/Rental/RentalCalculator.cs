using System.Globalization;
using System.Text;

namespace Rental
{
    public class RentalCalculator
    {
        private readonly IEnumerable<Rental> _rentals;
        public double Amount { get; private set; }

        public RentalCalculator(IEnumerable<Rental> rentals) => _rentals = rentals;

        public string Calculate()
        {
            if (!_rentals.Any())
            {
                throw new InvalidOperationException("No rentals on which perform calculation");
            }

            var result = new StringBuilder();

            foreach (var rental in _rentals)
            {
                Amount += rental.Amount;

                result.Append(FormatLine(rental, rental.Amount));
            }

            result.Append($"Total amount | {Amount.ToString(CultureInfo.InvariantCulture)}");

            return result.ToString();
        }

        private string FormatLine(Rental rental, double amount)
            => $"{rental.Date.ToString("dd-MM-yyyy")} : {rental.Label} | {amount.ToString(CultureInfo.InvariantCulture)}{Environment.NewLine}";
    }
}
