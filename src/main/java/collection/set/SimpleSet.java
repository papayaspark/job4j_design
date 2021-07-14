package collection.set;

import collection.SimpleArray;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArray<T> set = new SimpleArray<>();

    @Override
    public boolean add(T value) {
        if (!contains(value)) {
            set.add(value);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean contains(T value) {
        Iterator<T> iSet = set.iterator();
        boolean rsl = false;
        while (iSet.hasNext()) {
            if (Objects.equals(value, iSet.next())) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return set.iterator().hasNext();
            }

            @Override
            public T next() {
                return (T) set.iterator().next();
            }
        };
    }
}

