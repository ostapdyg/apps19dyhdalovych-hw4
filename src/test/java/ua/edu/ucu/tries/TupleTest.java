package ua.edu.ucu.tries;

import static org.junit.Assert.*;
import org.junit.Test;

import ua.edu.ucu.tries.Tuple;

public class TupleTest {
    @Test
    public void testTuple() {
        Tuple t = new Tuple("ABc", 10);
        assertEquals(t.term, "ABc");
        assertEquals(t.weight, 10);
    }
}
