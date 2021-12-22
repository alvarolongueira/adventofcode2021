package com.alvarolongueira.adventofcode.day15;

import org.junit.Assert;
import org.junit.Test;

public class CaveRiskMainApplicationTest {

    private static final String PATH = "com/alvarolongueira/adventofcode/day15/";

    @Test
    public void readFileWebExample() {
        CaveRiskService service = new CaveRiskService(PATH + "inputTestWebExample.txt");

        int result = service.calculate();

        Assert.assertEquals(40, result);
    }

    @Test
    public void readFileWebExamplePartTwo() {
        CaveRiskService service = new CaveRiskService(PATH + "inputTestWebExample.txt", true);

        int result = service.calculate();

        Assert.assertEquals(315, result);
    }
}
