package com.alvarolongueira.adventofcode.day11;

public class FlashSpreadMainApplication {

    private final static String filePath = "com/alvarolongueira/adventofcode/day11/input.txt";

    public static void main(String[] args) {
        FlashSpreadService service = new FlashSpreadService(filePath);

        long flashes = service.calculate(100);
        System.out.println("Flashes after 100 steps: " + flashes);

        System.out.println("----------------");

        int firstTimeAllFlashes = service.firstFlash();
        System.out.println("Flashes all together step: " + firstTimeAllFlashes);

    }
}
