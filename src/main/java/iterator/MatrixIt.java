package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean check = false;
        if (row < data.length && column <= data[row].length && data[row].length == 0) {
            row++;
            if (row >= data.length) {
                check = false;
            }
        }
        if (row < data.length && column <= data[row].length && data[row].length > 0) {
            check = true;
        }
        return check;
    }

    @Override
    public Integer next() {
        int rsl = 0;
        if (hasNext()) {
            for (int i : data[row]) {
                rsl = data[row][column++];
                while (rsl == 0) {
                    column++;
                }
                break;
            }
            while (row < data.length && column == data[row].length) {
                row++;
                column = 0;
            }
        } else {
            throw new NoSuchElementException();
        }
        return rsl;
    }
}
