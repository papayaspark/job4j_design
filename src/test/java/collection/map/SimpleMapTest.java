//package collection.map;
//
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//import static org.hamcrest.Matchers.is;
//
//public class SimpleMapTest {
//
//    @Test
//    public void whenPutOne() {
//        SimpleMap.MapEntry<Integer, Integer>[] table = new SimpleMap.MapEntry[3];
//        SimpleMap<Integer, Integer> map = new SimpleMap<>(table);
//        map.put(1, 111);
//        assertNotNull(map);
//    }
//
//    @Test
//    public void whenPutThree() {
//        SimpleMap.MapEntry<Integer, Integer>[] table = new SimpleMap.MapEntry[12];
//        SimpleMap<Integer, Integer> map = new SimpleMap<>(table);
//        map.put(1, 111);
//        map.put(2, 222);
//        map.put(4, 0);
//        assertNotNull(map);
//        assertTrue(map.put(3, 2));
//    }
//
//    @Test
//    public void iterator() {
//    }
//
//    @Test
//    public void whenGet() {
//        SimpleMap.MapEntry<Integer, Integer>[] table = new SimpleMap.MapEntry[3];
//        table[0] = new SimpleMap.MapEntry<>(0, 0);
//        table[1] = new SimpleMap.MapEntry<>(1, 1);
//        table[2] = new SimpleMap.MapEntry<>(2, 2);
//        SimpleMap<Integer, Integer> map = new SimpleMap<>(table);
//        assertThat(map.get(1), is(table[1].value));
//        assertNull(map.get(5));
//    }
//
//    @Test
//    public void whenExpand() {
//        SimpleMap.MapEntry<Integer, Integer>[] table = new SimpleMap.MapEntry[100];
//        SimpleMap<Integer, Integer> map = new SimpleMap<>(table);
//        map.put(1, 111);
//        map.put(5, 555);
//        map.put(4, 78);
//        map.put(12, 5);
//        int v = map.get(4);
//        assertThat(v, is(78));
//    }
//}