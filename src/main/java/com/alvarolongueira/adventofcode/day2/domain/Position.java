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
    private boolean aimActivated;

    public static Position of(int x, int y) {
        return new Position(x, y, false);
    }

    public static Position ofWithAim(int x, int y) {
        return new Position(x, y, true);
    }

    public void apply(Movement movement) {
        if (this.isAimActivated()) {

        } else {
            this.applyWithoutAim(movement);
        }
    }

    private void applyWithoutAim(Movement movement) {
        this.x += movement.getPosition().getX();
        this.y += movement.getPosition().getY();

        this.x = this.keepMin(this.x);
        this.y = this.keepMin(this.y);
    }

    private void applyWithAim(Movement movement) {

    }

    private int keepMin(int current) {
        if (current < 0) {
            return 0;
        }
        return current;
    }
}
