package com.alvarolongueira.adventofcode.day8;

import java.util.List;

public class DisplayOutputMainApplication {

    private final static String filePath = "com/alvarolongueira/adventofcode/day8/input.txt";

    public static void main(String[] args) {
        DisplayOutputService service = new DisplayOutputService(filePath);

        long simpleDigitsAppear = service.calculate();
        System.out.println("Times 1,4,7,8 appears: " + simpleDigitsAppear);

        System.out.println("----------------");
        List<DisplayOutputResult> results = service.calculateComplex();
        long complexValue = DisplayOutputResult.sumValues(results);
        System.out.println("Complex value: " + complexValue);

    }
}
