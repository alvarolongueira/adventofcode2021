package com.alvarolongueira.adventofcode.day12;

import org.immutables.value.Value;

@Value.Immutable(builder = false)
public abstract class CaveCode {

    @Value.Parameter
    public abstract String getCode();

    public static CaveCode of(String code) {
        return ImmutableCaveCode.of(code);
    }

    public boolean isStart() {
        return this.getCode().equals("start");
    }

    public boolean isEnd() {
        return this.getCode().equals("end");
    }

    public boolean isBig() {
        return this.getCode().toUpperCase().equals(this.getCode());
    }

}
