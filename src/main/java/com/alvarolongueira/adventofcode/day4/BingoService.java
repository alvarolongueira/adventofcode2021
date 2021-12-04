package com.alvarolongueira.adventofcode.day4;

import java.util.List;

import com.alvarolongueira.adventofcode.common.FileCustomUtils;

public class BingoService {

    private final String file;
    private String randomNumbers;
    private List<String> lineBoards;

    public BingoService(String file) {
        this.file = file;
    }

    public BoardBingo playBingo() {
        this.prepare();


        return null;
    }

    public void prepare() {
        FileCustomUtils reader = new FileCustomUtils(this.file);
        this.randomNumbers = reader.readLine().get();
        this.lineBoards = reader.readAllNoLineBreaks();
    }

}
