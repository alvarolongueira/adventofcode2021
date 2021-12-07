package com.alvarolongueira.adventofcode.day6;

import java.rmi.UnexpectedException;

public class LanternfishMainApplication {

    private final static String filePath = "com/alvarolongueira/adventofcode/day6/input.txt";

    public static void main(String[] args) throws UnexpectedException {
        LanternfishService service = new LanternfishService(filePath);

        long amountOfFishes = service.grow(80);
        System.out.println("Amount of lanternfish after 80 days: " + amountOfFishes);

        System.out.println("----------------");

        amountOfFishes = service.grow(256);
        System.out.println("Amount of lanternfish after 256 days: " + amountOfFishes);

    }
}
