package com.alvarolongueira.adventofcode.day13;

import org.junit.Test;

public class FoldPaperMainApplicationTest {

    private static final String PATH = "com/alvarolongueira/adventofcode/day13/";

    @Test
    public void readFileWebExample() {
        FoldPaperService service = new FoldPaperService(PATH + "inputTestWebExample.txt");
        service.calculate();
//        Assert.assertEquals(10, result.size());
    }


//    @Test
//    public void readFileWebExamplePartTwo() {
//        FoldPaperService service = new FoldPaperService(PATH + "inputTestWebExample.txt");
//        Set<CavePath> result = service.calculate(true);
//        Assert.assertEquals(36, result.size());
//    }

}

