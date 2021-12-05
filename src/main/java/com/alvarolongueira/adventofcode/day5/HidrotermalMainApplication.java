package com.alvarolongueira.adventofcode.day5;

import java.rmi.UnexpectedException;

public class HidrotermalMainApplication {

    private final static String filePath = "com/alvarolongueira/adventofcode/day5/input.txt";

    public static void main(String[] args) throws UnexpectedException {
        HidrotermalService service = new HidrotermalService(filePath);

        long points = service.calculatePointsMoreThanTwo(false);
        System.out.println("Points overlap: " + points);

        System.out.println("----------------");

        long pointsDiagonalToo = service.calculatePointsMoreThanTwo(true);
        System.out.println("Points overlap: " + pointsDiagonalToo);

    }
}
