package com.alvarolongueira.adventofcode.day2;

import com.alvarolongueira.adventofcode.day2.domain.SubmarinePosition;

public class PositionMainApplication {

    private final static String filePath = "com/alvarolongueira/adventofcode/day2/input.txt";

    public static void main(String[] args) {
        PositionCalculatorService service = new PositionCalculatorService(filePath);

        SubmarinePosition submarinePosition = new SubmarinePosition();
        submarinePosition = service.move(submarinePosition);
        System.out.println("Position is " + submarinePosition.toString() + " and multiply is " + submarinePosition.multiply());

    }

}
