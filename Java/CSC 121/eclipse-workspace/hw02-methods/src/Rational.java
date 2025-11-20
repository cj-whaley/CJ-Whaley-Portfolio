/**
 * represents a rational number with a whole number 
 *    numerator and denominator
 */
class Rational {

	int numerator;
	int denominator;
	
	Rational(int numerator, int denominator) {
		this.numerator = numerator / gcd(numerator, denominator);
		this.denominator = denominator / gcd(numerator, denominator);
	}
	

	
	// produces gcd(|m|, |n|)
		 private static int gcd(int m, int n) {
		     if (m < 0) m = -m;
		     if (n < 0) n = -n;
		     if (0 == n) return m;
		     else return gcd(n, m % n);
		 }
	
	/* TEMPLATE:
	  public ??? RationalMethod(...) {
	    ... this.numerator ...      -- int
	    ... this.denominator ...    -- int
	  }
	  */ 

	/*
	 *  represents a rational number in String form
	 */
	
//	public String toString() {
//		return "";
//	}
	
	public String toString() {
		if (this.denominator == 1) {
			return "" + this.numerator;
		}
		else if (this.denominator == 0) {
			return "Irrational Number";
		}
		else if (this.numerator == 0) {
			return "0";
		}
		else if (this.numerator == this.denominator) {
			return "1";
		}
		else {
			return this.numerator + "/" + this.denominator;
		}
	}
	 
	 /*
	  *  produces the sum of this and that
	  */
	 
	 // public Rational plus(Rational that) {
	 //   return null;
	 //  }
	 
	public Rational plus(Rational that) {
		return new Rational(((this.numerator * that.denominator) + (this.denominator * that.numerator)),
				            (this.denominator * that.denominator));
	}
	
	/*
	  *  produces the difference of this and that
	  */
	 
	 // public Rational minus(Rational that) {
	 //   return null;
	 //  }
	 
	public Rational minus(Rational that) {
		return new Rational(((this.numerator * that.denominator) - (this.denominator * that.numerator)),
				            (this.denominator * that.denominator));
	}
	
	/*
	  *  produces the product of this and that
	  */
	 
	 // public Rational times(Rational that) {
	 //   return null;
	 //  }
	 
	public Rational times(Rational that) {
		return new Rational((this.numerator * that.numerator), (this.denominator * that.denominator));
	}
	
	/*
	  *  produces the result of this divided by that
	  */
	 
	 // public Rational dividedBy(Rational that) {
	 //   return null;
	 //  }
	 
	public Rational dividedBy(Rational that) {
		return new Rational((this.numerator * that.denominator), (this.denominator * that.numerator));
	}
}
