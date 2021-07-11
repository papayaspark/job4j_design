package collection.list;

import java.util.*;

public class SimpleLinkedList<E> implements List<E>{
    private Object[] array;
    private int count = 0;
    private int modCount = 0;
    Node head;

    public SimpleLinkedList() {
        this.array = new Object[10];
    }

    @Override
    public void add(E value) {
        if (count == array.length) {
            array = Arrays.copyOf(array, array.length + (array.length >> 1));
        }
        Node<E> node;
        if (head == null) {
            node = new Node(null, value, null);
            head = node;
            array[count++] = head;
        } else {
            Node<E> l = head;
            node = new Node<>(l, value, null);
            l.next = node;
            array[count++] = node;
        }
        modCount++;

    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, count);
        return (E) this.array[index];
    }

    @Override
    public Iterator<E> iterator() throws ConcurrentModificationException, NoSuchElementException {
        return new Iter();
    }

    public class Iter implements Iterator<E> {
        Node<E> next;
        int counter;
        int expectedModCount;

        public Iter() {
            this.expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            return this.counter < count;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            next = (Node<E>) array[counter++];
            return (E) next.item;
        }
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
