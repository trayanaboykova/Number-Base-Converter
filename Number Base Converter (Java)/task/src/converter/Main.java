package converter;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    private static final String DIGITS = "0123456789abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter two numbers in format: {source base} {target base} (To quit type /exit) ");
            String line = scanner.nextLine().trim();
            if (line.equals("/exit")) break;

            String[] parts = line.split("\\s+");
            int sourceBase = Integer.parseInt(parts[0]);
            int targetBase = Integer.parseInt(parts[1]);

            while (true) {
                System.out.print("Enter number in base " + sourceBase + " to convert to base " + targetBase
                        + " (To go back type /back) ");
                String input = scanner.nextLine().trim().toLowerCase();

                if (input.equals("/back")) {
                    System.out.println();
                    break;
                }

                System.out.println("Conversion result: " + convert(input, sourceBase, targetBase));
                System.out.println();
            }
        }
    }

    private static String convert(String s, int sourceBase, int targetBase) {
        if (!s.contains(".")) {
            return new BigInteger(s, sourceBase).toString(targetBase);
        }

        String[] parts = s.split("\\.", -1);
        String intPart = parts[0].isEmpty() ? "0" : parts[0];
        String fracPart = parts.length > 1 ? parts[1] : "";

        // integer part
        BigInteger intDec = new BigInteger(intPart, sourceBase);
        String intOut = intDec.toString(targetBase);

        // build fraction as rational: fracNum / denom
        BigInteger fracNum = BigInteger.ZERO;
        BigInteger srcBaseBI = BigInteger.valueOf(sourceBase);
        for (int i = 0; i < fracPart.length(); i++) {
            int v = DIGITS.indexOf(fracPart.charAt(i));
            fracNum = fracNum.multiply(srcBaseBI).add(BigInteger.valueOf(v));
        }
        BigInteger denom = fracPart.isEmpty() ? BigInteger.ONE : srcBaseBI.pow(fracPart.length());

        // generate 6 digits in target base (5 + 1 for rounding)
        BigInteger tgtBaseBI = BigInteger.valueOf(targetBase);
        int[] digits = new int[6];

        BigInteger num = fracNum;
        for (int i = 0; i < 6; i++) {
            num = num.multiply(tgtBaseBI);
            BigInteger[] divRem = num.divideAndRemainder(denom);
            digits[i] = divRem[0].intValue();
            num = divRem[1];
        }

        // round based on 6th digit
        if (digits[5] >= targetBase / 2.0) {
            for (int i = 4; i >= 0; i--) {
                digits[i]++;
                if (digits[i] < targetBase) break;
                digits[i] = 0;
                if (i == 0) {
                    // carry into integer part
                    intDec = intDec.add(BigInteger.ONE);
                    intOut = intDec.toString(targetBase);
                }
            }
        }

        StringBuilder fracOut = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            fracOut.append(DIGITS.charAt(digits[i]));
        }

        return intOut + "." + fracOut;
    }
}
