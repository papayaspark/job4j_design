package collection.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Tree.Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> parents = findBy(parent);
        if (parents.isPresent()) {
            Optional<Node<E>> kid = findBy(child);
            if (kid.isEmpty()) {
                Node<E> gotKid = new Node<>(child);
                parents.get().children.add(gotKid);
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> filter = eNode -> eNode.value.equals(value);
        return findByPredicate(filter);
    }

    public boolean isBinary() {
        Predicate<Node<E>> filter = eNode -> eNode.children.size() > 2;
        return findByPredicate(filter).isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

}
