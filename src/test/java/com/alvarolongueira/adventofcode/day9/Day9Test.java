package com.alvarolongueira.adventofcode.day9;

import org.junit.Assert;
import org.junit.Test;

public class Day9Test {

    private static final String PATH = "com/alvarolongueira/adventofcode/day9/";

    @Test
    public void readFileWebExample() {
        LowPointPositionService service = new LowPointPositionService(PATH + "inputTestWebExample.txt");

        long result = service.calculate();
        Assert.assertEquals(15, result);
    }

    @Test
    public void readFileWebExamplePartTwo() {
        LowPointPositionService service = new LowPointPositionService(PATH + "inputTestWebExample.txt");

        long result = service.calculate();
        Assert.assertEquals(15, result);

        long resultBasins = service.calculateBasins();
        Assert.assertEquals(1134, resultBasins);
    }

}
