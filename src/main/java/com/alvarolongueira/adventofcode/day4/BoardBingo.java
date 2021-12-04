package com.alvarolongueira.adventofcode.day4;

import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Setter
@Getter

public class BoardBingo implements Cloneable {

    private Hashtable<GridBoard, Integer> boardBackup = new Hashtable<>();
    private Hashtable<GridBoard, Integer> boardToPlay = new Hashtable<>();
    private boolean rowMarked = false;
    private boolean columnMarked = false;
    private int prize = 0;

    public void markNumber(int number) {
        Optional<GridBoard> gridMarked = Optional.empty();

        for (Map.Entry<GridBoard, Integer> current : this.boardToPlay.entrySet()) {
            if (current.getValue().equals(number)) {
                this.prize = number;
                current.setValue(-1);
                gridMarked = Optional.of(current.getKey());
                break;
            }
        }

        if (gridMarked.isPresent()) {
            int currentY = gridMarked.get().getY();
            this.rowMarked = this.boardToPlay.entrySet().stream()
                    .filter(entry -> entry.getKey().getY() == currentY)
                    .allMatch(entry -> entry.getValue() == -1);

            int currentX = gridMarked.get().getX();
            this.columnMarked = this.boardToPlay.entrySet().stream()
                    .filter(entry -> entry.getKey().getX() == currentX)
                    .allMatch(entry -> entry.getValue() == -1);
        }

    }

    public int calculate() {
        int sum = this.boardToPlay.entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .mapToInt(current -> current.getValue()).sum();
        return this.prize * sum;
    }

    @Override
    protected BoardBingo clone() {
        try {
            return (BoardBingo) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "BoardBingo{" +
                "\nrowMarked=" + this.rowMarked + ", columnMarked=" + this.columnMarked + ", prize=" + this.prize +
                "\nboardBackup={\n" + this.boardToString(this.boardBackup) + "}" +
                "\nboardToPlay={\n" + this.boardToString(this.boardToPlay) + "}"
                ;
    }

    private String boardToString(Hashtable<GridBoard, Integer> board) {
        String text = "[ ";

        for (int y = 1; y <= 5; y++) {
            for (int x = 1; x <= 5; x++) {
                GridBoard grid = GridBoard.of(x, y);
                text += board.get(grid) + " ";
            }
            text += "]\n";
            if (y != 5) {
                text += "[ ";
            }
        }
        return text;
    }

}