package com.alvarolongueira.adventofcode.day3;

public class PowerConsumptionMainApplication {

    private final static String filePath = "com/alvarolongueira/adventofcode/day3/input.txt";

    public static void main(String[] args) {
        PowerConsumptionService service = new PowerConsumptionService(filePath);

        EnergyRate energy = service.calculateEnergy();
        System.out.println("Energy is " + energy.toString() + " and multiply is " + energy.multiply());

        LifeSupportRate lifeSupport = service.calculateLifeSupport();
        System.out.println("LifeSupport is " + lifeSupport.toString() + " and multiply is " + lifeSupport.multiply());

    }

}
