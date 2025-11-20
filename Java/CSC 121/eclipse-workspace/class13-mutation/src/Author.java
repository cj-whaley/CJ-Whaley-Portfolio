import java.util.Objects;

/** Represents authors of books */
class Author {
	String name;
	int yob;
	Book book; 
	
	Author(String name, int yob) {
		super();
		this.name = name;
		this.yob = yob;
		this.book = null;
	}
	
	public boolean sameAuthor(Author that) {
		return true;
	}
	
	/**
	 * changes the book of this author to the given one
	 * @param newBook
	 */
	public void addBook(Book newBook) {
		this.book = newBook;
	}

	@Override
	public int hashCode() {
		return Objects.hash(book, name, yob);
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
		return //Objects.equals(book, other.book) && 
				Objects.equals(name, other.name) && yob == other.yob;
	}
	
}
