package collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked <T> implements Iterable<T> {
    private Node<T> head;
    int count = 0;
    int modCount = 0;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        count++;
    }

    public void addFirst(T value) {
        final Node<T> f = head;
        head = new Node<>(value, f);
        count++;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> first = head;
        final T element = first.value;
        final Node<T> next = first.next;
        first.value = null;
        first.next = null;
        head = next;
        count--;
        modCount++;
        return element;
    }

    private static class Node<T> {
        T value;
        Node<T> next;
        Node<T> prev;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}