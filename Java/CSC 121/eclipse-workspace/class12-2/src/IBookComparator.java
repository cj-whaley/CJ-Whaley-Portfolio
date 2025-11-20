
interface IBookComparator {
	int compare(Book a, Book b);
}

class BookPriceComparator implements IBookComparator {

	@Override
	public int compare(Book a, Book b) {
		return a.salePrice() - b.salePrice();
	}
	
}