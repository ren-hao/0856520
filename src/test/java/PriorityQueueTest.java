import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
//import java.util.PriorityQueue;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(Parameterized.class)
public class PriorityQueueTest {
    private Object[] input;
    private Object[] expect;
    private PriorityQueue<Object> p;

    public PriorityQueueTest(Object[] input, Object [] expect){
        this.input = input;
        this.expect = expect;
    }

    @Parameterized.Parameters
    public static Collection queueElements() {
        return Arrays.asList(new Object [][][] {
                {{4, 3, 5, 6, 1}, {1, 3, 4, 5, 6}},
                {{"e", "a", "d", "b", "c"}, {"a", "b", "c", "d", "e"}},
                {{7, -8, 9}, {-8, 7, 9}},
                {{"!", "@", "#"}, {"!", "#", "@"}},
                {{2.234567, 1.123456, 3.345678}, {1.123456, 2.234567, 3.345678}},
        });
    }

    @Before
    public void setUp() {
        p = new PriorityQueue<>();
        for (Object i : input) {
            p.offer(i);
        }
    }

    @Test
    public void PriorityQueue() {
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
            PriorityQueue<Integer> p2 = new PriorityQueue<>(-1);
        });
    }

    @Test
    public void clear() {
        p.clear();
        assertEquals(0, p.size());
    }

    @Test
    public void offer() {
        PriorityQueue<Integer> p1;
        p1 = new PriorityQueue<Integer>();
        // for normal insert
        assertEquals(true, p1.offer(8));

        Exception exception = assertThrows(NullPointerException.class, () -> {
            p.offer(null);
        });
    }

    @Test
    public void peek() {
        assertEquals(expect[0], p.peek());
    }

    @Test
    public void forEach() {
        Exception exception = assertThrows(ConcurrentModificationException.class, () -> {
            p.forEach(element -> System.out.println(p.poll()));
        });
    }

    @Test
    public void forEachRemaining() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            p.spliterator().forEachRemaining(null);
        });
    }

    @Test
    public void poll() {
        Object s;
        int index = 0;
        while((s = p.poll()) != null) {
            assertEquals(expect[index++], s);
        }
    }

    @Test
    public void size() {
        assertEquals(input.length, p.size());
    }

    @Test
    public void remove() {
        for (Object o : expect) {
            assertEquals(true, p.remove(o));
        }
        assertEquals(false, p.remove(null));
    }
}