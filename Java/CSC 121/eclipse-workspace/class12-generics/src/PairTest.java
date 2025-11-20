import static org.junit.Assert.*;
import org.junit.Test;

public class PairTest {
    PairSame<String> name = new PairSame<String>("Eduardo", "Liu");
    PairSame<Integer> posn = new PairSame<Integer>(31, 75);

    @Test 
    public void testPair() {
        assertEquals("Eduardo Liu", name.toString());
        assertEquals("31 75", posn.toString());

        assertEquals("Liu Eduardo", name.swap().toString());
        assertEquals("75 31", posn.swap().toString());
    }
}
