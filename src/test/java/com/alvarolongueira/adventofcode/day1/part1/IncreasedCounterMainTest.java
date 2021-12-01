package com.alvarolongueira.adventofcode.day1.part1;

import org.junit.Assert;
import org.junit.Test;

import com.alvarolongueira.adventofcode.day1.part1.service.IncreasedCounterService;

public class IncreasedCounterMainTest {

    private static final String PATH = "com/alvarolongueira/adventofcode/day1/part1/";

    @Test
    public void readFileTestEmpty() {
        IncreasedCounterService service = new IncreasedCounterService(PATH + "inputTest1.txt");
        int result = service.calculate();
        Assert.assertEquals(0, result);
    }

    @Test
    public void readFileTestOneUpOneDown() {
        IncreasedCounterService service = new IncreasedCounterService(PATH + "inputTest2.txt");
        int result = service.calculate();
        Assert.assertEquals(1, result);
    }

    @Test
    public void readFileTestTwoUpsOneDown() {
        IncreasedCounterService service = new IncreasedCounterService(PATH + "inputTest3.txt");
        int result = service.calculate();
        Assert.assertEquals(2, result);
    }

    @Test
    public void readFileTestFourUpOneEqualBetween() {
        IncreasedCounterService service = new IncreasedCounterService(PATH + "inputTest4.txt");
        int result = service.calculate();
        Assert.assertEquals(4, result);
    }

    @Test
    public void readFileTestExample() {
        IncreasedCounterService service = new IncreasedCounterService(PATH + "inputTest5.txt");
        int result = service.calculate();
        Assert.assertEquals(7, result);
    }
}
