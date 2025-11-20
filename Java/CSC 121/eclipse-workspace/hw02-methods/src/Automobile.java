/**
 *  represents an automobile for sale
 */
class Automobile {

	String model;
	int price;  // in dollars
	double mileage;  // in miles per gallon
	boolean used;    // is the automobile used or not
	int tankCapacity; // how many gallons of gas the automobile can hold
	
	Automobile(String model, int price, double mileage, boolean used, int tankCapacity) {
		this.model = model;
		this.price = price;
		this.mileage = mileage;
		this.used = used;
		this.tankCapacity = tankCapacity;
	}
	
	/* TEMPLATE:
	  public ??? AutomobileMethod(...) {
	    ... this.model ...            -- String
	    ... this.price ...            -- int
	    ... this.mileage ...          -- double
	    ... this.used ...             -- boolean
	    ... this.tankCapacity ...     -- int
	  }
	  */ 
	
	/*
	 *  represents the number of whole miles left that can be driven
	 *   based on the given percentage of fuel left
	 */
	
//	public int range(double percent) {
//		return 0;
//	}
	
	public int range(double percent) {
		if (this.used) {
			return (int) (this.mileage * this.tankCapacity * percent * 0.9);
		}
		else {
			return (int) (this.mileage * this.tankCapacity * percent);
		}
	}
}
