package com.alvarolongueira.adventofcode.day14;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class PolymerMainApplicationTest {

    private static final String PATH = "com/alvarolongueira/adventofcode/day14/";

    @Test
    public void readFileWebExampleOptionOne() {
        PolymerServiceOptionOne service = new PolymerServiceOptionOne(PATH + "inputTestWebExample.txt");

        Map<String, Long> mapResult = service.calculate(10);
        Assert.assertEquals(1749L, this.get(mapResult, "B"));
        Assert.assertEquals(298, this.get(mapResult, "C"));
        Assert.assertEquals(161, this.get(mapResult, "H"));
        Assert.assertEquals(865, this.get(mapResult, "N"));

        long result = service.calculateResult(mapResult);
        Assert.assertEquals(1588, result);
    }

    @Test
    public void readFileWebExampleOptionTwo() {
        PolymerServiceOptionTwo service = new PolymerServiceOptionTwo(PATH + "inputTestWebExample.txt");

        Map<String, Long> mapResult = service.calculate(10);
        Assert.assertEquals(1749L, this.get(mapResult, "B"));
        Assert.assertEquals(298, this.get(mapResult, "C"));
        Assert.assertEquals(161, this.get(mapResult, "H"));
        Assert.assertEquals(865, this.get(mapResult, "N"));

        long result = service.calculateResult(mapResult);
        Assert.assertEquals(1588, result);
    }

    @Test
    public void readFileWebExampleOptionThree() {
        PolymerServiceOptionThree service = new PolymerServiceOptionThree(PATH + "inputTestWebExample.txt");

        Map<String, Long> mapResult = service.calculate(10);
        Assert.assertEquals(1749L, this.get(mapResult, "B"));
        Assert.assertEquals(298, this.get(mapResult, "C"));
        Assert.assertEquals(161, this.get(mapResult, "H"));
        Assert.assertEquals(865, this.get(mapResult, "N"));

        long result = service.calculateResult(mapResult);
        Assert.assertEquals(1588, result);
    }

    @Test
    public void readFileWebExampleOptionThreePartTwo() {
        PolymerServiceOptionThree service = new PolymerServiceOptionThree(PATH + "inputTestWebExample.txt");

        Map<String, Long> mapResult = service.calculate(40);
        Assert.assertEquals(2192039569602L, this.get(mapResult, "B"));
        Assert.assertEquals(3849876073L, this.get(mapResult, "H"));

        long result = service.calculateResult(mapResult);
        Assert.assertEquals(2188189693529L, result);
    }

    private long get(Map<String, Long> map, String key) {
        return map.get(key);
    }


}

