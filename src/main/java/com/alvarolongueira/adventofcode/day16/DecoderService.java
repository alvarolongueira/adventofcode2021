package com.alvarolongueira.adventofcode.day16;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.alvarolongueira.adventofcode.common.FileCustomUtils;
import com.alvarolongueira.adventofcode.common.ListCustomUtils;
import com.alvarolongueira.adventofcode.common.NumberCustomUtils;

public class DecoderService {

    private final String file;
    private long sumVersions = 0L;

    public DecoderService(String file) {
        this.file = file;
    }

    public long calculateAndSumVersionsFromFile() {
        String phrase = this.prepare();
        this.calculate(phrase);
        return this.sumVersions;
    }

    public long calculateAndSumVersions(String phrase) {
        this.calculate(phrase);
        return this.sumVersions;
    }

    public List<String> calculate(String phrase) {
        this.sumVersions = 0L;

        List<String> chain = this.calculateDecimal(phrase);

        return this.translate(chain);
    }

    private List<String> translate(List<String> chain) {
        List<String> result = new ArrayList<>();

        while (!chain.isEmpty()) {
            result.addAll(this.examine(chain));

            boolean thereIsMore = chain.stream().filter(current -> current.equals("1")).findAny().isPresent();
            if (!thereIsMore) {
                chain.clear();
            }
        }
        return result;
    }


    private List<String> examine(List<String> chain, Optional<Long> numberOfSegments) {
        List<String> list = this.examine(chain);
        return list;
    }

    private List<String> examine(List<String> chain) {
        List<String> result = new ArrayList<>();

        String valueVersion = this.pollValues(chain, 3);
        long currentVersion = NumberCustomUtils.convertBinaryToDecimal(valueVersion);
        this.sumVersions += currentVersion;

        String valueType = this.pollValues(chain, 3);
        long type = NumberCustomUtils.convertBinaryToDecimal(valueType);

        if (type == 4) {
            result.add(this.addValue(chain));

        } else {
            boolean lengthTypeIdZero = this.pollValues(chain, 1).equals("0");
            if (lengthTypeIdZero) {
                result.addAll(this.addValuesByLength(chain));
            } else {
                result.addAll(this.addValuesByNumber(chain));
            }
        }

        return result;
    }

    private String addValue(List<String> chain) {
        String value = "";
        boolean isLast = false;

        while (!isLast) {
            String first = this.pollValues(chain, 1);
            isLast = first.startsWith("0");
            value += this.pollValues(chain, 4);
        }

        return String.valueOf(NumberCustomUtils.convertBinaryToDecimal(value));
    }

    private List<String> addValuesByLength(List<String> chain) {
        String value = this.pollValues(chain, 15);
        long length = NumberCustomUtils.convertBinaryToDecimal(value);
        String substring = this.pollValues(chain, length);

        List<String> chainToExamine = ListCustomUtils.split(substring, "");
        return this.translate(chainToExamine);
    }

    private List<String> addValuesByNumber(List<String> chain) {
        List<String> result = new ArrayList<>();

        String value = this.pollValues(chain, 11);
        long numberOfSegments = NumberCustomUtils.convertBinaryToDecimal(value);
        for (int i = 0; i < numberOfSegments; i++) {
            result.addAll(this.examine(chain, Optional.of(numberOfSegments)));
        }

        return result;
    }

    private String pollValues(List<String> chain, long length) {
        String value = "";
        for (int i = 0; i < length; i++) {
            value += chain.get(0);
            chain.remove(0);
        }

        return value;
    }

    private List<String> calculateDecimal(String hexadecimal) {
        String chain = ListCustomUtils.split(hexadecimal, "").stream()
                .map(current -> NumberCustomUtils.convertHexToBinaryWithPad(current, 4))
                .reduce("", (a, b) -> a + b);
        return ListCustomUtils.split(chain, "");
    }

    private String prepare() {
        FileCustomUtils reader = new FileCustomUtils(this.file);
        return reader.readLine().orElseThrow(() -> new IllegalArgumentException("Line not found"));
    }

}
