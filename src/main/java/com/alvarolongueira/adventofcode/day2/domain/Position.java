package com.alvarolongueira.adventofcode.day2.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Position {

    private int x;
    private int y;

    public static Position of(int x, int y) {
        return new Position(x, y);
    }

    public void apply(Movement movement) {
        this.apply(movement.getPosition().getX(), movement.getPosition().getY());
    }

    public void apply(int addX, int addY) {
        this.x += addX;
        this.y += addY;

        this.x = this.keepMin(this.x);
        this.y = this.keepMin(this.y);
    }

    private int keepMin(int current) {
        if (current < 0) {
            return 0;
        }
        return current;
    }
}
