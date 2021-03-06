package com.alvarolongueira.adventofcode.day3;

import org.junit.Assert;
import org.junit.Test;

public class Day3Test {

    private static final String PATH = "com/alvarolongueira/adventofcode/day3/";

    @Test
    public void readFileTestEmpty() {
        EnergyRate expectedEnergy = new EnergyRate();
        LifeSupportRate expectedLifeSupport = new LifeSupportRate();

        PowerConsumptionService service = new PowerConsumptionService(PATH + "inputTest1.txt");

        EnergyRate resultEnergy = service.calculateEnergy();
        Assert.assertTrue(this.sameValues(expectedEnergy, resultEnergy));
        Assert.assertEquals(0, resultEnergy.multiply());

        LifeSupportRate resultLifeSupport = service.calculateLifeSupport();
        Assert.assertTrue(this.sameValues(expectedLifeSupport, resultLifeSupport));
        Assert.assertEquals(0, resultLifeSupport.multiply());
    }

    @Test
    public void readFileWebExample() {
        EnergyRate expectedEnergy = new EnergyRate("10110", "01001", 22, 9);
        LifeSupportRate expectedLifeSupport = new LifeSupportRate("10111", "01010", 23, 10);

        PowerConsumptionService service = new PowerConsumptionService(PATH + "inputTestWebExample.txt");

        EnergyRate resultEnergy = service.calculateEnergy();
        Assert.assertTrue(this.sameValues(expectedEnergy, resultEnergy));
        Assert.assertEquals(198, resultEnergy.multiply());
    }

    @Test
    public void readFileWebExamplePartTwo() {
        EnergyRate expectedEnergy = new EnergyRate("10110", "01001", 22, 9);
        LifeSupportRate expectedLifeSupport = new LifeSupportRate("10111", "01010", 23, 10);

        PowerConsumptionService service = new PowerConsumptionService(PATH + "inputTestWebExample.txt");

        EnergyRate resultEnergy = service.calculateEnergy();
        Assert.assertTrue(this.sameValues(expectedEnergy, resultEnergy));
        Assert.assertEquals(198, resultEnergy.multiply());

        LifeSupportRate resultLifeSupport = service.calculateLifeSupport();
        Assert.assertTrue(this.sameValues(expectedLifeSupport, resultLifeSupport));
        Assert.assertEquals(230, resultLifeSupport.multiply());
    }

    private boolean sameValues(EnergyRate expected, EnergyRate result) {
        boolean isTheSame = true;

        if (!expected.getGammaRate().equals(result.getGammaRate())) {
            isTheSame = false;
        }
        if (expected.getGammaRateDecimal() != result.getGammaRateDecimal()) {
            isTheSame = false;
        }
        if (!expected.getEpsilonRate().equals(result.getEpsilonRate())) {
            isTheSame = false;
        }
        if (expected.getEpsilonRateDecimal() != result.getEpsilonRateDecimal()) {
            isTheSame = false;
        }
        if (!isTheSame) {
            System.err.println("Expected : " + expected);
            System.err.println("Result : " + result);
        }
        return isTheSame;
    }

    private boolean sameValues(LifeSupportRate expected, LifeSupportRate result) {
        boolean isTheSame = true;

        if (!expected.getOxygenRate().equals(result.getOxygenRate())) {
            isTheSame = false;
        }
        if (expected.getOxygenRateDecimal() != result.getOxygenRateDecimal()) {
            isTheSame = false;
        }
        if (!expected.getCoscrubberRate().equals(result.getCoscrubberRate())) {
            isTheSame = false;
        }
        if (expected.getCoscrubberRateDecimal() != result.getCoscrubberRateDecimal()) {
            isTheSame = false;
        }
        if (!isTheSame) {
            System.err.println("Expected : " + expected);
            System.err.println("Result : " + result);
        }
        return isTheSame;
    }

}
