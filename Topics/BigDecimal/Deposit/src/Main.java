import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BigDecimal startingAmount = new BigDecimal(scanner.nextLine());
        BigDecimal yearlyPercent = new BigDecimal(scanner.nextLine());
        int years = Integer.parseInt(scanner.nextLine());

        BigDecimal hundred = new BigDecimal("100");
        BigDecimal rate = yearlyPercent.divide(hundred, 10, RoundingMode.HALF_UP);
        BigDecimal multiplier = BigDecimal.ONE.add(rate);

        BigDecimal finalAmount = startingAmount.multiply(
                multiplier.pow(years)
        ).setScale(2, RoundingMode.CEILING);

        System.out.println("Amount of money in the account: " + finalAmount);
    }
}
