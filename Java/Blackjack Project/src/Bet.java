import java.awt.Color;

import processing.core.PApplet;

public class Bet {
	private Color color; // red (or black) chips
	private boolean betPlaced; // is the bet placed on the table
	private int value;


	Bet(Color color, boolean betPlaced, int value) {
		this.color = color;
		this.betPlaced = betPlaced;
		this.value = value;
	}

	/* draw this bet on the given window at the given location */
	public void draw(PApplet w) {
		w.imageMode(PApplet.CENTER);
        w.image(w.loadImage("chip.png"), 500, 650);

		w.textAlign(PApplet.RIGHT);
		w.text("Bet: $" + this.value, 600, 600);



	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isBetPlaced() {
		return betPlaced;
	}

	public void setBetPlaced(boolean betPlaced) {
		this.betPlaced = betPlaced;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}


}
