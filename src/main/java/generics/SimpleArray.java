package generics;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    Object[] array;
    private int count = 0;

    private SimpleArray(int num) {
        this.array = new Objects[num];
    }

    private void add(T model) {
        array[count++] = model;
    }

    private void set(int index, T model) {
        Objects.checkIndex(index, count);
        array[index] = model;
    }

    private void remove(int index) {
        Objects.checkIndex(index, count);
        array[index] = null;
        System.arraycopy(array, index + 1, array, index, array.length - 1);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iter();
    }

    class Iter implements Iterator<T> {
        int count;

        @Override
        public boolean hasNext() {
            return count < array.length;
        }

        @Override
        public T next() {
            if (count >= array.length) {
                throw new NoSuchElementException();
            }
            return (T) array[count++];
        }
    }
}
