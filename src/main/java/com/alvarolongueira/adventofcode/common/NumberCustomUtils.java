package com.alvarolongueira.adventofcode.common;

public class NumberCustomUtils {

    public static long convertBinaryToDecimal(String binaryNumber) {
        return convertBinaryToDecimal(Long.valueOf(binaryNumber));
    }

    public static long convertBinaryToDecimal(long binaryNumber) {

        long decimalNumber = 0;
        int base = 1;

        while (binaryNumber > 0) {
            long lastDigit = binaryNumber % 10;
            binaryNumber = binaryNumber / 10;
            decimalNumber += lastDigit * base;
            base = base * 2;
        }

        return decimalNumber;
    }
}
