package com.alvarolongueira.adventofcode.day4;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.alvarolongueira.adventofcode.common.FileCustomUtils;
import com.alvarolongueira.adventofcode.common.ListCustomUtils;

public class BingoService {

    private final String file;
    private List<Integer> randomNumbers;
    private List<String> lineBoards;
    private List<BoardBingo> boards;

    public BingoService(String file) {
        this.file = file;
    }

    public BoardBingo playBingo() throws UnexpectedException {
        this.prepare();
        this.createBoards();

        for (int randomNumber : this.randomNumbers) {
            Optional<BoardBingo> winner = this.findWinner(randomNumber);
            if (winner.isPresent()) {
                return winner.get();
            }
        }

        throw new UnexpectedException("Winner not found");
    }

    private Optional<BoardBingo> findWinner(int number) {

        for (BoardBingo board : this.boards) {
            board.markNumber(number);
            if (board.isLineMarked()) {
                return Optional.of(board);
            }
        }

        return Optional.empty();
    }

    private void createBoards() {
        List<BoardBingo> list = new ArrayList<>();

        BoardBingo newBoard = new BoardBingo();
        Hashtable<GridBoard, Integer> boardTable = new Hashtable<>();

        int x = 1;
        int y = 1;
        for (int i = 1; i <= this.lineBoards.size(); i++) {

            List<Integer> numbers = Arrays.stream(this.lineBoards.get(i - 1).split(" "))
                    .filter(value -> !value.isEmpty())
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());

            for (int number : numbers) {
                GridBoard grid = GridBoard.of(x, y);
                boardTable.put(grid, number);
                x++;
            }

            x = 1;
            y++;

            if (i % 5 == 0) {
                newBoard.setBoardBackup(new Hashtable(boardTable));
                newBoard.setBoardToPlay(new Hashtable(boardTable));
                list.add(newBoard.clone());
                newBoard = new BoardBingo();
                boardTable = new Hashtable<>();
                y = 1;
            }
        }

        this.boards = list;
    }

    public void prepare() {
        FileCustomUtils reader = new FileCustomUtils(this.file);
        this.randomNumbers = ListCustomUtils.convertToInt(Arrays.asList(reader.readLine().get().split(",")));
        this.lineBoards = reader.readAllNoLineBreaks();
    }

}
