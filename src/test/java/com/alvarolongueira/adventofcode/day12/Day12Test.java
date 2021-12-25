package com.alvarolongueira.adventofcode.day12;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class Day12Test {

    private static final String PATH = "com/alvarolongueira/adventofcode/day12/";

    @Test
    public void readFileWebExampleOne() {
        CavePathService service = new CavePathService(PATH + "inputTestWebExample1.txt");
        Set<CavePath> result = service.calculate();
        Assert.assertEquals(10, result.size());
    }

    @Test
    public void readFileWebExampleTwo() {
        CavePathService service = new CavePathService(PATH + "inputTestWebExample2.txt");
        Set<CavePath> result = service.calculate();
        Assert.assertEquals(19, result.size());
    }

    @Test
    public void readFileWebExampleThree() {
        CavePathService service = new CavePathService(PATH + "inputTestWebExample3.txt");
        Set<CavePath> result = service.calculate();
        Assert.assertEquals(226, result.size());
    }

    @Test
    public void readFileWebExampleOnePartTwo() {
        CavePathService service = new CavePathService(PATH + "inputTestWebExample1.txt");
        Set<CavePath> result = service.calculate(true);
        Assert.assertEquals(36, result.size());
    }

    @Test
    public void readFileWebExampleTwoPartTwo() {
        CavePathService service = new CavePathService(PATH + "inputTestWebExample2.txt");
        Set<CavePath> result = service.calculate(true);
        Assert.assertEquals(103, result.size());
    }

    @Test
    public void readFileWebExampleThreePartTwo() {
        CavePathService service = new CavePathService(PATH + "inputTestWebExample3.txt");
        Set<CavePath> result = service.calculate(true);
        Assert.assertEquals(3509, result.size());
    }

}

