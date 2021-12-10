package com.alvarolongueira.adventofcode.day6;

import org.junit.Assert;
import org.junit.Test;

public class LanternfishMainApplicationTest {

    private static final String PATH = "com/alvarolongueira/adventofcode/day6/";

    @Test
    public void readFileWebExample() {
        LanternfishService service = new LanternfishService(PATH + "inputTestWebExample.txt");

        long resultDay1 = service.grow(1);
        Assert.assertEquals(5, resultDay1);

        long resultDay2 = service.grow(2);
        Assert.assertEquals(6, resultDay2);

        long resultDay5 = service.grow(5);
        Assert.assertEquals(10, resultDay5);

        long resultDay10 = service.grow(10);
        Assert.assertEquals(12, resultDay10);

        long resultDay15 = service.grow(15);
        Assert.assertEquals(20, resultDay15);

        long resultDay18 = service.grow(18);
        Assert.assertEquals(26, resultDay18);

        long resultDay80 = service.grow(80);
        Assert.assertEquals(5934, resultDay80);
    }

    @Test
    public void readFileWebExamplePartTwo() {
        LanternfishService service = new LanternfishService(PATH + "inputTestWebExample.txt");

        long resultDay256 = service.grow(256);
        Assert.assertEquals(26984457539L, resultDay256);
    }
}
