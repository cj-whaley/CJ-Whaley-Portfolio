
interface IPredicate <T> {
	boolean satisfiedBy(T a);
}


class BornAfter1940 implements IPredicate<Author> {
	public boolean satisfiedBy(Author a) {
		return a.yob > 1940;
	}
}


class NonFictionPredicate implements IPredicate<Book> {
	public boolean satisfiedBy(Book r) {
		return r.isNonFiction();
	}	
}

class Under20Predicate implements IPredicate<Book> {
	public boolean satisfiedBy(Book r) {
		return r.salePrice() < 20;
	}	
}

class ByAuthorPredicate implements IPredicate<Book> {
	Author auth;

	public ByAuthorPredicate(Author auth) {
		this.auth = auth;
	}

	public boolean satisfiedBy(Book r) {
		return r.writtenBy(this.auth);
	}	
}

