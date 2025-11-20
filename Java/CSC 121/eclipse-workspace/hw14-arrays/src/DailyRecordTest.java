
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class DailyRecordTest {

    DailyRecord dr = new DailyRecord();
    
    @Test
    void test() {
    	assertThrows(RuntimeException.class, () -> dr.getTemp(10));
    	assertThrows(RuntimeException.class, () -> dr.getTemp(9));
        
        assertEquals(-1000, dr.getHigh());
        
        dr.recordTemp(10, 68);
        assertEquals(68, dr.getTemp(10));
        assertThrows(RuntimeException.class, () -> dr.getTemp(9));
        
        assertEquals(68, dr.getHigh());

        dr.recordTemp(14, 73);
        dr.recordTemp(6, 59);
        dr.recordTemp(22, 62);

        assertEquals(73, dr.getHigh());
        
        assertEquals(59, dr.getLow());
        
        assertEquals(65, dr.getAverage());
    } 

}