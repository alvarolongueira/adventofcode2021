package com.alvarolongueira.adventofcode.day10;

import org.junit.Assert;
import org.junit.Test;

public class ChunkAnalyzeMainApplicationTest {

    private static final String PATH = "com/alvarolongueira/adventofcode/day10/";

    @Test
    public void readFileWebExample() {
        ChunkAnalyzeService service = new ChunkAnalyzeService(PATH + "inputTestWebExample.txt");

        ChunkBracketResult result = service.calculate();

        Assert.assertEquals(Integer.valueOf(2), result.getBracketErrors().get(Bracket.ROUND));
        Assert.assertEquals(Integer.valueOf(1), result.getBracketErrors().get(Bracket.SQUARE));
        Assert.assertEquals(Integer.valueOf(1), result.getBracketErrors().get(Bracket.CURLY));
        Assert.assertEquals(Integer.valueOf(1), result.getBracketErrors().get(Bracket.ANGLE));
        Assert.assertEquals(26397, result.sumErrors());
    }

    @Test
    public void readFileWebExamplePartTwo() {
        ChunkAnalyzeService service = new ChunkAnalyzeService(PATH + "inputTestWebExample.txt");

        ChunkBracketResult result = service.calculate();

        Assert.assertEquals("}}]])})]", this.toString(result, 0));
        Assert.assertEquals(")}>]})", this.toString(result, 1));
        Assert.assertEquals("}}>}>))))", this.toString(result, 2));
        Assert.assertEquals("]]}}]}]}>", this.toString(result, 3));
        Assert.assertEquals("])}>", this.toString(result, 4));

        Assert.assertEquals(288957, this.points(result, 0));
        Assert.assertEquals(5566, this.points(result, 1));
        Assert.assertEquals(1480781, this.points(result, 2));
        Assert.assertEquals(995444, this.points(result, 3));
        Assert.assertEquals(294, this.points(result, 4));

        Assert.assertEquals(288957, result.scoreFromMissed());
    }

    private String toString(ChunkBracketResult result, int index) {
        return this.bracketList(result, index).toStringCloseList();
    }

    private long points(ChunkBracketResult result, int index) {
        return this.bracketList(result, index).score();
    }

    private BracketList bracketList(ChunkBracketResult result, int index) {
        return result.getBracketMissed().get(index);
    }
}

