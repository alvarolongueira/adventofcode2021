package com.alvarolongueira.adventofcode.day7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alvarolongueira.adventofcode.common.FileCustomUtils;
import com.alvarolongueira.adventofcode.common.ListCustomUtils;
import com.alvarolongueira.adventofcode.day5.HidrotermalLinePoint;

public class CrabFuelService {

    private final String file;
    private List<HidrotermalLinePoint> hidrotermalLinePoints = new ArrayList<>();

    public CrabFuelService(String file) {
        this.file = file;
    }

    public long calculate() {
        return this.calculate(false);
    }

    public long calculate(boolean withFactorial) {

        FileCustomUtils reader = new FileCustomUtils(this.file);
        List<Integer> positions = ListCustomUtils.convertToInt(Arrays.asList(reader.readLine().get().split(",")));

        long intermediate = Integer.divideUnsigned(positions.stream().mapToInt(current -> current).sum(), positions.size());
        long min = positions.stream().mapToLong(value -> value).min().getAsLong();
        long max = positions.stream().mapToLong(value -> value).max().getAsLong();

        long result = Long.MAX_VALUE;

        for (long i = intermediate; i <= max; i++) {
            long currentI = i;
            long sum = positions.stream().mapToLong(position -> this.getDifference(currentI, position, withFactorial)).sum();
            if (sum <= result) {
                result = sum;
            } else {
                break;
            }
        }
        for (long i = intermediate; i >= min; i--) {
            long currentI = i;
            long sum = positions.stream().mapToLong(position -> this.getDifference(currentI, position, withFactorial)).sum();
            if (sum <= result) {
                result = sum;
            } else {
                break;
            }
        }

        return result;
    }

    private long getDifference(long currentI, long position, boolean withFactorial) {
        long difference = Math.abs(currentI - position);
        if (withFactorial) {
            return this.accumulate(difference);
        }
        return difference;
    }

    public long accumulate(long number) {
        long result = 0L;
        for (long i = 0; i <= number; i++) {
            result += i;
        }
        return result;
    }

}
