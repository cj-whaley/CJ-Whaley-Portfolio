import java.util.Objects;

/** Represent books */
class Book {
	String title;
	int price;
	int quantity;
	Author author;
	
	Book(String title, int price, int quantity, Author author) {
		super();
		this.title = title;
		this.price = price;
		this.quantity = quantity;
		this.author = author;
		
		this.author.addBook(this);
	}

	public boolean sameBook(Book that) {
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, price, quantity, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && price == other.price && quantity == other.quantity
				&& Objects.equals(title, other.title);
	}
	
}