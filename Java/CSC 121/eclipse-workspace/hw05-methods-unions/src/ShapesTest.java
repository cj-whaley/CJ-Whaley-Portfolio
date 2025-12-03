/**
 * @author cjwhaley
 */
import static org.junit.Assert.*;
import org.junit.Test;

public class ShapesTest {
    IShape s = new Square(new Posn(200,100), 50.0);
    IShape c = new Circle(new Posn(225,125), 25.0);
    IShape t1 = new sideTriangle(new Posn(100,100), 20.0);
    IShape t2 = new sideTriangle(new Posn(50,225), 35.0);
    IShape t3 = new pointTriangle(new Posn(50,50), new Posn(50,80), new Posn(80,50));
    IShape t4 = new pointTriangle(new Posn(100,50), new Posn(100,90), new Posn(140,50));

    Posn A = new Posn(100, 130); //  -> false (contained by s or c)
    Posn B = new Posn(220, 50);  //  -> false
    Posn C = new Posn(270, 140); //  -> false
    Posn D = new Posn(230, 170); //  -> false
    Posn E = new Posn(225, 120); //  -> true



    @Test
    public void testArea() {
        assertEquals(2500.0, this.s.area(), 0.1);
        assertEquals(1963.5, this.c.area(), 0.1);
        assertEquals(200.0, this.t1.area(), 0.1);
        assertEquals(612.5, this.t2.area(), 0.1);
        assertEquals(450.0, this.t3.area(), 0.1);
        assertEquals(800.0, this.t4.area(), 0.1);
    }

    @Test
    public void testInRange() {
        assertEquals(true, D.inRange(B, 40, 200));
        assertEquals(false, D.inRange(B, 5, 200));
        assertEquals(false, D.inRange(B, 30, 100));
    }

    @Test
    public void testContains() {
        assertEquals(false, this.s.contains(this.A));
        assertEquals(false, this.s.contains(this.B));
        assertEquals(false, this.s.contains(this.C));
        assertEquals(false, this.s.contains(this.D));
        assertEquals(true, this.s.contains(this.E));

        assertEquals(false, this.c.contains(this.A));
        assertEquals(false, this.c.contains(this.B));
        assertEquals(false, this.c.contains(this.C));
        assertEquals(false, this.c.contains(this.D));
        assertEquals(true, this.c.contains(this.E));
        
        assertEquals(true, this.t1.contains(new Posn(105, 110)));
        assertEquals(false, this.t1.contains(B));
        assertEquals(true, this.t3.contains(new Posn(55,60)));
        assertEquals(false, this.t4.contains(E));
    }
    
    @Test
    public void testSlope() {
    	assertEquals(-1.0 , new Posn(100,120).slope(new Posn(110,110)), 0.1);
    	assertEquals(-0.6, this.A.slope(B), 0.1);
    	assertEquals(-0.75, this.C.slope(D), 0.1);
    }
    
    @Test 
    public void testPerimeter() {
    	assertEquals(200.0, this.s.perimeter(), 0.1);
    	assertEquals(40.0, new Square(A, 10.0).perimeter(), 0.1);
    	assertEquals(157.1, this.c.perimeter(), 0.1);
    	assertEquals(62.8, new Circle(D, 10.0).perimeter(), 0.1);
    	assertEquals(68.3, this.t1.perimeter(), 0.1);
    	assertEquals(119.5, this.t2.perimeter(), 0.1);
    	assertEquals(102.4, this.t3.perimeter(), 0.1);
    	assertEquals(136.6, this.t4.perimeter(), 0.14);
    	
    }

}