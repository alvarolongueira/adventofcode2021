package com.alvarolongueira.adventofcode.day10;

public enum Bracket {
    ROUND("(", ")", 3, 1),
    SQUARE("[", "]", 57, 2),
    CURLY("{", "}", 1197, 3),
    ANGLE("<", ">", 25137, 4);

    private final String open;
    private final String close;
    private final int cost;
    private final int score;

    private Bracket(String open, String close, int cost, int score) {
        this.open = open;
        this.close = close;
        this.cost = cost;
        this.score = score;
    }

    public String getOpen() {
        return this.open;
    }

    public String getClose() {
        return this.close;
    }

    public Character getOpenChar() {
        return this.open.charAt(0);
    }

    public Character getCloseChar() {
        return this.close.charAt(0);
    }

    public int getCost() {
        return this.cost;
    }

    public int getScore() {
        return this.score;
    }

    public String both() {
        return "\\" + this.open + "\\" + this.close;
    }

}
