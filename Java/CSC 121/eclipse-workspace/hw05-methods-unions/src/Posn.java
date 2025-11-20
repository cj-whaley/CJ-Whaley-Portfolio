/**
 * @author cjwhaley
 */

// shapes - methods

/*
 RECALL RACKET *SL:

  ;; A Shape is one of:
  ;;   -- circle, (make-circle posn number)
  ;;   -- square, (make-square posn number)

  (define-struct circle (center radius))
  (define-struct square (nw size))

  ;; shape-func : Shape ??? -> ???
  (define (shape-func a-shape ...) 
    (cond [(circle? a-shape)  ... (circle-center a-shape)
                              ... (circle-radius a-shape) ...]
          [(square? a-shape)  ... (square-nw a-shape) 
                              ... (square-size a-shape) ...]))

 */


/*

                             +----------+
                             |  IShape  |
                             +----------+
                                  |
                                 / \
                                +---+
                                  |
                      +-----------+-----------+
                      |                       |
                      |                       |
   +------------------+---------+      +------+---------------------+
   |          Square            |      |          Circle            |
   +----------------------------+      +----------------------------+
   | CartPt nw                  |      | CartPt center              |
   | double size                |      | double radius              |
   +----------------------------+      +----------------------------+


   +------------------------+
   |        :               |
   |        :100  B         |
   |        :               |
   |   200  +-------+       |
   |~~~~~~~~|       |       |
   |        |   E   |50  C  |
   |    A   |       |       |
   |        +-------+       |
   |                        |
   |            D           |
   +------------------------+

 */


/** Represents a 2D cartesian coordinate */
//  - Needed for the shapes defined below
class Posn {
	double x;
	double y;

	Posn(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/* TEMPLATE
  public ??? posnMethod(...) {
    ... this.x      -- double
    ... this.y      -- double

    ... this.inRange(Posn, double, double)  -- boolean
  }    
	 */

	// determine whether `this` point is within dx units to the right, 
	//  and dy units below `that` point
	public boolean inRange(Posn that, double dx, double dy) {
		return this.x > that.x && this.y > that.y &&
				this.x < (that.x + dx) && this.y < (that.y + dy);
	}

	/** determines the slope between two points */
	public double slope(Posn z) {
		return (this.y - z.y) / (this.x - z.x);
	}

}


/** represents a geometric shape at some location */
interface IShape {
	/** compute the area of the shape */
	public double area();

	/** is the given point within this shape? */
	public boolean contains(Posn p);
	
	/** computes the perimeter of the shape */
	public double perimeter();
}


/** represents a circle */
class Circle implements IShape {
	Posn center;
	double radius;

	Circle(Posn center, double radius) {
		this.center = center;
		this.radius = radius;
	}

	/* TEMPLATE:
  public ?? circleMethod(...) {
    ... this.center     -- CartPt
    ... this.radius     -- double

    ... this.area()     -- double
    ... this.contains(CartPt) -- boolean

    ... this.center.cartptMethod(...)  -- ???    
  }    
	 */

	/** calculate the area of this circle */
	public double area() {
		return Math.PI * this.radius * this.radius;
	}

	/** Is the given point in this circle */
	public boolean contains(Posn p) {
		return (this.sumSquares(p.x-this.center.x, p.y-this.center.y)
				< 
				(this.radius*this.radius));
		// no need to take the square root -- just compare the sum of the squares and the radius squared
	}

	/** Sum the squares of the two arguments */
	public double sumSquares(double dx, double dy) {
		return (dx*dx+dy*dy);
	}  
	
	/** computes the perimeter of the circle */
	public double perimeter() {
		return (Math.PI * this.radius * 2.0);
	}
}


/** represents a square */
class Square implements IShape {
	Posn nw;
	double size;

	Square(Posn nw, double size) {
		this.nw = nw;
		this.size = size;
	}

	/* TEMPLATE:
  public ?? squareMethod(...) {
    ... this.nw         -- CartPt
    ... this.size       -- double

    ... this.area()     -- double
    ... this.contains(CartPt) -- boolean

    ... this.nw.cartptMethod(...)  -- ???    
  }    
	 */

	/** calculate the area of this Square */
	public double area() {
		return this.size * this.size;
	}

	/** is the given point within this square */
	public boolean contains(Posn p) {   
		return p.inRange(this.nw, this.size, this.size);
	}
	
	/** computes the perimeter of the square */
	public double perimeter() {
		return (this.size * 4.0);
	}

}

/** represents a right isosceles triangle by using the sw corner Posn and side length */
class sideTriangle implements IShape {
	Posn sw;
	double side;

	sideTriangle(Posn sw, double side) {
		this.sw = sw;
		this.side = side;
	}

	/** calculate the area of this Triangle */
	public double area() {
		return (this.side * this.side) / 2.0;
	}

	/** is the given point within this triangle */
	public boolean contains(Posn p) {   
		return ((p.x >= this.sw.x) 
				&& (p.y >= this.sw.y) 
				&& ((new Posn(this.sw.x, (this.sw.y + this.side)).slope(p)) <= -1.0)); 
	}
	
	/** Sum the squares of the two arguments */
	public double sumSquares(double dx, double dy) {
		return (dx*dx+dy*dy);
	}  
	
	/** computes the perimeter of the triangle */
	public double perimeter() {
		return ((this.side * 2.0) + (Math.sqrt(sumSquares(this.side, this.side))));
	}
}

/** represents a right isosceles triangle by using the Posns of all 3 corners */
class pointTriangle implements IShape {
	Posn sw;
	Posn nw;
	Posn se;

	pointTriangle(Posn sw, Posn nw, Posn se) {
		this.sw = sw;
		this.nw = nw;
		this.se = se;
	}

	/** calculate the side length of a triangle based on its Posns */
	public double sideLength() {
		return (this.nw.y - this.sw.y);
	}

	/** calculate the area of this Triangle */
	public double area() {
		return (this.sideLength() * this.sideLength()) / 2.0;
	}

	/** is the given point within this triangle */
	public boolean contains(Posn p) {   
		return ((p.x >= this.sw.x) && (p.y >= this.sw.y) && (this.nw.slope(p) <= -1.0));
	}
	
	/** Sum the squares of the two arguments */
	public double sumSquares(double dx, double dy) {
		return (dx*dx+dy*dy);
	}  
	
	/** computes the perimeter of the triangle */
	public double perimeter() {
		return ((this.sideLength() * 2.0) + (Math.sqrt(sumSquares(this.sideLength(), this.sideLength()))));
	}
}
