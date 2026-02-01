package converter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print(
                    "Do you want to convert /from decimal or /to decimal? (To quit type /exit) "
            );
            String command = scanner.nextLine();

            if (command.equals("/exit")) {
                break;
            }

            switch (command) {
                case "/from":
                    System.out.print("Enter number in decimal system: ");
                    int decimalNumber = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter target base: ");
                    int targetBase = Integer.parseInt(scanner.nextLine());

                    String converted = Integer.toString(decimalNumber, targetBase);
                    if (targetBase == 16) {
                        converted = converted.toLowerCase(); // matches examples
                    }

                    System.out.println("Conversion result: " + converted);
                    break;

                case "/to":
                    System.out.print("Enter source number: ");
                    String sourceNumber = scanner.nextLine();

                    System.out.print("Enter source base: ");
                    int sourceBase = Integer.parseInt(scanner.nextLine());

                    int result = Integer.parseInt(sourceNumber, sourceBase);
                    System.out.println("Conversion to decimal result: " + result);
                    break;

                default:
                    // invalid commands are ignored per task description
                    break;
            }
        }
    }
}
