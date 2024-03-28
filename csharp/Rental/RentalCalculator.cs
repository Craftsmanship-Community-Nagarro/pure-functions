using System.Globalization;
using System.Text;

namespace Rental
{
    public class RentalCalculator
    {
        public double Calculate(IEnumerable<Rental> rentals)
        {
            CheckForRentals(rentals);

            return rentals.Sum(x => x.Amount);
        }

        public string Format(IEnumerable<Rental> rentals)
        {
            CheckForRentals(rentals);

            var result = new StringBuilder();

            result.Append(string.Join(Environment.NewLine, rentals.Select(FormatLine)));

            result.Append($"{Environment.NewLine}Total amount | {Calculate(rentals).ToString(CultureInfo.InvariantCulture)}");

            return result.ToString();
        }

        private static void CheckForRentals(IEnumerable<Rental> rentals)
        {
            if (!rentals.Any())
            {
                throw new InvalidOperationException("No rentals on which perform calculation");
            }
        }

        private string FormatLine(Rental rental)
            => $"{rental.Date.ToString("dd-MM-yyyy")} : {rental.Label} | {rental.Amount.ToString(CultureInfo.InvariantCulture)}";
    }
}
