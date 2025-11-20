
public class Rectangle {
	private int width;
	private int height;

	public Rectangle() {
		this.width = 
				1

				;
		this.height = 
				1

				;
	}

	public Rectangle(int side) {
		this.width = 
				side

				;
		this.height = 
				side

				;
	}

	// Constructor 3: Constructor with two parameters (rectangle)
	public Rectangle(int width, int height) {
		this.width = 
				width

				;
		this.height = 
				height

				;
	}

	public int area() { 
		return this.width * this.height;
	}

}

