package com.alvarolongueira.adventofcode.day2;

import com.alvarolongueira.adventofcode.day2.domain.SubmarinePosition;

public class PositionMainApplication {

    private final static String filePath = "com/alvarolongueira/adventofcode/day2/input.txt";

    public static void main(String[] args) {
        PositionCalculatorService service = new PositionCalculatorService(filePath);

        SubmarinePosition first = SubmarinePosition.of();
        first = service.move(first);
        System.out.println("Position is " + first.toString() + " and multiply is " + first.multiply());

        SubmarinePosition second = SubmarinePosition.ofWithAim();
        second = service.move(second);
        System.out.println("Position is " + second.toString() + " and multiply is " + second.multiply());

    }

}
