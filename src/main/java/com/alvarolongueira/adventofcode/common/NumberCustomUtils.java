package com.alvarolongueira.adventofcode.common;

import java.util.Collections;
import java.util.List;

import com.google.common.base.Strings;

public class NumberCustomUtils {

    public static long convertBinaryToDecimal(String binaryNumber) {

        List<String> list = ListCustomUtils.split(binaryNumber, "");
        Collections.reverse(list);

        long i = 0;
        long square = 0;
        for (String current : list) {
            if (current.equals("1")) {
                square += (long) Math.pow(2, i);
            }
            i++;

        }
        return square;
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

    public static String convertDecimalToBinary(long decimal) {
        return Long.toBinaryString(decimal);
    }

    public static String convertHexToDecimalWithPad(String hexadecimal, int padLength) {
        return padZeros(String.valueOf(convertHexToDecimal(hexadecimal)), padLength);
    }

    public static long convertHexToDecimal(String hexadecimal) {
        return Long.parseLong(hexadecimal, 16);
    }

    public static String convertHexToBinaryWithPad(String hexadecimal, int padLength) {
        return padZeros(convertDecimalToBinary(convertHexToDecimal(hexadecimal)), padLength);
    }

    public static String convertHexToBinary(String hexadecimal) {
        return convertDecimalToBinary(convertHexToDecimal(hexadecimal));
    }

    public static String padZeros(String chain, int length) {
        return Strings.padStart(chain, length, '0');
    }
}
