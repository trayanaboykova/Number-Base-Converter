import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String number = scanner.nextLine();
        int newScale = Integer.parseInt(scanner.nextLine());

        BigDecimal value = new BigDecimal(number);
        BigDecimal rounded = value.setScale(newScale, RoundingMode.HALF_DOWN);

        System.out.println(rounded);
    }
}
