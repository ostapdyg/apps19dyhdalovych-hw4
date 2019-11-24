package ua.edu.ucu.tries;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.hamcrest.Matchers.containsInAnyOrder;

import ua.edu.ucu.tries.RWayTrie;
import ua.edu.ucu.tries.Tuple;


public class RWayTrieTest{
    @Test
    public void testTrie(){
        RWayTrie tr = new RWayTrie();
        tr.add(new Tuple("ans", 3));
        assertTrue(tr.contains("ans"));
        tr.add(new Tuple("acs", 3));
        assertTrue(tr.contains("acs"));
        Iterable<String> actResult = tr.words();
        String[] expResult = {"ans", "acs"};
        assertThat(actResult, containsInAnyOrder(expResult));
        String[] expResult1 = {"acs"};
        assertThat(tr.wordsWithPrefix("ac"), containsInAnyOrder(expResult1));
        assertEquals(tr.size(), 2);
    }

    
}
