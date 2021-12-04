package com.alvarolongueira.adventofcode.day2;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SubmarinePosition {

    private Position position;
    private boolean aimActivated = false;
    private int aim = 0;

    private SubmarinePosition(Position position) {
        this.position = position;
    }

    private SubmarinePosition(Position position, boolean aimActivated) {
        this.position = position;
        this.aimActivated = aimActivated;
    }

    public static SubmarinePosition of() {
        return new SubmarinePosition(Position.of(0, 0));
    }

    public static SubmarinePosition of(Position position) {
        return new SubmarinePosition(position);
    }

    public static SubmarinePosition ofWithAim() {
        return new SubmarinePosition(Position.of(0, 0), true);
    }

    public static SubmarinePosition ofWithAim(Position position) {
        return new SubmarinePosition(position, true);
    }

    public void move(List<Action> actions) {
        actions.stream().forEach(action -> this.move(action));
    }

    private void move(Action action) {
        if (this.isAimActivated()) {
            this.moveWithAim(action);
        } else {
            this.moveNoAim(action);
        }
    }

    private void moveNoAim(Action action) {
        for (int i = 0; i < action.getTimes(); i++) {
            this.position.apply(action.getMovement());
        }
    }

    private void moveWithAim(Action action) {
        if (action.getMovement().equals(Movement.UP)) {
            this.aim -= action.getTimes();
        }
        if (action.getMovement().equals(Movement.DOWN)) {
            this.aim += action.getTimes();
        }
        if (action.getMovement().equals(Movement.FORWARD)) {
            int addX = action.getTimes();
            int addY = action.getTimes() * this.getAim();
            this.position.apply(addX, addY);
        }
    }

    public int multiply() {
        return this.position.getX() * this.position.getY();
    }

}
