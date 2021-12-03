package com.alvarolongueira.adventofcode.day3;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alvarolongueira.adventofcode.common.FileCustomUtils;
import com.alvarolongueira.adventofcode.common.NumberCustomUtils;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class PowerConsumptionService {

    private final String file;

    public PowerConsumptionService(String file) {
        this.file = file;
    }

    public EnergyRate calculateEnergy() {
        EnergyRate energy = new EnergyRate();

        List<String> readings = this.read();
        if (readings.isEmpty()) {
            return energy;
        }

        int halfAmountOfReadings = readings.size() / 2;
        Map<Integer, Integer> map = this.getMap(readings);

        String gammaRate = "";
        String epsilonRate = "";
        for (int value : map.values()) {
            boolean moreZeros = value < halfAmountOfReadings;
            if (moreZeros) {
                gammaRate += "0";
                epsilonRate += "1";
            } else {
                gammaRate += "1";
                epsilonRate += "0";
            }
        }

        energy.setGammaRate(gammaRate);
        energy.setEpsilonRate(epsilonRate);
        energy.setGammaRateDecimal(NumberCustomUtils.convertBinaryToDecimal(gammaRate));
        energy.setEpsilonRateDecimal(NumberCustomUtils.convertBinaryToDecimal(epsilonRate));
        return energy;
    }

    private Map<Integer, Integer> getMap(List<String> readings) {
        Map<Integer, Integer> map = new HashMap<>();

        for (String reading : readings) {
            for (int i = 0; i < reading.length(); i++) {
                int current = Character.getNumericValue(reading.charAt(i));
                Integer value = map.get(i);
                if (value == null) {
                    value = 0;
                }
                value += current;
                map.put(i, value);
            }
        }

        return map;
    }

    public LifeSupportRate calculateLifeSupport() {
        LifeSupportRate lifeSupport = new LifeSupportRate();

        List<String> readings = this.read();
        if (readings.isEmpty()) {
            return lifeSupport;
        }

        int maxIndex = readings.stream().findAny().get().length() - 1;
//        String oxygenRate = this.readList(readings, true, 0, maxIndex);
//        String coscrubberRate = this.readList(readings, false, 0, maxIndex);
        String oxygenRate = this.readList(readings, true);
        String coscrubberRate = this.readList(readings, false);

        lifeSupport.setOxygenRate(oxygenRate);
        lifeSupport.setCoscrubberRate(coscrubberRate);
        lifeSupport.setOxygenRateDecimal(NumberCustomUtils.convertBinaryToDecimal(oxygenRate));
        lifeSupport.setCoscrubberRateDecimal(NumberCustomUtils.convertBinaryToDecimal(coscrubberRate));
        return lifeSupport;
    }

    private String readList(Collection<String> list, boolean mostCommon) {
        if (list.size() == 1) {
            return list.stream().findFirst().get();
        }

        Multimap<Integer, String> map = ArrayListMultimap.create();

        String rest = "";

        for (String current : list) {
            int key = Integer.valueOf(current.substring(0, 1));
            if (current.length() >= 2) {
                rest = current.substring(1, current.length());
            }
            map.put(key, rest);
        }

        Collection<String> listWithZero = map.get(0);
        Collection<String> listWithOne = map.get(1);

        boolean chooseOneList = this.chooseOneList(mostCommon, listWithZero, listWithOne);

        String value = chooseOneList ? "1" : "0";
        if (rest.length() < 1) {
            return value;
        }

        Collection<String> choosenList = chooseOneList ? listWithOne : listWithZero;
        return value + this.readList(choosenList, mostCommon);
    }

    private boolean chooseOneList(boolean mostCommon, Collection<String> listWithZero, Collection<String> listWithOne) {
        if (mostCommon) {
            if (listWithOne.size() >= listWithZero.size()) {
                return true;
            }
        } else {
            if (listWithOne.size() < listWithZero.size()) {
                return true;
            }
        }
        return false;
    }

    private List<String> read() {
        FileCustomUtils reader = new FileCustomUtils(this.file);
        return reader.readAllNoLineBreaks();
    }

}
