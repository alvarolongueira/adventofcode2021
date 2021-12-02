package com.alvarolongueira.adventofcode.day2;

public class PositionMainApplication {

    private final static String filePath = "com/alvarolongueira/adventofcode/day2/input.txt";

    public static void main(String[] args) {
        PositionCalculatorService service = new PositionCalculatorService(filePath);

        int result = service.calculate();
        System.out.println("Position is " + result + " and multiply is ");

    }

}
