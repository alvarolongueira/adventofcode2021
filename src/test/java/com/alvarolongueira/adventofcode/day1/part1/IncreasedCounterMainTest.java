package com.alvarolongueira.adventofcode.day1.part1;

import org.junit.Assert;
import org.junit.Test;

import com.alvarolongueira.adventofcode.day1.part1.service.IncreasedCounterService;

public class IncreasedCounterMainTest {

    @Test
    public void readFileTestEmpty() {
        IncreasedCounterService service = new IncreasedCounterService("adventofcode/day1/part1/inputTest1.txt");
        int result = service.calculate();
        Assert.assertEquals(0, result);
    }

    @Test
    public void readFileTestOneUpOneDown() {
        IncreasedCounterService service = new IncreasedCounterService("adventofcode/day1/part1/inputTest2.txt");
        int result = service.calculate();
        Assert.assertEquals(1, result);
    }

    @Test
    public void readFileTestTwoUpsOneDown() {
        IncreasedCounterService service = new IncreasedCounterService("adventofcode/day1/part1/inputTest3.txt");
        int result = service.calculate();
        Assert.assertEquals(2, result);
    }

    @Test
    public void readFileTestFourUpOneEqualBetween() {
        IncreasedCounterService service = new IncreasedCounterService("adventofcode/day1/part1/inputTest4.txt");
        int result = service.calculate();
        Assert.assertEquals(4, result);
    }

    @Test
    public void readFileTestExample() {
        IncreasedCounterService service = new IncreasedCounterService("adventofcode/day1/part1/inputTest5.txt");
        int result = service.calculate();
        Assert.assertEquals(7, result);
    }
}
