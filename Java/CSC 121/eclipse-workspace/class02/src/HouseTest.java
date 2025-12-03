import static org.junit.Assert.*;
import org.junit.Test;

//examples and tests of House objects and methods
public class HouseTest {

	  House ranch = new House("Ranch", 2500, "25 Maple St", 375000);
	  House colon = new House("Colonial", 2000, "18 Thelberg Rd", 250000);
	  House cape = new House("Cape", 4000, "12 Timberline Dr", 400000);
	  
	  @Test
	  public void testCostPerSqFt() {
	    assertEquals(150, this.ranch.costPerSqFt());
	    assertEquals(125, this.colon.costPerSqFt());
	    assertEquals(100, this.cape.costPerSqFt());
	  }
	  
	  @Test
	  public void testMoreExpensiveThan() {
	    assertEquals(false, this.ranch.moreExpensiveThan(400000));
	    assertEquals(true, this.ranch.moreExpensiveThan(100000));
	    assertEquals(false, this.cape.moreExpensiveThan(400000));
	    assertEquals(true, this.cape.moreExpensiveThan(399999));
	  }
	  
	  @Test
	  public void testMorePerSqFtThan() {
	    assertEquals(true, this.colon.morePerSqFtThan(cape));
	    assertEquals(false, this.colon.morePerSqFtThan(ranch));
	  }
	  
	  @Test
	  public void testComputeTax() {
	    assertEquals(7500, this.ranch.computeTax());
	    assertEquals(3750, this.colon.computeTax());
	    assertEquals(8000, this.cape.computeTax());
	    assertEquals(1000, new House("Ranch", 1300, "2 Main St", 100000).computeTax());
	  }
}