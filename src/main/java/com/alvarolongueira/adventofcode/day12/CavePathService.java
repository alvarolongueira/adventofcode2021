package com.alvarolongueira.adventofcode.day12;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.alvarolongueira.adventofcode.common.FileCustomUtils;
import com.alvarolongueira.adventofcode.common.ListCustomUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

public class CavePathService {

    private final String file;
    private final Set<CaveCode> caves = new HashSet<>();
    private final Set<CaveConnection> caveConnections = new HashSet<>();

    private CaveCode start = null;
    private boolean repeatSmallTwice = false;

    public CavePathService(String file) {
        this.file = file;
        this.prepare();
    }

    public Set<CavePath> calculate() {
        return this.calculate(false);
    }

    public Set<CavePath> calculate(boolean repeatSmallTwice) {
        this.repeatSmallTwice = repeatSmallTwice;

        Set<CavePath> cavePaths = this.buildCaves(this.start, this.caves);

        return cavePaths;
    }

    private Set<CavePath> buildCaves(CaveCode start, Set<CaveCode> availableTargets) {
        Set<CavePath> cavePaths = new HashSet<>();
        cavePaths.add(CavePath.of(ImmutableList.of(start)));
        cavePaths.addAll(this.addCavePaths(start, this.caves, cavePaths));

        return cavePaths.stream()
                .filter(cavePath -> cavePath.isValid())
                .collect(Collectors.toSet());
    }

    private Set<CavePath> addCavePaths(CaveCode source, Set<CaveCode> availableTargets, Set<CavePath> cavePaths) {
        if (source.isEnd()) {
            return cavePaths;
        }

        Set<CavePath> copy = ImmutableSet.copyOf(cavePaths);

        for (CaveCode target : availableTargets) {
            if (this.existConnection(source, target)) {

                Set<CavePath> newPaths = this.addNewCavePaths(copy, target);
                if (!newPaths.isEmpty()) {

                    Set<CaveCode> newAvailableTargets = new HashSet<>(availableTargets);
                    boolean needsRemove = this.needsRemove(target, newPaths);
                    if (needsRemove) {
                        newAvailableTargets.remove(target);
                    }
                    cavePaths.addAll(this.addCavePaths(target, newAvailableTargets, newPaths));
                }
            }
        }
        return cavePaths;
    }

    private boolean needsRemove(CaveCode target, Set<CavePath> newPaths) {
        if (this.repeatSmallTwice) {
            return false;
        }
        if (target.isBig()) {
            return false;
        }

        return true;
    }

    private Set<CavePath> addNewCavePaths(Set<CavePath> cavePaths, CaveCode code) {
        Set<CavePath> newCavePaths = new HashSet<>();

        for (CavePath current : cavePaths) {
            newCavePaths.add(current.ofWithAdd(code));
        }

        if (this.repeatSmallTwice) {
            newCavePaths = newCavePaths.stream().filter(current -> !current.hasDuplicated()).collect(Collectors.toSet());
        }

        return newCavePaths;
    }

    private boolean existConnection(CaveCode source, CaveCode target) {
        return this.caveConnections.stream().anyMatch(connection ->
                source.equals(connection.getSource()) && target.equals(connection.getTarget()));
    }

    private void prepare() {
        FileCustomUtils reader = new FileCustomUtils(this.file);

        List<String> lines = reader.readAllNoLineBreaks();
        for (String line : lines) {
            List<String> codes = ListCustomUtils.split(line, "-");

            CaveCode first = CaveCode.of(codes.get(0));
            CaveCode second = CaveCode.of(codes.get(1));

            this.caves.add(first);
            this.caves.add(second);

            if (!first.isStart() && !second.isEnd()) {
                this.caveConnections.add(CaveConnection.of(second, first));
            }
            this.caveConnections.add(CaveConnection.of(first, second));
        }

        this.start = this.caves.stream().filter(caveCode -> caveCode.isStart()).findFirst().get();
        this.caves.remove(this.start);
    }

}
