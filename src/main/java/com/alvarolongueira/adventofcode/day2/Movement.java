package com.alvarolongueira.adventofcode.day2;

public enum Movement {
    FORWARD("forward", Position.of(1, 0)),
    UP("up", Position.of(0, -1)),
    DOWN("down", Position.of(0, 1));

    private final String code;
    private final Position position;

    private Movement(String code, Position position) {
        this.code = code;
        this.position = position;
    }

    private String getCode() {
        return this.code;
    }

    public Position getPosition() {
        return this.position;
    }

    public static Movement get(String code) {
        for (Movement current : Movement.values()) {
            if (current.getCode().equals(code)) {
                return current;
            }
        }
        throw new IllegalArgumentException("Action code undefined " + code);
    }

}
