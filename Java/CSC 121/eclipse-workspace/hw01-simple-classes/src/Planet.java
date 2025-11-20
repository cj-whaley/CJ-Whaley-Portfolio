/**
 * represents a planet in the solar system
 */
class Planet {

	String name;
	double distance;  // from sun, in millions of miles
	double gravity;  // relative to earth's gravity
	
	Planet(String name, double distance, double gravity) {
		this.name = name;
		this.distance = distance;
		this.gravity = gravity;
	}
	
	
}
