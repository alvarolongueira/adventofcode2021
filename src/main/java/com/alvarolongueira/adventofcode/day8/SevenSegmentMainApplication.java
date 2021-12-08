package com.alvarolongueira.adventofcode.day8;

import java.rmi.UnexpectedException;
import java.util.List;

public class SevenSegmentMainApplication {

    private final static String filePath = "com/alvarolongueira/adventofcode/day8/input.txt";

    public static void main(String[] args) throws UnexpectedException {
        SevenSegmentService service = new SevenSegmentService(filePath);

        long simpleDigitsAppear = service.calculate();
        System.out.println("Times 1,4,7,8 appears: " + simpleDigitsAppear);

        System.out.println("----------------");
        List<DisplayOutputResult> results = service.calculateComplex();
        long complexValue = DisplayOutputResult.sumValues(results);
        System.out.println("Complex value: " + complexValue);

    }
}
