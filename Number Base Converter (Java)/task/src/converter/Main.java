package converter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number in decimal system: ");
        int number = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter target base: ");
        int base = Integer.parseInt(scanner.nextLine());

        String result = Integer.toString(number, base);

        // Hyperskill usually expects uppercase for hex
        if (base == 16) {
            result = result.toUpperCase();
        }

        System.out.println("Conversion result: " + result);
    }
}
