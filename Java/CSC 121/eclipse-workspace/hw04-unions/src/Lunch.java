
public class Lunch {
	Soup soup;
	Salad salad;
	Sandwich sandwich;
	


	Lunch(Soup soup, Salad salad, Sandwich sandwich) {
		super();
		this.soup = soup;
		this.salad = salad;
		this.sandwich = sandwich;
	}

	Lunch order1 = new Lunch(new Soup("Chicken Noodle"), new Salad("Ceasar", "Ceasar"), new Sandwich("Wheat", "Turkey", "Mayo"));

}
