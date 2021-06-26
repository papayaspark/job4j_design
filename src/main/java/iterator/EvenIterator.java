package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator<Integer> {
    int[] numbers;
    int i = 0;

    public EvenIterator(final int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        for (int j = i; j < numbers.length; j++) {
            if (numbers[j] % 2 == 0) {
                i = j;
                break;
            }
        }
        return numbers[i] % 2 == 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return numbers[i++];
    }
}
