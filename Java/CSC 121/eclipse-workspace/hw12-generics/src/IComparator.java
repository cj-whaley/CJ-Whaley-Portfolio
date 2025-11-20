
public interface IComparator <X> {
	int compare(X a, X b);

}

class YoBCompare implements IComparator<Author> {
	public int compare(Author a, Author b) {
		return a.yob - b.yob;
	}
}

class NameLengthCompare implements IComparator<Author> {
	public int compare(Author a, Author b) {
		return a.name.length() - b.name.length();
	}
}

class BookPriceComparator implements IComparator<Book> {

	public int compare(Book a, Book b) {
		return a.salePrice() - b.salePrice();
	}
	
}