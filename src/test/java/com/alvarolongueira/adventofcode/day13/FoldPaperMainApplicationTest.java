package com.alvarolongueira.adventofcode.day13;

import org.junit.Assert;
import org.junit.Test;

public class FoldPaperMainApplicationTest {

    private static final String PATH = "com/alvarolongueira/adventofcode/day13/";

    @Test
    public void readFileWebExample() {
        FoldPaperService service = new FoldPaperService(PATH + "inputTestWebExample.txt");

        int resultOne = service.calculate(1);
        Assert.assertEquals(17, resultOne);

        int resultTwo = service.calculate(2);
        Assert.assertEquals(16, resultTwo);
    }

}

