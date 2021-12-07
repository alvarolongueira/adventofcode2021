package com.alvarolongueira.adventofcode.day7;

import java.rmi.UnexpectedException;

public class CrabFuelMainApplication {

    private final static String filePath = "com/alvarolongueira/adventofcode/day7/input.txt";

    public static void main(String[] args) throws UnexpectedException {
        CrabFuelService service = new CrabFuelService(filePath);

        long minFuelNeeded = service.calculate();
        System.out.println("Min fuel needed: " + minFuelNeeded);

        System.out.println("----------------");

        minFuelNeeded = service.calculate(true);
        System.out.println("Min fuel needed with extra cost : " + minFuelNeeded);

    }
}
