package collection.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table;
    private Set<K> keySet;

    public SimpleMap(MapEntry<K, V>[] table) {
        this.table = table;
    }

    @Override
    public boolean put(K key, V value) {
        var entry = new MapEntry<K, V>(key, value);
        int index = indexFor(hash(entry.hashCode()));
        if (index > table.length - 1) {
            expand();
            capacity = table.length;
        }
        if (table[index] != null) {
            return false;
        }
        table[index] = entry;
        count++;
        modCount++;
        return true;
    }

    private int hash(int hashCode) {
        return hashCode() ^ (hashCode >>> 8);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        table = Arrays.copyOf(table, table.length + (table.length >> 1));
    }

    private Set<K> keySet(MapEntry<K, V>[] table) {
        Set<K> keySet = new HashSet<K>();
        if (table != null) {
            for (int i = 0; i < capacity; i++) {
                if (table[i] != null) {
                    keySet.add(table[i].getKey());
                }
            }
        } else {
            return null;
        }
        return keySet;
    }

    @Override
    public V get(K key) {
        V value = null;
        for (var v: table) {
            if(v.getKey().equals(key)) {
                value = v.getValue();
            }
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        if (key == null || keySet.contains(key)) {
            return false;
        }
        for (var element: table) {
            if (element.getKey().equals(key)) {
                element = null;
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet(table).iterator();
//                new Iterator<K>() {
//            private Iterator<K> iSet;
//            int expectedModCount = modCount;
//
//            @Override
//            public boolean hasNext() {
//                return iSet.hasNext();
//            }
//
//            @Override
//            public K next() {
//                if (!hasNext()) {
//                    throw new NoSuchElementException();
//                }
//                if (expectedModCount != modCount) {
//                    throw new ConcurrentModificationException();
//                }
//                return (K) iSet.next();
//            }
        }

    public static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

//    public static void main(String[] args) {
//        SimpleMap<Integer, Integer> map = new SimpleMap<>();
////
//        map.put(1, 111);
//        System.out.println(map.table);
//        map.put(5, 555);
//        map.put(42, 4242);
//        map.put(34, 34);
//        map.put(65, 0);
//        System.out.println(map.table);
////        while (map.iterator().hasNext()) {
////            System.out.println(map.iterator().next());
////        }
////        Set<Integer> keySet = new HashSet<>();
////            for (MapEntry<Integer, Integer> keyEl : map.table) {
////                System.out.println((keyEl.getKey()));
////            }
////        System.out.println(keySet);
//        System.out.println(map);
//        System.out.println();
////        Set<Integer> set = map.keySet(map.table);
////        System.out.println(set);
//    }


    static class Node<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public final String toString() {
            return key + "=" + value;
        }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof MapEntry) {
                MapEntry<K,V> e = (MapEntry<K,V>)o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }
}