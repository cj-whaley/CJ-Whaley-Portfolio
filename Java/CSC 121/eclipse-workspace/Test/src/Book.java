
/**
   Represents a book in our bookstore
   
   There are three kinds of books: fiction, non-fiction, or
    textbook, represented by symbols 'F 'N 'T
 
 */


class Book {

	String title;
	String author;
	int price;    // in whole dollars
	char kind;
	
	// boilerplate constructor
	Book(String title, String author, int price, char kind) {
		this.title = title;
		this.author = author;
		this.price = price;
		this.kind = kind;
	}
	
	
	
}
