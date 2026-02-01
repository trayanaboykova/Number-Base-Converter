package converter;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter two numbers in format: {source base} {target base} (To quit type /exit) ");
            String line = scanner.nextLine().trim();

            if (line.equals("/exit")) {
                break;
            }

            String[] parts = line.split("\\s+");
            int sourceBase = Integer.parseInt(parts[0]);
            int targetBase = Integer.parseInt(parts[1]);

            while (true) {
                System.out.print("Enter number in base " + sourceBase + " to convert to base " + targetBase
                        + " (To go back type /back) ");
                String number = scanner.nextLine().trim();

                if (number.equals("/back")) {
                    System.out.println();
                    break;
                }

                BigInteger decimal = new BigInteger(number, sourceBase);
                String result = decimal.toString(targetBase);

                System.out.println("Conversion result: " + result);
                System.out.println();
            }
        }
    }
}
