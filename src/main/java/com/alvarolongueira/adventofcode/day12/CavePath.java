package com.alvarolongueira.adventofcode.day12;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.immutables.value.Value;

import com.google.common.collect.ImmutableList;

@Value.Immutable(builder = false)
public abstract class CavePath {

    @Value.Parameter
    public abstract List<CaveCode> getCaveCodes();


    public static CavePath of(List<CaveCode> caveCodes) {
        return ImmutableCavePath.of(caveCodes);
    }

    public CavePath ofWithAdd(CaveCode caveCode) {
        List<CaveCode> newList = ImmutableList.<CaveCode>builder()
                .addAll(this.getCaveCodes())
                .add(caveCode)
                .build();
        return CavePath.of(newList);
    }

    public boolean isValid() {
        if (this.getCaveCodes().isEmpty()) {
            return false;
        }

        CaveCode start = this.getCaveCodes().get(0);
        if (!start.isStart()) {
            return false;
        }

        CaveCode end = this.getCaveCodes().get(this.getCaveCodes().size() - 1);
        if (!end.isEnd()) {
            return false;
        }

        return true;
    }

    public boolean hasDuplicated() {
        int founded = 0;
        Set<CaveCode> list = new HashSet<>();
        for (CaveCode code : this.getCaveCodes()) {
            if (!code.isBig() && list.contains(code)) {
                founded++;
                if (founded > 1) {
                    return true;
                }
            }
            list.add(code);
        }
        return false;
    }
}
