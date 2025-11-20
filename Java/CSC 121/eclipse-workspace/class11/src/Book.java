// Methods for classes with self references (lists)

/*
 +---------------+
 | Book          |
 +---------------+
 | String title  |
 | Author author |--+
 | int price     |  |
 | char kind     |  |
 +---------------+  |
                    v
             +-------------+
             | Author      |
             +-------------+
             | String name |
             | int yob     |
             +-------------+
 */

import java.util.Objects;

/** to represent a book in a bookstore */
class Book{
	String title;
	Author author;
	int price;
	char kind;

	Book(String title, Author author, int price, char kind){
		this.title = title;
		this.author = author;
		this.price = price;
		this.kind = kind;    
	}

	/** was this book written by the given author? */
	boolean writtenBy(Author author){
		return this.author.samePerson(author);
	}

	/** compute the discounted sale price for this book:
	 the sale price of the book depends on the daily discounts
	 these may differ depending on the kind of book
	 suppose today we have the following discounts:
	 there is 30% discount on fiction books
	 there is 20% discount on nonfiction books
	 textbooks sell at full price
	 */
	int salePrice(){    
		if (this.kind == 'F'){
			return this.price - 3 * (this.price / 10);
		} else {if (this.kind == 'N'){
			return this.price - 2 * (this.price / 10);
		} else {
			return this.price;
		}}
	}

	/** is this book a non-fiction one? */
	public boolean isNonFiction() {
		return this.kind == 'N';
	}

	/** is this book's sale price under $20 */
	public boolean isUnder20() {
		return this.salePrice() < 20;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, kind, price, title);
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
		return Objects.equals(author, other.author) && kind == other.kind && price == other.price
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", price=" + price + ", kind=" + kind + "]";
	}
}


/** to represent a book author */
class Author{
	String name;
	int yob;

	Author(String name, int yob){
		this.name = name;
		this.yob = yob;
	}

	/** is this author the same person as that one, based on name */
	boolean samePerson(Author that){
		return this.name.equals(that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, yob);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		return Objects.equals(name, other.name) && yob == other.yob;
	}

	@Override
	public String toString() {
		return "Author [name=" + name + ", yob=" + yob + "]";
	}

}
