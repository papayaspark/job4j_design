package collection;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(Arrays.asList(0, 1, 2, 3), Is.is(input));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> elements = new ArrayList<>(Arrays.asList(0, 1, 2));
        List<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 2, 5, 7, 20));
        ListUtils.removeAll(list, elements);
        assertThat(Arrays.asList(5, 7, 20), Is.is(list));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 2, 5, 7, 20));
        Predicate<Integer> filter = i -> i > 5;
        int value = 1;
        ListUtils.replaceIf(list, filter, value);
        assertThat(Arrays.asList(0, 1, 2, 5, 1, 1), Is.is(list));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 2, 5, 7, 20));
        Predicate<Integer> filter = i -> i > 5;
        ListUtils.removeIf(list, filter);
        assertThat(Arrays.asList(0, 1, 2, 5), Is.is(list));
    }
}