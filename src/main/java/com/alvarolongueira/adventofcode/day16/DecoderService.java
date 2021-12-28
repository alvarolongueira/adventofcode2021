package com.alvarolongueira.adventofcode.day16;

import java.util.ArrayList;
import java.util.List;

import com.alvarolongueira.adventofcode.common.FileCustomUtils;
import com.alvarolongueira.adventofcode.common.ListCustomUtils;
import com.alvarolongueira.adventofcode.common.NumberCustomUtils;
import com.google.common.collect.ImmutableList;

public class DecoderService {

    private final String file;
    private long sumVersions = 0L;

    public DecoderService(String file) {
        this.file = file;
    }

    public long calculateFromFile() {
        String phrase = this.prepare();
        return this.calculate(phrase);
    }

    public long calculate(String phrase) {
        this.sumVersions = 0L;
        List<String> chain = this.calculateDecimal(phrase);
        return this.translate(chain).get(0);
    }

    public long getSumVersions() {
        return this.sumVersions;
    }

    private List<Long> translate(List<String> chain) {
        List<Long> result = new ArrayList<>();

        while (!chain.isEmpty()) {
            result.addAll(this.examine(chain));

            boolean thereIsMore = chain.stream().filter(current -> current.equals("1")).findAny().isPresent();
            if (!thereIsMore) {
                chain.clear();
            }
        }
        return result;
    }

    private List<Long> examine(List<String> chain) {
        List<Long> resultValues = new ArrayList<>();

        String valueVersion = this.pollValues(chain, 3);
        long currentVersion = NumberCustomUtils.convertBinaryToDecimal(valueVersion);
        this.sumVersions += currentVersion;

        String valueType = this.pollValues(chain, 3);
        int type = (int) NumberCustomUtils.convertBinaryToDecimal(valueType);

        if (type == 4) {
            resultValues.add(this.getValue(chain));

        } else {
            boolean lengthTypeIdZero = this.pollValues(chain, 1).equals("0");
            if (lengthTypeIdZero) {
                resultValues.addAll(this.geValuesByLength(chain));
            } else {
                resultValues.addAll(this.getValuesByNumber(chain));
            }
        }

        long resultOperation = this.operate(type, resultValues);

        return ImmutableList.of(resultOperation);
    }


    private long getValue(List<String> chain) {
        String value = "";
        boolean isLast = false;

        while (!isLast) {
            String first = this.pollValues(chain, 1);
            isLast = first.startsWith("0");
            value += this.pollValues(chain, 4);
        }

        return NumberCustomUtils.convertBinaryToDecimal(value);
    }

    private List<Long> geValuesByLength(List<String> chain) {
        String value = this.pollValues(chain, 15);
        long length = NumberCustomUtils.convertBinaryToDecimal(value);
        String substring = this.pollValues(chain, length);

        List<String> chainToExamine = ListCustomUtils.split(substring, "");
        return this.translate(chainToExamine);
    }

    private List<Long> getValuesByNumber(List<String> chain) {
        List<Long> result = new ArrayList<>();

        String value = this.pollValues(chain, 11);
        long numberOfSegments = NumberCustomUtils.convertBinaryToDecimal(value);
        for (int i = 0; i < numberOfSegments; i++) {
            result.addAll(this.examine(chain));
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

    private long operate(int valueType, List<Long> list) {
        long value = 0L;

        switch (valueType) {
            case 0: //Sum
                for (long current : list) {
                    value += current;
                }
                break;

            case 1: //Product
                if (list.size() != 1) {
                    value = 1;
                    for (long current : list) {
                        value = value * current;
                    }
                } else {
                    value = list.get(0);
                }
                break;

            case 2: //Minimum
                value = Long.MAX_VALUE;
                for (long current : list) {
                    if (current < value) {
                        value = current;
                    }
                }
                break;

            case 3: //Maximum
                for (long current : list) {
                    if (current > value) {
                        value = current;
                    }
                }
                break;

            case 4: //Value
                value = list.get(0);
                break;

            case 5: //Greater than
                if (list.get(0) > list.get(1)) {
                    value = 1L;
                }
                break;

            case 6: //Less than
                if (list.get(0) < list.get(1)) {
                    value = 1L;
                }
                break;

            case 7: //Equals to
                if (list.get(0).compareTo(list.get(1)) == 0) {
                    value = 1L;
                }
                break;
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
