package collection.list;

import java.util.*;

public class SimpleLinkedList<E> implements List<E> {
    private Node<E> head;
    private Node<E> tail;
    private int count = 0;
    private int modCount = 0;

    public SimpleLinkedList() {
    }

    @Override
    public void add(E value) {
        Node<E> element = tail;
        Node<E> node = new Node<>(element, value, null);
        tail = node;
        if (element == null) {
            head = node;
        } else {
            element.next = node;
        }
        modCount++;
        count++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, count);
        Node<E> element = head;
            for (int i = 0; i < index; i++) {
                element = element.next;
            }
        return element.item;
    }

    @Override
    public Iterator<E> iterator() throws ConcurrentModificationException, NoSuchElementException {
        return new Iter();
    }

    public class Iter implements Iterator<E> {
        Node<E> following;
        int counter;

        public Iter() {
            this.following = head;
            this.counter = modCount;
        }

        @Override
        public boolean hasNext() {
            return this.following != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (counter != modCount) {
                throw new ConcurrentModificationException();
            }
            E value = following.item;
            following = following.next;
            return value;
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
