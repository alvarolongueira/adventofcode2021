package com.alvarolongueira.adventofcode.day9;

import org.junit.Assert;
import org.junit.Test;

public class LowPointLocationMainApplicationTest {

    private static final String PATH = "com/alvarolongueira/adventofcode/day9/";

    @Test
    public void readFileWebExample() {
        LowPointLocationService service = new LowPointLocationService(PATH + "inputTestWebExample.txt");

        long result = service.calculate();
        Assert.assertEquals(15, result);
    }

    @Test
    public void readFileWebExamplePartTwo() {
        LowPointLocationService service = new LowPointLocationService(PATH + "inputTestWebExample.txt");

        long result = service.calculate();
        Assert.assertEquals(15, result);

        long resultBasins = service.calculateBasins();
        Assert.assertEquals(1134, resultBasins);
    }

}
