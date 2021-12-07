package com.alvarolongueira.adventofcode.day7;

import java.rmi.UnexpectedException;

import org.junit.Assert;
import org.junit.Test;

public class CrabFuelMainApplicationTest {

    private static final String PATH = "com/alvarolongueira/adventofcode/day7/";

    @Test
    public void readFileWebExample() throws UnexpectedException {
        CrabFuelService service = new CrabFuelService(PATH + "inputTestWebExample.txt");

        long result = service.calculate();
        Assert.assertEquals(37, result);

    }

    @Test
    public void readFileWebExamplePartTwo() throws UnexpectedException {
        CrabFuelService service = new CrabFuelService(PATH + "inputTestWebExample.txt");

        long result = service.calculate();
        Assert.assertEquals(37, result);

        result = service.calculate(true);
        Assert.assertEquals(168, result);
    }

}
