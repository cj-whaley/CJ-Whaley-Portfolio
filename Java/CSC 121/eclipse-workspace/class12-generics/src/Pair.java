
public class Pair <X,Y> {
	X first;
	Y second;
	
	Pair(X first, Y second) {
		this.first = first;
		this.second = second;
	}
	
	public String toString() {
		return first + " " + second;
	}
	
	public Pair <Y,X> swap(){
		return new Pair <Y,X>(this.second, this.first);
	}

}

/* toString() is special because it is defined in the Object class, and therefore can be */
/*  used on any class. However, it is usually more effective to override this default */
/*  method in order to produce a more meaningful output that we can understand. */
/*  Object is the base class in Java that all other Java classes inherit from, and the */
/*  toString() method is defined in this class. If you do not override the toString() method, */
/*  it will produce the name of the class, an @, and the hexcode representing the data in the class. */
/*  Pair inherits from the Object class*/